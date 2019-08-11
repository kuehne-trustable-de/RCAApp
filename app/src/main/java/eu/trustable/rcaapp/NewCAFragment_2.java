package eu.trustable.rcaapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;

import eu.trustable.rcaapp.model.NewCaViewModel;

public class NewCAFragment_2 extends DialogFragment {

    private static final String TAG = "NewCAFragment_2";

    private NewCaViewModel mViewModel;

    public static NewCAFragment_2 newInstance() {
        return new NewCAFragment_2();

         }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.new_ca_fragment_2, container, false);
        final NewCaViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewCaViewModel.class);

        ((TextView)view.findViewById(R.id.txtX500Subject)).setText(mViewModel.x500Subject.toString());
        Log.d(TAG, "setting X500 subject to " + mViewModel.x500Subject);

        ((TextView)view.findViewById(R.id.txtValidityDays)).setText("" + mViewModel.validityPeriodDays);

        view.findViewById(R.id.certCreationPanel).setVisibility(View.GONE);

        Button btnOk = (Button) (view.findViewById(R.id.btnSubmit));
        btnOk.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

//                view.findViewById(R.id.certCreationPanel).setVisibility(View.VISIBLE);

                String pw1 = ((EditText)view.findViewById(R.id.txtNewCA_QPW1)).getText().toString();
                String pw2 = ((EditText)view.findViewById(R.id.txtNewCA_QPW2)).getText().toString();
                String pw3 = ((EditText)view.findViewById(R.id.txtNewCA_QPW3)).getText().toString();
                String pw4 = ((EditText)view.findViewById(R.id.txtNewCA_QPW4)).getText().toString();

                HashMap<Integer, char[]> passwordMap = new HashMap<Integer, char[]>();
                if( pw1.trim().length() > 0){
                    passwordMap.put(0, pw1.toCharArray());
                }
                if( pw2.trim().length() > 0){
                    passwordMap.put(1, pw2.toCharArray());
                }
                if( pw3.trim().length() > 0){
                    passwordMap.put(2, pw3.toCharArray());
                }
                if( pw4.trim().length() > 0){ 
                    passwordMap.put(3, pw4.toCharArray());
                }
                mViewModel.passwordMap = passwordMap;

                new CreateCertificateTask().execute(mViewModel);
            }
        });

        Button btnCancel = (Button) (view.findViewById(R.id.btnCancel));
        btnCancel.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(NewCaViewModel.class);
        // TODO: Use the ViewModel
    }


    // Innere Klasse HoleDatenTask führt den asynchronen Task auf eigenem Arbeitsthread aus
    public class CreateCertificateTask extends AsyncTask<NewCaViewModel, Integer, String[]> {

        private final String LOG_TAG = CreateCertificateTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(NewCaViewModel ... models) {

            String[] resultArray = new String[2];

            CryptoUtil cu = new CryptoUtil();

            try {

                String signAlgo = cu.getSigningAlgoForKeyType(mViewModel.keyTypeLength);

                KeyPair kp = cu.createKeyPair(mViewModel.keyTypeLength);
                Log.d(TAG, "key pair created successfully");

                X509Certificate cert = cu.buildSelfSignedCertificate(kp, mViewModel.x500Subject, mViewModel.validityPeriodDays, mViewModel.keyTypeLength);
                Log.d(TAG, "certificate created successfully");

                RootCertificateItem rci = new RootCertificateItem(kp, cert, mViewModel.N, mViewModel.M, mViewModel.passwordMap, signAlgo);

                PersistentModel pm = PersistentModel.getInstance();
                String certId = pm.addKeyAndCertificate(rci);

                pm.persist();

                // delete the precious passwords
                for( Integer key:mViewModel.passwordMap.keySet()){
                    char[] pwClear = mViewModel.passwordMap.get(key);
                    Arrays.fill(pwClear, (char)0);
                }
                mViewModel.passwordMap.clear();

                String subject = cert.getSubjectDN().getName();
                Log.d(TAG, "Root Certificate created successfully: " + subject);

                resultArray[0] = certId;
                resultArray[1] = subject;

//                view.findViewById(R.id.certCreationPanel).setVisibility(View.GONE);


            } catch (GeneralSecurityException | OperatorCreationException | IOException e){
                Log.e(TAG, "creation of selfsigned certificate failed", e);

//                Snackbar.make(v, "Problem creating / storing root  certificate: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
 //                       .setAction("Action", null).show();
            }
            return resultArray;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            // Auf dem Bildschirm geben wir eine Statusmeldung aus, immer wenn
            // publishProgress(int...) in doInBackground(String...) aufgerufen wird
            Toast.makeText(getActivity(), values[0] + " von " + values[1] + " geladen",
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(String[] strings) {

            Log.d(TAG, "in onPostExecute for certId " + strings[0]);

            // refresh the cert tree view
            MainActivity mainActivity = (MainActivity) getActivity();
            if(mainActivity!=null){
                try{
                    mainActivity.refreshTreeViewData();
                }catch(IOException ioe) {
                    Log.e(TAG, "problem reading persistent content ", ioe);
                    Toast.makeText(mainActivity, "Problem reading persistent aftre creation of CA ...", Toast.LENGTH_LONG).show();
                }
            }

            final RelativeLayout containerView = (RelativeLayout)getActivity().findViewById(R.id.container);
            containerView.invalidate();

            dismiss();

            String certId = strings[0];
            String subject = strings[1];

            PersistentModel pm = PersistentModel.getInstance();
            RootCertificateItem rci = pm.findRootByCertId(certId);

            QRShowFragment qrFrag = QRShowFragment.newInstance(rci.getCert(), subject);

            qrFrag.show(getActivity().getSupportFragmentManager(), "tag");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
            Toast.makeText(getActivity(), " New Certificate '" + subject + "' created successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

package eu.trustable.rcaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

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

        Button btnOk = (Button) (view.findViewById(R.id.btnSubmit));
        btnOk.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String pw1 = ((EditText)view.findViewById(R.id.txtNewCA_QPW1)).getText().toString();
                String pw2 = ((EditText)view.findViewById(R.id.txtNewCA_QPW2)).getText().toString();
                String pw3 = ((EditText)view.findViewById(R.id.txtNewCA_QPW3)).getText().toString();
                String pw4 = ((EditText)view.findViewById(R.id.txtNewCA_QPW4)).getText().toString();

                CryptoUtil cu = new CryptoUtil();

                try {
                    KeyPair kp = cu.createKeyPair(mViewModel.keyTypeLength);
                    Log.d(TAG, "key pair created successfully");

                    X509Certificate cert = cu.buildSelfSignedCertificate(kp, mViewModel.x500Subject, mViewModel.validityPeriodDays, mViewModel.keyTypeLength);
                    Log.d(TAG, "certificate created successfully");

                    PersistentModel pm = PersistentModel.getInstance();
                    String certId = pm.addKeyAndCertificate(kp, cert, 2, 4);

                    pm.persist();

                    String subject = cert.getSubjectDN().getName();
                    Log.d(TAG, "Root Certificate created successfully: " + subject);

                    dismiss();

                    QRShowFragment qrFrag = QRShowFragment.newInstance(cert.getEncoded(), subject);

                    qrFrag.show(getActivity().getSupportFragmentManager(), "tag");

                } catch (GeneralSecurityException | OperatorCreationException | IOException e){
                    Log.e(TAG, "creation of selfsigned certificate failed", e);
                    Snackbar.make(v, "Problem creating / storing root  certificate: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
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

}

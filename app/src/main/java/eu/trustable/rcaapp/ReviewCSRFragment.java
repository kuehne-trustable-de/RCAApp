package eu.trustable.rcaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;

import eu.trustable.rcaapp.model.NewCsrViewModel;

public class ReviewCSRFragment extends DialogFragment {

    private static final String TAG = "ReviewCSRFragment";

    private NewCsrViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.review_csr_fragment, container, false);
        final NewCsrViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewCsrViewModel.class);

        GridLayout gridCSRAttributes = (GridLayout)view.findViewById(R.id.layoutReviewCSRFragment);

        CryptoUtil cu = new CryptoUtil();

        try {
            PKCS10CertificationRequest p10Req = cu.convertPemToPKCS10CertificationRequest(mViewModel.csrPEM);
            TextView csrSubjectText = (TextView)view.findViewById(R.id.txtCsrSubject);
            String subject = p10Req.getSubject().toString();
            if( subject.length() > 50){
                subject= subject.substring(0, 50) + " ...";
            }
            csrSubjectText.setText(subject);

            Map<String, String> reqAttributes = cu.explainCertificateRequestAttributes(mViewModel.csrPEM);
            Log.d(TAG, "reqAttributes has #" + reqAttributes.size() + " elements");

            gridCSRAttributes.setRowCount(reqAttributes.size() + 1);

            int row = 1;
            for (String name:reqAttributes.keySet()) {

                TextView attNameText = new TextView(getContext());
                attNameText.setText(name);

                TextView attValueText = new TextView(getContext());
                String attrValue = reqAttributes.get(name);
                if( attrValue.length() > 50){
                    attrValue = attrValue.substring(0, 50) + " ...";
                }
                attValueText.setText(attrValue);

//                titleText.setCompoundDrawablesWithIntrinsicBounds(rightIc, 0, 0, 0);

                GridLayout.LayoutParams layoutParamsName = new GridLayout.LayoutParams();
                layoutParamsName.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParamsName.width = GridLayout.LayoutParams.WRAP_CONTENT;

                // The last parameter in the specs is the weight, which gives equal size to the cells
                layoutParamsName.columnSpec = GridLayout.spec(0, 1, 1);
                layoutParamsName.rowSpec = GridLayout.spec(row, 1, 1);

                // Optional, if you want the text to be centered within the cell
                layoutParamsName.setGravity(Gravity.LEFT);

                gridCSRAttributes.addView(attNameText, layoutParamsName);

                GridLayout.LayoutParams layoutParamsValue = new GridLayout.LayoutParams();
                layoutParamsValue.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParamsValue.width = GridLayout.LayoutParams.WRAP_CONTENT;

                // The last parameter in the specs is the weight, which gives equal size to the cells
                layoutParamsValue.columnSpec = GridLayout.spec(1, 1, 1);
                layoutParamsValue.rowSpec = GridLayout.spec(row, 1, 1);

                // Optional, if you want the text to be centered within the cell
                layoutParamsValue.setGravity(Gravity.LEFT);
                gridCSRAttributes.addView(attValueText, layoutParamsValue);

                Log.d(TAG, "adding row #" + row + " for '" + name + "' : '" + reqAttributes.get(name) + "'");

                row++;
            }


            Spinner committerSpinner = ((Spinner)view.findViewById(R.id.spinnerCommitterName));

            selectSpinnerItemByValue(committerSpinner, "2");
            /*
            TextView textCommitterView = (TextView)committerSpinner.getSelectedView();
            String quorumString = textCommitterView.getText().toString();
*/

            int nRequired = mViewModel.issuingCert.getN();
            int stillNeeded = nRequired - mViewModel.passwordMap.size();

            TextView committerHintText = (TextView)view.findViewById(R.id.txtReview_CommiterHint);
            String hintText = stillNeeded + " additional committers required";
            if( stillNeeded == 1){
                hintText = "final committer";
            }
            committerHintText.setText(hintText);

        } catch (IOException |OperatorCreationException | GeneralSecurityException |PKCSException e) {
            e.printStackTrace();
        }


        Button btnOk = (Button) (view.findViewById(R.id.btnSubmit));
        btnOk.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Spinner committerSpinner = ((Spinner)view.findViewById(R.id.spinnerCommitterName));
                TextView textCommitterView = (TextView)committerSpinner.getSelectedView();
                int nCommitter = Integer.parseInt( textCommitterView.getText().toString()) - 1; // temp tweak !!

                String pw = ((EditText)view.findViewById(R.id.txtReview_QPW1)).getText().toString();
                if( pw.trim().length() > 0){

                    Log.d(TAG, "adding to password map '" + nCommitter + "' / '" +pw+ "'");
                    mViewModel.passwordMap.put(nCommitter, pw.toCharArray());
                }

                int nRequired = mViewModel.issuingCert.getN();
                Log.d(TAG, "nRequired : "+nRequired+", password map length : " + mViewModel.passwordMap.size());
                if( mViewModel.passwordMap.size() < nRequired) {

                    dismiss();

                    ReviewCSRFragment reviewDialog = new ReviewCSRFragment();
                    reviewDialog.show(getActivity().getSupportFragmentManager(), "tag");

                    Snackbar.make(v, "#" + (nRequired - mViewModel.passwordMap.size()) +" additional password required ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else{

                    Log.d(TAG, "quorum of #" + nRequired + " reached!");
                    CryptoUtil cu = new CryptoUtil();

                    PersistentModel pm = PersistentModel.getInstance();
                    RootCertificateItem rci = pm.findByCertId(mViewModel.issuingCertId);

                    try {

                        X509Certificate cert = cu.signCertificateRequest(rci, mViewModel.passwordMap, mViewModel.csrPEM);

                        // delete the precious passwords
                        for( Integer key:mViewModel.passwordMap.keySet()){
                            char[] pwClear = mViewModel.passwordMap.get(key);
                            Arrays.fill(pwClear, (char)0);
                        }
                        mViewModel.passwordMap.clear();

                        rci.addIssuedCertList(cert);

                        pm.persist();

                        String subject = cert.getSubjectDN().getName();
                        Log.d(TAG, "Certificate signed successfully: " + subject);

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


                        dismiss();

                        QRShowFragment qrFrag = QRShowFragment.newInstance(cert.getEncoded(), subject);
                        qrFrag.show(getActivity().getSupportFragmentManager(), "reviewCSRFragmentTag");

                    } catch (IOException | OperatorCreationException | GeneralSecurityException | PKCSException e) {
                        Log.e(TAG, "Problem signing CSR: ", e);
                        Snackbar.make(v, "Problem signing CSR: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
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

//        mViewModel = ViewModelProviders.of(getActivity()).get(NewCsrViewModel.class);
        // TODO: Use the ViewModel

    }

    public static void selectSpinnerItemByValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}

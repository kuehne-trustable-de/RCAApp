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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import eu.trustable.rcaapp.model.NewCrlViewModel;

public class ReviewCRLFragment extends DialogFragment {

    private static final String TAG = "ReviewCRLFragment";

    private NewCrlViewModel mViewModel;
    private String certIdParam;
    private boolean initPasswordMap = true;

    public static ReviewCRLFragment newInstance(String certId) {
        ReviewCRLFragment fragment = new ReviewCRLFragment();

        fragment.certIdParam = certId;

        return fragment;
    }

    public static ReviewCRLFragment followUpInstance(String certId) {
        ReviewCRLFragment fragment = new ReviewCRLFragment();

        fragment.certIdParam = certId;
        fragment.initPasswordMap = false;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.review_crl_fragment, container, false);
        final NewCrlViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewCrlViewModel.class);
        mViewModel.issuingCertId = certIdParam;
        if( initPasswordMap ) {
            mViewModel.passwordMap = new HashMap<Integer, char[]>();
        }

        RelativeLayout layoutReviewRevokedCerts = (RelativeLayout)view.findViewById(R.id.LayoutReviewRevokedCerts);
        GridLayout gridCRLItems = (GridLayout)view.findViewById(R.id.LayoutReviewCRLItems);

        CryptoUtil cu = new CryptoUtil();

        try {
            TextView crlIssuerText = (TextView) view.findViewById(R.id.txtCrlIssuer);

            PersistentModel pm = PersistentModel.getInstance();
            mViewModel.issuingCert = pm.findRootByCertId(certIdParam);

            Log.d(TAG, "issuing CRL for CertId '" + certIdParam + "'");

            String issuer = mViewModel.issuingCert.getSubject().toString();
            Log.d(TAG, "issuing CRL for Cert '" + issuer + "'");

            if (issuer.length() > 50) {
                issuer = issuer.substring(0, 50) + " ...";
            }
            crlIssuerText.setText(issuer);

            int revokedCerts = 0;
            int pendingCerts = 0;
            mViewModel.revokedCertificateList = new ArrayList<IssuedCertificateItem>();
            for( IssuedCertificateItem ici: mViewModel.issuingCert.getIssuedCertList()){
                if( ici.getRevocationReason() != IssuedCertificateItem.CRL_REASON_NOT_REVOKED){
                    mViewModel.revokedCertificateList.add(ici);
                    revokedCerts++;
                    if( ici.isRevocationPending()){
                        pendingCerts++;
                    }
                }
            }

            Log.d(TAG, "issuing CRL for Cert '" + issuer + "' with " + revokedCerts + " revoked certs, "+ pendingCerts + " pending revocation.");

            Log.d(TAG, "CRL will have #" + mViewModel.revokedCertificateList.size() + " elements");

            if(revokedCerts == 0){
                layoutReviewRevokedCerts.setVisibility(View.GONE);
            }else{
                layoutReviewRevokedCerts.setVisibility(View.VISIBLE);
            }

            gridCRLItems.setRowCount(mViewModel.revokedCertificateList.size());


            int row = 0;
            for (IssuedCertificateItem iciRevoked:mViewModel.revokedCertificateList) {

                TextView subjectText = new TextView(getContext());

                String subject = iciRevoked.getSubject();
                if( subject.length() > 50){
                    subject = subject.substring(0, 50) + " ...";
                }

                subjectText.setText(subject);

                TextView reasonText = new TextView(getContext());
                String reasonValue = CryptoUtil.crlReasonAsString(iciRevoked.getRevocationReason());
                if( reasonValue.length() > 50){
                    reasonValue = reasonValue.substring(0, 50) + " ...";
                }
                reasonText.setText(reasonValue);

                TextView revDateText = new TextView(getContext());
                String revDateValue = android.text.format.DateFormat.getDateFormat(getActivity()).format(iciRevoked.getRevocationDate());
                revDateText.setText(revDateValue);


//                titleText.setCompoundDrawablesWithIntrinsicBounds(rightIc, 0, 0, 0);

                GridLayout.LayoutParams layoutParamsName = new GridLayout.LayoutParams();
                layoutParamsName.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParamsName.width = GridLayout.LayoutParams.WRAP_CONTENT;

                // The last parameter in the specs is the weight, which gives equal size to the cells
                layoutParamsName.columnSpec = GridLayout.spec(0, 1, 1);
                layoutParamsName.rowSpec = GridLayout.spec(row, 1, 1);

                // Optional, if you want the text to be centered within the cell
                layoutParamsName.setGravity(Gravity.LEFT);

                gridCRLItems.addView(subjectText, layoutParamsName);

                GridLayout.LayoutParams layoutParamsValue = new GridLayout.LayoutParams();
                layoutParamsValue.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParamsValue.width = GridLayout.LayoutParams.WRAP_CONTENT;

                // The last parameter in the specs is the weight, which gives equal size to the cells
                layoutParamsValue.columnSpec = GridLayout.spec(1, 1, 1);
                layoutParamsValue.rowSpec = GridLayout.spec(row, 1, 1);

                // Optional, if you want the text to be centered within the cell
                layoutParamsValue.setGravity(Gravity.LEFT);

                gridCRLItems.addView(reasonText, layoutParamsValue);

                GridLayout.LayoutParams layoutParamsDate = new GridLayout.LayoutParams();
                layoutParamsDate.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParamsDate.width = GridLayout.LayoutParams.WRAP_CONTENT;

                // The last parameter in the specs is the weight, which gives equal size to the cells
                layoutParamsDate.columnSpec = GridLayout.spec(2, 1, 1);
                layoutParamsDate.rowSpec = GridLayout.spec(row, 1, 1);

                // Optional, if you want the text to be centered within the cell
                layoutParamsDate.setGravity(Gravity.LEFT);
                gridCRLItems.addView(revDateText, layoutParamsDate);

                Log.d(TAG, "adding row #" + row + " for '" + subject + "' : '" + reasonText + "' on " + revDateValue);

                row++;
            }

            Spinner committerSpinner = ((Spinner) view.findViewById(R.id.spinnerCommitterName));

            selectSpinnerItemByValue(committerSpinner, "2");

            int nRequired = mViewModel.issuingCert.getN();
            int stillNeeded = nRequired - mViewModel.passwordMap.size();

            Log.d(TAG, "nRequired : "+nRequired+", stillNeeded : " + stillNeeded);

            TextView committerHintText = (TextView) view.findViewById(R.id.txtReview_CommiterHint);
            String hintText = stillNeeded + " additional committers required";
            if (stillNeeded == 1) {
                hintText = "final committer";
            }
            committerHintText.setText(hintText);

//        } catch (IOException |OperatorCreationException | GeneralSecurityException |PKCSException e) {
        } catch(Exception e ){
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

                    ReviewCRLFragment crlReviewFrag = ReviewCRLFragment.followUpInstance(certIdParam);
                    crlReviewFrag.show(getActivity().getSupportFragmentManager(), "tag");

                    Snackbar.make(v, "#" + (nRequired - mViewModel.passwordMap.size()) +" additional password required ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else{

                    Log.d(TAG, "quorum of #" + nRequired + " reached!");
                    CryptoUtil cu = new CryptoUtil();

                    PersistentModel pm = PersistentModel.getInstance();
                    RootCertificateItem rci = pm.findRootByCertId(mViewModel.issuingCertId);


                    try {

                        X509CRL crl = cu.signCRL(rci, mViewModel.passwordMap, mViewModel.revokedCertificateList, mViewModel.crlValiditySeconds);

                        // delete the precious passwords
                        for( Integer key:mViewModel.passwordMap.keySet()){
                            char[] pwClear = mViewModel.passwordMap.get(key);
                            Arrays.fill(pwClear, (char)0);
                        }
                        mViewModel.passwordMap.clear();

                        rci.setNextCRLUpdate( crl.getNextUpdate());

                        pm.persist();

                        Log.d(TAG, "CRL signed successfully, next update: " + crl.getNextUpdate());

                        // refresh the cert tree view
                        MainActivity mainActivity = (MainActivity) getActivity();
                        if(mainActivity!=null){
                            try{
                                mainActivity.refreshTreeViewData();
                            }catch(IOException ioe) {
                                Log.e(TAG, "problem reading persistent content ", ioe);
                                Toast.makeText(mainActivity, "Problem reading persistent after creation of new CRL ...", Toast.LENGTH_LONG).show();
                            }
                        }


                        dismiss();

                        QRShowFragment qrFrag = QRShowFragment.newInstance(crl, "CRL for " + rci.getSubject());
                        qrFrag.show(getActivity().getSupportFragmentManager(), "reviewCRLFragmentTag");

                    } catch (IOException | OperatorCreationException | GeneralSecurityException e) {
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

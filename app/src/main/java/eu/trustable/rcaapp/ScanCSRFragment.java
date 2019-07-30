package eu.trustable.rcaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import eu.trustable.rcaapp.model.NewCsrViewModel;

public class ScanCSRFragment extends DialogFragment {

    private static final String TAG = "ScanCSRFragment";

    private NewCsrViewModel mViewModel;
    private String certIdParam;


    public static ScanCSRFragment newInstance(String certId) {
        ScanCSRFragment fragment = new ScanCSRFragment();

        fragment.certIdParam = certId;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.scan_csr_fragment, container, false);
        final NewCsrViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewCsrViewModel.class);

        startScan();

        EditText csrPEMText = (EditText)view.findViewById(R.id.txtCsrPEM);
        csrPEMText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                TextView csrSubjectDescrText = (TextView)view.findViewById(R.id.txtCsrSubjectDescr);
                csrSubjectDescrText.setVisibility(View.GONE);
                TextView csrSubjectText = (TextView)view.findViewById(R.id.txtCsrSubject);
                csrSubjectText.setVisibility(View.GONE);
                csrSubjectText.setText("");

                if( s != null && s.length() > 0) {
                    CryptoUtil cu = new CryptoUtil();
                    try {
                        PKCS10CertificationRequest p10Req = cu.convertPemToPKCS10CertificationRequest(s.toString());
                        csrSubjectText.setVisibility(View.VISIBLE);
                        csrSubjectText.setText(p10Req.getSubject().toString());
                        csrSubjectText.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        Toast.makeText(getContext(), "CSR: Invalid format or content: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        Button btnOk = (Button) (view.findViewById(R.id.btnSubmit));
        btnOk.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String csrPEM = ((EditText)view.findViewById(R.id.txtCsrPEM)).getText().toString();


                if( csrPEM.trim().length() == 0) {
                    Snackbar.make(v, "CSR must be defined", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    mViewModel.csrPEM = csrPEM;

                    CryptoUtil cu = new CryptoUtil();

                    PersistentModel pm = PersistentModel.getInstance();
                    RootCertificateItem rci = pm.findByCertId(certIdParam);

                    try {
                        X509Certificate issuingCertificate = cu.getCertificateFromBytes(rci.getCert());
                        PrivateKey privKey = cu.convertPemToPrivateKey(rci.privKeyPEM);
                        X509Certificate cert = cu.signCertificateRequest(issuingCertificate, privKey, mViewModel.csrPEM);

                        rci.addIssuedCertList(cert);

                        pm.persist();

                        String subject = cert.getSubjectDN().getName();
                        Log.d(TAG, "Certificate signed successfully: " + subject);

                        dismiss();

                        QRShowFragment qrFrag = QRShowFragment.newInstance(cert.getEncoded(), subject);
                        qrFrag.show(getActivity().getSupportFragmentManager(), "scanCSRFragmentTag");

                    } catch (IOException | OperatorCreationException | GeneralSecurityException e) {
                        Snackbar.make(v, "Problem singning CSR: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


//                    NewCAFragment_2 newCADialog_2 = new NewCAFragment_2();
//                    newCADialog_2.show(getActivity().getSupportFragmentManager(), "tag");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult in Fragment: " + requestCode + " / " + resultCode);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                EditText csrPEMText = (EditText)this.getView().findViewById(R.id.txtCsrPEM);
                csrPEMText.setText( result.getContents());
//                csrPEMText.setText( new String(result.getRawBytes()));
            }
        }
    }

    private void startScan(){
        IntentIntegrator integrator = new IntentIntegrator(getActivity());

        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a CSR");
//        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);

        integrator.initiateScan();

    }
}

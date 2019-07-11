package eu.trustable.rcaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

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

        new IntentIntegrator(getActivity()).initiateScan(); // `this` is the current Activity
//        IntentIntegrator.forFragment(this).initiateScan(); // `this` is the current Fragment

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
                        X509Certificate issuingCertificate = cu.getCertificateFromBytes(rci.cert);
                        PrivateKey privKey = cu.convertPemToPrivateKey(rci.privKeyPEM);
                        X509Certificate cert = cu.signCertificateRequest(issuingCertificate, privKey, mViewModel.csrPEM);

                        rci.addIssuedCertList(cert);

                        pm.persist();

                        String subject = cert.getSubjectDN().getName();
                        Log.d(TAG, "Certificate signed successfully: " + subject);

                        dismiss();

                        QRShowFragment qrFrag = QRShowFragment.newInstance(cert.getEncoded(), subject);
                        qrFrag.show(getActivity().getSupportFragmentManager(), "tag");

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

        mViewModel = ViewModelProviders.of(getActivity()).get(NewCsrViewModel.class);
        // TODO: Use the ViewModel
    }

}

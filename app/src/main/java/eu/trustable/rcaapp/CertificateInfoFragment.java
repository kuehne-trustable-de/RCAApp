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
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import org.bouncycastle.operator.OperatorCreationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CertificateInfoFragment extends DialogFragment {

    private static final String TAG = "CertificateInfoFragment";

    // the fragment initialization parameter
    private static final String ARG_CertId = "certId";

    private String certIdParam;

    public static CertificateInfoFragment newInstance(String certId) {
        CertificateInfoFragment fragment = new CertificateInfoFragment();

        Bundle args = new Bundle();
        args.putString(ARG_CertId, certId);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        certIdParam = getArguments().getString(ARG_CertId);
        PersistentModel pm = PersistentModel.getInstance();
        RootCertificateItem rci = pm.findByCertId(certIdParam);

        final View view = inflater.inflate(R.layout.certificate_info_fragment, container, false);

        ((TextView)view.findViewById(R.id.txtCertInfoX500Subject)).setText(rci.subject);
        Log.d(TAG, "setting X500 subject to " + rci.subject);

        try {
            CertificateFactory  factory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(rci.cert));

            ((TextView)view.findViewById(R.id.txtCertInfoSerial)).setText(cert.getSerialNumber().toString());

            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

            ((TextView)view.findViewById(R.id.txtCertInfoValidFrom)).setText(df.format(cert.getNotBefore()));
            ((TextView)view.findViewById(R.id.txtCertInfoValidTo)).setText(df.format(cert.getNotAfter()));

        } catch (CertificateException e) {
            Log.e(TAG, "problem parsing certificate #" + certIdParam, e);
        }


        Button btnQR = (Button) (view.findViewById(R.id.btnShowQR));
        btnQR.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (getArguments() != null) {

                    dismiss();

                    PersistentModel pm = PersistentModel.getInstance();
                    RootCertificateItem rci = pm.findByCertId(certIdParam);
                    QRShowFragment qrFrag = QRShowFragment.newInstance(rci.cert, rci.subject);
                    qrFrag.show(getActivity().getSupportFragmentManager(), "tag");
                }
            }
        });

        Button btnOk = (Button) (view.findViewById(R.id.btnScanCSR));
        btnOk.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (getArguments() != null) {

                    dismiss();

                    ScanCSRFragment scanFrag = ScanCSRFragment.newInstance(certIdParam);
                    scanFrag.show(getActivity().getSupportFragmentManager(), "tag");
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

}

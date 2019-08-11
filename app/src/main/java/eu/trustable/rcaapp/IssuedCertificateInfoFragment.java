package eu.trustable.rcaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

import org.bouncycastle.operator.OperatorCreationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IssuedCertificateInfoFragment extends DialogFragment {

    private static final String TAG = "IssuedCertificateInfoFragment";

    // the fragment initialization parameter
    private static final String ARG_CertId = "certId";

    private String certIdParam;

    public static IssuedCertificateInfoFragment newInstance(String certId) {
        IssuedCertificateInfoFragment fragment = new IssuedCertificateInfoFragment();

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

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        certIdParam = getArguments().getString(ARG_CertId);
        PersistentModel pm = PersistentModel.getInstance();

        Log.d(TAG, "building view for cert id " + certIdParam);

        IssuedCertificateItem ici = pm.findIssuedByCertId(certIdParam);

        final View view = inflater.inflate(R.layout.issued_certificate_info_fragment, container, false);


        if( ici.getRevocationReason() == IssuedCertificateItem.CRL_REASON_NOT_REVOKED ){
            ((TextView)view.findViewById(R.id.txtCertInfoRevocationReasonDescr)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.txtCertInfoRevocationDateDescr)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.txtCertInfoRevocationDate)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.txtCertInfoRevocationReason)).setVisibility(View.GONE);

        }else{
            ((TextView)view.findViewById(R.id.txtCertInfoRevocationReasonDescr)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.txtCertInfoRevocationDateDescr)).setVisibility(View.VISIBLE);
            TextView revReason = (TextView)view.findViewById(R.id.txtCertInfoRevocationReason);
            revReason.setText(CryptoUtil.crlReasonAsString(ici.getRevocationReason()));
            TextView revDate = (TextView)view.findViewById(R.id.txtCertInfoRevocationDate);
            revDate.setText(df.format(ici.getRevocationDate()));

        }

        ((TextView)view.findViewById(R.id.txtCertInfoX500Subject)).setText(ici.getSubject());
        Log.d(TAG, "setting X500 subject to " + ici.getSubject());

        try {
            CertificateFactory  factory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(ici.getCert()));

            ((TextView)view.findViewById(R.id.txtCertInfoX500Issuer)).setText(cert.getIssuerDN().getName());

            ((TextView)view.findViewById(R.id.txtCertInfoSerial)).setText(cert.getSerialNumber().toString());

            ((TextView)view.findViewById(R.id.txtCertInfoValidFrom)).setText(df.format(cert.getNotBefore()));
            ((TextView)view.findViewById(R.id.txtCertInfoValidTo)).setText(df.format(cert.getNotAfter()));


        } catch (CertificateException e) {
            Log.e(TAG, "problem parsing certificate #" + certIdParam, e);
        }




        Button btnRevoke = (Button) (view.findViewById(R.id.btnRevoke));
        btnRevoke.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (getArguments() != null) {

                    Spinner spinnerRevocationReason = ((Spinner)view.findViewById(R.id.spinnerRevocationReason));
                    TextView textRevocationReasonView = (TextView)spinnerRevocationReason.getSelectedView();

                    int revReason = CryptoUtil.crlReasonFromString(textRevocationReasonView.getText().toString());

                    PersistentModel pm = PersistentModel.getInstance();
                    IssuedCertificateItem ici = pm.findIssuedByCertId(certIdParam);
                    ici.setRevocationReason(revReason);
                    ici.setRevocationDate(new Date());
                    ici.setRevocationPending(true);

                    try {
                        pm.persist();

                        dismiss();
                    } catch (IOException e){
                        Log.e(TAG, "revocation of certificate failed", e);

                        Snackbar.make(v, "Problem revoking certificate: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show();
                    }

                }
            }
        });

        Button btnQR = (Button) (view.findViewById(R.id.btnShowQR));
        btnQR.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (getArguments() != null) {

                    dismiss();

                    PersistentModel pm = PersistentModel.getInstance();
                    IssuedCertificateItem ici = pm.findIssuedByCertId(certIdParam);
                    QRShowFragment qrFrag = QRShowFragment.newInstance(ici.getCert(), ici.getSubject());
                    qrFrag.show(getActivity().getSupportFragmentManager(), "tag");
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

package eu.trustable.rcaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class RootCertificateInfoFragment extends DialogFragment {

    private static final String TAG = "RootCertificateInfoFragment";

    // the fragment initialization parameter
    private static final String ARG_CertId = "certId";

    private String certIdParam;

    public static RootCertificateInfoFragment newInstance(String certId) {
        RootCertificateInfoFragment fragment = new RootCertificateInfoFragment();

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
        RootCertificateItem rci = pm.findRootByCertId(certIdParam);

        final View view = inflater.inflate(R.layout.root_certificate_info_fragment, container, false);

        ((TextView)view.findViewById(R.id.txtCertInfoX500Subject)).setText(rci.getSubject());
        Log.d(TAG, "setting X500 subject to " + rci.getSubject());

        try {
            CertificateFactory  factory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(rci.getCert()));

            ((TextView)view.findViewById(R.id.txtCertInfoSerial)).setText(cert.getSerialNumber().toString());

            ((TextView)view.findViewById(R.id.txtCertInfoValidFrom)).setText(android.text.format.DateFormat.getDateFormat(getActivity()).format(cert.getNotBefore()));
            ((TextView)view.findViewById(R.id.txtCertInfoValidTo)).setText(android.text.format.DateFormat.getDateFormat(getActivity()).format(cert.getNotAfter()));

            Date nextUpdate = rci.getNextCRLUpdate();
            if( nextUpdate ==  null){
                nextUpdate = new Date();
            }
            ((TextView)view.findViewById(R.id.txtCertInfoCRLExpiry)).setText(android.text.format.DateFormat.getDateFormat(getActivity()).format(nextUpdate));

            ((TextView)view.findViewById(R.id.txtCertInfoNumberOfIssuedCertificates)).setText("" + rci.getIssuedCertList().size());

        } catch (CertificateException e) {
            Log.e(TAG, "problem parsing certificate #" + certIdParam, e);
        }



        Button btnIssueCRL = (Button) (view.findViewById(R.id.btnIssueCRL));
        btnIssueCRL.setOnClickListener( new Button.OnClickListener()
        {
          @Override
          public void onClick(View v) {
              if (getArguments() != null) {

                  dismiss();

                  Log.d(TAG, "building CRL for cert ID " + certIdParam);

                  ReviewCRLFragment crlReviewFrag = ReviewCRLFragment.newInstance(certIdParam);
                  crlReviewFrag .show(getActivity().getSupportFragmentManager(), "tag");
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
                    RootCertificateItem rci = pm.findRootByCertId(certIdParam);
                    QRShowFragment qrFrag = QRShowFragment.newInstance(rci.getCert(), rci.getSubject());
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

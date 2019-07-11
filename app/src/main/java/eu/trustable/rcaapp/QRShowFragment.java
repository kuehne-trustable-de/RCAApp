package eu.trustable.rcaapp;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringWriter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QRShowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QRShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QRShowFragment extends DialogFragment {

    private static final String TAG = "QRShowFragment";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CertId = "certSubject";
    private static final String ARG_CertPem = "certPem";

    private String certPem;
    private String certSubject;

    private OnFragmentInteractionListener mListener;

    public QRShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param certPem Parameter 1.
     * @return A new instance of fragment QRShowFragment.
     */
    public static QRShowFragment newInstance(String certPem ) {
        QRShowFragment fragment = new QRShowFragment();

        Bundle args = new Bundle();
        args.putString(ARG_CertPem, certPem);
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param certBytes Parameter 1.
     * @param certSubject Parameter 2.
     * @return A new instance of fragment QRShowFragment.
     */
    public static QRShowFragment newInstance(byte[] certBytes, String certSubject ) {
        QRShowFragment fragment = new QRShowFragment();

        Bundle args = new Bundle();

        CryptoUtil cu = new CryptoUtil();
        args.putString(ARG_CertPem, cu.certToPem(certBytes));

        args.putString(ARG_CertId, certSubject);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            certSubject = getArguments().getString(ARG_CertId);
        }

        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_qrshow, container, false);

        Button btnCancel = (Button) (view.findViewById(R.id.buttonQRCancel));

        if (getArguments() != null) {

            if(getArguments().containsKey(ARG_CertId)) {
                certSubject = getArguments().getString(ARG_CertId);
            }else{
                certSubject = "---";
            }
            certPem = getArguments().getString(ARG_CertPem);
//            PersistentModel pm = PersistentModel.getInstance();
//            RootCertificateItem rci = pm.findByCertId(certSubject);

            TextView txtQR = (TextView) view.findViewById(R.id.txtQRCertificateInfo);
            txtQR.setText(certSubject);

            ImageView imgQR = (ImageView) view.findViewById(R.id.imageQRCertificate);

            try {
                Log.d(TAG, "writing certificate '"+certSubject+"' as PEM:\n" + certPem);

//                View decorView = getDialog().getWindow().getDecorView();

                Point size = new Point();
                getActivity().getWindowManager().getDefaultDisplay().getSize(size);

                int qrWidth = size.x;
                int qrHeight = ((size.y - 20) * 7 ) / 8 ;

                Log.d(TAG, "decorView size = "+size.x+" / " + size.y + " -> " + qrHeight);

                Bitmap bitmap = encodeasBitmap(certPem, qrWidth, qrHeight);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(qrWidth, qrHeight);
                imgQR.setLayoutParams(layoutParams);
                imgQR.setImageBitmap(bitmap);
                imgQR.requestLayout();
            } catch (WriterException e) {
                Log.e(TAG, "problem encoding QR code", e);
            } //end of catch block

        }

        btnCancel.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public Bitmap encodeasBitmap(String str, int width, int height) throws WriterException {

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, width, height);
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bmp;

        } catch (IllegalArgumentException e) {
            //unsupported format
            return null;
        }
    }
}

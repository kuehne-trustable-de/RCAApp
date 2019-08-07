package eu.trustable.rcaapp;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.bouncycastle.asn1.x500.X500Name;

import eu.trustable.rcaapp.model.NewCaViewModel;

public class NewCAFragment_1 extends DialogFragment {

    private static final String TAG = "NewCAFragment_1";

    private NewCaViewModel mViewModel;

    public static NewCAFragment_1 newInstance() {
        return new NewCAFragment_1();

         }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.new_ca_fragment_1, container, false);
        final NewCaViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewCaViewModel.class);

        Button btnOk = (Button) (view.findViewById(R.id.btnSubmit));
        btnOk.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String subject = ((EditText)view.findViewById(R.id.txtNewCASubject)).getText().toString();

                Spinner keyAlgoSpinner = ((Spinner)view.findViewById(R.id.spinnerKeyAlgo));
                TextView textKeyTypeView = (TextView)keyAlgoSpinner.getSelectedView();
                mViewModel.keyTypeLength = textKeyTypeView.getText().toString();

                Spinner validitySpinner = ((Spinner)view.findViewById(R.id.spinnerValidity));
                TextView textValidityView = (TextView)validitySpinner.getSelectedView();
                String valString = textValidityView.getText().toString();

                String[] mValidityArray = getResources().getStringArray(R.array.key_validity_array);
                if( mValidityArray[1].equals(valString) ){
                    mViewModel.validityPeriodDays = 2 * 365;
                }else if( mValidityArray[2].equals(valString) ){
                    mViewModel.validityPeriodDays = 4 * 365;
                }else{
                    mViewModel.validityPeriodDays = 365;
                }
                Log.d(TAG, "validity period days : " + mViewModel.validityPeriodDays);

                Spinner quorumSpinner = ((Spinner)view.findViewById(R.id.spinnerQuorum));
                TextView textQuorumView = (TextView)quorumSpinner.getSelectedView();
                String quorumString = textQuorumView.getText().toString();

                String[] quorumArray = getResources().getStringArray(R.array.quorum_array);
                if( quorumArray[1].equals(quorumString) ){
                    mViewModel.N = 2;
                    mViewModel.M = 4;
                }else if( quorumArray[2].equals(quorumString)){
                    mViewModel.N = 3;
                    mViewModel.M = 4;
                }else if( quorumArray[3].equals(quorumString) ){
                    mViewModel.N = 4;
                    mViewModel.M = 4;
                }else{
                    mViewModel.N = 1;
                    mViewModel.M = 4;
                }
                Log.d(TAG, "selected quorum : " + mViewModel.N + " out of " + mViewModel.M);


                if( subject.trim().length() == 0) {
                    Snackbar.make(v, "Subject must be defined", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    try {
                        X500Name dn = new X500Name(subject);
                        String x500Subject = dn.toString();
                        Log.d(TAG, "reparsed subject : " + x500Subject);

                        mViewModel.x500Subject = dn;

                        dismiss();

                        NewCAFragment_2 newCADialog_2 = new NewCAFragment_2();
                        newCADialog_2.show(getActivity().getSupportFragmentManager(), "tag");
                    }catch( IllegalArgumentException ie){
                        Snackbar.make(v, "Subject must be valid directory string", Snackbar.LENGTH_LONG)
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

        mViewModel = ViewModelProviders.of(getActivity()).get(NewCaViewModel.class);
        // TODO: Use the ViewModel
    }

}

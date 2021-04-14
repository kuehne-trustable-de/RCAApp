package eu.trustable.rcaapp;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.HashMap;

import eu.trustable.rcaapp.model.CommitableViewModel;

public class CommitmentFragment extends DialogFragment {

    private static final String TAG = "CommitmentFragment";


    void initCommitterView(@NonNull final View view, final CommitableViewModel mViewModel, boolean initPasswordMap) {

        if (initPasswordMap) {
            mViewModel.passwordMap = new HashMap<Integer, char[]>();
        }

        CryptoUtil cu = new CryptoUtil();

        Spinner committerSpinner = ((Spinner) view.findViewById(R.id.spinnerCommitterName));

        selectSpinnerItemNotPresentYet(committerSpinner, mViewModel.passwordMap);

        int nRequired = mViewModel.issuingCert.getN();
        int stillNeeded = nRequired - mViewModel.passwordMap.size();

        Log.d(TAG, "nRequired : " + nRequired + ", stillNeeded : " + stillNeeded);

        TextView committerHintText = (TextView) view.findViewById(R.id.txtReview_CommiterHint);
        String hintText = stillNeeded + " committer required";
        if (stillNeeded == 1) {
            hintText = "final committer";
        }
        committerHintText.setText(hintText);
    }

    boolean additionalCommitmentRequired(@NonNull final View view, final CommitableViewModel mViewModel){

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
            Snackbar.make(view, "#" + (nRequired - mViewModel.passwordMap.size()) +" additional password required ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
            return true;
        }else{
            Log.d(TAG, "quorum of #" + nRequired + " reached!");
            return false;
        }
    }


    public static void selectSpinnerItemByValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    public static void selectSpinnerItemNotPresentYet(Spinner spinner, HashMap<Integer, char[]> passwordMap) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (!passwordMap.containsKey(getCommitterId((String)(spinner.getItemAtPosition(i))))) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    static Integer getCommitterId(String item){
        return new Integer(Integer.parseInt(item) - 1);
    }

    public static void clearPasswords(HashMap<Integer, char[]> passwordMap) {
        // delete the precious passwords
        for (Integer key : passwordMap.keySet()) {
            char[] pwClear = passwordMap.get(key);
            Arrays.fill(pwClear, (char) 0);
        }
        passwordMap.clear();
    }

}

package eu.trustable.rcaapp.holder;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import eu.trustable.rcaapp.IssuedCertificateItem;
import eu.trustable.rcaapp.R;


/**
 *
 */
public class IssuingCertificateItemHolder extends TreeNode.BaseNodeViewHolder<IssuedCertificateItem > {

    private static final String TAG = "IssuingCertificateItemHolder";


    public IssuingCertificateItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IssuedCertificateItem icItem ) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.issuing_cert_tree_item, null, false);

        ((TextView) view.findViewById(R.id.txtIssuingCertSubject)).setText(icItem.getSubject());
        ((TextView) view.findViewById(R.id.txtIssuingCertExpiryDate)).setText(android.text.format.DateFormat.getDateFormat( super.context).format(icItem.getValidUntil()));

        return view;
    }

    @Override
    public void toggle(boolean active) {
//        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
        Log.d(TAG, "toggle called : " + active);
    }

}

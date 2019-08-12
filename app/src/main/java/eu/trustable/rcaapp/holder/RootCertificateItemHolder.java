package eu.trustable.rcaapp.holder;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import java.util.Date;

import eu.trustable.rcaapp.R;
import eu.trustable.rcaapp.RootCertificateItem;

/**
 *
 */
public class RootCertificateItemHolder extends TreeNode.BaseNodeViewHolder<RootCertificateItem > {

    private static final String TAG = "RootCertificateItemHolder";

    public RootCertificateItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, RootCertificateItem rcItem ) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.root_cert_tree_item, null, false);

        ((TextView) view.findViewById(R.id.txtIssuingCertSubject)).setText(rcItem.getSubject());

        ((TextView) view.findViewById(R.id.txtIssuingCertExpiryDate)).setText(android.text.format.DateFormat.getDateFormat( super.context).format(rcItem.getValidUntil()));

        TextView tvNextCRL = (TextView) view.findViewById(R.id.txtRootCertNextCRL);
        Date nextUpdate = rcItem.getNextCRLUpdate();
        if( nextUpdate ==  null){
            nextUpdate = new Date();
        }
        tvNextCRL.setText(android.text.format.DateFormat.getDateFormat( super.context).format(nextUpdate));

        return view;
    }

    @Override
    public void toggle(boolean active) {
//        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
        Log.d(TAG, "toggle called : " + active);
    }

}

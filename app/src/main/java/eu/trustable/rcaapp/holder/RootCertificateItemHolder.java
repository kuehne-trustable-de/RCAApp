package eu.trustable.rcaapp.holder;


        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.TextView;

        import com.unnamed.b.atv.model.TreeNode;

        import eu.trustable.rcaapp.R;
        import eu.trustable.rcaapp.RootCertificateItem;

/**
 *
 */
public class RootCertificateItemHolder extends TreeNode.BaseNodeViewHolder<RootCertificateItem > {

    private static final String TAG = "RootCertificateItemHolder";

    private TextView tvSubject;
    private TextView tvExpiry;
    private TextView tvNextCRL;

    public RootCertificateItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, RootCertificateItem rcItem ) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.root_cert_tree_item, null, false);

        tvSubject = (TextView) view.findViewById(R.id.txtIssuingCertSubject);
        tvSubject.setText(rcItem.getSubject());

        tvExpiry = (TextView) view.findViewById(R.id.txtIssuingCertExpiryDate);
        tvExpiry.setText(rcItem.getValidUntil().toString());

        tvNextCRL = (TextView) view.findViewById(R.id.txtRootCertNextCRL);
        tvNextCRL.setText("soon ...");

        return view;
    }

    @Override
    public void toggle(boolean active) {
//        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
        Log.d(TAG, "toggle called : " + active);
    }

}

package eu.trustable.rcaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.io.IOException;
import java.util.Date;

import eu.trustable.rcaapp.holder.IssuingCertificateItemHolder;
import eu.trustable.rcaapp.holder.RootCertificateItemHolder;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    NewCAFragment_1 newCADialog = new NewCAFragment_1();

                    newCADialog.show(getSupportFragmentManager(), "tag");
                }
            });

            refreshTreeViewData();

        }catch(IOException ioe){

            Log.e(TAG, "problem reading persistent content ", ioe);

            Toast.makeText( this, "Problem reading persistent content while startup, exiting...", Toast.LENGTH_LONG).show();

            // shutdown, makes no sense to carry on with broken data
            (new Handler()).postDelayed(new Runnable() {
                public void run() {
//                    this.finishAffinity();
                    System.exit(0);
                }
            }, 5000);

        }
    }

    private TreeNode.TreeNodeClickListener nodeRootClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {

            for (TreeNode n : node.getChildren()) {
                n.setExpanded( !n.isExpanded());
                Log.d(TAG, "expanding node " + n.isExpanded());
            }

            CertificateItem certItem = (CertificateItem) value;
            int nChildren = node.getChildren().size();
            String msg = "Root certificate '" + certItem.getSubject() + "' has #"+ nChildren+" children." ;
            if( nChildren == 0){
                msg = "Root certificate '" + certItem.getSubject() + "' has no children.";
            }
            Toast.makeText( node.getViewHolder().getView().getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    };

    private TreeNode.TreeNodeLongClickListener nodeRootLongClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {
            CertificateItem certItem = (CertificateItem) value;
//            Toast.makeText( node.getViewHolder().getView().getContext(), "Long click: " + certItem.getSubject(), Toast.LENGTH_SHORT).show();

            RootCertificateInfoFragment certInfoFrag = RootCertificateInfoFragment.newInstance(certItem.getCertId());
            certInfoFrag.show(getSupportFragmentManager(), "tag");

            return true;
        }
    };

    private TreeNode.TreeNodeLongClickListener nodeIssuedLongClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {
            CertificateItem certItem = (CertificateItem) value;
            Log.d(TAG, "nodeIssuedLongClickListener called for " + certItem);
            Toast.makeText( node.getViewHolder().getView().getContext(), "Long click: " + certItem.getSubject(), Toast.LENGTH_SHORT).show();

            IssuedCertificateInfoFragment  certInfoFrag = IssuedCertificateInfoFragment .newInstance(certItem.getCertId());
            certInfoFrag.show(getSupportFragmentManager(), "tag");

            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: " + requestCode + " / " + resultCode);

        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {

            Log.d(TAG, "forwarding onActivityResult to ScanCSRFragment");
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                Log.d(TAG, "calling onActivityResult for " + fragment.getTag());
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void refreshTreeViewData() throws IOException {

        PersistentModel pm = PersistentModel.read(getApplicationContext());

        TreeNode root = TreeNode.root();

        for (RootCertificateItem rci : pm.getRootCertList()) {
            TreeNode rootCA = new TreeNode(rci);
            rootCA.setClickListener(nodeRootClickListener);
            rootCA.setLongClickListener(nodeRootLongClickListener);

            rootCA.setViewHolder(new RootCertificateItemHolder(this));

            for (IssuedCertificateItem ici : rci.getIssuedCertList()) {

                TreeNode issuingCA = new TreeNode(ici);
                issuingCA.setViewHolder(new IssuingCertificateItemHolder(this));
                issuingCA.setLongClickListener(nodeIssuedLongClickListener);
                rootCA.addChildren(issuingCA);
            }

            root.addChild(rootCA);
        }

        final RelativeLayout containerView = (RelativeLayout) findViewById(R.id.container);

        AndroidTreeView tView = new AndroidTreeView(this, root);
        containerView.removeAllViewsInLayout();
        containerView.addView(tView.getView());
    }

    String formatDate(Date date){
        return android.text.format.DateFormat.getDateFormat(this).format(date);
    }
}

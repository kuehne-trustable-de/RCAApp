package eu.trustable.rcaapp;

import android.content.Intent;
import android.os.Bundle;
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

import eu.trustable.rcaapp.holder.IssuingCertificateItemHolder;
import eu.trustable.rcaapp.holder.RootCertificateItemHolder;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PersistentModel pm = PersistentModel.read(getApplicationContext());

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

        TreeNode root = TreeNode.root();

        for( RootCertificateItem rci: pm.getRootCertList()){
            TreeNode rootCA = new TreeNode(rci);
            rootCA.setClickListener(nodeRootClickListener);
            rootCA.setLongClickListener(nodeRootLongClickListener);

            rootCA.setViewHolder(new RootCertificateItemHolder(this));

            for( IssuedCertificateItem ici: rci.getIssuedCertList()){

                TreeNode issuingCA = new TreeNode(ici);
                issuingCA.setViewHolder(new IssuingCertificateItemHolder(this));
                root.addChildren(issuingCA);
            }

            root.addChild(rootCA);
        }

        final RelativeLayout containerView = (RelativeLayout) findViewById(R.id.container);

        AndroidTreeView tView = new AndroidTreeView(this, root);
        containerView.addView(tView.getView());

    }

    private TreeNode.TreeNodeClickListener nodeRootClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {

            for (TreeNode n : node.getChildren()) {
                n.setExpanded( !n.isExpanded());
                Log.d(TAG, "expanding node " + n.isExpanded());
            }

            CertificateItem certItem = (CertificateItem) value;
            Toast.makeText( node.getViewHolder().getView().getContext(), "Short click: " + certItem.getSubject() + ",  has children " + node.getChildren().size(), Toast.LENGTH_SHORT).show();
        }
    };

    private TreeNode.TreeNodeLongClickListener nodeRootLongClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {
            CertificateItem certItem = (CertificateItem) value;
            Toast.makeText( node.getViewHolder().getView().getContext(), "Long click: " + certItem.getSubject(), Toast.LENGTH_SHORT).show();

            CertificateInfoFragment certInfoFrag = CertificateInfoFragment.newInstance(certItem.getCertId());
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
/*
            if( fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            } else {
                Log.d(TAG, "findFragmentByTag('scanCSRFragmentTag') failed");
            }
*/
                /*
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
*/
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}

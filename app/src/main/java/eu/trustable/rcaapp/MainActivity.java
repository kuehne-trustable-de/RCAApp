package eu.trustable.rcaapp;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    private Listener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PersistentModel.read(getApplicationContext());

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

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.certList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(new ItemAdapter(this, PersistentModel.getInstance()));

    }

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


    public interface Listener {
        void onItemClicked(int position);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView caListItemSubject;
        final TextView caListItemExpiresOn;
        final TextView caListItemCRLExpiresOn;

        final PersistentModel pm;

        ViewHolder(View itemView, PersistentModel pmArg) {

            super(itemView);
            this.pm = pmArg;

            caListItemSubject = (TextView) itemView.findViewById(R.id.caListItemSubject);
            caListItemExpiresOn = (TextView) itemView.findViewById(R.id.caListItemExpiresOn);
            caListItemCRLExpiresOn = (TextView) itemView.findViewById(R.id.caListItemCRLExpiresOn);

            caListItemSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int nItem = getAdapterPosition();
                    if( nItem > 0) {
                        String certId = pm.getRootCertList().get(nItem).certId;

                        CertificateInfoFragment certInfoFrag = CertificateInfoFragment.newInstance(certId);
                        certInfoFrag.show(getSupportFragmentManager(), "tag");
                    }
/*
                    if (mListener != null) {
                        mListener.onItemClicked(getAdapterPosition());
                    }
*/
                }
            });
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private PersistentModel pm;
        private LayoutInflater mInflater;

        ItemAdapter(Context context, PersistentModel pm) {
            this.mInflater = LayoutInflater.from(context);
            this.pm = pm;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false);
            return new ViewHolder(view, pm);

 //           return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder called for item " + position);
            RootCertificateItem rcItem = pm.getRootCertList().get(position);
            holder.caListItemSubject.setText(rcItem.subject);
            holder.caListItemExpiresOn.setText("Foo");
            holder.caListItemCRLExpiresOn.setText("Bar");
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount return " + pm.getRootCertList().size());
            return pm.getRootCertList().size();
        }

    }

}

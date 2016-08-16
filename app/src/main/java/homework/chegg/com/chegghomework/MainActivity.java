package homework.chegg.com.chegghomework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        buildUI();
    }

    private void setupToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void buildUI() {

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                onRefreshData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO fetch data from all data sources, aggregate data and display in RecyclerView
    private void onRefreshData() {

        Toast.makeText(this, "Fetch data aggregate and show on RecyclerView", Toast.LENGTH_SHORT).show();
    }
}

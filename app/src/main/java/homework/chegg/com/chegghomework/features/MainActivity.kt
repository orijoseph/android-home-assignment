package homework.chegg.com.chegghomework.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import homework.chegg.com.chegghomework.R

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildUI()
    }

    private fun setupToolbar() {

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun buildUI() {

        setContentView(R.layout.activity_main)
        setupToolbar()
        mRecyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_refresh -> {
                onRefreshData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // TODO fetch data from all data sources, aggregate data and display in RecyclerView
    private fun onRefreshData() {

        Toast.makeText(this, "Fetch data aggregate and show on RecyclerView", Toast.LENGTH_SHORT).show()
    }
}

package homework.chegg.com.chegghomework.features

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import homework.chegg.com.chegghomework.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    val adapter: DataAdapter = DataAdapter()

    val mViewModel: DataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildUI()

        mViewModel.viewState.observe(this, Observer { updateUi(it) })

        mViewModel.getData()
    }

    private fun buildUI() {

        data_recycler_view.layoutManager = LinearLayoutManager(this)
        data_recycler_view.addItemDecoration(DividerItemDecoration(data_recycler_view.context, requestedOrientation))
        data_recycler_view.adapter = adapter
        setupToolbar()
    }

    private fun setupToolbar() {

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun updateUi(state: MainActivityState?) {
        when (state) {
            is MainActivityState.LoadingState -> {
                displayLoading(state.displayLoading)
                adapter.submitList(state.list)
            }

            is MainActivityState.NotAllLoaded -> {
                displayLoading(state.displayLoading)
                adapter.submitList(state.list)
                showNotAllDataHasBeenUploadedMessage()
            }

            is MainActivityState.LoadedAll -> {
                displayLoading(state.displayLoading)
                adapter.submitList(state.list)
            }
        }
    }

    private fun showNotAllDataHasBeenUploadedMessage() {
        val snackbar = Snackbar
                .make(data_recycler_view, "Not all Data has been uploaded", Snackbar.LENGTH_LONG)
                .setAction("Reload")
                { view -> mViewModel.getData() }.addCallback(object : Snackbar.Callback() {})
        snackbar.show()
    }

    private fun displayLoading(displayLoading: Boolean) {
        progress.visibility = if (displayLoading) View.VISIBLE else View.GONE
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

    private fun onRefreshData() {
        mViewModel.getData()
    }
}

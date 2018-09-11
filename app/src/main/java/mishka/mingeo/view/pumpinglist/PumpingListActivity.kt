package mishka.mingeo.view.pumpinglist

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_pumping_list.*
import kotlinx.android.synthetic.main.floating_button.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.component.DaggerPumpingListActivityComponent
import mishka.mingeo.view.BaseActivityKt
import mishka.mingeo.view.pumping.PumpingActivity
import java.io.File
import java.io.FileWriter

class PumpingListActivity : BaseActivityKt() {

    @Inject
    lateinit var adapter: PumpingListAdapter

    @Inject
    lateinit var viewModel: PumpingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pumping_list)

        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        val component = DaggerPumpingListActivityComponent.builder()
                .activityComponent(getActivityComponent())
                .build()

        component.inject(this)

        adapter.pumpingListActivity = this

        val layoutManager = LinearLayoutManager(this)
        rvPumpings.layoutManager = layoutManager
        rvPumpings.adapter = adapter

        viewModel.pumpings.observe(this, Observer { pumpings -> adapter.pumpings = pumpings ?: ArrayList() })

        btnAction.setOnClickListener {
            val createdPumpingId = viewModel.onCreatePumpingClick()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pumping_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_db -> deleteDatabase("database")
            R.id.send -> onSendClick()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onSendClick() {
    }

    fun onPumpingSelected(pumping: Pumping) {
        val intent = Intent(this, PumpingActivity::class.java)
        intent.putExtra("pumping", pumping)
        startActivity(intent)
    }
}

package mishka.mingeo.view.pumping

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_pumping.*
import kotlinx.android.synthetic.main.floating_button.*
import kotlinx.android.synthetic.main.proprty_card.view.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.mingeo.MinGeoApplication
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.component.DaggerActivityComponent
import mishka.mingeo.di.component.DaggerPumpingActivityComponent
import mishka.mingeo.di.component.PumpingActivityComponent
import mishka.mingeo.di.module.PumpingModule
import mishka.mingeo.view.BaseActivity
import mishka.mingeo.view.BaseActivityKt
import mishka.mingeo.view.dialog.CreateDepthDialog
import mishka.mingeo.view.dialog.SetPumpPowerDialog
import mishka.mingeo.view.dialog.SetValueDialog
import mishka.mingeo.view.plot.PlotView
import mishka.mingeo.view.pumping.borehole.BoreholeActivity
import mishka.mingeo.view.pumping.note.NotesFragment
import mishka.mingeo.view.pumping.pumpinginfo.BoreholeSummaryAdapter
import java.io.File

class PumpingActivity : BaseActivityKt() {

    @Inject
    lateinit var viewModel: PumpingViewModel

    @Inject
    lateinit var boreholeAdapter: BoreholeAdapter

    lateinit var component: PumpingActivityComponent

    private lateinit var notesFragment: NotesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pumping)

        setSupportActionBar(toolbar)

        val pumping = intent.getSerializableExtra("pumping") as Pumping

        component = DaggerPumpingActivityComponent.builder()
                .activityComponent(getActivityComponent())
                .pumpingModule(PumpingModule(pumping))
                .build()

        component.inject(this)

        rvBoreholes.adapter = boreholeAdapter
        rvBoreholes.layoutManager = LinearLayoutManager(this)

        viewModel.boreholes.observe(this, Observer {
            boreholeAdapter.boreholes = it ?: ArrayList()
        })

        viewModel.getPumping().observe(this, Observer {
            pumpPower.propertyValue.text = it?.pumpPower.toString()
        })

        btnAction.setOnClickListener {
            onCreateBoreholeClick()
        }

        pumpPower.propertyName.text = "Мощность насоса"
        pumpPower.propertyValue.setOnClickListener { showSetPumpPowerDialog() }

        notesFragment = supportFragmentManager.findFragmentById(R.id.notesFragment) as NotesFragment
        hideNotes()
    }

    override fun onResume() {
        super.onResume()
        (chartContainer as PlotView).plot(viewModel.getSummaryDepth())
    }

    private fun showSetPumpPowerDialog() {
        val addDepthDialog = SetPumpPowerDialog()
        addDepthDialog.listener = object : SetValueDialog.SetValueListener {
            override fun onValueSet(value: Float) {
                viewModel.setPumpPower(value)
            }
        }
        addDepthDialog.show(fragmentManager, "set_pump_power")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pumping_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private var isNotesVisible = false
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.showNotes -> {
                if(isNotesVisible)
                    hideNotes()
                else showNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideNotes() {
        supportFragmentManager.beginTransaction().hide(notesFragment).commit()
        isNotesVisible = false
    }

    private fun showNotes() {
        supportFragmentManager.beginTransaction().show(notesFragment).commit()
        isNotesVisible = true
    }

    private fun onCreateBoreholeClick() {
        val createdBorehole = viewModel.onCreateBoreholeClick()
//        val intent = Intent(this, BoreholeActivity::class.java)
//        intent.putExtra("borehole", createdBorehole)
//        startActivity(intent)
    }


}

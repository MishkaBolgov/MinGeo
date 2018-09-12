package mishka.mingeo.view.pumping.note


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*

import mishka.mingeo.R
import mishka.mingeo.data.model.Note
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.module.NotesFragmentModule
import mishka.mingeo.utils.PermissionUtils
import mishka.mingeo.view.pumping.PumpingActivity
import java.io.File
import javax.inject.Inject

class NotesFragment : Fragment() {

    @Inject
    lateinit var recorder: Recorder

    @Inject
    lateinit var viewModel: NotesViewModel

    @Inject
    lateinit var adapter: NoteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)


        view.btnRecord.setOnTouchListener { view, event ->
            if (PermissionUtils.isAudioPermissionGranted(activity))
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> onBtnRecordDown()
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> onBtnRecordUp()
                }
            else PermissionUtils.requestAudioPermission(activity)
            true
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val component = (activity as PumpingActivity).component

        component.inject(this)


        rvNotes.layoutManager = LinearLayoutManager(activity)
        rvNotes.adapter = adapter

        viewModel.notes.observe(this, Observer {
            adapter.notes = it ?: ArrayList()
        })
    }

    private fun onBtnRecordDown() {
        recorder.startRecording()
        view?.let {
            it.pbRecordingIndicator.visibility = View.VISIBLE
        }
    }

    private fun onBtnRecordUp() {
        recorder.stopRecording()
        view?.let {
            it.pbRecordingIndicator.visibility = View.INVISIBLE
        }
    }

}

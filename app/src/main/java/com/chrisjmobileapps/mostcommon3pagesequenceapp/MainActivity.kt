package com.chrisjmobileapps.mostcommon3pagesequenceapp

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.MainPresenter
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.dagger.DaggerMainComponent
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.dagger.MainModule
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.navigator.MainPresenterToViewNavigator
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.rx.Event
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.view.MainAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenterToViewNavigator {

    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var mainAdapter: MainAdapter

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val threePageSequenceApplication = ThreePageSequenceApplication.get(this@MainActivity)

        val mainComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .threePageSequenceApplicationComponent(threePageSequenceApplication.threePageSequenceApplicationComponent)
                .build()

        mainComponent.inject(this)
        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(mainAdapter)
        mainPresenter.onCreate()
        mainPresenter.processEvent(Event.SubmitApacheLogEvent)
    }

    override fun onPause() {
        dismissProgressDialog()
        super.onPause()
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }

    override fun setPageSequenceFrequencyListToAdapter(pageSequenceFrequencyList: List<PageSequenceFrequency>?) {
        mainAdapter.setPageSequenceFrequencyList(pageSequenceFrequencyList)
    }

    override fun dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing()) {
                progressDialog!!.dismiss()
                progressDialog = null
            }
        }
    }

    override fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.show()
        }
    }
}

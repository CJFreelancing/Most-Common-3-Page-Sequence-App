package com.chrisjmobileapps.mostcommon3pagesequenceapp.main

import android.util.Log
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.navigator.MainPresenterToViewNavigator
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.rx.Action
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.rx.Event
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.rx.Result
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.rx.Result.SubmitApacheLogResult
import com.chrisjmobileapps.mostcommon3pagesequenceapp.network.NetworkService
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainPresenter(val networkService: NetworkService, val mainPresenterToViewNavigator: MainPresenterToViewNavigator) {
    private lateinit var compositeDisposable: CompositeDisposable
    private val eventsSubject: PublishSubject<Event> = PublishSubject.create()

    fun onCreate() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(compose().subscribe({ mainUiStateModel ->
            subscribeStateModelRender(mainUiStateModel)
        }, { error ->
            //TODO replace logging with timber
            //Log.e("HERE_", "{$error.message")
        }))
    }

    private fun actionFromEvent(event: Event): Action {
        when (event) {
            is Event.SubmitApacheLogEvent -> return Action.SubmitApacheLogAction
        }
    }

    fun processEvent(event: Event) {
        eventsSubject.onNext(event)
    }

    private fun compose(): Observable<MainUiStateModel> {
        return eventsSubject
                .map { event -> actionFromEvent(event) }
                .publish { sharedAction ->
                    sharedAction.ofType(Action.SubmitApacheLogAction::class.java).compose(fetchApacheLog)
                }
                .scan(MainUiStateModel.idle(), resultsToStateUi)
    }

    private val resultsToStateUi = BiFunction { state: MainUiStateModel, result: Result ->
        when (result) {
            is SubmitApacheLogResult -> when (result) {
                is SubmitApacheLogResult.Success ->
                    MainUiStateModel.success(result.pageSequenceFrequencyList)
                is SubmitApacheLogResult.InProgress ->
                    MainUiStateModel.inProgress()
                is SubmitApacheLogResult.Failure ->
                    MainUiStateModel.failure(result.error)
            }
        }
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

    private val fetchApacheLog = ObservableTransformer<Action.SubmitApacheLogAction, Result> { action ->
        action.flatMap { action ->
            networkService.getApacheLog().toObservable()
                    .map { pageSequenceFrequency ->  SubmitApacheLogResult.Success(pageSequenceFrequency) }
                    .cast(SubmitApacheLogResult::class.java)
                    .onErrorReturn { error -> SubmitApacheLogResult.Failure(error.message) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(SubmitApacheLogResult.InProgress)
        }
    }

    private fun subscribeStateModelRender(mainUiStateModel: MainUiStateModel) {
        if(mainUiStateModel.inProgress) {
            mainPresenterToViewNavigator.showProgressDialog()
        } else {
            mainPresenterToViewNavigator.dismissProgressDialog()
        }

        if(!mainUiStateModel.inProgress) {
            if (mainUiStateModel.success) {
                mainPresenterToViewNavigator.setPageSequenceFrequencyListToAdapter(mainUiStateModel.pageSequenceFrequencyList)
            } else {
                val errorMessage = mainUiStateModel.errorMessage
                //TODO add if error happens
            }
        }
    }
}
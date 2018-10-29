package com.chrisjmobileapps.mostcommon3pagesequenceapp.main

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency

data class MainUiStateModel(val inProgress: Boolean,
                            val success: Boolean,
                            val errorMessage: String?,
                            val pageSequenceFrequencyList:  List<PageSequenceFrequency>?) {
    companion object {
        fun idle() : MainUiStateModel {
            return MainUiStateModel(false, false, null, null)
        }

        fun inProgress() : MainUiStateModel {
            return MainUiStateModel(true, false, null, null)
        }

        fun success(pageSequenceFrequencyList: List<PageSequenceFrequency>?) : MainUiStateModel {
            return MainUiStateModel(false, true, null, pageSequenceFrequencyList)
        }

        fun failure(errorMessage: String?) : MainUiStateModel {
            return MainUiStateModel(false, false, errorMessage, null)
        }
    }
}
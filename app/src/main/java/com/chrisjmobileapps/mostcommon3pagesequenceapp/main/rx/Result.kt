package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.rx

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency

sealed class Result {
    sealed class SubmitApacheLogResult : Result() {
        data class Success(val pageSequenceFrequencyList: List<PageSequenceFrequency>): SubmitApacheLogResult()
        data class Failure(val error: String?): SubmitApacheLogResult()
        object InProgress: SubmitApacheLogResult()
    }
}
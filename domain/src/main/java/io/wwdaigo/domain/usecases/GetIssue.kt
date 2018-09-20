package io.wwdaigo.domain.usecases

import io.reactivex.Observable
import io.wwdaigo.domain.commons.Result
import io.wwdaigo.domain.entities.Issue

interface GetIssue {
    val callback: Callback

    fun getIssue(id: Int)

    interface Callback {
        val issue: Observable<Result<Issue>>
    }
}
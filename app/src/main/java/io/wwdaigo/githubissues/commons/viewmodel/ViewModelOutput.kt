package io.wwdaigo.githubissues.commons.viewmodel

import io.reactivex.Observable
import io.wwdaigo.githubissues.commons.Errors

interface ViewModelOutput {
    val isLoading: Observable<Boolean>
    val successMessage: Observable<String>
    val errorStatus: Observable<Errors>
}
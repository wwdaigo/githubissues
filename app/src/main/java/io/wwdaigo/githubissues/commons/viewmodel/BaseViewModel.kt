package io.wwdaigo.githubissues.commons.viewmodel

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.wwdaigo.githubissues.commons.Errors
import retrofit2.adapter.rxjava2.Result
import java.net.UnknownHostException

abstract class BaseViewModel: ViewModelOutput {

    protected val isLoadingSubject = PublishSubject.create<Boolean>()
    override val isLoading: Observable<Boolean>
        get() = isLoadingSubject

    protected  val successMessageSubject = PublishSubject.create<String>()
    override val successMessage: Observable<String>
        get() = successMessageSubject

    protected  val errorStatusSubject = PublishSubject.create<Errors>()
    override val errorStatus: Observable<Errors>
        get() = errorStatusSubject


    protected fun <T> errorHandling(response: Result<T>) {
        response.error()?.let {
            if (it is UnknownHostException)
                errorStatusSubject.onNext(Errors.NO_INTERNET)
            else
                errorStatusSubject.onNext(Errors.SERVER_ERROR)
        }
    }
}
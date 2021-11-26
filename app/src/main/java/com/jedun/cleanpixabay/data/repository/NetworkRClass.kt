package com.jedun.cleanpixabay.data.repository


/* NetworkBoundSource - using Observable in RxJava/RxAndroid */

//abstract class NetworkBoundResource<ResultType, RequestType> {
//
//    private val asObservable: Observable<Resource<ResultType>>
//
//    init {
//        val source: Observable<Resource<ResultType>>
//        if (shouldFetch()) {
//
//            source = createCall()
//                .subscribeOn(Schedulers.io())
//                .doOnNext {
//                    saveCallResult(processResponse(it)!!)
//                }
//                .flatMap<Resource<ResultType>> {
//                    loadFromDb().toObservable()
//                        .map { Resource.Success(it) }
//                }
//                .doOnError { onFetchFailed() }
//                .onErrorResumeNext { t: Throwable ->
//                    loadFromDb().toObservable().map { Resource.Error(t.message!!, it) }
//                }
////                .observeOn(AndroidSchedulers.mainThread())
//
//        } else {
//            source = loadFromDb()
//                .toObservable()
//                .map { Resource.Success(it) }
//        }
//
//        asObservable = Observable.concat(
//            loadFromDb()
//                .toObservable()
//                .map { Resource.Loading(it) }
//                .take(1),
//            source
//        )
//    }
//
//    fun getAsObservable(): Observable<Resource<ResultType>> {
//        return asObservable
//    }
//
//    private fun onFetchFailed() {}
//
//    private fun processResponse(response: RequestType): RequestType? {
//        return response
//    }
//
//    protected abstract fun saveCallResult(item: RequestType)
//
//    protected abstract fun shouldFetch(): Boolean
//
//    protected abstract fun loadFromDb(): Flowable<ResultType>
//
//    protected abstract fun createCall(): Observable<RequestType>
//}
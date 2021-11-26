package com.jedun.cleanpixabay.data


//abstract class NetworkBoundResource<ResultType, RequestType> protected constructor() {
//    private val TAG = NetworkBoundResource::class.java.simpleName
//    private val result: Flowable<Resource<ResultType>>
//    fun asFlowable(): Flowable<Resource<ResultType>> {
//        return result
//    }
//
//    private fun processResponse(response: Response<RequestType>): RequestType? {
//        return response.body()
//    }
//
//    private fun processInternalError(response: Response<RequestType>) {
//        var error: String? = null
//        if (response.errorBody() != null) {
//            try {
//                error = response.errorBody()!!.string()
//            } catch (ignored: IOException) {
//                Log.e(TAG, "Error while parsing response: " + ignored.message)
//            }
//            throw Exceptions.propagate(Throwable(response.code().toString() + ": " + error))
//        }
//    }
//
//    protected abstract fun saveCallResult(item: RequestType)
//    protected abstract fun loadFromDb(): Flowable<ResultType>
//    protected abstract fun createCall(): Flowable<Response<RequestType>>
//
//    init {
//
//        // Lazy db observable.
//        val dbObservable = Flowable.defer {
//            loadFromDb() // Read from disk on Computation Scheduler
//                .subscribeOn(Schedulers.computation())
//        }
//
//        // Lazy network observable.
//        val networkObservable = Flowable.defer(
//            Callable<Publisher<out ResultType>> {
//                createCall() // Request API on IO Scheduler
//                    .subscribeOn(Schedulers.io()) // Read/Write to disk on Computation Scheduler
//                    .observeOn(Schedulers.computation())
//                    .doOnNext(Consumer<Response<RequestType>> { request: Response<RequestType> ->
//                        if (request.isSuccessful) {
//                            processResponse(request)?.let { saveCallResult(it) }
//                        } else {
//                            processInternalError(request)
//                        }
//                    })
//                    .onErrorReturn(Function { throwable: Throwable? ->
//                        throw Exceptions.propagate(
//                            throwable!!
//                        )
//                    })
//                    .flatMap(Function<Response<RequestType>, Publisher<out ResultType>> { _: Response<RequestType>? -> loadFromDb() })
//            }
//        )
//        result = if (NetworkUtils.isNetworkStatusAvailable()) networkObservable
////            .map<Resource<List<ResultType>>>()
//            .onErrorReturn { t: Throwable ->
//                Resource.error(
//                    t.message,
//                    null
//                )
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .startWith(Resource::loading) else dbObservable
//            .map<Any>(Resource::success)
//            .onErrorReturn { t: Throwable ->
//                Resource.error(
//                    t.message,
//                    null
//                )
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .startWith(Resource::loading)
//    }
//}
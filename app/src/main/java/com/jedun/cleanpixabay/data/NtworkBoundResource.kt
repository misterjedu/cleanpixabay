package com.jedun.cleanpixabay.data

import com.jedun.cleanpixabay.utils.Resource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import java.util.concurrent.atomic.AtomicReference


/**
 * The base repository to handle important functionality of repositories.
 *
 * @param E the entity that is related to this repository. Every repository responsible for
 * one and only one entity.
 */
abstract class BaseRepository {
    /**
     * Loads an entity from database and network.
     *
     * @param D a data type
     * @param databaseFlowable a flowable to load an entity from database.
     * @param netSingle a single to load an entity from server.
     * @param persist a high order function to persist an entity.
     */
    protected fun <D> perform(
        databaseFlowable: Flowable<List<D>>,
        netSingle: Single<List<D>>,
        persist: (List<D>) -> Unit
    ): Flowable<Resource<List<D>>> {

        val cachedData = AtomicReference<List<D>?>()
        val processor = PublishProcessor.create<Resource<List<D>>>()
        val flowable = databaseFlowable.doOnNext {
            cachedData.set(it)
        }.share()

        return Flowable.merge(flowable.take(1)
            .flatMap {
                if (it.isEmpty()) {
                    handleNetSingle(netSingle, persist, cachedData)
                } else {
                    concatJustFlowable(
                        Resource.Loading(),
                        handleNetSingle(netSingle, persist, cachedData)
                    )
                }
            }, flowable.skip(1)
            .flatMap {
                if (it.isNotEmpty()) {
                    concatJustFlowable(Resource.Success(it), processor)
                } else {
                    processor
                }
            })
            .onErrorResumeNext(io.reactivex.functions.Function {
                concatJustFlowable(
                    Resource.Error(it.message ?: "Unknown error", cachedData.get()),
                    processor
                )
            })
    }

    private fun <D> concatJustFlowable(
        d: Resource<List<D>>,
        flowable: Flowable<Resource<List<D>>>
    ) = Flowable.concat(Flowable.just(d), flowable)

    private fun <D> handleNetSingle(
        netSingle: Single<List<D>>,
        persist: (List<D>) -> Unit,
        cachedData: AtomicReference<List<D>?>
    ): Flowable<Resource<List<D>>> = netSingle.toFlowable().flatMap {
        persist(it)
        Flowable.empty<Resource<List<D>>>()
    }.onErrorReturn {
        Resource.Error(it.message ?: "Unknown error", cachedData.get())
    }
}


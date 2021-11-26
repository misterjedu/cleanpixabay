package com.jedun.cleanpixabay.presentation.ui.searchimages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.model.PixabayRequest
import com.jedun.cleanpixabay.domain.usecase.SearchImageUseCase
import com.jedun.cleanpixabay.domain.util.Event
import com.jedun.cleanpixabay.presentation.mappers.HitDetailMapper
import com.jedun.cleanpixabay.presentation.mappers.HitPreviewMapper
import com.jedun.cleanpixabay.presentation.model.HitDetail
import com.jedun.cleanpixabay.presentation.model.ViewState
import com.jedun.cleanpixabay.presentation.ui.searchimages.states.HitPreviewViewState
import com.jedun.cleanpixabay.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
    private val hitDetailMapper: HitDetailMapper,
    private val hitPreviewMapper: HitPreviewMapper
) : ViewModel() {

    var page: Int
    var query: String

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _imagesStateObservable = MutableLiveData<HitPreviewViewState>()
    val imagesStateObservable: LiveData<HitPreviewViewState> = _imagesStateObservable

    private var imagesStateTemp: HitPreviewViewState =
        HitPreviewViewState(isLoading = true, viewState = ViewState.LOADING)

    private val _imagePreviewDetailList: MutableLiveData<List<HitDetail>> = MutableLiveData()
    val imagePreviewDetailList: LiveData<List<HitDetail>> = _imagePreviewDetailList

    init {
        page = 1
        query = "fruit"
        getResult(page)
    }

    fun getResult(pageNumber: Int) {

        _imagesStateObservable.value = HitPreviewViewState(
            isLoading = true,
            viewState = if (imagesStateTemp.images.isNotEmpty()) ViewState.SUCCESS else ViewState.LOADING,
            images = imagesStateTemp.images
        )


        searchImageUseCase(pixabayRequest = PixabayRequest(query = query, page = pageNumber))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Resource<List<Hit>>> {

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(result: Resource<List<Hit>>) {
                    page = pageNumber

                    when (result) {
                        is Resource.Error -> {
                            println("Failure Printed")

                            _imagesStateObservable.value = HitPreviewViewState(
                                isLoading = false,
                                error = result.message ?: "",
                                images = imagesStateTemp.images,
                                viewState = if (imagesStateTemp.images.isNotEmpty()) ViewState.SUCCESS else ViewState.ERROR,
                                snackError = Event(result.message ?: "")
                            )
                        }
                        is Resource.Loading -> {
                            _imagesStateObservable.value = HitPreviewViewState(
                                isLoading = true,
                                viewState = if (imagesStateTemp.images.isNotEmpty()) ViewState.SUCCESS else ViewState.LOADING,
                            )
                        }
                        is Resource.Success -> {
                            println("Success Printed")
                            imagesStateTemp = HitPreviewViewState(
                                isLoading = false,
                                images = result.data!!.map { hit -> hitPreviewMapper.mapToDomain(hit) },
                                viewState = ViewState.SUCCESS
                            )

                            _imagesStateObservable.value = imagesStateTemp
                            _imagePreviewDetailList.value =
                                result.data.map { hit -> hitDetailMapper.mapToDomain(hit) }

                        }
                    }
                }

                override fun onError(e: Throwable) {
                    _imagesStateObservable.value = HitPreviewViewState(
                        isLoading = false,
                        error = e.message ?: "",
                        images = imagesStateTemp.images,
                        viewState = if (imagesStateTemp.images.isNotEmpty()) ViewState.SUCCESS else ViewState.ERROR,
                        snackError = Event(e.message ?: "")
                    )
                }

                override fun onComplete() {}
            })
    }

    fun searchNextPage() {
        getResult(page + 1)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}





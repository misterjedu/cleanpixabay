package com.jedun.cleanpixabay.presentation.ui.searchimages.states

import com.jedun.cleanpixabay.domain.util.Event
import com.jedun.cleanpixabay.presentation.model.HitPreview
import com.jedun.cleanpixabay.presentation.model.ViewState

data class HitPreviewViewState(
    val isLoading: Boolean = false,
    var images: List<HitPreview> = listOf(),
    val error: String = "",
    val viewState: ViewState,
    val snackError: Event<String> = Event("")
)

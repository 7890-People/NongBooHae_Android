package com.konkuk.nongboohae.remote.response

import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel

data class DiseaseListResponse(
    val diseases: List<DiseaseListPresentModel>
)

package com.konkuk.nongboohae.remote.response

data class DiseaseListResponse(
    val diseases: List<DiseaseListPresentModel>
)

data class DiseaseListPresentModel(
    val id: String,
    val imgUrl: String?,
    val korName: String,
    val engName: String,
    val category: String
)
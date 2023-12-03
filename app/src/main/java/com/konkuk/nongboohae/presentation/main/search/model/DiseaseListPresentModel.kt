package com.konkuk.nongboohae.presentation.main.search.model


data class DiseaseListPresentModel(
    val id: String,
    val imgUrl: String?,
    val korName: String,
    val engName: String,
    val category: String
)
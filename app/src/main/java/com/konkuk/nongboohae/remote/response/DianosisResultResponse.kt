package com.konkuk.nongboohae.remote.response

data class DiagnosisResultResponse(
    val diseaseName:String,
    val condition: String,
    val symptoms: String,
    val preventionMethod: String,
    val diseaseImg: String
)
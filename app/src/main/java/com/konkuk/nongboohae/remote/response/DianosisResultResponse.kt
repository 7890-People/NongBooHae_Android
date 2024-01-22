package com.konkuk.nongboohae.remote.response

import java.io.Serializable

data class DiagnosisResultResponse(
    val diseaseName:String,
    val condition: String,
    val symptoms: String,
    val preventionMethod: String,
    val diseaseImg: String
) : Serializable
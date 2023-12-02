package com.konkuk.nongboohae.presentation.main.search

import com.konkuk.nongboohae.presentation.base.BaseRepository
import com.konkuk.nongboohae.remote.api.DiseasesApi
import com.konkuk.nongboohae.remote.response.DiseaseListPresentModel
import com.konkuk.nongboohae.remote.response.DiseaseListResponse
import com.konkuk.nongboohae.util.network.ApiState
import com.konkuk.nongboohae.util.network.RetrofitClient

class SearchRepository : BaseRepository() {
    val api = RetrofitClient.create(DiseasesApi::class.java)

    // 실제 서버 구현 함수
//    suspend fun getDiseases(category: String): ApiState<DiseaseListResponse> =
//        makeRequest { api.getDiseases(category = category) }

    fun getDiseases(category: String): ApiState<DiseaseListResponse> =
        ApiState.Success(data = DiseaseListResponse(
            listOf(
                DiseaseListPresentModel(
                    id = "1", imgUrl = "https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3013_0120161027094643027_TMB.jpg", korName = "갈색 무늬병", engName = "Leaf spot", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "2", imgUrl = "https://www.nongsaro.go.kr/portal/ps/pss/pssa/photoSearchLst.ps?menuId=PS00202&sKidofcomdtyTabCode=FT&sKidofcomdtyCode=FT040603&hlsctCode=&sicknsCode=&nnmyInsectCode=#", korName = "그을음점무늬병", engName = "Fly speak", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "3", imgUrl = null, korName = "근두암종병", engName = "Crown gall", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "4", imgUrl = null, korName = "꼭지마름병", engName = "Peduncle", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "5", imgUrl = null, korName = "노균병", engName = "Downy mildew", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "6", imgUrl = null, korName = "녹병", engName = "Rust", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "7", imgUrl = null, korName = "덩굴쪼김병", engName = "Dead arm", category = "PODO"
                ),
                DiseaseListPresentModel(
                    id = "8", imgUrl = null, korName = "먼지곰팡이병", engName = "Dusty mold", category = "PODO"
                ),
            )
        ))
}

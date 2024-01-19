package com.konkuk.nongboohae.presentation.main.search

import com.konkuk.nongboohae.presentation.base.BaseRepository
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel
import com.konkuk.nongboohae.remote.response.DiseaseListResponse
import com.konkuk.nongboohae.util.network.ApiState
import kotlinx.coroutines.delay

class SearchRepository : BaseRepository() {
//    val api = RetrofitClient.create(DiseasesApi::class.java)

    // 실제 서버 구현 함수
//    suspend fun getDiseases(category: String): ApiState<DiseaseListResponse> =
//        makeRequest { api.getDiseases(category = category) }

    suspend fun getDiseases(category: String): ApiState<DiseaseListResponse> {
        return ApiState.Success(
            data = DiseaseListResponse(
                listOf(
                    DiseaseListPresentModel(
                        id = "1",
                        imgUrl = null,
                        korName = "갈색 무늬병",
                        engName = "Leaf spot",
                        category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "2",

                        imgUrl = null, korName = "그을음점무늬병",
                        engName = "Fly speak",
                        category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "3",

                        imgUrl = null, korName = "근두암종병",
                        engName = "Crown gall",
                        category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "4",
                        imgUrl = null,
                        korName = "꼭지마름병",
                        engName = "Peduncle",
                        category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "5",
                        imgUrl = null,
                        korName = "노균병",
                        engName = "Downy mildew",
                        category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "6", imgUrl = null, korName = "녹병", engName = "Rust", category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "7",
                        imgUrl = null,
                        korName = "덩굴쪼김병",
                        engName = "Dead arm",
                        category = "포도"
                    ),
                    DiseaseListPresentModel(
                        id = "8",
                        imgUrl = null,
                        korName = "먼지곰팡이병",
                        engName = "Dusty mold",
                        category = "포도"
                    ),
                )
            )
        )
    }
}

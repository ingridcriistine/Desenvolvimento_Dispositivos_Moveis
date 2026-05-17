package com.example.viacep

import com.example.viacep.model.Address
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {
    @GET("/ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): Address
}
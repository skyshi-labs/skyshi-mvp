package com.skyshi.test.service

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.skyshi.mvp.skymvp.service.NetManager
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

internal class ErrorInterceptor(private val netManager: NetManager) : Interceptor {

    private var response: Response? = null
    private var responseBody: String? = null
    private var responseJson: JsonObject? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

//        if (!netManager.isConnectedToInternet) throw ErrorConnectionException()

        try {
            response = chain.proceed(chain.request())

            val isMaintenance: Boolean? = response?.header("maintenance-status")?.toBoolean()
            val isMaintenance2: Boolean? = response?.header("Maintenance-Status")?.toBoolean()

            responseBody = response!!.body()!!.string()
            responseJson = JsonParser().parse(responseBody).asJsonObject

        } catch (e: Exception) {
//            if (e is ErrorMaintenanceException) throw e
//            if (e is UnknownHostException) throw ErrorConnectionException()
//            if (e is SocketTimeoutException) throw ErrorConnectionException("Connection Timeout")
        }

        if (!response!!.isSuccessful) {
            val errorMessage = parseErrorMessage(responseJson)
//
//            if (response!!.code() >= HttpURLConnection.HTTP_INTERNAL_ERROR)
//                throw ErrorConnectionException("Oops, Internal Server Error")
//
//            if (response!!.code() >= HttpURLConnection.HTTP_BAD_REQUEST)
//                throw BadRequestException(Gson().fromJson(responseJson, ErrorResponse::class.java), errorMessage)
//
//            if (response!!.code() == HttpURLConnection.HTTP_FORBIDDEN || response!!.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
////                EventBus.getDefault().post(AuthenticationException(errorMessage))
//                throw AuthenticationException(errorMessage.toString())
//            }
            throw RuntimeException(errorMessage)
        }
        return response!!
                .newBuilder()
                .body(ResponseBody.create(response!!.body()!!.contentType(), responseBody!!))
                .build()
    }

    private fun parseErrorMessage(jsonObject: JsonObject?): String? {
        return jsonObject?.get("message")?.asString
    }
}
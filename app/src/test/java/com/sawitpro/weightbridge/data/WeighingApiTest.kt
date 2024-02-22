package com.sawitpro.weightbridge.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.utils.TestUtilities
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class WeighingApiTest {

    private val mockWebServer = MockWebServer()
    private lateinit var api: WeighingApi

    @Before
    fun setup() {
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeighingApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch get weighing list`() = runBlocking {
        val mockBody = TestUtilities.getResponseBodyFromJsonFile(
            filename = "get_weighing_ticket_list_success.json"
        )

        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockBody)

        mockWebServer.enqueue(mockResponse)

        val response = api.getWeighingList()
        val request = mockWebServer.takeRequest()

        assert(response.isSuccessful)
        assert(request.method == "GET")
        assert(request.path == "/posts.json")
        assert(response.body()!!.count() == 3)
        assert(response.body()!!.keys.elementAt(0) == "-NqxNttkoh63e0IITemA")
        assert(response.body()!!.values.elementAt(0).driverName == "Asep Munandar")
    }

    @Test
    fun `fetch update weighing ticket`() = runBlocking {
        val mockBody = TestUtilities.getResponseBodyFromJsonFile(
            filename = "update_weighing_ticket_success.json"
        )

        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockBody)

        mockWebServer.enqueue(mockResponse)

        val response = api.updateWeighingList("-NqxNttkoh63e0IITemA",
            RequestCreateEditWeighingTicketEntity(
                driverName = "Sunarto",
                licenseNumber = "B 2812 SM",
                date = "2024-02-04",
                inboundWeight = 104.6,
                outboundWeight = 103.0
            )
        )
        val request = mockWebServer.takeRequest()

        assert(response.isSuccessful)
        assert(request.method == "PUT")
        assert(request.path == "/posts/-NqxNttkoh63e0IITemA.json")
        assert(response.body()!!.driverName == "Sunarto")
    }

    @Test
    fun `fetch create weighing ticket`() = runBlocking {
        val mockBody = TestUtilities.getResponseBodyFromJsonFile(
            filename = "create_weighing_ticket_success.json"
        )

        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockBody)

        mockWebServer.enqueue(mockResponse)

        val response = api.setWeighingList(
            RequestCreateEditWeighingTicketEntity(
                driverName = "Sunarto",
                licenseNumber = "B 2812 SM",
                date = "2024-02-04",
                inboundWeight = 104.6,
                outboundWeight = 103.0
            )
        )
        val request = mockWebServer.takeRequest()

        assert(response.isSuccessful)
        assert(request.method == "POST")
        assert(request.path == "/posts.json")
        assert(response.body()!!.name == "-NqxNttkoh63e0IITemA")
    }
}
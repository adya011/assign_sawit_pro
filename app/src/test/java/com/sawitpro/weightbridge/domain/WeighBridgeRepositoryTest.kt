package com.sawitpro.weightbridge.domain

import app.cash.turbine.test
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.data.model.entity.RequestCreateEditWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.UpdateWeighingTicketEntity
import com.sawitpro.weightbridge.data.model.entity.WeighingTicketEntity
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.domain.repository.WeighBridgeRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class WeighBridgeRepositoryTest : BaseTest() {

    private val weighingApi: WeighingApi = mockk(relaxed = true)
    private lateinit var repo: WeighBridgeRepositoryImpl

    override fun setup() {
        super.setup()
        repo = WeighBridgeRepositoryImpl(weighingApi, dao)
    }

    @Test
    fun `get weighing list success - return weighing list`() = runTest {
        coEvery {
            weighingApi.getWeighingList()
        } returns Response.success(
            mapOf(
                "-NqxNttkoh63e0IITemA" to WeighingTicketDto(
                    driverName = "driver1",
                    licenseNumber = "AC 4321 DE",
                    date = "2024-02-09",
                    inboundWeight = 13.0,
                    outboundWeight = 12.0
                ),
                "-Nr1DQZKrjc-gfcJbQCE" to WeighingTicketDto(
                    driverName = "driver2",
                    licenseNumber = "AB 1234 CD",
                    date = "2024-02-09",
                    inboundWeight = 15.0,
                    outboundWeight = 14.0
                )
            )
        )

        repo.getWeighingList(false).test {
            assertEquals(
                DataResult.Loading<List<WeighingTicketEntity>>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Success(
                    listOf(
                        WeighingTicketEntity(
                            uId = "-NqxNttkoh63e0IITemA",
                            driverName = "driver1",
                            licenseNumber = "AC 4321 DE",
                            date = "2024-02-09",
                            inboundWeight = 13.0,
                            outboundWeight = 12.0,
                            netWeight = 1.0
                        ),
                        WeighingTicketEntity(
                            uId = "-Nr1DQZKrjc-gfcJbQCE",
                            driverName = "driver2",
                            licenseNumber = "AB 1234 CD",
                            date = "2024-02-09",
                            inboundWeight = 15.0,
                            outboundWeight = 14.0,
                            netWeight = 1.0
                        )
                    )
                ),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.getWeighingList()
        }

        confirmVerified(weighingApi)
    }

    @Test
    fun `get weighing list error - return error`() = runTest {
        val mockErrorCode = 500
        val mockErrorMessage = "Response.error()"

        coEvery {
            weighingApi.getWeighingList()
        } returns Response.error(
            mockErrorCode,
            "{\"errorMessage\":\"error\",\"code\":\"400\"}".toResponseBody("application/json".toMediaTypeOrNull())
        )

        repo.getWeighingList(false).test {
            assertEquals(
                DataResult.Loading<List<WeighingTicketEntity>>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Error<List<WeighingTicketEntity>>(mockErrorMessage, mockErrorCode),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.getWeighingList()
        }

        confirmVerified(weighingApi)
    }

    @Test
    fun `set weighing detail success - return uId`() = runTest {
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        coEvery {
            weighingApi.setWeighingList(
                mockRequestWeighingTicket
            )
        } returns Response.success(
            SetWeighingResponseDto(
                name = "-NqxNttkoh63e0IITemA"
            )
        )

        repo.setWeighingDetail(mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<SetWeighingTicketEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Success(
                    SetWeighingTicketEntity(
                        name = "-NqxNttkoh63e0IITemA"
                    )
                ),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.setWeighingList(mockRequestWeighingTicket)
        }

        confirmVerified(weighingApi)
    }

    @Test
    fun `set weighing detail error - return error`() = runTest {
        val mockErrorCode = 500
        val mockErrorMessage = "Response.error()"
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        coEvery {
            weighingApi.setWeighingList(mockRequestWeighingTicket)
        } returns Response.error(
            mockErrorCode,
            "{\"errorMessage\":\"error\",\"code\":\"400\"}".toResponseBody("application/json".toMediaTypeOrNull())
        )

        repo.setWeighingDetail(mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<SetWeighingTicketEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Error<SetWeighingTicketEntity>(mockErrorMessage, mockErrorCode),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.setWeighingList(mockRequestWeighingTicket)
        }

        confirmVerified(weighingApi)
    }

    @Test
    fun `update weighing detail success - return weighing data`() = runTest {
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AC 4321 DE",
            date = "2024-02-09",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        coEvery {
            weighingApi.updateWeighingList(
                mockUid,
                mockRequestWeighingTicket
            )
        } returns Response.success(
            WeighingTicketDto(
                driverName = "driver1",
                licenseNumber = "AC 4321 DE",
                date = "2024-02-09",
                inboundWeight = 13.0,
                outboundWeight = 12.0
            )
        )

        repo.updateWeighingDetail(mockUid, mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<WeighingTicketEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Success(
                    UpdateWeighingTicketEntity(
                        driverName = "driver1",
                        licenseNumber = "AC 4321 DE",
                        date = "2024-02-09",
                        inboundWeight = 13.0,
                        outboundWeight = 12.0,
                        netWeight = 1.0
                    )
                ),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.updateWeighingList(mockUid, mockRequestWeighingTicket)
        }

        confirmVerified(weighingApi)
    }

    @Test
    fun `update weighing detail error - return error`() = runTest {
        val mockUid = "-NqxNttkoh63e0IITemA"
        val mockErrorCode = 500
        val mockErrorMessage = "Response.error()"
        val mockRequestWeighingTicket = RequestCreateEditWeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13.0,
            outboundWeight = 12.0
        )

        coEvery {
            weighingApi.updateWeighingList(mockUid, mockRequestWeighingTicket)
        } returns Response.error(
            mockErrorCode,
            "{\"errorMessage\":\"error\",\"code\":\"400\"}".toResponseBody("application/json".toMediaTypeOrNull())
        )

        repo.updateWeighingDetail(mockUid, mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<WeighingTicketEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Error<SetWeighingTicketEntity>(mockErrorMessage, mockErrorCode),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.updateWeighingList(mockUid, mockRequestWeighingTicket)
        }

        confirmVerified(weighingApi)
    }
}
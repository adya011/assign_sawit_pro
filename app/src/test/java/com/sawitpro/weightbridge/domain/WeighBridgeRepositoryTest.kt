package com.sawitpro.weightbridge.domain

import app.cash.turbine.test
import com.sawitpro.weightbridge.base.BaseTest
import com.sawitpro.weightbridge.data.core.DataResult
import com.sawitpro.weightbridge.data.model.dto.SetWeighingResponseDto
import com.sawitpro.weightbridge.data.model.dto.WeighingTicketDto
import com.sawitpro.weightbridge.data.model.entity.SetWeighingTicketResponseEntity
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
    private val repo: WeighBridgeRepositoryImpl = WeighBridgeRepositoryImpl(weighingApi)

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
                    inboundWeight = 13,
                    outboundWeight = 12
                ),
                "-Nr1DQZKrjc-gfcJbQCE" to WeighingTicketDto(
                    driverName = "driver2",
                    licenseNumber = "AB 1234 CD",
                    date = "2024-02-09",
                    inboundWeight = 15,
                    outboundWeight = 14
                )
            )
        )

        repo.getWeighingList().test {
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
                            inboundWeight = 13,
                            outboundWeight = 12
                        ),
                        WeighingTicketEntity(
                            uId = "-Nr1DQZKrjc-gfcJbQCE",
                            driverName = "driver2",
                            licenseNumber = "AB 1234 CD",
                            date = "2024-02-09",
                            inboundWeight = 15,
                            outboundWeight = 14
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

        repo.getWeighingList().test {
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
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
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
                DataResult.Loading<SetWeighingTicketResponseEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Success(
                    SetWeighingTicketResponseEntity(
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
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        coEvery {
            weighingApi.setWeighingList(mockRequestWeighingTicket)
        } returns Response.error(
            mockErrorCode,
            "{\"errorMessage\":\"error\",\"code\":\"400\"}".toResponseBody("application/json".toMediaTypeOrNull())
        )

        repo.setWeighingDetail(mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<SetWeighingTicketResponseEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Error<SetWeighingTicketResponseEntity>(mockErrorMessage, mockErrorCode),
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
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AC 4321 DE",
            date = "2024-02-09",
            inboundWeight = 13,
            outboundWeight = 12
        )

        coEvery {
            weighingApi.updateWeighingList(
                mockRequestWeighingTicket
            )
        } returns Response.success(
            WeighingTicketDto(
                driverName = "driver1",
                licenseNumber = "AC 4321 DE",
                date = "2024-02-09",
                inboundWeight = 13,
                outboundWeight = 12
            )
        )

        repo.updateWeighingDetail(mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<WeighingTicketEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Success(
                    WeighingTicketEntity(
                        driverName = "driver1",
                        licenseNumber = "AC 4321 DE",
                        date = "2024-02-09",
                        inboundWeight = 13,
                        outboundWeight = 12
                    )
                ),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.updateWeighingList(mockRequestWeighingTicket)
        }

        confirmVerified(weighingApi)
    }

    @Test
    fun `update weighing detail error - return error`() = runTest {
        val mockErrorCode = 500
        val mockErrorMessage = "Response.error()"
        val mockRequestWeighingTicket = WeighingTicketEntity(
            driverName = "driver1",
            licenseNumber = "AAA",
            date = "13 Sep 23",
            inboundWeight = 13,
            outboundWeight = 12
        )

        coEvery {
            weighingApi.updateWeighingList(mockRequestWeighingTicket)
        } returns Response.error(
            mockErrorCode,
            "{\"errorMessage\":\"error\",\"code\":\"400\"}".toResponseBody("application/json".toMediaTypeOrNull())
        )

        repo.updateWeighingDetail(mockRequestWeighingTicket).test {
            assertEquals(
                DataResult.Loading<WeighingTicketEntity>(),
                awaitItem()
            )

            assertEquals(
                DataResult.Error<SetWeighingTicketResponseEntity>(mockErrorMessage, mockErrorCode),
                awaitItem()
            )

            awaitComplete()
        }

        coVerify {
            weighingApi.updateWeighingList(mockRequestWeighingTicket)
        }

        confirmVerified(weighingApi)
    }
}
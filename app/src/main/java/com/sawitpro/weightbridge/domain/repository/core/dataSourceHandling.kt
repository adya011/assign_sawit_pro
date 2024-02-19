package com.sawitpro.weightbridge.domain.repository.core

import com.sawitpro.weightbridge.data.core.DataResult
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import java.util.concurrent.TimeoutException

suspend fun <Source: Any, Result: Any> dataSourceHandling(
    networkCall: suspend () -> Response<Source>,
    mapper: DataMapper<Source, Result>
): Flow<DataResult<Result>> = flow {
    emit(DataResult.Loading())
    try {
        val response = withTimeout(10000) { networkCall.invoke() }

        if (response.isSuccessful) {
            val body = response.body()
            val mappedData = mapper.mapDataModel(body)

            if (body != null) {
                emit(DataResult.Success(mappedData))
            }
        } else {
            emit(
                DataResult.Error(
                    response.message(),
                    response.code()
                )
            )
        }
    } catch (e: TimeoutCancellationException) {
        emit(DataResult.Error(e.message.toString(), 0))
    } catch (e: TimeoutException) {
        emit(DataResult.Error(e.message.toString(), 0))
    } catch (e: Exception) {
        emit(DataResult.Error(e.message.toString(), 0))
    }
}
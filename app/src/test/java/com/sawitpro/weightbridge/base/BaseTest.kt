package com.sawitpro.weightbridge.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sawitpro.weightbridge.data.local.dao.WeighingTicketDao
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
@Suppress("UnnecessaryAbstractClass")
abstract class BaseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatchers = UnconfinedTestDispatcher()

    lateinit var weighingRepo: WeighBridgetRepository
    lateinit var dao: WeighingTicketDao

    @Before
    open fun setup() {
        Dispatchers.setMain(testDispatchers)
        weighingRepo = mockk()
        dao = mockk(relaxed = true)
    }

    @After
    open fun cleanup() {
        Dispatchers.resetMain()
    }
}
package com.lizardstudio.connectivitychallenge

import com.lizardstudio.connectivitychallenge.connectivity.ConnectionStatus
import com.lizardstudio.connectivitychallenge.connectivity.ConnectivityObserver
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTests {

    @MockK
    lateinit var connectivityObserver: ConnectivityObserver

    private lateinit var sut: MainViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when observer emits Online then viewModel state is Online`() = runTest {
        // Arrange
        every { connectivityObserver.isConnected } returns flowOf(ConnectionStatus.Online)

        // Act
        sut = MainViewModel(connectivityObserver)
        advanceUntilIdle()

        // Assert
        assertEquals(ConnectionStatus.Online, sut.isConnected.value)
    }

    @Test
    fun `when observer emits Offline then viewModel state is Offline`() = runTest {
        // Arrange
        every { connectivityObserver.isConnected } returns flowOf(ConnectionStatus.Offline)

        // Act
        sut = MainViewModel(connectivityObserver)
        advanceUntilIdle()

        // Assert
        assertEquals(ConnectionStatus.Offline, sut.isConnected.value)
    }

    @Test
    fun `when observer emits AirplaneMode then viewModel state is AirplaneMode`() = runTest {
        // Arrange
        every { connectivityObserver.isConnected } returns flowOf(ConnectionStatus.AirplaneMode)

        // Act
        sut = MainViewModel(connectivityObserver)
        advanceUntilIdle()

        // Assert
        assertEquals(ConnectionStatus.AirplaneMode, sut.isConnected.value)
    }
}
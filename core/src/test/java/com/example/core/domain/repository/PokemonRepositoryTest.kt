package com.example.core.domain.repository

import app.cash.turbine.test
import com.example.core.data.BaseResult
import com.example.core.data.remote.repository.PokemonRepositoryImpl
import com.example.core.data.remote.service.ApiService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class PokemonRepositoryTest {

    private val service = mockk<ApiService>()
    private lateinit var sut: PokemonRepository

    @Before
    fun setup() {
        sut = PokemonRepositoryImpl(service)
    }

    @Test
    fun `test get list pokemon return timeout` () = runBlocking {
        coEvery {
            service.getPokemonList()
        } throws SocketTimeoutException()

        sut.getPokemonList().test {
            when(val result = awaitItem()) {
                is BaseResult.Error -> {
                    assertEquals(
                        "Timeout",
                        result.errorMessage
                    )
                }
                else -> Unit
            }
            awaitComplete()
        }

        coVerify(exactly = 1) {
            service.getPokemonList()
        }

        confirmVerified(service)

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

}
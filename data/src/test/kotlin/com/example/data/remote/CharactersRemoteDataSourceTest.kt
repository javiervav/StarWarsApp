package com.example.data.remote

import com.example.data.api.StarWarsRetrofitApi
import com.example.data.getCharacterResponse
import com.example.data.model.CharacterResponseWrapper
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class CharactersRemoteDataSourceTest {

    private val starWarsRetrofitApi: StarWarsRetrofitApi = mockk()
    private val charactersRemoteDataSource = CharactersRemoteDataSource(
        starWarsRetrofitApi = starWarsRetrofitApi
    )

    @Test
    fun `when api success getCharacters should return list of character response`() = runTest {
        val expectedCharacters = listOf(
            getCharacterResponse("John Doe"),
            getCharacterResponse("Jane Doe"),
        )
        val expectedWrapper = CharacterResponseWrapper(
            totalAmount = 50,
            previousCharactersUrl = "previousCharactersUrl",
            nextCharactersUrl = "nextCharactersUrl",
            characters = expectedCharacters,
        )
        coEvery { starWarsRetrofitApi.getCharacters() } returns Response.success(expectedWrapper)

        val result = charactersRemoteDataSource.getCharacters()

        assertThat(result).isEqualTo(expectedCharacters)
    }

    @Test
    fun `when api fails getCharacters should return empty list`() = runTest {
        coEvery {
            starWarsRetrofitApi.getCharacters()
        } returns Response.error<CharacterResponseWrapper>(400, "".toResponseBody())

        val result = charactersRemoteDataSource.getCharacters()

        assertThat(result).isEmpty()
    }

    @Test
    fun `when response body is null getCharacters should return empty list`() = runTest {
        coEvery { starWarsRetrofitApi.getCharacters() } returns Response.success(null)

        val result = charactersRemoteDataSource.getCharacters()

        assertThat(result).isEmpty()
    }
}
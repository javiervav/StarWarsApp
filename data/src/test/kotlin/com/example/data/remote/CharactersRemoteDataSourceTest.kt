package com.example.data.remote

import com.example.data.api.AkababRetrofitApi
import com.example.data.api.StarWarsRetrofitApi
import com.example.data.getCharacterResponse
import com.example.data.model.CharacterImageResponse
import com.example.data.model.CharacterResponse
import com.example.data.model.CharacterResponseWrapper
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import kotlin.collections.toList

class CharactersRemoteDataSourceTest {

    private val starWarsRetrofitApi: StarWarsRetrofitApi = mockk()
    private val akababRetrofitApi: AkababRetrofitApi = mockk()
    private val charactersRemoteDataSource = CharactersRemoteDataSource(
        starWarsRetrofitApi = starWarsRetrofitApi,
        akababRetrofitApi = akababRetrofitApi,
    )

    @Test
    fun `when apis success then getCharacters should emit correct characters with image`() = runTest {
        val characterResponses: List<CharacterResponse> = listOf(
            getCharacterResponse("John Doe", null),
            getCharacterResponse("Jane Doe", null),
        )
        val characterResponseWrapper: CharacterResponseWrapper = mockk {
            every { characters } returns characterResponses
        }
        coEvery { starWarsRetrofitApi.getCharacters() } returns Response.success(characterResponseWrapper)
        val firstExpectedCharacterImageResponse: CharacterImageResponse = mockk {
            every { imageUrl } returns "imageUrl1"
        }
        val secondExpectedCharacterImageResponse: CharacterImageResponse = mockk {
            every { imageUrl } returns "imageUrl2"
        }
        coEvery { akababRetrofitApi.getCharacterById(any()) }
            .returns(Response.success(firstExpectedCharacterImageResponse))
            .andThen(Response.success(secondExpectedCharacterImageResponse))
        val expectedCharactersSecondEmission: List<CharacterResponse> = listOf(
            getCharacterResponse("John Doe", "imageUrl1"),
            getCharacterResponse("Jane Doe", null),
        )
        val expectedCharactersThirdEmission: List<CharacterResponse> = listOf(
            getCharacterResponse("John Doe", "imageUrl1"),
            getCharacterResponse("Jane Doe", "imageUrl2"),
        )

        val resultFlow = charactersRemoteDataSource.getCharacters()
        // NOTE: val result = resultFlow.toList()
        // This is not valid because it returns the same list in positions 1 and 2. The reason is that I'm emitting the same list reference with updated values
        val result = resultFlow.map { it.toList() }.toList()

        assertThat(result.size).isEqualTo(3)
        assertThat(result[0]).isEqualTo(characterResponses)
        assertThat(result[1]).isEqualTo(expectedCharactersSecondEmission)
        assertThat(result[2]).isEqualTo(expectedCharactersThirdEmission)
        coVerify(exactly = 1) { starWarsRetrofitApi.getCharacters() }
        coVerify(exactly = 1) { akababRetrofitApi.getCharacterById(1) }
        coVerify(exactly = 1) { akababRetrofitApi.getCharacterById(2) }
    }

    @Test
    fun `when api fails getting characters then getCharacters should not emit anything`() = runTest {
        coEvery { starWarsRetrofitApi.getCharacters() } returns Response.error(400, "".toResponseBody())

        val result = charactersRemoteDataSource.getCharacters()

        assertThat(result.firstOrNull()).isNull()
        coVerify(exactly = 1) { starWarsRetrofitApi.getCharacters() }
        coVerify(exactly = 0) { akababRetrofitApi.getCharacterById(any()) }
    }

    @Test
    fun `when api fails getting characters image then getCharacters should emit characters without image`() = runTest {
        val characterResponses: List<CharacterResponse> = listOf(
            getCharacterResponse("John Doe", null),
            getCharacterResponse("Jane Doe", null),
        )
        val characterResponseWrapper: CharacterResponseWrapper = mockk {
            every { characters } returns characterResponses
        }
        coEvery { starWarsRetrofitApi.getCharacters() } returns Response.success(characterResponseWrapper)
        coEvery { akababRetrofitApi.getCharacterById(any()) } returns Response.error(400, "".toResponseBody())

        val resultFlow = charactersRemoteDataSource.getCharacters()
        val result = resultFlow.map { it.toList() }.toList()

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0]).isEqualTo(characterResponses)
        coVerify(exactly = 1) { starWarsRetrofitApi.getCharacters() }
        coVerify(exactly = 1) { akababRetrofitApi.getCharacterById(1) }
        coVerify(exactly = 1) { akababRetrofitApi.getCharacterById(2) }
    }

    @Test
    fun `when getting characters return null then getCharacters should emit empty list of characters`() = runTest {
        coEvery { starWarsRetrofitApi.getCharacters() } returns Response.success(null)

        val resultFlow = charactersRemoteDataSource.getCharacters()
        val result = resultFlow.map { it.toList() }.toList()

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0]).isEmpty()
    }
}
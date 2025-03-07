package ua.aniloom.data.datasorce.network.pagingsorce.anime

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import retrofit2.HttpException
import ua.aniloom.data.datasorce.network.dto.JikanPagingDto
import ua.aniloom.data.datasorce.network.dto.anime.JikanAnimePreviewDto
import ua.aniloom.domain.models.anime.AnimePreview
import java.io.IOException
import java.io.InterruptedIOException

class AnimeScheduleTodayPagingSource(
    private val day: String,
    private val request: suspend (limit: Int, page: Int, day: String) -> ApiResponse<JikanPagingDto<JikanAnimePreviewDto>>
): PagingSource<Int, AnimePreview>() {

    private var lastPage: Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimePreview> {
        val limit = params.loadSize
        val page = params.key ?: lastPage ?: getLastVisiblePage(limit).also { lastPage = it }

        return try {
            val responseData = when (val apiResponse = request(limit, page, day)) {
                is ApiResponse.Success -> apiResponse.data
                is ApiResponse.Failure -> throw IOException(apiResponse.message())
            }

            val itemList: List<AnimePreview> = responseData.data.map { it.mapToDomain() }

            val currentPage = responseData.paging.currentPage
            val nextKey = (currentPage - 1).takeIf { currentPage > 1 }
            val prevKey = (currentPage + 1).takeIf { responseData.paging.hasNextPage }

            LoadResult.Page(
                data = itemList,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: NullPointerException) {
            LoadResult.Error(exception)
        } catch (exception: InterruptedIOException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimePreview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.nextKey?.plus(1) ?: anchorPage?.prevKey?.minus(1)
        }
    }

    private suspend fun getLastVisiblePage(limit: Int) = when (val apiResponse = request(limit, 1, day)) {
        is ApiResponse.Success -> apiResponse.data.paging.lastVisiblePage
        is ApiResponse.Failure -> throw IOException(apiResponse.message())
    }
}
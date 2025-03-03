package ua.aniloom.data.datasorce.network.pagingsorce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import retrofit2.HttpException
import ua.aniloom.data.datasorce.network.dto.DtoMapper
import ua.aniloom.data.datasorce.network.dto.PagingDto
import java.io.IOException
import java.io.InterruptedIOException

private const val BASE_STARTING_OFFSET_INDEX = 0


abstract class BaseMALPagingSource<Domain : Any, DTO : DtoMapper<DTO, Domain>>(
    private val request: suspend (limit: Int, offset: Int) -> ApiResponse<PagingDto<DTO>>
) : PagingSource<Int, Domain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Domain> {
        val offset = params.key ?: BASE_STARTING_OFFSET_INDEX
        val limit = params.loadSize

        return try {

            val responseData = when(
                val apiResponse = request(limit, offset)
            ) {
                is ApiResponse.Success -> apiResponse.data
                is ApiResponse.Failure -> throw IOException(apiResponse.message())
            }

            val itemList: List<Domain> = responseData.data.map { pagingData ->
                pagingData.node.mapToDomain()
            }

            val prevKey = responseData.paging.previous?.let { extractOffset(it) }
            val nextKey = responseData.paging.next?.let { extractOffset(it) }

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

    override fun getRefreshKey(state: PagingState<Int, Domain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun extractOffset(url: String): Int? {
        val regex = "offset=(\\d+)".toRegex()
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1)?.toIntOrNull()
    }
}
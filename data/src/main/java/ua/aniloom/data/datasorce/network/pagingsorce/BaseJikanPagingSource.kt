package ua.aniloom.data.datasorce.network.pagingsorce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import retrofit2.HttpException
import ua.aniloom.data.datasorce.network.dto.DtoMapper
import ua.aniloom.data.datasorce.network.dto.JikanPagingDto
import java.io.IOException
import java.io.InterruptedIOException

private const val BASE_STARTING_PAGE_INDEX = 0


abstract class BaseJikanPagingSource<Domain : Any, DTO : DtoMapper<DTO, Domain>>(
    private val request: suspend (limit: Int, page: Int) -> ApiResponse<JikanPagingDto<DTO>>
) : PagingSource<Int, Domain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Domain> {
        val page = params.key ?: BASE_STARTING_PAGE_INDEX
        val limit = params.loadSize

        return try {

            val responseData = when(
                val apiResponse = request(limit, page)
            ) {
                is ApiResponse.Success -> apiResponse.data
                is ApiResponse.Failure -> throw IOException(apiResponse.message())
            }

            val itemList: List<Domain> = responseData.data.map { it.mapToDomain() }

            val currentPage = responseData.paging.currentPage
            val prevKey = (currentPage - 1).takeIf { currentPage > 1 }
            val nextKey = (currentPage + 1).takeIf { responseData.paging.hasNextPage }

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

}
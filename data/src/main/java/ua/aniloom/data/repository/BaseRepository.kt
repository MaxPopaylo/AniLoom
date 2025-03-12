package ua.aniloom.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.aniloom.data.datasorce.network.dto.DtoMapper
import ua.aniloom.data.datasorce.network.pagingsorce.BaseJikanPagingSource
import ua.aniloom.data.datasorce.network.pagingsorce.BaseMALPagingSource

abstract class BaseRepository {

    protected fun <Domain : Any, DTO : DtoMapper<DTO, Domain>> doPagingRequest(
        pagingSource: BaseMALPagingSource<Domain, DTO>,
        pageSize: Int = 6,
        prefetchDistance: Int = pageSize,
        enablePlaceholders: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE
    ): Flow<PagingData<Domain>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enablePlaceholders,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }

    protected fun <Domain : Any, DTO : DtoMapper<DTO, Domain>> doPagingRequest(
        pagingSource: BaseJikanPagingSource<Domain, DTO>,
        pageSize: Int = 6,
        prefetchDistance: Int = pageSize,
        enablePlaceholders: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE
    ): Flow<PagingData<Domain>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enablePlaceholders,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }
}
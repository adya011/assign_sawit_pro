package com.sawitpro.weightbridge.domain.repository.core

interface DataMapper<RemoteDataModel: Any, DomainModel: Any> {
    fun mapDataModel(dataModel: RemoteDataModel?): DomainModel
}
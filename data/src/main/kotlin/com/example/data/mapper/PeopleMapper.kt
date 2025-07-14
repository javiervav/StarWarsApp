package com.example.data.mapper

import com.example.data.model.PeopleResponse
import com.example.domain.model.People

object PeopleMapper {

    fun List<PeopleResponse>.toDomainModel(): List<People> =
        this.map { it.toDomainModel() }

    fun PeopleResponse.toDomainModel(): People {
        return People(
            name = this.name
        )
    }
}
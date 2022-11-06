package com.example.usejdbctemplate.batch.entity

import org.springframework.jdbc.core.BeanPropertyRowMapper
import java.time.OffsetDateTime

data class TaskEntity(
    var id: Int? = null,
    var name: String? = null,
    var expirationDate: OffsetDateTime? = null,
    var createdAt: OffsetDateTime? = null,
    var updatedAt: OffsetDateTime? = null,
) {
    companion object {
        val mapper = BeanPropertyRowMapper(TaskEntity::class.java)
    }
}
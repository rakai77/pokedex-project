package com.example.core.domain.model

import com.example.core.data.local.entity.UserEntity

data class User(
    val id: Int = 0,
    val username: String,
    val password: String
)

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        username = this.username,
        password = this.password
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        username = this.username,
        password = this.password
    )
}
package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.User

interface UserRepository {
    suspend fun register(user: User): BaseResult<Boolean>
    suspend fun login(username: String, password: String): BaseResult<User>
}
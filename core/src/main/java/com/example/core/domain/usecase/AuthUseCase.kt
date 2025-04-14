package com.example.core.domain.usecase

import com.example.core.data.BaseResult
import com.example.core.domain.model.User
import com.example.core.domain.repository.UserRepository

class AuthUseCase(
    private val userRepository: UserRepository
) {
    suspend fun register(user: User): BaseResult<Boolean> {
        return userRepository.register(user)
    }

    suspend fun login(username: String, password: String): BaseResult<User> {
        return userRepository.login(username, password)
    }
}
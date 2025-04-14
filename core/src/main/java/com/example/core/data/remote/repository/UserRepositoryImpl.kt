package com.example.core.data.remote.repository

import com.example.core.data.BaseResult
import com.example.core.data.local.UserDao
import com.example.core.domain.model.User
import com.example.core.domain.model.toDomain
import com.example.core.domain.model.toEntity
import com.example.core.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun register(
        user: User
    ): BaseResult<Boolean> {
        return try {
            val existing = userDao.getUserByUsername(user.username)
            if (existing != null) {
                BaseResult.Error(errorMessage = "Username already exists")
            } else {
                userDao.insertUser(user.toEntity())
                BaseResult.Success(true)
            }
        } catch (e: Exception) {
            BaseResult.Error(errorMessage = e.localizedMessage)
        }
    }

    override suspend fun login(
        username: String,
        password: String
    ): BaseResult<User> {
        return try {
            val userEntity = userDao.getUserByUsername(username)
            if (userEntity != null && userEntity.password == password) {
                BaseResult.Success(userEntity.toDomain())
            } else {
                BaseResult.Error(errorMessage = "Invalid username or password")
            }
        } catch (e: Exception) {
            BaseResult.Error(errorMessage = e.localizedMessage)
        }
    }

}
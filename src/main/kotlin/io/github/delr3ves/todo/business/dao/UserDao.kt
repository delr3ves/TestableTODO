package io.github.delr3ves.todo.business.dao

import io.github.delr3ves.todo.business.model.User

interface UserDao {

    fun create(user: User): User

    fun get(id: Long): User?

    fun delete(user: User)

    fun findAll(): Collection<User>

}
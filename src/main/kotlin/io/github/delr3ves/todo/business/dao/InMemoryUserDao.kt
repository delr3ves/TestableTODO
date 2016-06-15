package io.github.delr3ves.todo.business.dao

import io.github.delr3ves.todo.business.model.User
import java.util.*

class InMemoryUserDao : UserDao {

    private companion object InMemoryStorage {
        val inMemoryStorage = HashMap<Long, User>()
    }

    override fun create(user: User): User {
        return inMemoryStorage.putIfAbsent(user.id, user)?:user
    }

    override fun get(id: Long): User? {
        return inMemoryStorage.get(id)
    }

    override fun delete(user: User) {
        inMemoryStorage.remove(user.id)
    }

    override fun findAll(): Collection<User> {
        return inMemoryStorage.values
    }
}

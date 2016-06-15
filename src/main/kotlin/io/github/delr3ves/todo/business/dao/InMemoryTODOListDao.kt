package io.github.delr3ves.todo.business.dao

import io.github.delr3ves.todo.business.model.TODOList
import io.github.delr3ves.todo.business.model.User
import java.util.*

class InMemoryTODOListDao(val user: User) : TODOListDao {

    private companion object InMemoryStorage {
        val inMemoryStorage: MutableMap<User, MutableMap<Long, TODOList>> = HashMap()
    }

    override fun create(list: TODOList): TODOList {
        val byUser = inMemoryStorage.getOrPut(user) { HashMap() }
        byUser.put(list.id, list)
        return list
    }

    override fun find(id: Long): TODOList? {
        return inMemoryStorage.get(user).orEmpty().get(id)
    }

    override fun update(list: TODOList): TODOList {
        val byUser = inMemoryStorage.getOrPut(user){HashMap()}
        byUser.put(list.id, list)
        return list
    }

    override fun findAll(): Collection<TODOList> {
        return inMemoryStorage.get(user).orEmpty().values
    }

    override fun delete(list: TODOList) {
        inMemoryStorage.getOrElse(user) { HashMap() }
                .remove(list.id)
    }

}
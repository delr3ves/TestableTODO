package io.github.delr3ves.todo.business.dao

import io.github.delr3ves.todo.business.model.User


class TODOListDaoFactory {

    fun getInstance(user: User): TODOListDao {
        return InMemoryTODOListDao(user)
    }
}

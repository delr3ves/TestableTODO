package io.github.delr3ves.todo.business.dao

import io.github.delr3ves.todo.business.model.TODOList

interface TODOListDao {

    fun create(list: TODOList): TODOList

    fun find(id: Long): TODOList?

    fun update(list: TODOList): TODOList

    fun findAll(): Collection<TODOList>

    fun delete(list: TODOList)

}
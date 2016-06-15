package io.github.delr3ves.todo.business.model

import org.joda.time.DateTime

data class TODOList(
        val id: Long,
        val title:String,
        val tasks: List<Task>?,
        var updatedOn: DateTime?,
        val owner:User?
)
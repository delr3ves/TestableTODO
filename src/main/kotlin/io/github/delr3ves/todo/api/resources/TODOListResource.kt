package io.github.delr3ves.todo.api.resources

import com.codahale.metrics.annotation.Timed
import io.github.delr3ves.todo.business.dao.InMemoryTODOListDao
import io.github.delr3ves.todo.business.dao.InMemoryUserDao
import io.github.delr3ves.todo.business.dao.UserDao
import io.github.delr3ves.todo.business.model.TODOList
import io.github.delr3ves.todo.business.model.Task
import io.github.delr3ves.todo.business.notification.MessageSender
import org.joda.time.DateTime
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/todo/{userId}")
class TODOListResource(val userDao: UserDao =InMemoryUserDao()) {

    @POST
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun createTodoList(@PathParam("userId") userId: Long, list: TODOList): TODOList {
        val user = userDao.get(userId)
                ?: throw NotFoundException("User with id $userId does not found")
        list.copy(owner = user, updatedOn = DateTime())
        InMemoryTODOListDao(user).create(list)

        return list
    }

    @POST
    @Path("/{listId}/tasks")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun addTaskToList(@PathParam("userId") userId: Long, @PathParam("listId") listId: Long, task: Task): Task {
        val user = userDao.get(userId)
                ?: throw NotFoundException("User with id $userId does not found")
        val inMemoryTODOListDao = InMemoryTODOListDao(user)
        val list = inMemoryTODOListDao.find(listId) ?: throw NotFoundException("List with id $listId does not found")
        val tasks = list.tasks?:ArrayList()
        val listToUpdate = list.copy(updatedOn = DateTime.now(), tasks = tasks.plus(task))
        inMemoryTODOListDao.update(listToUpdate)
        MessageSender.send(user, "Task ${task.id} successfully added!")
        return task
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun get(@PathParam("userId") userId: Long, @PathParam("id") listId: Long): TODOList {
        val user = userDao.get(userId)
                ?: throw NotFoundException("User with id $userId does not found")
        return InMemoryTODOListDao(user).find(listId)
                ?: throw NotFoundException("List with id $listId does not found")
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun findAll(@PathParam("userId") userId: Long): Collection<TODOList> {
        val user = userDao.get(userId)
                ?: throw NotFoundException("User with id $userId does not found")
        return InMemoryTODOListDao(user).findAll()
    }
}

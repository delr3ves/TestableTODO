package io.github.delr3ves.todo.api.resources

import com.codahale.metrics.annotation.Timed
import io.github.delr3ves.todo.business.dao.InMemoryTODOListDao
import io.github.delr3ves.todo.business.dao.InMemoryUserDao
import io.github.delr3ves.todo.business.dao.UserDao
import io.github.delr3ves.todo.business.model.TODOList
import io.github.delr3ves.todo.business.model.User
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/users")
class UserResource(val userDao: UserDao = InMemoryUserDao()) {

    @POST
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun createUser(user: User): User {
        return userDao.create(user)
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun findUser(@PathParam("id") userId: Long): User? {
        return userDao.get(userId)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    @Timed
    fun findAll(): Collection<User> {
        return userDao.findAll()
    }
}

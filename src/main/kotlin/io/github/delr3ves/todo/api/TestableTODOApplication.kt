package io.github.delr3ves.todo.api

import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.github.delr3ves.todo.api.config.TestableTODOConfig
import io.github.delr3ves.todo.api.resources.ConfigResource
import io.github.delr3ves.todo.api.resources.TODOListResource
import io.github.delr3ves.todo.api.resources.UserResource
import io.github.delr3ves.todo.business.dao.InMemoryUserDao
import io.github.delr3ves.todo.business.dao.TODOListDaoFactory

class TestableTODOApplication : Application<TestableTODOConfig>() {


    override fun getName(): String {
        return "TestableTODO"
    }

    override fun initialize(bootstrap: Bootstrap<TestableTODOConfig>) {
        bootstrap.objectMapper.registerModule(KotlinModule())
    }

    override fun run(configuration: TestableTODOConfig, environment: Environment) {
        //DAOS
        val userDao = InMemoryUserDao()
        val todoListDaoFactory = TODOListDaoFactory()

        //Resources
        environment.jersey().register(TODOListResource(userDao, todoListDaoFactory))
        environment.jersey().register(UserResource(userDao))
        environment.jersey().register(ConfigResource(configuration))
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic fun main(args: Array<String>) {
            TestableTODOApplication().run(*args)
        }
    }

}

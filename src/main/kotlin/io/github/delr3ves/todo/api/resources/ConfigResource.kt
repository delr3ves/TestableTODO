package io.github.delr3ves.todo.api.resources

import io.github.delr3ves.todo.api.config.TestableTODOConfig
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/config")
class ConfigResource(val config: TestableTODOConfig) {

    @GET
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun getConfiguration(): TestableTODOConfig {
        return config
    }
}


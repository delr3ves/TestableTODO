package io.github.delr3ves.todo.business.notification

import io.github.delr3ves.todo.business.model.User
import org.slf4j.LoggerFactory

object MessageSender {

    fun send(user: User, message: String) {
        LoggerFactory.getLogger(MessageSender::class.java)
                .info("Sending message '$message' to user ${user.id}")
    }
}
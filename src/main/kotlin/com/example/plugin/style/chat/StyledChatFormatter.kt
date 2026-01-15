package com.example.plugin.style.chat

import com.example.plugin.utils.dsl.message
import com.example.plugin.utils.extensions.hasPermission
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import java.awt.Color

class StyledChatFormatter : PlayerChatEvent.Formatter {
    override fun format(
        player: PlayerRef,
        message: String,
    ): Message {
        return message {
            custom(generatePrefix(player))
            text(
                content = "[",
            )
            text(
                content = player.username,
                color = Color.CYAN,
                bold = true,
            )
            text(
                content = "]: ",
            )
            text(
                content = message,
                color = Color.WHITE,
            )
        }
    }

    private fun generatePrefix(player: PlayerRef): Message {
        return message {
            text(
                content = "[",
            )
            if (player.hasPermission("op.add")) {
                text(
                    content = "ADMIN",
                    color = Color.RED,
                    bold = true,
                )
            } else {
                text(
                    content = "PLAYER",
                    color = Color.YELLOW,
                    bold = true,
                )
            }
            text(
                content = "] ",
            )
        }
    }
}

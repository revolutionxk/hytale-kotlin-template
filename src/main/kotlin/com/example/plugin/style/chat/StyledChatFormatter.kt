package com.example.plugin.style.chat

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent
import com.hypixel.hytale.server.core.permissions.PermissionsModule
import com.hypixel.hytale.server.core.universe.PlayerRef
import java.awt.Color

class StyledChatFormatter : PlayerChatEvent.Formatter {
    val permissionHolder: PermissionsModule? = PermissionsModule.get()

    override fun format(
        player: PlayerRef,
        message: String
    ): Message {
        return Message.join(
            generatePrefix(player),
            Message.raw("["),
            Message.raw(player.username).color(Color.CYAN),
            Message.raw("]: "),
            Message.raw(message).color(Color.WHITE)
        )
    }

    private fun generatePrefix(player: PlayerRef): Message {
        if (permissionHolder == null) {
            return Message.raw("[PLAYER] ").color(Color.GREEN)
        }

        if (permissionHolder.hasPermission(player.uuid, "op.add"))
            return Message.raw("[ADMIN] ").color(Color.RED)

        return Message.raw("[PLAYER] ").color(Color.GREEN)
    }
}
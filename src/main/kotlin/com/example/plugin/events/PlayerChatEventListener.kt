package com.example.plugin.events

import com.example.plugin.style.chat.StyledChatFormatter
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent

object PlayerChatEventListener {
    val FORMATTER = StyledChatFormatter()

    fun onPlayerChat(event: PlayerChatEvent) {
        event.formatter = FORMATTER
    }
}
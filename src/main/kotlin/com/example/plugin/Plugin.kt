package com.example.plugin

import com.example.plugin.commands.ExampleCommand
import com.example.plugin.config.ExampleConfig
import com.example.plugin.events.PlayerChatEventListener
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.hypixel.hytale.server.core.util.Config

class Plugin(init: JavaPluginInit) : JavaPlugin(init) {
    private val config: Config<ExampleConfig> = withConfig("ExampleConfig", ExampleConfig.CODEC)

    override fun setup() {
        super.setup()

        registerCommands()
        registerEvents()

        // Create default config if it doesn't exist
        config.save()
    }

    private fun registerCommands() {
        commandRegistry.registerCommand(ExampleCommand())
    }

    private fun registerEvents() {
        eventRegistry.registerGlobal(PlayerChatEvent::class.java, PlayerChatEventListener::onPlayerChat)
    }
}
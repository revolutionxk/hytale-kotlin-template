package com.example.plugin.commands

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.hypixel.hytale.server.core.util.EventTitleUtil

class ExampleCommand : AbstractPlayerCommand("example", "An example command", false) {
    override fun execute(
        context: CommandContext,
        store: Store<EntityStore?>,
        ref: Ref<EntityStore?>,
        player: PlayerRef,
        world: World
    ) {
        EventTitleUtil.showEventTitleToPlayer(
            player,
            Message.raw("Hello world!"),
            Message.raw("This is an example command."),
            true
        )
    }
}
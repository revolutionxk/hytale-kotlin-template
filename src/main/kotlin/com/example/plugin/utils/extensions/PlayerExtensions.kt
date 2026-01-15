package com.example.plugin.utils.extensions

import com.hypixel.hytale.server.core.permissions.PermissionsModule
import com.hypixel.hytale.server.core.universe.PlayerRef

fun PlayerRef.hasPermission(permission: String): Boolean {
    val permissionsModule = PermissionsModule.get()
    return permissionsModule?.hasPermission(this.uuid, permission) ?: false
}

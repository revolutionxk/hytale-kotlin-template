package com.example.plugin.utils.dsl

import com.hypixel.hytale.server.core.Message
import java.awt.Color

@DslMarker
annotation class MessageDsl

@MessageDsl
class MessageScope {
    internal val children = mutableListOf<Message>()

    fun text(
        content: String,
        color: Color? = null,
        hexColor: String? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        monospace: Boolean? = null,
        link: String? = null,
    ) {
        val msg = Message.raw(content).apply {
            color?.let { color(it) }
            hexColor?.let { color(Color.decode(it)) }
            bold?.let { bold(it) }
            italic?.let { italic(it) }
            monospace?.let { monospace(it) }
            link?.let { link(it) }
        }
        children.add(msg)
    }

    fun translation(
        key: String,
        color: Color? = null,
        hexColor: String? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        monospace: Boolean? = null,
        link: String? = null,
        params: (ParamsScope.() -> Unit)? = null,
    ) {
        val msg = Message.translation(key).apply {
            color?.let { color(it) }
            hexColor?.let { color(Color.decode(it)) }
            bold?.let { bold(it) }
            italic?.let { italic(it) }
            monospace?.let { monospace(it) }
            link?.let { link(it) }

            params?.let {
                val paramsScope = ParamsScope(this)
                paramsScope.apply(it)
            }
        }
        children.add(msg)
    }

    fun spacer() {
        children.add(Message.raw("\n"))
    }

    fun nested(
        color: Color? = null,
        hexColor: String? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        monospace: Boolean? = null,
        link: String? = null,
        content: MessageScope.() -> Unit,
    ) {
        val scope = MessageScope()
        scope.content()

        val msg = Message.empty().apply {
            color?.let { color(it) }
            hexColor?.let { color(Color.decode(it)) }
            bold?.let { bold(it) }
            italic?.let { italic(it) }
            monospace?.let { monospace(it) }
            link?.let { link(it) }

            scope.children.forEach { insert(it) }
        }

        children.add(msg)
    }

    fun row(
        separator: String = "",
        content: MessageScope.() -> Unit,
    ) {
        val scope = MessageScope()
        scope.content()

        scope.children.forEachIndexed { index, message ->
            children.add(message)
            if (index < scope.children.size - 1 && separator.isNotEmpty()) {
                children.add(Message.raw(separator))
            }
        }
    }

    fun column(content: MessageScope.() -> Unit) {
        val scope = MessageScope()
        scope.content()

        scope.children.forEachIndexed { index, message ->
            children.add(message)
            if (index < scope.children.size - 1) {
                children.add(Message.raw("\n"))
            }
        }
    }

    fun success(content: String) = text(content, color = Color.GREEN)
    fun error(content: String) = text(content, color = Color.RED)
    fun warning(content: String) = text(content, color = Color.YELLOW)
    fun info(content: String) = text(content, color = Color.CYAN)
    fun link(content: String, url: String, color: Color = Color.BLUE) = text(content, color = color, link = url)

    fun custom(message: Message) {
        children.add(message)
    }
}

@MessageDsl
class ParamsScope(private val message: Message) {
    fun param(key: String, value: String) = message.param(key, value)
    fun param(key: String, value: Boolean) = message.param(key, value)
    fun param(key: String, value: Int) = message.param(key, value)
    fun param(key: String, value: Long) = message.param(key, value)
    fun param(key: String, value: Double) = message.param(key, value)
    fun param(key: String, value: Float) = message.param(key, value)
    fun param(key: String, value: Message) = message.param(key, value)

    infix fun String.to(value: String) = message.param(this, value)
    infix fun String.to(value: Boolean) = message.param(this, value)
    infix fun String.to(value: Int) = message.param(this, value)
    infix fun String.to(value: Long) = message.param(this, value)
    infix fun String.to(value: Double) = message.param(this, value)
    infix fun String.to(value: Float) = message.param(this, value)
    infix fun String.to(value: Message) = message.param(this, value)
}

fun message(block: MessageScope.() -> Unit): Message {
    val scope = MessageScope()
    scope.block()

    return if (scope.children.isEmpty()) {
        Message.empty()
    } else {
        Message.empty().apply {
            scope.children.forEach { insert(it) }
        }
    }
}

fun String.asMessage() = Message.raw(this)

fun String.asTranslation() = Message.translation(this)

fun String.colored(color: Color) = Message.raw(this).color(color)

fun String.colored(hexColor: String) = Message.raw(this).color(Color.decode(hexColor))

fun List<Message>.join(separator: String = "") = message {
    this@join.forEachIndexed { index, msg ->
        custom(msg)
        if (index < this@join.size - 1 && separator.isNotEmpty()) {
            text(separator)
        }
    }
}

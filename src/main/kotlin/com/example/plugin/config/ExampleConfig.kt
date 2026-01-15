package com.example.plugin.config

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec

class ExampleConfig {
    companion object {
        val CODEC = BuilderCodec
            .builder(ExampleConfig::class.java, ::ExampleConfig)
            .append(
                KeyedCodec("ExampleText", Codec.STRING),
                { config, value -> config.exampleText = value },
                { config -> config.exampleText })
            .add()
            .append(
                KeyedCodec("ExampleNumber", Codec.INTEGER),
                { config, value -> config.exampleNumber = value },
                { config -> config.exampleNumber })
            .add()
            .append(
                KeyedCodec("ExampleFlag", Codec.BOOLEAN),
                { config, value -> config.exampleFlag = value },
                { config -> config.exampleFlag })
            .add()
            .build()
    }

    private var exampleText: String = "Default Text"
    private var exampleNumber: Int = 42
    private var exampleFlag: Boolean = true
}
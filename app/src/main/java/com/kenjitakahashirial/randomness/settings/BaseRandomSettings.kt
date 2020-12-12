package com.kenjitakahashirial.randomness.settings

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
abstract class BaseRandomSettings

val baseRandomSettingsSerializerModule = SerializersModule {
    polymorphic(BaseRandomSettings::class) {
        subclass(RandomIntegerSettings::class)
        subclass(FlipCoinSettings::class)
        subclass(RollDiceSettings::class)
        subclass(RandomDecimalSettings::class)
        subclass(RandomWordSettings::class)
        subclass(RandomColorSettings::class)
        subclass(MagicEightBallSettings::class)
    }
}

val baseRandomSettingsSerializerFormat = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    serializersModule = baseRandomSettingsSerializerModule
}
package tech.logex.bungeecross

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object PluginConfig : AutoSavePluginConfig("config") {
    val listenBot by value<Long>(0)
    val listenGroup by value<Long>(0)

    val redisAddress by value<String>("localhost")
    val redisPort by value<Int>(6379)
    val redisDB by value<Int>(0)
    val redisPass by value<String>("")
}
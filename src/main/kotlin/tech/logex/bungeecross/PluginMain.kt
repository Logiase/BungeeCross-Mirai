package tech.logex.bungeecross

import com.google.auto.service.AutoService
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.disable
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.subscribeGroupMessages

@AutoService(JvmPlugin::class)
object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = Constants.PLUGIN_ID,
        version = Constants.PLUGIN_VERSION,
    ) {
        name("BungeeCross-Mirai")
        author("Logiase")
    }
) {

    private val redisClient: RedisClient = RedisClient(this)

    override fun onEnable() {
        PluginConfig.reload()

        if (PluginConfig.listenBot == 0L || PluginConfig.listenGroup == 0L) {
            this.disable()
        }

        redisClient.connect(
            PluginConfig.redisAddress,
            PluginConfig.redisPort,
            PluginConfig.redisDB,
            PluginConfig.redisPass
        )

        this.launch {
            for (msg in redisClient.channel) {
                Bot.getInstanceOrNull(PluginConfig.listenBot)?.getGroup(PluginConfig.listenGroup)
                    ?.sendMessage("[${msg.sender}]: ${msg.message}")
            }
        }

        subscribeGroupMessages {
            sentFrom(PluginConfig.listenGroup) and startsWith("#") reply {
                redisClient.pushMessage("${sender.id}||${message.contentToString()}")
                Unit
            }
        }
    }

    override fun onDisable() {
        redisClient.disconnect()
    }
}
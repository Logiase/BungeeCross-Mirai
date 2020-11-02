package tech.logex.bungeecross

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand

object Commands {

    object Status : SimpleCommand(
        PluginMain,
        "bcstatus",
        description = "查看BungeeCross状态"
    ) {

        @Handler
        suspend fun CommandSender.handle() {
            sendMessage(
                PluginMain.redisClient.status()
            )
        }

    }

}
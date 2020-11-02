package tech.logex.bungeecross

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import redis.clients.jedis.Jedis

class RedisClient(
    private val scope: CoroutineScope
) {

    init {
        startLoop()
    }

    private var client: Jedis? = null
    private val _channel: Channel<Message> = Channel<Message>()
    private var _isInitiated: Boolean = false

    private fun startLoop() {
        scope.launch {
            while (startSubscribed && isInitiated) {
                val t = client!!.brpop("mc")
                if (t.size != 2) {
                    PluginMain.logger.warning("receive message size invalid")
                    continue
                }

                val temp = t[1].split("||")
                if (temp.size != 2) {
                    PluginMain.logger.warning("receive message format invalid: ${t[1]}")
                    continue
                }

                val message = Message(temp[0], temp[1])
                _channel.send(message)
            }
        }
    }

    var startSubscribed: Boolean = false

    val isInitiated: Boolean
        get() = _isInitiated

    val channel: Channel<Message>
        get() = _channel

    fun connect(address: String, port: Int, db: Int, password: String) {
        client = Jedis(
            address,
            port,
            false,
        )
        client!!.auth(password)
        client!!.select(db)
        _isInitiated = true
    }

    fun disconnect() {
        client?.disconnect()
        client = null
        _isInitiated = false
    }

    fun pushMessage(msg: String) {
        if (!isInitiated) {
            return
        }
        client!!.lpush("qq", msg)
    }

    data class Message(
        val sender: String,
        val message: String,
    )
}
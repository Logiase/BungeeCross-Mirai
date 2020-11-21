# BungeeCross-Mirai

Built for [BungeeCross](https://github.com/keuin/BungeeCross), a BungeeCord plugin aim to chat easily.

BungeeCross-Mirai is a [Mirai-Console](https://github.com/mamoe/mirai-console) plugin for [BungeeCross](https://github.com/keuin/BungeeCross).

## How to use

Just put it into `plugins` folder.

And edit the config file in `config/BungeeCross-Mirai/config.yml`:

``` yaml
# The bot you want to use
listenBot: 2662969831
# The group you want to listen and serve
listenGroup: 749597899
# redis address
redisAddress: redis
# redis port
redisPort: 6379
# redis db
redisDB: 0
# redis password
# if there is a `#` in password, do not forget to wrap it with ''
redisPass: abcd
```

Optionally, you can give permission `tech.logex.bungee-cross:*` to all user, so they can use `/bcstatus` to check the status of Bot and redis.

## ScreenShot

~~No ScreenShots.~~

## Thanks to

Library used:

- The Bot Framework
  - [Mirai](https://github.com/mamoe/mirai)
  - [Mirai-Console](https://github.com/mamoe/mirai-console)
- Redis Client:
  - [jedis](https://github.com/redis/jedis)
- And other indirectly use.

Authors:

- @Logiase
- @keuin
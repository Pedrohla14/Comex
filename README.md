# COMEX - CURSO ALURA #
O projeto Comex é um sistema de e-commerce marketplace. 
Seu objetivo é permitir que a sua base de clientes tenha acesso a vários vendedores e, assim,
consolidar-se como o shopping eletrônico mais popular da internet.

## REDIS

https://collabnix.com/how-to-setup-and-run-redis-in-a-docker-container/
https://www.digitalocean.com/community/tutorials/spring-boot-redis-cache

# Redis Config

spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379

``` java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

docker run --rm -d --name redis -p 6379:6379 redis:6.2

docker exec -it redis bash

buscar chaves:

	keys *

acessar o cache pela chave:

	get "RelatorioPedidoCategoria::SimpleKey []"
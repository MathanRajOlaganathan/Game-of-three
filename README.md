# **Game of Three**
 A game with two independent units – the players –
communicating with each other using an API.



## Tech Stack

1. Java - 15

2. Maven - 3.8.x

3. Spring Boot - 2.4.X

## Requirements

1. When a player starts, it incepts a random (whole) number and sends it to the second
   player as an approach of starting the game. The receiving player can now always choose
   between adding one of {1, 0, 1} to get to a number that is divisible by 3.
2. Divide it by three. The resulting whole number is then sent back to the original sender.
   The same rules are applied until one player reaches the number 1(after the division).
3. Both players should be able to play automatically without user input. The
   type of input (manual, automatic) should be optionally adjustable by the player.


## Steps to Setup

**1. Clone the application**

```bash
https://github.com/MathanRajOlaganathan/Game-of-three.git
```

**2. Build and run the app using maven**

```bash
cd banking-system
mvn package
java -jar target/game-of-three.jar
java -jar target/game-of-three.jar  --spring.config.name=application2
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```
Alternatively, you can run the built  jar which is  placed under FoodDelivery-Spicy directory -

```bash
java -jar target/game-of-three.jar
java -jar target/game-of-three.jar  --spring.config.name=application2
```


The server will start at <http://localhost:8080>.

The server will start at <http://localhost:8082>.

The swagger will start at <http://localhost:8080/swagger-ui/>.

**Application Jar**  [game-of-three.jar](game-of-three.jar)
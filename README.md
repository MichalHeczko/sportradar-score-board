# Football World Cup Score Board

## Requirements

Java 25. Maven is not required — the project includes the Maven wrapper.

## Build and test

```bash
./mvnw verify
```

## Usage

```java
ScoreBoard board = new ScoreBoard();
 
board.startMatch("Mexico", "Canada");
board.updateScore("Mexico", "Canada", 0, 5);
board.finishMatch("Mexico", "Canada");
 
List<Match> summary = board.getSummary();
```
 
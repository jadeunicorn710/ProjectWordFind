# Test Plan

**Author**: Team 45

Version 2: Updated final tests, adding instructions on how to run tests

## 1 Testing Strategy

### 1.1 Overall strategy

We will be using a combination of unit testing, integration testing, static analysis, and inspection.

Unit tests will be used to cover all non-private methods of all classes, trying to test the relevant boundaries of all partitions. All unit tests will mock all dependencies.

Integration testing will be used to test functionality from a user's point of view. That is, they will cover the application's behavior as defined by its use cases.

Static analysis will be used for testing for non-functional errors and general poor coding practices.

In addition to automated testing, we will also take advantage of standard alpha testing.

All tests must be consistent. That is, if there is a failure condition, the tests must always fail.

### 1.2 Test Selection

We will mainly be using the category partition method for unit testing. This will aid in obtaining large code coverage.

For our integration tests, we will be using the finite state machine method. Specifically, we will create a finite state machine that whose states are user visible application states, such as application screens and the general content of those screens. For example, one state would be a game with a board size of three and another would be a game with a board size of five. In this example, transitions would be actions that the user can perform from the current screen.

For static analysis, we will use the standard Android lint tests created by Google. We will further use Checkstyle's default Google Style Guide rules.

Alpha testing by its very nature has no specific method for test selection.

### 1.3 Adequacy Criterion

For unit test coverage, we will rely on simple code coverage criteria, aiming for at least 80% code coverage.

For integration tests, we will define new tests when there is either an obvious functionality change or when a bug is discovered. In the latter case, an integration test that reproduces the bug will be created.

There are no adequacy criteria for both static analysis and alpha testing.

### 1.4 Bug Tracking

Due to constraints imposed on us, such as a lack of Jira, we will be using GitHub's issue tracker.

### 1.5 Technology

For unit testing, we will be using JUnit and Mockito.

For integration testing, we will be using Espresso.

For static analysis, we will be using the standard Android linter and Checkstyle.

For inspection, we will use GitHub's Pull Request functionality.

All tests can be run by running `./gradlew build && ./gradlew connectedAndroidTest`.

## 2 Test Cases

| Purpose                                                      | Steps                                                                                           | Expected Result                                                                 | Actual Result                                                                                | P/F info | Additional Information                      |
|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|----------|---------------------------------------------|
| 1. Game starts correctly with default values                 | Open the menu and press the start game button                                                   | The game starts and UI reflects default settings                                | The game starts and UI reflects default settings                                             | Pass     | Manual test                           |
| 2. Game settings opens correctly with default values         | Open the menu and press the settings button                                                     | The settings UI shows with correct default values                               | The settings UI shows with correct default values                                            | Pass     | Manual test                           |
| 3a. Game settings only lets you input valid game times (invalid) | Go to settings, enter a time other than 1-5 and click save   | Error text showed up saying "Input integers between 1 and 5 minutes" | Error text showed up saying "Input integers between 1 and 5 minutes" | Pass     | Integration test       |
| 3b. Game settings only lets you input valid game times (valid) | Go to settings, enter a time between 1-5 and click save      | The time limit changed to input value from default value 3   | The time limit changed to input value from default value 3   | Pass     | Integration test       |
| 4a. Game settings only lets you input valid board sizes (invalid) | Go to settings, enter a board size other than 4-8 and click save | Error text showed up saying "Input integers between 4 and 8" | Error text showed up saying "Input integers between 4 and 8" | Pass     | Integration test       |
| 4b. Game settings only lets you input valid board sizes (valid) | Go to settings, enter a board size between 4-8 and click save | The board size changed to input value from default value 4   | The board size changed to input value from default value 4   | Pass     | Integration test       |
| 5a. Game settings only lets you input valid letter weights (invalid) | Go to settings, enter a letter weight other than 1-5 and click save | Error text showed up saying "Input integers between 1 and 5 for weight" | Error text showed up saying "Input integers between 1 and 5 for weight" | Pass     | Integration test       |
| 5b. Game settings only lets you input valid letter weights (valid) | Go to settings, enter a letter weight between 1-5 and click save | The letter weight associated with the input letter saved     | The letter weight associated with the input letter saved     | Pass     | Integration test       |
| 6a. Game settings only lets you input valid letters (invalid) | Go to settings, enter a letter (first input of Letter Weight) other than A-Z and click save | Error text showed up saying "Input letters between A and Z for letter" | Error text showed up saying "Input letters between A and Z for letter" | Pass     | Integration test       |
| 6b. Game settings only lets you input valid letters (valid)  | Go to settings, enter a letter (first input of Letter Weight) between A-Z and click save | The letter weight associated with the input letter saved     | The letter weight associated with the input letter saved     | Pass     | Integration test       |
| 7. All letters are selectable after resetting board          | Start a game, select some letters, reset board               | All letters are selectable                                   | All letters are selectable                                   | Pass     | Manual test            |
| 8a. Letter in board is displayed correctly ('Q')             | Call Letter.getLetter('Q') and Letter.displayAs()            | 'Qu' displayed instead of 'Q'                                | 'Qu' displayed instead of 'Q'                                | Pass     | Unit Test              |
| 8b. Letter in board is displayed correctly (letters non-'Q') | Call Letter.getLetter(letters non-'Q') and Letter.displayAs() | Letter displayed same as input                               | letter displayed same as input                               | Pass     | Unit Test              |
| 8c. Letter in board is displayed correctly (other than 'A'-'Z') | Call Letter.getLetter(other than 'A'-'Z') and Letter.displayAs() | RuntimeException saying "Letter should be between A and Z"   | RuntimeException saying "Letter should be between A and Z"   | Pass     | Unit Test              |
| 9. Word usage count is properly tracked                      | Call Board.enterWord() with a valid word                     | WordStatistics.incrementCount() is called once               | WordStatistics.incrementCount() is called once               | Pass     | Unit test              |
| 10a. Letter worth is determined correctly ('Q')              | Call Letter.getLetter('Q') and Letter.getWorth()             | Letter worths 2 points                                       | Letter worths 2 points                                       | Pass     | Unit Test              |
| 10b. Letter worth is determined correctly (non-'Q')          | Call Letter.getLetter(non-'Q') and Letter.getWorth()         | Letter worths 1 point                                        | Letter worths 1 point                                        | Pass     | Unit Test              |
| 11. Number of words in a game is properly tracked            | Call Board.enterWord() with a valid word                     | GameStatistics.incrementNumberOfWords() is called once       | GameStatistics.incrementNumberOfWords() is called once       | Pass     | Unit test              |
| 12a. Vowel and consonant are distinguished correctly (Vowel) | Call Letter.getLetter('Vowel') and Letter.isVowel()          | Returned true                                                | Returned true                                                | Pass     | Unit Test              |
| 12b. Vowel and consonant are distinguished correctly (Consonant) | Call Letter.getLetter('Consonant') and Letter.isVowel()      | Returned false                                               | Returned false                                               | Pass     | Unit Test              |
| 13. Score is properly tracked for statistics                 | Call Board.enterWord() with a valid word                     | GameStatistics.updateScore() is called with the new score    | GameStatistics.updateScore() is called with the new score    | Pass     | Unit test              |
| 14. Board prevents duplicate words                           | Call Board.enterWord() with a used word from a previous Board | Duplicate word not allowed                                   | Duplicate word not allowed                                   | Pass     | Manual test            |
| 15. Times reset in a game is properly tracked                | Call Game.resetBoard()                                       | GameStatistics.incrementTimesReset() is called once          | GameStatistics.incrementTimesReset() is called once          | Pass     | Unit test              |
| 16. The board has the correct ratio of vowels to consonants  | Start a game                                                 | Vowels make up 20% of the board while consonant make up 80%  | Vowels make up 20% of the board while consonant make up 80%  | Pass     | Manual test            |
| 17. List of game statistics appears properly                 | Given mocked GameStatistics, open menu and navigate to game statistics | The correct game statistics appear                           | The correct game statistics appear                           | Pass     | Manual test            |
| 18. Detailed game statistics appears properly                | Given mocked GameStatistics, open game statistics, select one | The correct detailed game statistics appear                  | The correct detailed game statistics appear                  | Pass     | Manual test            |
| 19. List of word statistics appears properly                 | Given mocked WordStatistics, open menu and navigate to word statistics | The correct word statistics appear                           | The correct word statistics appear                           | Pass     | Manual test            |
| 20a. Already used letters cannot be reused                   | Given a Board with fixed data, enter a word, enter an overlapping word | An exception should be thrown                                | An exception should be thrown                                | Pass     | Manual test            |
| 20b. Validate that letter cannot be used twice               | Go to game, try select letter twice                          | Fail to select selected letter                               | Fail to select selected letter                               | Pass     | Manual test            |
| 21. Giving a negative score to GameStatistics works          | Call GameStatistics.updateScore() with a score of -1         | An exception should be thrown                                | An exception should be thrown                                | Pass     | Manual test            |
| 22. Giving a score of 0 to GameStatistics works              | Call GameStatistics.updateScore() with a score of 0          | Score is shown as 0 when you call getStatistics()            | Score is shown as 0 when you call getStatistics()            | Pass     | Manual test            |
| 23. Giving a positive score to GameStatistics works          | Call GameStatistics.updateScore() with a score of 1          | Score is shown as 1 when you call getStatistics()            | Score is shown as 1 when you call getStatistics()            | Pass     | Manual test            |
| 24. Word count is properly incremented in word statistics    | Call WordStatistics.incrementCount()                         | Count is incremented when you call getStatistics()           | Count is incremented when you call getStatistics()           | Pass     | Manual test            |
| 25. Word count is properly incremented in game statistics    | Call GameStatistics.incrementNumberOfWords()                 | Count is incremented when you call getStatistics()           | Count is incremented when you call getStatistics()           | Pass     | Manual test            |
| 26. Times reset is properly incremented in game statistics   | Call GameStatistics.incrementTimesReset()                    | Count is incremented when you call getStatistics()           | Count is incremented when you call getStatistics()           | Pass     | Manual test            |
| 27. Word statistics are obtained in desired order            | Open word statistics with a mocked list of WordStatistics (including ones with equal frequency) | Word statistics are shown in descending order of frequency then earliest played | Word statistics are shown in descending order of frequency then earliest played | Pass     | Manual test            |
| 28. Game statistics are obtained in desired order            | Open game statistics with a mocked list of GameStatistics (including ones with equal score) | Game statistics are shown in descending order of score then earliest played | Game statistics are shown in descending order of score then earliest played | Pass     | Manual test            |
| 29. Display Timer                                            | Go to game, check initial total time and timer counting down | Timer works as expected                                      | Timer works as expected                                      | Pass     | Manual test            |
| 30. Display Point                                            | Go to game, check point displayed correctly                  | Display points work as expected                              | Display points work as expected                              | Pass     | Manual test            |
| 31. Check Qu letter point                                    | Go to game, select letter has Qu                             | Qu gives 2 letter point                                      | Qu gives 2 letter point                                      | Pass     | Manual test            |
| 32. Test reset board                                         | Go to game, reset board                                      | Board reset, points -5                                       | Board reset, points -5                                       | Pass     | Manual test            |
| 33. Early Exit                                               | From game, press early exit                                  | Early exit game, land main page                              | Early exit game, land main page                              | Pass     | Manual test            |
| 34. Validate a word                                          | Select a single letter, and try submit as a word             | UI stops submitting single letter word                       | UI stops submitting single letter word                       | Pass     | Manual test            |
|                                                              |                                                              |                                                              |                                                              |          |                        |



Note: Static analysis and alpha testing are not included, as they are either defined by an external source or do not have rigid definitions.

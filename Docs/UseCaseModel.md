# Use Case Model

**Author**: \<6300Spring20Team45\>

## 1 Use Case Diagram

![UseCase](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team45/blob/master/GroupProject/Docs/Diagrams/UseCase.png "UseCase")

## 2 Use Case Descriptions

*Exit Game:*
- *Requirements: The system should allow the player to exit this game.*
- *Pre-conditions: The player chooses to start this game.*
- *Post-conditions: The system displays the score in this game and goes back to main menu.*
- *Scenarios:*
> Normal
1. The player clicks exit during the game;
2. The system calculates and displays the score in this game.
3. Go back to main menu.

*Enter Word:*
- *Requirements: The system should allow the player to enter the word.*
- *Pre-conditions: The player chooses to start this game.*
- *Post-conditions: The system records this word in this game and does not allow the individual letters in the same spots of board to be used twice in this game.* 
- *Scenarios:* 
> Normal
1. The player touches the screen and slides through a word.
2. The system checks whether the word follows the game’s rule.
3. The system records the successful word and will not allow the individual letters in the same spots of board to be used twice in this game. 
> Exceptional
1. If the player only touches one letter or touches the same spots twice, no information is recorded into the system.

*Reset Board:*
- *Requirements: The system should allow the player to reset the board.*
- *Pre-conditions: The player chooses to start this game.*
- *Post-conditions: The system creates a new board in this game and increases the number of reset.*
- *Scenarios:*
> Normal
1. The player chooses to reset the board.
2. The system creates a new board according to the previous settings.
3. The system increases the number of board reset and deducts 5 points for each reset.

> Exceptional:
1. If letter 'Q' was randomly selected in board, it should be replaced with 'Qu'.

*Set Game Duration:*
- *Requirements: The system should allow the player to set game duration.*
- *Pre-conditions: The player chooses to adjust game settings.*
- *Post-conditions: The system records the time duration and the timer will count down these minutes in the game that the player starts next.*
- *Scenarios:*
> Normal
1. The system shows the default 3 minutes.
2. The player inputs a number between 1 minute and 5 minutes.
3. The system records this number and in the following game, the timer will count down time from the set time.

*Set Board Size:*
- *Requirements: The system should allow the player to set board size.*
- *Pre-conditions: The player chooses to adjust game settings.*
- *Post-conditions: The system records the board size and the board in this set size will be given in the game that the player starts next.*
- *Scenarios:*
> Normal
1. The system shows the default 4.
2. The player inputs a number between 4 and 8.
3. The system records this number and in the following game, the board in size of number*number will be displayed in the following game.

*Set Letter Weights:*
- *Requirements: The system should allow the player to set letter weights.*
- *Pre-conditions: The player chooses to adjust game settings.*
- *Post-conditions: The system records the letter weights. The system randomly chooses the letters according to their weights and 80/20 vowels/consonants ratio. Then these letters will be shown on the board in the game that the player starts.* 
- *Scenarios:*
> Normal
1. The system shows the default weight 1.
2. The player inputs weights for selected letters between 1 and 5.
3. The system records these weights and in the following game. Then the weighted letters will be chosen randomly based on 80/20 vowels/consonants ratio and be displayed on the board.

*Display The Word and Frequency Across All games:*
- *Requirements: The system should allow the player to view the word and its frequency across all games.*
- *Pre-conditions: The player chooses to view word statistics.*
- *Post-conditions: The system displays the word and the frequency of this word across all games.*
- *Scenarios:*
> Normal
1. In the View Statistics part, the player chooses to view word statistics.
2. The system displays a list of words used starting from the most frequently played, including the word and the number of times this word which has been played across all games. 
> Exceptional
1. If the player has not completed at least one game, nothing is shown.
2. if there are multiple words with same frequency, system needs to determine how to show them in the list, e.g., alphabetically, or based on time order, etc.

*Display Score, Number of Reset and Number of Words:*
- *Requirements: The system should allow the player to view a list of score, number of reset and number of words.*
- *Pre-conditions: The player chooses to view game score statistics.*
- *Post-conditions: The system displays a list of scores, number of reset and number of words.*
- *Scenarios:*
> Normal
1. In the View Statistics part, the player chooses to view score statistics.
2. The system displays a list of scores in descending order by final game score, including the final game score, the number of times the board was reset and the number of words entered in the game. 
> Exceptional
1. If the player has not completed at least one game, nothing is shown.
2. if there are multiple same final game scores, system needs to determine how to show them in the list, e.g., based on time played.

*Display Board Size, Game Duration and Highest Scoring Word:*
- *Requirements: The system should allow the player to view board size, game duration and highest scoring word in the selected game.*
- *Pre-conditions: The player chooses to view game score statistics and clicks on the score.*
- *Post-conditions: The system displays board size, game duration and highest scoring word in the selected game.*
- *Scenarios:*
> Normal
1. In the View Statistics part, the player chooses to view score statistics.
2. In the list of scores, one score is chosen.
3. The system displays the settings for that game’s board size, number of minutes, and the highest scoring word played in the game. When multiple words score an equal number of points, the first played will be displayed.
> Exceptional
1. If the player has not completed at least one game, nothing is shown.


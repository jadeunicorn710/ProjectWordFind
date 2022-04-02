package edu.gatech.seclass.wordfind6300;

import android.os.CountDownTimer;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Consumer;
import edu.gatech.seclass.wordfind6300.storage.ApplicationDatabase;
import edu.gatech.seclass.wordfind6300.storage.GameStatistics;
import edu.gatech.seclass.wordfind6300.storage.GameStatisticsDao;
import edu.gatech.seclass.wordfind6300.storage.WordStatisticsDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

public class Game {

  private static final int ONE_SECOND_IN_MILLISECONDS = 1000;
  private static final int RESET_PENALTY = 5;

  private static Game gameInstance;

  private final GameSettings gameSettings;
  private final GameStatistics gameStatistics;
  private final GameStatisticsDao gameStatisticsDao;
  private final WordStatisticsDao wordStatisticsDao;

  private Letter[][] boardLetters;
  private boolean[][] boardUsages;
  private CountDownTimer countDownTimer;
  private EnumeratedDistribution<Letter> enumeratedLetterDistribution;
  private EnumeratedDistribution<Letter> enumeratedVowelDistribution;
  private int score = 0;
  private int scoreOfHighestScoringWord = 0;

  /**
   * Get a singleton instead of {@code Game}.
   *
   * @return Singleton instead of {@code Game}
   */
  public static Game getInstance(ApplicationDatabase database, GameSettings gameSettings) {
    if (gameInstance == null) {
      gameInstance = new Game(database, gameSettings);
    }
    return gameInstance;
  }

  @VisibleForTesting
  Game(ApplicationDatabase database, GameSettings gameSettings) {
    this.gameSettings = gameSettings;
    gameStatistics = new GameStatistics(gameSettings.getBoardSize(),
        gameSettings.getGameDuration());
    gameStatisticsDao = database.getGameStatisticsDao();
    wordStatisticsDao = database.getWordStatisticsDao();
    createdEnumeratedLetterDistributions();
    createBoard();
  }

  /**
   * Play the word specified using the indices of its letters in the order they appear.
   *
   * @param indices The indices of the letters of the words in order.
   * @throws IllegalMoveException Thrown if {@param indices} contains an already used index or if
   * {@param indices} contains less than two characters.
   */
  public void enterWord(List<Pair<Integer, Integer>> indices) throws IllegalMoveException {
    int previousScore = score;
    StringBuilder wordBuilder = new StringBuilder(indices.size());
    for (Pair<Integer, Integer> index : indices) {
      if (indices.size() <= 1 || boardUsages[index.getFirst()][index.getSecond()]) {
        throw new IllegalMoveException();
      }
      boardUsages[index.getFirst()][index.getSecond()] = true;
      Letter letter = boardLetters[index.getFirst()][index.getSecond()];
      score += letter.getWorth();
      wordBuilder.append(letter.displayAs());
    }
    String word = wordBuilder.toString();
    int wordScore = score - previousScore;
    if (wordScore > scoreOfHighestScoringWord) {
      gameStatistics.setHighestScoringWord(word);
      scoreOfHighestScoringWord = wordScore;
    }
    gameStatistics.incrementNumberOfWords();
    wordStatisticsDao.incrementWordFrequency(word);
  }

  /**
   * Get a matrix containing all {@code Letter}s on the board.
   *
   * @return Matrix containing all {@code Letter}s found on the board.
   */
  public Letter[][] getBoardLetters() {
    return boardLetters;
  }

  /**
   * Get the current score.
   *
   * @return The current score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Exit the game, saving any information that needs to be saved to the correct statistics.
   */
  public void exitGame() {
    gameStatistics.setScore(getScore());
    gameStatisticsDao.save(gameStatistics);
    getCountDownTimer().cancel();
    gameInstance = null;
  }

  /**
   * Reset the game board and reduce the score accordingly.
   */
  public void resetBoard() {
    createBoard();
    score -= RESET_PENALTY;
    gameStatistics.incrementTimesReset();
  }

  /**
   * Start the game and run each runnable as needed.
   *
   * @param onTick Consumer to run every second, consuming time remaining in milliseconds
   * @param onFinishCallback Runnable to run once the game ends
   */
  public void startGame(Consumer<Long> onTick, Runnable onFinishCallback) {
    long gameDurationInMillis = TimeUnit.MINUTES.toMillis(gameSettings.getGameDuration());
    countDownTimer = new CountDownTimer(gameDurationInMillis, ONE_SECOND_IN_MILLISECONDS) {
      public void onTick(long millisUntilFinished) {
        onTick.accept(millisUntilFinished);
      }

      public void onFinish() {
        onFinishCallback.run();
      }
    }.start();
  }

  private void createBoard() {
    int boardSize = gameSettings.getBoardSize();
    boardLetters = new Letter[boardSize][boardSize];
    boardUsages = new boolean[boardSize][boardSize];
    List<Letter> letters = getShuffledLetters(boardSize * boardSize);
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        boardLetters[i][j] = letters.get(i * boardSize + j);
      }
    }
  }

  private void createdEnumeratedLetterDistributions() {
    List<Pair<Letter, Double>> consonantWeights = new ArrayList<>();
    List<Pair<Letter, Double>> vowelWeights = new ArrayList<>();
    for (Letter letter : Letter.getLetters()) {
      if (letter.isVowel()) {
        vowelWeights.add(new Pair<>(letter,
            (double) gameSettings.getLetterWeight(letter.displayAs())));
      } else {
        consonantWeights.add(new Pair<>(letter,
            (double) gameSettings.getLetterWeight(letter.displayAs())));
      }
    }
    enumeratedLetterDistribution = new EnumeratedDistribution<>(consonantWeights);
    enumeratedVowelDistribution = new EnumeratedDistribution<>(vowelWeights);
  }

  @VisibleForTesting
  CountDownTimer getCountDownTimer() {
    return countDownTimer;
  }

  private List<Letter> getShuffledLetters(int size) {
    List<Letter> letters = new ArrayList<>();
    int numberOfVowels = (int) Math.ceil(size / 5);
    for (int i = 0; i < size; i++) {
      if (i < numberOfVowels) {
        letters.add(enumeratedVowelDistribution.sample());
      } else {
        letters.add(enumeratedLetterDistribution.sample());
      }
    }
    Collections.shuffle(letters);
    return letters;
  }

}

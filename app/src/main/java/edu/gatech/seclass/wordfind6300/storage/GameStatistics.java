package edu.gatech.seclass.wordfind6300.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import edu.gatech.seclass.wordfind6300.Statistics;
import java.util.Locale;

@Entity
public class GameStatistics implements Statistics {

  @PrimaryKey(autoGenerate = true)
  private int gameId;

  private final int boardSize;
  private final int duration;
  private final long whenFinished;

  @Nullable
  private String highestScoringWord;
  private int numberOfWords;
  private int score;
  private int timesReset;

  @Ignore
  public GameStatistics(int boardSize, int duration) {
    this(boardSize, duration, null, 0, 0, 0, System.currentTimeMillis());
  }

  GameStatistics(int boardSize, int duration, @Nullable String highestScoringWord,
      int numberOfWords, int score, int timesReset, long whenFinished) {
    this.boardSize = boardSize;
    this.duration = duration;
    this.highestScoringWord = highestScoringWord;
    this.numberOfWords = numberOfWords;
    this.score = score;
    this.timesReset = timesReset;
    this.whenFinished = whenFinished;
  }

  /**
   * Get string representing detailed game statistics.
   *
   * @return The detailed game statistics
   */
  public String getDetailedStatistics() {
    return String.format(Locale.US, "Board size: %d\nDuration: %d minutes\n"
            + "Highest scoring word: %s", boardSize, duration,
        highestScoringWord == null ? "N/A" : highestScoringWord);
  }

  /**
   * Get this game's unique ID.
   *
   * @return Unique ID
   */
  public int getGameId() {
    return gameId;
  }

  /**
   * Get string representation for simple statistics.
   *
   * @return Simple statistics
   */
  public String getStatistics() {
    return String.format(Locale.US, "Final score: %d\nTimes reset: %d\nWords entered: %d",
        score, timesReset, numberOfWords);
  }

  /**
   * Increment the numbers of words.
   */
  public void incrementNumberOfWords() {
    numberOfWords += 1;
  }

  /**
   * Increment the times reset.
   */
  public void incrementTimesReset() {
    timesReset += 1;
  }

  /**
   * Set the highest scoring word.
   *
   * @param word Highest scoring word
   */
  public void setHighestScoringWord(@NonNull String word) {
    highestScoringWord = word;
  }

  public void setScore(int score) {
    this.score = score;
  }

  int getBoardSize() {
    return boardSize;
  }

  int getDuration() {
    return duration;
  }

  @VisibleForTesting
  public int getScore() {
    return score;
  }

  @Nullable
  String getHighestScoringWord() {
    return highestScoringWord;
  }

  int getNumberOfWords() {
    return numberOfWords;
  }

  @VisibleForTesting
  public int getTimesReset() {
    return timesReset;
  }

  long getWhenFinished() {
    return whenFinished;
  }

  void setGameId(int gameId) {
    this.gameId = gameId;
  }

}

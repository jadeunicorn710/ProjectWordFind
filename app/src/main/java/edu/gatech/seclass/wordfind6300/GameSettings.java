package edu.gatech.seclass.wordfind6300;

import android.content.Context;
import android.content.SharedPreferences;

public class GameSettings {

  public static final String mypreference = "mypref";
  public static final String TimeLimit = "timeLimitKey";
  public static final String BoardSize = "boardSizeKey";

  private final SharedPreferences sharedPreferences;

  public GameSettings(Context context) {
    sharedPreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
  }

  void setGameDuration(int timelimit) throws IllegalArgumentException {
    if (timelimit < 1 || timelimit > 5) {
      throw new IllegalArgumentException("Illegal game duration!");
    }
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(TimeLimit, timelimit);
    editor.apply();
  }

  int getGameDuration() {
    return sharedPreferences.getInt(TimeLimit, 5);
  }

  void setBoardSize(int boardsize) throws IllegalArgumentException {
    if (boardsize < 4 || boardsize > 8) {
      throw new IllegalArgumentException("Illegal board size!");
    }
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(BoardSize, boardsize);
    editor.apply();
  }

  int getBoardSize() {
    return sharedPreferences.getInt(BoardSize, 5);
  }

  void setLetterWeights(String letter, int weight) throws IllegalArgumentException {
    if (!(letter.matches(".*[A-Z].*"))) {
      throw new IllegalArgumentException("Illegal input letter!");
    }
    if (weight < 1 || weight > 5) {
      throw new IllegalArgumentException("Illegal letter weight!");
    }
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(letter, weight);
    editor.apply();
  }

  int getLetterWeight(String letter) {
    return sharedPreferences.getInt(letter, 1);
  }

}

package edu.gatech.seclass.wordfind6300;

import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public class Letter {

  private final String displayAs;
  private final int worth;

  /**
   * Generate a list of all possible {@code Letter}s.
   *
   * @return List of all possible {@code Letter}s.
   */
  public static List<Letter> getLetters() {
    List<Letter> letters = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      char newLetter = (char) ('A' + i);
      Letter letter = getLetter(newLetter);
      letters.add(letter);
    }
    return letters;
  }

  @VisibleForTesting
  static Letter getLetter(char letter) {
    if (!((Character.toString(letter)).matches(".*[A-Z].*"))) {
      throw new RuntimeException("Letter should be between A and Z");
    }
    boolean isQ = letter == 'Q';
    String displayAs = isQ ? "Qu" : Character.toString(letter);
    int worth = isQ ? 2 : 1;
    return new Letter(displayAs, worth);
  }

  private Letter(String displayAs, int worth) {
    this.displayAs = displayAs;
    this.worth = worth;
  }

  public String displayAs() {
    return displayAs;
  }

  public int getWorth() {
    return worth;
  }

  public boolean isVowel() {
    return "AEIOU".contains(displayAs);
  }

}

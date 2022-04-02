package edu.gatech.seclass.wordfind6300.storage;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import edu.gatech.seclass.wordfind6300.Statistics;
import java.util.Locale;

@Entity
public class WordStatistics implements Statistics {

  @PrimaryKey
  @NonNull
  private final String word;
  private final long timeFirstUsed;

  private int frequency;

  @Ignore
  WordStatistics(@NonNull String word) {
    this(word, 0, System.currentTimeMillis());
  }

  WordStatistics(@NonNull String word, int frequency, long timeFirstUsed) {
    this.word = word;
    this.frequency = frequency;
    this.timeFirstUsed = timeFirstUsed;
  }

  public String getStatistics() {
    return String.format(Locale.US, "Word: %s\nFrequency: %d", word, frequency);
  }

  int getFrequency() {
    return frequency;
  }

  long getTimeFirstUsed() {
    return timeFirstUsed;
  }

  @NonNull
  String getWord() {
    return word;
  }

  void incrementWordCount() {
    frequency += 1;
  }

}

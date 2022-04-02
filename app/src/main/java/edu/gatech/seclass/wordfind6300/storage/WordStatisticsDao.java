package edu.gatech.seclass.wordfind6300.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

@Dao
public abstract class WordStatisticsDao {

  /**
   * Increase the stored frequency of a word by 1.
   *
   * @param word The word whose frequency should be increased
   */
  @Transaction
  public void incrementWordFrequency(String word) {
    WordStatistics wordStatistics = get(word);
    if (wordStatistics == null) {
      wordStatistics = new WordStatistics(word);
      insert(wordStatistics);
    }
    wordStatistics.incrementWordCount();
    update(wordStatistics);
  }

  @Query("SELECT * FROM WordStatistics ORDER BY frequency DESC, timeFirstUsed")
  public abstract List<WordStatistics> getAllSortedByFrequency();

  @Query("SELECT * FROM WordStatistics WHERE word=(:word)")
  abstract WordStatistics get(String word);

  @Insert
  abstract void insert(WordStatistics... wordStatistics);

  @Update
  abstract void update(WordStatistics... wordStatistics);

}

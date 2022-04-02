package edu.gatech.seclass.wordfind6300.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public abstract class GameStatisticsDao {

  @Query("SELECT * FROM GameStatistics WHERE gameId=(:gameId)")
  public abstract GameStatistics get(int gameId);

  @Query("SELECT * FROM GameStatistics ORDER BY score DESC, whenFinished")
  public abstract List<GameStatistics> getAllSortedByFrequency();

  @Insert
  public abstract void save(GameStatistics... gameStatistics);

}

package edu.gatech.seclass.wordfind6300.storage;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GameStatistics.class, WordStatistics.class},
    version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {

  private static ApplicationDatabase database;

  /**
   * Get a singleton of the application database.
   *
   * @param context The applications context
   * @return The application database
   */
  public static ApplicationDatabase get(Context context) {
    if (database == null) {
      database = Room
          .databaseBuilder(context, ApplicationDatabase.class, "app-database")
          .allowMainThreadQueries()
          .build();
    }
    return database;
  }

  public abstract GameStatisticsDao getGameStatisticsDao();

  public abstract WordStatisticsDao getWordStatisticsDao();

}

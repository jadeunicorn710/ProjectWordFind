package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.seclass.wordfind6300.storage.ApplicationDatabase;
import edu.gatech.seclass.wordfind6300.storage.GameStatistics;
import edu.gatech.seclass.wordfind6300.storage.GameStatisticsDao;
import java.util.List;

public class GameStatisticsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_stats);

    ApplicationDatabase database = ApplicationDatabase.get(this);
    GameStatisticsDao gameStatisticsDao = database.getGameStatisticsDao();
    List<GameStatistics> gameStatisticsList = gameStatisticsDao.getAllSortedByFrequency();

    ListView listView = findViewById(R.id.game_listview);
    ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.test_list_item,
        Utilities.map(gameStatisticsList, GameStatistics::getStatistics));
    listView.setAdapter(arrayAdapter);

    listView.setOnItemClickListener((parent, view, position, id) -> {
      int gameId = gameStatisticsList.get(position).getGameId();
      Intent intent = new Intent(this, GameDetailStatisticsActivity.class);
      intent.putExtra(GameDetailStatisticsActivity.EXTRA_GAME_ID, gameId);
      startActivity(intent);
    });
  }

}

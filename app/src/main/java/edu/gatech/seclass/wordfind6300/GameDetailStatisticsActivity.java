package edu.gatech.seclass.wordfind6300;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.seclass.wordfind6300.storage.ApplicationDatabase;
import edu.gatech.seclass.wordfind6300.storage.GameStatistics;
import edu.gatech.seclass.wordfind6300.storage.GameStatisticsDao;
import java.util.ArrayList;
import java.util.List;

public class GameDetailStatisticsActivity extends AppCompatActivity {

  static final String EXTRA_GAME_ID = "GameId";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_stats_detail);

    int gameId = getIntent().getIntExtra(EXTRA_GAME_ID, 0);
    ApplicationDatabase database = ApplicationDatabase.get(this);
    GameStatisticsDao gameStatisticsDao = database.getGameStatisticsDao();
    GameStatistics gameStatistics = gameStatisticsDao.get(gameId);

    List<String> list = new ArrayList<>();
    list.add(gameStatistics.getDetailedStatistics());

    ListView listView = findViewById(R.id.gameDetail_listview);
    ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.test_list_item, list);
    listView.setAdapter(arrayAdapter);
  }

}

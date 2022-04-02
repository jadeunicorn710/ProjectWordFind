package edu.gatech.seclass.wordfind6300;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.seclass.wordfind6300.storage.ApplicationDatabase;
import edu.gatech.seclass.wordfind6300.storage.WordStatistics;
import edu.gatech.seclass.wordfind6300.storage.WordStatisticsDao;
import java.util.ArrayList;
import java.util.List;

public class WordStatisticsActivity extends AppCompatActivity {

  List<String> list = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_word_statistics);

    ListView listView = findViewById(R.id.word_listview);
    ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.test_list_item, list);
    listView.setAdapter(arrayAdapter);

    ApplicationDatabase database = ApplicationDatabase.get(this);
    WordStatisticsDao wordStatisticsDao = database.getWordStatisticsDao();
    List<WordStatistics> wordStatisticsList = wordStatisticsDao.getAllSortedByFrequency();
    for (WordStatistics wordStatistics : wordStatisticsList) {
      list.add(wordStatistics.getStatistics());
    }
  }

}

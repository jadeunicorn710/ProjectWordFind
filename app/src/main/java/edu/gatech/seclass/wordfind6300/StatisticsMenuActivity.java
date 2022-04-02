package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsMenuActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics_menu);
  }

  public void openGameStatistics(View view) {
    startActivity(new Intent(this, GameStatisticsActivity.class));
  }

  public void openWordStatistics(View view) {
    startActivity(new Intent(this, WordStatisticsActivity.class));
  }

}

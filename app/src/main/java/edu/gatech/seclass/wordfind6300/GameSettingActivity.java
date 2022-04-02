package edu.gatech.seclass.wordfind6300;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class GameSettingActivity extends AppCompatActivity {

  private EditText timelimit;
  private EditText boardsize;
  private EditText letter;
  private EditText weight;
  GameSettings gamesettings;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gamesettings);

    // Assign the found identifiers
    timelimit = (EditText) findViewById(R.id.gametimelimit);
    boardsize = (EditText) findViewById(R.id.gameboardsize);
    letter = (EditText) findViewById(R.id.gameletter);
    weight = (EditText) findViewById(R.id.gameweight);

    gamesettings = new GameSettings(this);
  }

  /**
   * Callback for back button.
   *
   * @param view The back button.
   */
  public void onBack(View view) {
    finish();
  }

  /**
   * Callback for save button.
   *
   * @param view The save button.
   */
  public void onSave(View view) {
    try {
      gamesettings.setGameDuration(Integer.parseInt(timelimit.getText().toString()));
    } catch (IllegalArgumentException e) {
      timelimit.setError("Input integers between 1 and 5 minutes");
    }

    try {
      gamesettings.setBoardSize(Integer.parseInt(boardsize.getText().toString()));
    } catch (IllegalArgumentException e) {
      boardsize.setError("Input integers between 4 and 8");
    }

    try {
      gamesettings.setLetterWeights(letter.getText().toString(),
          Integer.parseInt(weight.getText().toString()));
    } catch (IllegalArgumentException e) {
      letter.setError(
          "Input letters between A and Z for letter");
      weight.setError(
              "Input integers between 1 and 5 for weight");
    }

    finish();
  }

}

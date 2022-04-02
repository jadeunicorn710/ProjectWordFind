package edu.gatech.seclass.wordfind6300;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import edu.gatech.seclass.wordfind6300.storage.ApplicationDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.math3.util.Pair;

public class GameActivity extends AppCompatActivity {

  private final List<Button> selectedButtons = new ArrayList<>();
  private final List<Pair<Integer, Integer>> selectedLetters = new ArrayList<>();

  private Game game;
  private GameSettings gameSettings;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    game = Game.getInstance(ApplicationDatabase.get(this), new GameSettings(this));
    gameSettings = new GameSettings(this);
    drawBoard();
    game.startGame(this::updateRemainingTime, () -> runOnUiThread(this::showFinalScoreAndExit));
  }

  /**
   * Callback for exit button.
   *
   * @param view The exit button.
   */
  public void onExit(View view) {
    showFinalScoreAndExit();
  }

  /**
   * Callback for reset button.
   *
   * @param view The reset button.
   */
  public void onResetBoard(View view) {
    selectedLetters.clear();
    selectedButtons.clear();
    game.resetBoard();
    drawBoard();
    updateScore();
  }

  /**
   * Callback for submit button.
   *
   * @param view The reset button.
   */
  public void onSubmit(View view) {
    try {
      game.enterWord(selectedLetters);
    } catch (IllegalMoveException e) {
      Log.wtf("Game", "Use somehow entered an invalid word!");
    }
    disableSelectedButtons();
    selectedButtons.clear();
    selectedLetters.clear();
    updateScore();
  }

  private void disableSelectedButtons() {
    for (Button button : selectedButtons) {
      button.getBackground().clearColorFilter();
      button.setEnabled(false);
    }
  }

  private void drawBoard() {
    GridLayout board = findViewById(R.id.board);
    board.removeAllViews();

    int size = gameSettings.getBoardSize();
    board.setColumnCount(size);
    Letter[][] letters = game.getBoardLetters();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Letter letter = letters[i][j];
        Button b = new Button(this);
        b.setText(letter.displayAs());
        b.setEms(3);
        b.setPaddingRelative(10, 0, 10, 0);
        b.setMinimumHeight(0);
        b.setMinimumWidth(0);
        b.setAllCaps(false);

        Pair<Integer, Integer> index = new Pair<Integer, Integer>(i, j);
        b.setOnClickListener((view) -> onLetterClick(index, b));

        board.addView(b);
      }
    }
  }

  private boolean isLastSelectedLetterAdjacentToLetter(Pair<Integer, Integer> letter) {
    if (selectedLetters.size() == 0) {
      // Any letter is a valid choice for the first letter
      return true;
    }
    Pair<Integer, Integer> previousLetter = selectedLetters.get(selectedLetters.size() - 1);
    int deltaX = Math.abs(letter.getFirst() - previousLetter.getFirst());
    int deltaY = Math.abs(letter.getSecond() - previousLetter.getSecond());
    return deltaX <= 1 && deltaY <= 1;
  }

  private void onLetterClick(Pair<Integer, Integer> index, Button button) {
    if (selectedLetters.contains(index)) {
      selectedButtons.remove(button);
      selectedLetters.remove(index);
      button.getBackground().clearColorFilter();
    } else if (isLastSelectedLetterAdjacentToLetter(index)) {
      selectedButtons.add(button);
      selectedLetters.add(index);
      button.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }
    Button submitButton = findViewById(R.id.submitButton);
    submitButton.setEnabled(selectedButtons.size() >= 2);
  }

  private void showFinalScoreAndExit() {
    game.exitGame();
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Final score: " + game.getScore())
        .setCancelable(false)
        .setPositiveButton("OK", (dialog, id) -> finish());
    AlertDialog alert = builder.create();
    alert.show();
  }

  private void updateRemainingTime(long remainingTime) {
    int remainingTimeInSeconds = (int) Math.ceil(remainingTime / 1000.0);
    int minutes = (int) Math.floor(remainingTimeInSeconds / 60);
    int seconds = remainingTimeInSeconds % 60;
    TextView remainingTimeView = findViewById(R.id.remainingTimeView);
    runOnUiThread(() -> remainingTimeView.setText(String.format(Locale.US,
        "Time Remaining: %d minutes %d seconds", minutes, seconds)));
  }

  private void updateScore() {
    TextView scoreView = findViewById(R.id.scoreView);
    scoreView.setText(String.format(Locale.US, "Score: %d", game.getScore()));
  }

}

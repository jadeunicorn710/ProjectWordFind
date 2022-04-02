package edu.gatech.seclass.wordfind6300;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.CountDownTimer;
import edu.gatech.seclass.wordfind6300.storage.ApplicationDatabase;
import edu.gatech.seclass.wordfind6300.storage.GameStatistics;
import edu.gatech.seclass.wordfind6300.storage.GameStatisticsDao;
import edu.gatech.seclass.wordfind6300.storage.WordStatisticsDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class GameTest {

  private ApplicationDatabase database;
  private GameSettings gameSettings;

  @Before
  public void setUp() {
    database = mock(ApplicationDatabase.class);
    gameSettings = mock(GameSettings.class);
    when(gameSettings.getLetterWeight(anyString())).thenReturn(1);
  }

  @Test
  public void verifyScoreSavedCorrectlyOnExit() {
    GameStatisticsDao gameStatisticsDao = mock(GameStatisticsDao.class);
    when(database.getGameStatisticsDao()).thenReturn(gameStatisticsDao);
    Game game = spy(Game.getInstance(database, gameSettings));
    doReturn(mock(CountDownTimer.class)).when(game).getCountDownTimer();
    doReturn(500).when(game).getScore();

    game.exitGame();

    ArgumentCaptor<GameStatistics> gameStatisticsCaptor = ArgumentCaptor
        .forClass(GameStatistics.class);
    verify(gameStatisticsDao).save(gameStatisticsCaptor.capture());
    assertEquals(500, gameStatisticsCaptor.getValue().getScore());
  }

  @Test(expected = IllegalMoveException.class)
  public void verifyExceptionThrownIfOverlappingWordEntered() throws IllegalMoveException {
    when(gameSettings.getBoardSize()).thenReturn(3);
    when(database.getWordStatisticsDao()).thenReturn(mock(WordStatisticsDao.class));
    List<Pair<Integer, Integer>> firstWord = new ArrayList<>();
    List<Pair<Integer, Integer>> secondWord = new ArrayList<>();
    Pair<Integer, Integer> commonPair = new Pair<>(0, 0);
    firstWord.add(commonPair);
    firstWord.add(new Pair<>(1, 1));
    secondWord.add(commonPair);
    secondWord.add(new Pair<>(2, 2));

    Game game = spy(new Game(database, gameSettings));
    game.enterWord(firstWord);
    game.enterWord(secondWord);
  }

  @Test
  public void verifyCanEnterMultipleWords() throws IllegalMoveException {
    when(gameSettings.getBoardSize()).thenReturn(4);
    when(database.getWordStatisticsDao()).thenReturn(mock(WordStatisticsDao.class));

    Game game = spy(new Game(database, gameSettings));
    enterTwoValidWords(game);
  }

  @Test
  public void verifyGameWithCorrectResetCountSavedCorrectlyOnExit() {
    GameStatisticsDao gameStatisticsDao = mock(GameStatisticsDao.class);
    when(database.getGameStatisticsDao()).thenReturn(gameStatisticsDao);
    Game game = spy(new Game(database, gameSettings));
    doReturn(mock(CountDownTimer.class)).when(game).getCountDownTimer();

    game.resetBoard();
    game.resetBoard();
    game.exitGame();

    ArgumentCaptor<GameStatistics> gameStatisticsCaptor = ArgumentCaptor
        .forClass(GameStatistics.class);
    verify(gameStatisticsDao).save(gameStatisticsCaptor.capture());
    assertEquals(2, gameStatisticsCaptor.getValue().getTimesReset());
  }

  @Test
  public void verifyScoreIsCorrectlyCalculate() throws IllegalMoveException {
    GameStatisticsDao gameStatisticsDao = mock(GameStatisticsDao.class);
    when(database.getGameStatisticsDao()).thenReturn(gameStatisticsDao);
    when(gameSettings.getBoardSize()).thenReturn(4);
    when(database.getWordStatisticsDao()).thenReturn(mock(WordStatisticsDao.class));
    Game game = spy(new Game(database, gameSettings));
    doReturn(mock(CountDownTimer.class)).when(game).getCountDownTimer();

    game.resetBoard();
    enterTwoValidWords(game);
    game.resetBoard();
    game.exitGame();

    ArgumentCaptor<GameStatistics> gameStatisticsCaptor = ArgumentCaptor
        .forClass(GameStatistics.class);
    verify(gameStatisticsDao).save(gameStatisticsCaptor.capture());
    assertEquals(-6, gameStatisticsCaptor.getValue().getScore());
    assertEquals(2, gameStatisticsCaptor.getValue().getTimesReset());
  }

  private void enterTwoValidWords(Game game) throws IllegalMoveException {
    List<Pair<Integer, Integer>> firstWord = new ArrayList<>();
    List<Pair<Integer, Integer>> secondWord = new ArrayList<>();
    firstWord.add(new Pair<>(0, 0));
    firstWord.add(new Pair<>(1, 1));
    secondWord.add(new Pair<>(2, 2));
    secondWord.add(new Pair<>(3, 3));

    game.enterWord(firstWord);
    game.enterWord(secondWord);
  }

}

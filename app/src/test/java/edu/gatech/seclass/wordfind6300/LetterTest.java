package edu.gatech.seclass.wordfind6300;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LetterTest {

  private Letter letter;

  @Before
  public void setUp() {
    letter = null;
  }

  @After
  public void tearDown() {
    letter = null;
  }


  // Test Purpose: This test checks whether method displayAs can show letter Q correctly
  @Test
  public void testGameSettings1() {
    Letter string = letter.getLetter('Q');
    assertEquals("Qu", string.displayAs());
  }

  // Test Purpose: This test checks whether method displayAs can show other letter correctly
  @Test
  public void testGameSettings2() {
    Letter string = letter.getLetter('A');
    assertEquals("A", string.displayAs());
  }

  // Test Purpose: This test checks whether method displayAs can correctly identify input not equal
  // to A-Z
  @Test(expected = RuntimeException.class)
  public void testGameSettings3() {
    Letter string = letter.getLetter('a');
  }

  // Test Purpose: This test checks whether getWorth can determine worth of letter Q correctly
  @Test
  public void testGameSettings4() {
    Letter string = letter.getLetter('Q');
    assertEquals(2, string.getWorth());
  }

  // Test Purpose: This test checks whether method getWorth can determine worth of letter
  // other than Q correctly
  @Test
  public void testGameSettings5() {
    Letter string = letter.getLetter('A');
    assertEquals(1, string.getWorth());
  }

  // Test Purpose: This test checks whether method isVowel can determine a vowel correctly
  @Test
  public void testGameSettings6() {
    Letter string = letter.getLetter('A');
    assertTrue(string.isVowel());
  }

  // Test Purpose: This test checks wheter method isVowel can determine a consonant correctly
  @Test
  public void testGameSettings7() {
    Letter string = letter.getLetter('B');
    assertFalse(string.isVowel());
  }
}

package edu.gatech.seclass.wordfind6300;

import androidx.arch.core.util.Function;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

  private Utilities() {
  }

  /**
   * Create a new list by mapping the contents of the provided list using the provided mapper.
   *
   * @param list The list to map from
   * @param mapper The mapper specifying how to map values
   * @param <F> The type of content in the source list
   * @param <T> The type of content in the destination list
   * @return List mapped from provided list using provided mapper
   */
  public static <F, T> List<T> map(List<F> list, Function<F, T> mapper) {
    List<T> targetList = new ArrayList<>();
    for (F item : list) {
      targetList.add(mapper.apply(item));
    }
    return targetList;
  }

}

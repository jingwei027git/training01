package com.davidlab.training01.util;

import java.time.LocalTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

  public static void printlnWithTime(Object obj)
  {
    System.out.println(String.format("[%s]%s", LocalTime.now(), obj));
  }

}

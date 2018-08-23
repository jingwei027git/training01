package com.davidlab.training01._0825;

import com.davidlab.training01.dto.User;
import com.davidlab.training01.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.util.Pair;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BasicLambdaTest {

  /**
   * 練習 filter(predicate) 的用法
   */
  @Test
  public void practicePredicate()
  {
    final List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    /* =========================================================================
     * 請以實作 nums = nums.stream().filter(....).collect(Collectors.toList()); 將小於5(包含5)的值過濾掉
     * =========================================================================
     */

    // TODO

    Assert.assertArrayEquals(nums.toArray(), new Integer[]{6, 7, 8, 9, 10});
  }

  /**
   * 練習 map(function) 的用法
   */
  @Test
  public void practiceFunction()
  {
    final List<User> users = new ArrayList<User>() {{
      add(new User("mike", 28));
      add(new User("jeff", 28));
      add(new User("mike", 36));
    }};

    /* =========================================================================
     * 請將 List<User> 轉成 List<Integer> (Integer 的內容為 age)
     * =========================================================================
     */

    // TODO

  }

  /**
   * 練習 思考實作上的策略
   */
  @Test
  public void practiceConsiderDifferent()
  {
    final User nobita = new User("Nobita", 11, new BigDecimal(2_000));
    Util.printlnWithTime("=== start === ");

    /* =========================================================================
     * TODO 請思考以下 2 者寫法有什麼不同
     * =========================================================================
     */
    log(nobita.getJpnMoney());
    log(() -> nobita.getJpnMoney());

    Util.printlnWithTime("=== end === ");
  }

  private void log(Object obj)
  {
    final boolean flag = new Random().nextBoolean();
    Util.printlnWithTime(flag);
    if (flag) {
      Util.printlnWithTime(obj);
    }
  }

  private void log(Supplier<BigDecimal> supplier)
  {
    final boolean flag = new Random().nextBoolean();
    Util.printlnWithTime(flag);
    if (flag) {
      Util.printlnWithTime(supplier.get());
    }
  }

  /**
   * 練習 supplier 的用法
   */
  @Test
  public void practiceSupplier()
  {
    /* =========================================================================
     * 請完成 provideCurrencies 在呼叫 outputCurrencies 方法後需進行輸出, 輸出結果應如下
     * USD
     * TWD
     * =========================================================================
     */
//   outputCurrencies(provideCurrencies()); TODO
  }

  private void outputCurrencies(
    Supplier<List<String>> provideCurrencies)
  {
    // TODO
  }

  /**
   * TODO provideCurrencies
   */
//  private Supplier<List<String>> provideCurrencies()
//  {
//    ...
//  }


  /**
   * 練習 peek(consumer) 的用法
   */
  @Test
  public void practiceConsumer()
  {
    List<String> names = Arrays.asList("mike", "jeff", "david", "doraemon");

    /* =========================================================================
     * 請用 stream().forEach 逐行輸出姓名到 console
     * =========================================================================
     */
    // TODO

    /* =========================================================================
     * 請用 stream().peek(...) 輸出任一姓名到 console
     * =========================================================================
     */
    // TODO

    /* =========================================================================
     * 請完成以下缺省功能, 輸出結果應如下 (挑戰題)
     * mike
     * 4
     * [mike]
     * jeff
     * 4
     * [jeff]
     * david
     * 5
     * doraemon
     * 8
     * =========================================================================
     */
    names.stream()
//      .peek(...) // TODO 輸出姓名
      .map(name -> Pair.of(name, name.length())); // FIXME ";" 記的拿掉
//      .peek(...) // TODO 輸出姓名長度
//      .filter(...) // TODO 過濾姓名長度必須為 4
//      .forEach(...); // TODO 輸出過濾後的姓名, 輸出時需用[]包起來, ex: [mike]
  }

}

package com.davidlab.training01._0825;

import com.davidlab.training01.dto.User;
import com.davidlab.training01.util.Util;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BasicLambdaTest {

  /**
   * 執行緒相關Demo
   */
  @SneakyThrows
  @Test
  public void test1()
  {
    System.out.println(Thread.currentThread().getName());//印出現在Thread的名字
    System.out.println(123);

//    Runnable r = () ->
//    {
//      System.out.println(Thread.currentThread().getName());
//      Thread.sleep(3000L);
//      System.out.println("222");
//    };
//    new  Thread(r).run();
//    new  Thread(r).run();

    Runnable r2 = () -> {
      System.out.println(Thread.currentThread().getName());
      try {
        Thread.sleep(3000L);
      } catch (Exception e) {
      }
      System.out.println("333");
    };
    new Thread(r2).start();
    new Thread(r2).start();

    Thread.sleep(8000L);
  }

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

    // TODO 簡化
    List<Integer> nums2 = nums.stream().filter((n) -> n > 5
    ).collect(Collectors.toList());
    System.out.println(nums2);
    Assert.assertArrayEquals(nums2.toArray(), new Integer[]{6, 7, 8, 9, 10});

    // TODO 一般
    List<Integer> nums3 = nums.stream().filter(new Predicate<Integer>() {
      @Override
      public boolean test(Integer n)
      {
        return n>5;//n如果大於5 則回傳true
      }
    }).collect(Collectors.toList());
    System.out.println(nums3);
    Assert.assertArrayEquals(nums3.toArray(), new Integer[]{6, 7, 8, 9, 10});
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

    // TODO 簡化
    List<Integer> age = users.stream().map(User::getAge).collect(Collectors.toList());
    System.out.println(age);

    // TODO 一般
    List<Integer> age2 = users.stream().map(user ->
      user.getAge()
    ).collect(Collectors.toList());
    System.out.println(age2);
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

    //第一類寫法 傳進去前就調用nobita.getJpnMoney(),不管有沒有用到,會比較浪費資源
    log(nobita.getJpnMoney());

    //第二類寫法(下兩種等價)=>Supplier屬於惰性求值,等真正用到才把值計算出來 比較不會浪費資源
    //傳進去先不馬上調用nobita.getJpnMoney() 等確定用到(flag為True)時才執行supplier.get()=>才調用到nobita.getJpnMoney()
    log(() -> nobita.getJpnMoney());
    log(new Supplier<BigDecimal>() {
      @Override
      public BigDecimal get()
      {
        return nobita.getJpnMoney();
      }
    });

    Util.printlnWithTime("=== end === ");
  }

  //
  private void log(Object obj)
  {
    System.out.println(999);
    final boolean flag = new Random().nextBoolean();
    Util.printlnWithTime(flag);
    if (flag) {
      Util.printlnWithTime(obj);
    }
  }

  //
  private void log(Supplier<BigDecimal> supplier)
  {
    Util.printlnWithTime("log");
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
    outputCurrencies(provideCurrencies());
  }

  private void outputCurrencies(
    Supplier<List<String>> provideCurrencies)
  {
    System.out.println(provideCurrencies.get());
  }

  /**
   * TODO provideCurrencies
   */
  private Supplier<List<String>> provideCurrencies()
  {
    return () -> {
      ArrayList<String> a = new ArrayList<>();
      a.add("USD");
      a.add("TWD");
      return a;
    };
  }

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
//    names.stream().forEach(s->
//        System.out.println(s)
//      );

    /* =========================================================================
     * 請用 stream().peek(...) 輸出任一姓名到 console
     * =========================================================================
     */
    names.stream().peek(System.out::println).findAny();

    /* =========================================================================
     * stream相關方法流程(流的運行)
     * =========================================================================
     */

    // 一個method一個動作 較推薦
    names.stream()
      .peek(System.out::println)
      .peek(s -> System.out.println(s.length()))
      .map(s -> s.length())//map本身不會印 把s這些名字映射成名字長度
      .forEach(s -> System.out.println(s));//到這邊s變成長度數字 然後印出來

    // 全部寫在forEach內
    names.stream().forEach(s -> {
      System.out.println(s);
      System.out.println(s.length());
      int a=s.length();
      System.out.println(a);
    });

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

//      names.stream()
//      .peek() // TODO 輸出姓名
//      .map(name -> Pair.of(name, name.length()))
//      .peek(...) // TODO 輸出姓名長度
//      .filter(...) // TODO 過濾姓名長度必須為 4
//      .forEach(...); // TODO 輸出過濾後的姓名, 輸出時需用[]包起來, ex: [mike]

//    names.stream()
//      .peek(System.out::println)
//      .map(name -> Pair.of(name, name.length()))//把name跟name.length() 包在一起
//      .peek(pair->System.out.println(pair.getSecond()))//印出pair裡面的第二項(就是名字長度)
//      .filter(pair->pair.getSecond()==4)
//      .forEach(pair->System.out.println("["+pair.getFirst()+"]"));
  }

}

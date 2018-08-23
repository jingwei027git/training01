package com.davidlab.training01.dto;

import com.davidlab.training01.util.Util;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class User implements Serializable {

  private String name;
  private Integer age;
  private BigDecimal money = BigDecimal.ZERO;

  public User(
    String name,
    Integer age)
  {
    this.name = name;
    this.age = age;
  }

  public User(
    String name,
    Integer age,
    BigDecimal money)
  {
    this.name = name;
    this.age = age;
    this.money = money;
  }

  /**
   * 這是一個複雜的計算
   */
  @SneakyThrows
  public BigDecimal getJpnMoney()
  {
    Util.printlnWithTime("User::getJpnMoney was called (This will takes 5 secs)");
    Thread.sleep(5000);
    return money.multiply(new BigDecimal("4"));
  }

}

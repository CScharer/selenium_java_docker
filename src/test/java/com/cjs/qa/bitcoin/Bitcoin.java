package com.cjs.qa.bitcoin;

import java.math.BigDecimal;

public class Bitcoin {
  private String rate;
  private BigDecimal rateFloat;
  private String dateTimeStamp;

  public Bitcoin(String rate, BigDecimal rateFloat, String dateTimeStamp) {
    this.rate = rate;
    this.rateFloat = rateFloat;
    this.dateTimeStamp = dateTimeStamp;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public BigDecimal getRateFloat() {
    return rateFloat;
  }

  public void setRateFloat(BigDecimal rateFloat) {
    this.rateFloat = rateFloat;
  }

  public String getDateTimeStamp() {
    return dateTimeStamp;
  }

  public void setDateTimeStamp(String dateTimeStamp) {
    this.dateTimeStamp = dateTimeStamp;
  }

  @Override
  public String toString() {
    // Java 17: Text block for cleaner string formatting
    return
        """
        Date Time Stamp:[%s], Rate:[%s], Rate Float:[%s]
        """
        .formatted(getDateTimeStamp(), getRate(), getRateFloat())
        .trim();
  }
}

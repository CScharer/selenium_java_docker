package com.cjs.qa.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Encoder {
  private final String CHARACTERS26 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String CHARACTERS52 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private String characterSet;

  public Encoder(String base) {
    setCharacterSet(base);
  }

  public String getCharacterSet() {
    return characterSet;
  }

  private void setCharacterSet(String base) {
    switch (base) {
      case "52":
        setCharacterSet(CHARACTERS52);
        break;
      default:
        setCharacterSet(CHARACTERS26);
        break;
    }
  }

  public void main(String[] args) {
    // This is designed to be able to use the UPPERCASE and lower-case
    // letters of the alphabet, but
    // we should only use the UPPERCASE unless we know for sure what we need
    // is not case sensitive.
    final String cCHARS = "";
    final StringBuilder oStringBuilder = new StringBuilder();
    final DateFormat oFormatNano = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
    Date oDate = new Date();
    String sDateTimeStamp = oFormatNano.format(oDate);
    for (int iLoop = 1; iLoop <= 1; iLoop++) {
      final String sValueEncoded = getEncodedValue(cCHARS);
      final String sValueDecoded = getDecodedValue(sValueEncoded, cCHARS);
      oStringBuilder.append(sValueDecoded + " - " + sValueEncoded + Constants.NL);
    }
    oDate = new Date();
    oStringBuilder.append(sDateTimeStamp + Constants.NL);
    sDateTimeStamp = oFormatNano.format(oDate);
    oStringBuilder.append(sDateTimeStamp);
    System.out.println(oStringBuilder.toString());
  }

  public String getDecodedValue(String sValue, String base) {
    int iDecodedDate = 0;
    int iDecodedTime = 0;
    int iDecodedNano = 0;
    String sEncoded = "";
    String sDecoded = "";
    setCharacterSet(base);
    sEncoded = sValue;
    switch (base) {
      case "52":
        iDecodedDate = Decode(sEncoded.substring(0, 5));
        iDecodedTime = Decode(sEncoded.substring(5, 9));
        iDecodedNano = Decode(sEncoded.substring(9));
        break;
      default:
        iDecodedDate = Decode(sEncoded.substring(0, 6));
        iDecodedTime = Decode(sEncoded.substring(6, 11));
        iDecodedNano = Decode(sEncoded.substring(11));
        break;
    }
    sDecoded = String.valueOf(iDecodedDate);
    sDecoded += String.valueOf(iDecodedTime);
    sDecoded += String.valueOf(iDecodedNano);
    return sDecoded;
  }

  public String getEncodedValue(String sBase) {
    try {
      // We need to wait 1 Nanosecond so we make sure to get a unique ID.
      // TimeUnit.MILLISECONDS.sleep(1);
      TimeUnit.NANOSECONDS.sleep(1);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    setCharacterSet(sBase);
    final DateFormat oFormatDate = new SimpleDateFormat("yyyyMMdd");
    final DateFormat oFormatTime = new SimpleDateFormat("1HHmmss");
    final DateFormat oFormatNano = new SimpleDateFormat("1SSS");
    final Date oDate = new Date();
    final String sDate = oFormatDate.format(oDate);
    // Prepend a 1 as the time may be AM and would lose a character.
    final String sTime = oFormatTime.format(oDate);
    // Prepend a 1 as the Nanoseconds could have leading zeros which would
    // be lost characters.
    final String sNano = oFormatNano.format(oDate);
    final int iDate = Integer.valueOf(sDate);
    final int iTime = Integer.valueOf(sTime);
    final int iNano = Integer.valueOf(sNano);
    String sDateEncoded = Encode(iDate);
    sDateEncoded += Encode(iTime);
    sDateEncoded += Encode(iNano);
    return sDateEncoded;
  }

  public String Encode(int iNumber) {
    String sText = "";
    final int iTextLength = (int) Math.ceil(Math.log(iNumber) / Math.log(characterSet.length()));
    for (int iLoop = 0; iLoop < iTextLength; iLoop++) {
      // iLoop goes to log base characterSet.length() of iNumber
      // (using change of base formula)
      sText += characterSet.charAt(iNumber % characterSet.length());
      iNumber /= characterSet.length();
    }
    return sText;
  }

  public int Decode(String sText) {
    int iNumber = 0;
    final int iTextLength = sText.length();
    for (int iLoop = 0; iLoop < iTextLength; iLoop++) {
      iNumber += characterSet.indexOf(sText.charAt(0)) * Math.pow(characterSet.length(), iLoop);
      sText = sText.substring(1);
    }
    return iNumber;
  }
}

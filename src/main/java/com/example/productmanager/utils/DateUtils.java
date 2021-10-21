package com.example.productmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: nansongling
 * @Date: 2021/10/21 11:10 AM
 **/
public class DateUtils {
  public static String DATE_FORMAT = "yyyyMMdd";

  public static String getDateStr(Long timestamp, String format) {
    Date date = new Date(timestamp);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(date);
  }

  public static Long now() {
    return System.currentTimeMillis();
  }
}

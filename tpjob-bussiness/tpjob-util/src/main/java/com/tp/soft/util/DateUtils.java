package com.tp.soft.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * <strong>Description : </strong>日期公共类<br>
 * <p>
 * @author taop<br>
 * @version <strong>v1.0.0</strong>
 */
public class DateUtils {

  // 设置jwt有效期
  public static Date getExpiryDate(int minutes) {
    // 根据当前日期，来得到到期日期
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
  }

}

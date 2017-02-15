package com.erutulco.utils;

import java.sql.Time;
import java.util.GregorianCalendar;

/**
 * ImladrisCalendar class emulating the behavior of
 * GregorianCalendar but for Imladris Reckoning.
 */
public class ImladrisCalendar {

  /* ************** FIELDS ************** */

  /**
   * Constant index for Yen.
   */
  public static final int YEN = 0;
  /**
   * Constant index for Loa.
   */
  public static final int LOA = 1;
  /**
   * Constant index for Day of Loa.
   */
  public static final int DAY_OF_LOA = 2;
  /**
   * Constant index for Period of Loa.
   */
  public static final int PERIOD = 3;
  /**
   * Constant index for day of Period.
   */
  public static final int DAY_OF_PERIOD = 4;
  /**
   * Constant index for Day of Week.
   */
  public static final int DAY_OF_WEEK = 5;
  /**
   * Constant index for Week of Period.
   */
  public static final int WEEK_OF_PERIOD = 6;

  /* ************** PERIODS ************** */

  /**
   * Constant index for Yestarë.
   */
  public static final int YESTARE = 1;
  /**
   * Constant index for Tuilë.
   */
  public static final int TUILE = 2;
  /**
   * Constant index for Lairë.
   */
  public static final int LAIRE = 3;
  /**
   * Constant index for Yávië.
   */
  public static final int YAVIE = 4;
  /**
   * Constant index for Enderi.
   */
  public static final int ENDERI = 5;
  /**
   * Constant index for Quellë.
   */
  public static final int QUELLE = 6;
  /**
   * Constant index for Hrívë.
   */
  public static final int HRIVE = 7;
  /**
   * Constant index for Coirë.
   */
  public static final int COIRE = 8;
  /**
   * Constant index for Mettarë.
   */
  public static final int METTARE = 9;


  /* ************** DAYS OF WEEK ************** */

  /**
   * Constant index for Elenya.
   */
  public static final int ELENYA = 1;
  /**
   * Constant index for Anarya.
   */
  public static final int ANARYA = 2;
  /**
   * Constant index for Isilya.
   */
  public static final int ISILYA = 3;
  /**
   * Constant index for Aldúya.
   */
  public static final int ALDUYA = 4;
  /**
   * Constant index for Menelya.
   */
  public static final int MENELYA = 5;
  /**
   * Constant index for Valanya.
   */
  public static final int VALANYA = 6;

  /* ************** OTHER ************** */

  /**
   * Names for each Loa Period.
   */
  private static final String[] PERIODS_OF_LOA = {
      "Yestarë", "Tuilë", "Lairë", "Yávië", "Ender",
      "Quellë", "Hrívë", "Coirë", "Mettarë"
  };
  /**
   * Length of each Loa Period.
   */
  private static final int[] LENGTH_OF_PERIODS = {
      1, 54, 72, 54, 3, 54, 72, 54, 1
  };
  /**
   * Length of each Loa Period in Leap Loas.
   */
  private static final int[] LENGTH_OF_PERIODS_LEAP = {
      1, 54, 72, 54, 6, 54, 72, 54, 1
  };
  /**
   * Name of each Week Day.
   */
  private static final String[] DAYS_OF_WEEK = {
      "Elenya", "Anarya", "Isilya", "Aldúya", "Menelya", "Valanya"
  };
  /**
   * First Day of the Week of the first Loa in Yen I.
   */
  private static final int LOA_1_YEN_I_DAY_OF_WEEK = ELENYA;
  /**
   * Ranges of Loas for each Yen with the day of march in which Yestarë falls.
   */
  private static final int[][][] YESTARE_MAP = {
      {{1, 144, 26}}, // YEN I
      {{1, 144, 26}}, // YEN II
      {{1, 144, 26}}, // YEN III
      {{1, 144, 23}}, // YEN IV
      {{1, 144, 23}}, // YEN V
      {{1, 144, 23}}, // YEN VI
      {{1, 144, 20}}, // YEN VII
      {{1, 144, 20}}, // YEN VIII
      {{1, 144, 20}}, // YEN IX
      {{1, 144, 17}}, // YEN X
      {{1, 142, 17}, {143, 144, 27}}, // YEN XI
      {{1, 115, 27}, {116, 144, 28}}, // YEN XII
      {{1, 71, 25}, {72, 144, 26}}, // YEN XIII
      {{1, 27, 26}, {28, 144, 27}}, // YEN XIV
      {{1, 83, 27}, {84, 144, 28}}, // YEN XV
      // YEN XVI (loar 140-144 are uncertain)
      {{1, 39, 25}, {40, 139, 26}, {140, 144, 26}}
  };

  /* ************** ATTRIBUTES ************** */

  /**
   * GregorianCalendar instance to convert to or from Imladris.
   */
  private GregorianCalendar gregorian;
  /**
   * Flag to check if sunset is set.
   */
  private boolean sunsetDefined = false;
  /**
   * Time instance with the sunset information.
   */
  private Time sunset;
  /**
   * Integer number for Yen.
   */
  private int yenInt;
  /**
   * String for current Yen.
   */
  private String yen;
  /**
   * Integer number for Loa.
   */
  private int loa;
  /**
   * Integer number for the beginning day of the Loa.
   */
  private int loaBeginingDay;
  /**
   * Integer number for the day of the Loa.
   */
  private int dayOfLoa;
  /**
   * Flag for leap Loa.
   */
  private boolean leapLoa;
  /**
   * Integer number for the Period of the Loa.
   */
  private int periodOfLoaInt;
  /**
   * String for the Period of the Loa.
   */
  private String periodOfLoa;
  /**
   * Flag indicating if Period id month or special days.
   */
  private boolean inMonth;
  /**
   * Integer number for the month in the Loa.
   */
  private int monthOfLoa;
  /**
   * Integer number for the day in the Period.
   */
  private int dayOfPeriod;
  /**
   * Integer number for the week in the Period.
   */
  private int weekOfPeriod;
  /**
   * Integer number for the day of the week.
   */
  private int dayOfWeekInt;
  /**
   * String for the day of the week.
   */
  private String dayOfWeek;
  /**
   * Integer number for the week day of Yestarë.
   */
  private int yestareWeekDayInt;
  /**
   * String for the week day of Yestarë.
   */
  private String yestareWeekDay;

  /* ************** GETTERS & SETTERS ************** */

  public final GregorianCalendar getGregorian() {
    return gregorian;
  }

  public final void setGregorian(final GregorianCalendar gregorian) {
    this.gregorian = gregorian;
  }

  public final boolean isSunsetDefined() {
    return sunsetDefined;
  }

  public final void setSunsetDefined(final boolean sunsetDefined) {
    this.sunsetDefined = sunsetDefined;
  }

  public final Time getSunset() {
    return sunset;
  }

  public final void setSunset(final Time sunset) {
    this.setSunsetDefined(true);
    this.sunset = sunset;
  }

  public final int getYenInt() {
    return yenInt;
  }

  public final String getYen() {
    return this.yen;
  }

  public final int getLoa() {
    return loa;
  }

  public final int getLoaBeginingDay() {
    return loaBeginingDay;
  }

  public final int getDayOfLoa() {
    return dayOfLoa;
  }

  public final boolean isLeapLoa() {
    return leapLoa;
  }

  public final int getPeriodOfLoaInt() {
    return periodOfLoaInt;
  }

  public final String getPeriodOfLoa() {
    return periodOfLoa;
  }

  public final boolean isInMonth() {
    return inMonth;
  }

  public final int getMonthOfLoa() {
    return monthOfLoa;
  }

  public final int getDayOfPeriod() {
    return dayOfPeriod;
  }

  public final int getWeekOfPeriod() {
    return weekOfPeriod;
  }

  public final int getDayOfWeekInt() {
    return dayOfWeekInt;
  }

  public final String getDayOfWeek() {
    return dayOfWeek;
  }

  public final int getYestareWeekDayInt() {
    return yestareWeekDayInt;
  }

  public final String getYestareWeekDay() {
    return yestareWeekDay;
  }

  public final void setYenInt(final int yenInt) {
    this.setYen(this.intToRoman(yenInt));
    this.yenInt = yenInt;
  }

  public final void setYen(final String yen) {
    this.yen = yen;
  }

  public final void setLoa(final int loa) {
    this.loa = loa;
  }

  public final void setLoaBeginingDay(final int loaBeginingDay) {
    this.loaBeginingDay = loaBeginingDay;
  }

  public final void setDayOfLoa(final int dayOfLoa) {
    this.dayOfLoa = dayOfLoa;
  }

  public final void setLeapLoa(final boolean leapLoa) {
    this.leapLoa = leapLoa;
  }

  public final void setPeriodOfLoaInt(final int periodOfLoaInt) {
    this.setPeriodOfLoa(PERIODS_OF_LOA[periodOfLoaInt - 1]);
    this.periodOfLoaInt = periodOfLoaInt;
  }

  public final void setPeriodOfLoa(final String periodOfLoa) {
    this.periodOfLoa = periodOfLoa;
  }

  public final void setInMonth(final boolean inMonth) {
    this.inMonth = inMonth;
  }

  public final void setMonthOfLoa(final int monthOfLoa) {
    this.monthOfLoa = monthOfLoa;
  }

  public final void setDayOfPeriod(final int dayOfPeriod) {
    this.dayOfPeriod = dayOfPeriod;
  }

  public final void setWeekOfPeriod(final int weekOfPeriod) {
    this.weekOfPeriod = weekOfPeriod;
  }

  public final void setDayOfWeekInt(final int dayOfWeekInt) {
    this.setDayOfWeek(DAYS_OF_WEEK[dayOfWeekInt - 1]);
    this.dayOfWeekInt = dayOfWeekInt;
  }

  public final void setDayOfWeek(final String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public final void setYestareWeekDayInt(final int yestareWeekDayInt) {
    this.setYestareWeekDay(DAYS_OF_WEEK[yestareWeekDayInt - 1]);
    this.yestareWeekDayInt = yestareWeekDayInt;
  }

  public final void setYestareWeekDay(final String yestareWeekDay) {
    this.yestareWeekDay = yestareWeekDay;
  }

  /**
   * Constructor with today's date.
   */
  public ImladrisCalendar() {
    this(new GregorianCalendar());
  }

  /**
   * Constructor with today's date with sunset.
   * @param sunset Time of sunset
   */
  public ImladrisCalendar(Time sunset) {
    this(sunset, new GregorianCalendar());
  }

  /**
   * Constructor from Gregorian date.
   * @param gregorian Gregorian date to convert
   */
  public ImladrisCalendar(GregorianCalendar gregorian) {
    this.setGregorian(
        new GregorianCalendar(
            gregorian.get(GregorianCalendar.YEAR),
            gregorian.get(GregorianCalendar.MONTH),
            gregorian.get(GregorianCalendar.DAY_OF_MONTH),
            gregorian.get(GregorianCalendar.HOUR_OF_DAY),
            gregorian.get(GregorianCalendar.MINUTE),
            gregorian.get(GregorianCalendar.SECOND)
        )
    );
    this.convert();
  }

  /**
   * Constructor from Gregorian date with sunset.
   * @param sunset Time of sunset
   * @param gregorian Gregorian date to convert
   */
  public ImladrisCalendar(Time sunset, GregorianCalendar gregorian) {
    this.setSunset(sunset);
    this.setGregorian(
        new GregorianCalendar(
            gregorian.get(GregorianCalendar.YEAR),
            gregorian.get(GregorianCalendar.MONTH),
            gregorian.get(GregorianCalendar.DAY_OF_MONTH),
            gregorian.get(GregorianCalendar.HOUR_OF_DAY),
            gregorian.get(GregorianCalendar.MINUTE),
            gregorian.get(GregorianCalendar.SECOND)
        )
    );
    this.convert();
  }

  //

  public ImladrisCalendar(int year, int month, int dayOfMonth) {
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, 0, 0, 1));
    this.convert();
  }

  public ImladrisCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, 1));
    this.convert();
  }

  /**
   * Constructor from specified date and time arguments.
   * @param year Year
   * @param month Month is 1-based indexed (1-12|January-December)
   * @param dayOfMonth Day of the month
   * @param hourOfDay Hour of day (0-23)
   * @param minute Minutes (0-59)
   * @param second Seconds (0-59)
   */
  public ImladrisCalendar(int year, int month, int dayOfMonth,
                          int hourOfDay, int minute, int second) {
    this.setGregorian(
        new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, second)
    );
    this.convert();
  }

  /**
   *Constructor from specified sunset, date and time arguments.
   * @param sunset Time of sunset
   * @param year Year
   * @param month Month is 1-based indexed (1-12|January-December)
   * @param dayOfMonth Day of the month
   */
  public ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth) {
    this.setSunset(sunset);
    this.setGregorian(
        new GregorianCalendar(year, month - 1, dayOfMonth, 0, 0, 1)
    );
    this.convert();
  }

  /**
   *Constructor from specified sunset, date and time arguments.
   * @param sunset Time of sunset
   * @param year Year
   * @param month Month is 1-based indexed (1-12|January-December)
   * @param dayOfMonth Day of the month
   * @param hourOfDay Hour of day (0-23)
   * @param minute Minutes (0-59)
   */
  public ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth,
                          int hourOfDay, int minute) {
    this.setSunset(sunset);
    this.setGregorian(
        new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, 1)
    );
    this.convert();
  }

  /**
   * Constructor from specified sunset, date and time arguments.
   * @param sunset Time of sunset
   * @param year Year
   * @param month Month is 1-based indexed (1-12|January-December)
   * @param dayOfMonth Day of the month
   * @param hourOfDay Hour of day (0-23)
   * @param minute Minutes (0-59)
   * @param second Seconds (0-59)
   */
  public ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth,
                          int hourOfDay, int minute, int second) {
    this.setSunset(sunset);
    this.setGregorian(
        new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, second)
    );
    this.convert();
  }

  /**
   * Constructor from Imladris Reckoning date arguments only for Yestarë and Mettarë.
   * @param yen Yen string (i.e.: 'I', 'IV')
   * @param loa Loa integer
   * @param period Period integer for Yestarë or Mettarë
   */
  public ImladrisCalendar(String yen, int loa, int period) {
    this(yen, loa, period, 1);
  }

  // For any period, is 1-based indexed (1-9|YESTARE-METTARE)

  /**
   * Constructor from Imladris Reckoning date arguments for any Period.
   * @param yen Yen string (i.e.: 'I', 'IV')
   * @param loa Loa integer
   * @param period Period integer
   * @param day Day in Period integer
   */
  public ImladrisCalendar(String yen, int loa, int period, int day) {
    this.setYenInt(this.romanToInt(yen));
    this.setLoa(loa);
    this.setPeriodOfLoaInt(period);
    this.setDayOfPeriod(day);
    this.setGregorian(new GregorianCalendar());
    this.updateFromYenLoaPeriodAndDayOfPeriod();
  }

  /* ************* API Methods ************* */

  public boolean before(ImladrisCalendar cal) {
    return !this.same(cal) && !this.after(cal);
  }

  /**
   * Compare to other ImladrisCalendar instance.
   * @param cal Calendar to compare to
   * @return Result of comparison
   */
  public boolean same(ImladrisCalendar cal) {
    boolean same = false;
    if (this.getYenInt() == cal.getYenInt()
        && this.getLoa() == cal.getLoa()
        && this.getDayOfLoa() == cal.getDayOfLoa()) {
      same = true;
    }
    return same;
  }

  /**
   * Check if current instance is after the provided one.
   * @param cal Calendar to compare against
   * @return Result of comparison
   */
  public boolean after(ImladrisCalendar cal) {
    boolean after = false;
    if (this.getYenInt() < cal.getYenInt()) {
      after = true;
    } else if (this.getYenInt() == cal.getYenInt()) {
      if (this.getLoa() < cal.getLoa()) {
        after = true;
      } else if (this.getLoa() == cal.getLoa()) {
        if (this.getDayOfLoa() < cal.getDayOfLoa()) {
          after = true;
        }
      }
    }
    return after;
  }

  /**
   * Getter of the ImladrisCalendar fields of the calendar.
   * @param field Id of the index field to get value from
   * @return Value of the required field
   */
  public int get(int field) {
    int value = -1;
    switch (field) {
      case YEN:
        value = this.getYenInt();
        break;
      case LOA:
        value = this.getLoa();
        break;
      case DAY_OF_LOA:
        value = this.getDayOfLoa();
        break;
      case PERIOD:
        value = this.getPeriodOfLoaInt();
        break;
      case DAY_OF_PERIOD:
        value = this.getDayOfPeriod();
        break;
      case DAY_OF_WEEK:
        value = this.getDayOfWeekInt();
        break;
      case WEEK_OF_PERIOD:
        value = this.getWeekOfPeriod();
        break;
      default:
        break;
    }
    return value;
  }

  /**
   * Add 'amount' to 'field'.
   * Precondition: adding 'amount' must not overflow current 'field' state
   * @param field Id of the index field to add to
   * @param amount Amount of the type of field to add
   */
  public void add(int field, int amount) {
    switch (field) {
      case YEN:
        this.setYenInt(this.getYenInt() + amount);
        this.updateGregorian();
        break;
      case LOA:
        int loa = this.getLoa() + amount;
        this.add(YEN, (int) Math.floor(loa / 144));
        this.setLoa(loa % 144);
        this.updateGregorian();
        break;
      case PERIOD:
        break;
      case DAY_OF_LOA:
      case DAY_OF_PERIOD:
      case DAY_OF_WEEK:
        int dayOfLoa = this.getDayOfLoa() + amount;
        this.add(LOA, (int) Math.floor(dayOfLoa / 365));
        this.setDayOfLoa(dayOfLoa % 365);
        this.updateFromYenLoaAndDayOfLoa();
        break;
      case WEEK_OF_PERIOD:
        this.add(DAY_OF_LOA, amount * 6);
        break;
      default:
        break;
    }
  }

  /**
   * Get length of provided period.
   * @param period Period to get length of
   * @return Length of period
   */
  public int lengthOfPeriod(int period) {
    int[] lengths = LENGTH_OF_PERIODS;
    if (this.isLeapLoa()) {
      lengths = LENGTH_OF_PERIODS_LEAP;
    }
    return lengths[period - 1];
  }

  public void reconvert() {
    this.calculate(this.getGregorian());
  }

  /* ************* INTERNAL METHODS ************* */

  private void convert() {
    this.calculate(this.getGregorian());
  }

  /**
   * With yen, loa, period and dayOfPeriod set, update the rest.
   */
  private void updateFromYenLoaPeriodAndDayOfPeriod() {
    // calculate day of march of year 'y' in which loa begins
    int yen = this.getYenInt();
    int loa = this.getLoa();
    int y = (yen - 1) * 144 + loa;
    int loaBeg = this.loaBeginningDay(y);
    this.setLoaBeginingDay(loaBeg);
    // calculate if is leap loa
    boolean isLeapLoa = this.checkIfLeapLoa(loa);
    // calculate day of Loa
    int[] values = ImladrisCalendar.LENGTH_OF_PERIODS;
    if (isLeapLoa) {
      values = ImladrisCalendar.LENGTH_OF_PERIODS_LEAP;
    }
    int period = this.getPeriodOfLoaInt();
    int daysOfLoa = 0;
    for (int i = ImladrisCalendar.YESTARE; i < period; i++) {
      daysOfLoa += values[i - 1];
    }
    daysOfLoa += this.getDayOfPeriod();
    // if sunset defined, check if has passed
    if (this.isSunsetDefined()) {
      GregorianCalendar cal = this.getGregorian();
      String str = cal.get(GregorianCalendar.HOUR_OF_DAY) + ":"
          + cal.get(GregorianCalendar.MINUTE) + ":"
          + cal.get(GregorianCalendar.SECOND);
      Time time = Time.valueOf(str);
      if (!time.before(this.getSunset())) {
        daysOfLoa++;
      }
    }
    // calculate current week in month (if any), and day of week
    boolean isMonth = false;
    int month = 0;
    if (this.periodIsMonth(period)) {
      isMonth = true;
      month = this.calculateMonthFromPeriod(period);
    }
    int[] weekInfo = this.calculateWeekAndDayOfWeek(yen, loa, daysOfLoa);
    int weekOfPeriod = weekInfo[0];
    int dayOfWeek = weekInfo[1];
    int yestareWeekDay = weekInfo[2];
    // store data and return
    this.setDayOfLoa(daysOfLoa);
    this.setLeapLoa(isLeapLoa);
    this.setInMonth(isMonth);
    this.setMonthOfLoa(month);
    this.setWeekOfPeriod(weekOfPeriod);
    this.setDayOfWeekInt(dayOfWeek);
    this.setYestareWeekDayInt(yestareWeekDay);

    this.updateGregorian();
  }

  /**
   * With yen, loa and dayOfLoa set, update the rest.
   */
  private void updateFromYenLoaAndDayOfLoa() {
    int yen = this.getYenInt();
    int loa = this.getLoa();
    int dayOfLoa = this.getDayOfLoa();
    int y = (yen - 1) * 144 + loa;
    // calculate day of march of year 'y' in which loa begins
    int loaBeg = this.loaBeginningDay(y);
    // calculate if is leap loa
    boolean isLeapLoa = this.checkIfLeapLoa(loa);
    // calculate current month and day of month
    int[] periodInfo = this.calculatePeriodAndDayInPeriod(dayOfLoa, isLeapLoa);
    int period = periodInfo[0];
    int dayOfPeriod = periodInfo[1];
    // calculate current week in month (if any), and day of week
    boolean isMonth = false;
    int month = 0;
    if (this.periodIsMonth(period)) {
      isMonth = true;
      month = this.calculateMonthFromPeriod(period);
    }
    int[] weekInfo = this.calculateWeekAndDayOfWeek(yen, loa, dayOfLoa);
    int weekOfPeriod = weekInfo[0];
    int dayOfWeek = weekInfo[1];
    int yestareWeekDay = weekInfo[2];
    // store data and return
    this.setDayOfPeriod(dayOfPeriod);
    this.setLoaBeginingDay(loaBeg);
    this.setLeapLoa(isLeapLoa);
    this.setPeriodOfLoaInt(period);
    this.setInMonth(isMonth);
    this.setMonthOfLoa(month);
    this.setWeekOfPeriod(weekOfPeriod);
    this.setDayOfWeekInt(dayOfWeek);
    this.setYestareWeekDayInt(yestareWeekDay);

    this.updateGregorian();
  }

  private void updateGregorian() {
    int yen = this.getYenInt();
    int loa = this.getLoa();
    int dayOfLoa = this.getDayOfLoa();
    GregorianCalendar cal = this.getGregorian();
    cal.set(GregorianCalendar.YEAR, (yen - 1) * 144 + loa);
    cal.set(GregorianCalendar.MONTH, GregorianCalendar.MARCH);
    cal.set(GregorianCalendar.DAY_OF_MONTH, this.getLoaBeginingDay());
    cal.add(GregorianCalendar.DAY_OF_YEAR, dayOfLoa - 1);
  }

  private void calculate(GregorianCalendar cal) {
    // if sunset defined, check if has passed
    if (this.isSunsetDefined()) {
      String str = cal.get(GregorianCalendar.HOUR_OF_DAY) + ":"
          + cal.get(GregorianCalendar.MINUTE) + ":"
          + cal.get(GregorianCalendar.SECOND);
      Time time = Time.valueOf(str);
      if (!time.before(this.getSunset())) {
        cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
        cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
      }
    }
    int y = cal.get(GregorianCalendar.YEAR);
    // calculate day of march of year 'y' in which loa begins
    int loaBeg = this.loaBeginningDay(y);
    GregorianCalendar loaCal = new GregorianCalendar(y, GregorianCalendar.MARCH, loaBeg);
    // if that day hasn't come yet, use previous year's loa
    if (cal.before(loaCal)) {
      y = y - 1;
      loaBeg = this.loaBeginningDay(y);
    }
    // calculate loa
    int loa = this.calculateLoa(y);
    // calculate if is leap loa
    boolean isLeapLoa = this.checkIfLeapLoa(loa);
    // get amount of days of ongoing loa
    loaCal.set(GregorianCalendar.YEAR, y);
    loaCal.set(GregorianCalendar.DAY_OF_MONTH, loaBeg);
    int daysOfLoa = this.daysBetweenGregorian(loaCal, cal);
    // calculate current month and day of month
    int[] periodInfo = this.calculatePeriodAndDayInPeriod(daysOfLoa, isLeapLoa);
    int period = periodInfo[0];
    int dayOfPeriod = periodInfo[1];
    // calculate yen
    int yen = this.calculateYen(y);
    // calculate current week in month (if any), and day of week
    boolean isMonth = false;
    int month = 0;
    if (this.periodIsMonth(period)) {
      isMonth = true;
      month = this.calculateMonthFromPeriod(period);
    }
    int[] weekInfo = this.calculateWeekAndDayOfWeek(yen, loa, daysOfLoa);
    int weekOfPeriod = weekInfo[0];
    int dayOfWeek = weekInfo[1];
    int yestareWeekDay = weekInfo[2];
    // store data and return
    this.setYenInt(yen);
    this.setDayOfPeriod(dayOfPeriod);
    this.setLoa(loa);
    this.setLoaBeginingDay(loaBeg);
    this.setDayOfLoa(daysOfLoa);
    this.setLeapLoa(isLeapLoa);
    this.setPeriodOfLoaInt(period);
    this.setInMonth(isMonth);
    this.setMonthOfLoa(month);
    this.setWeekOfPeriod(weekOfPeriod);
    this.setDayOfWeekInt(dayOfWeek);
    this.setYestareWeekDayInt(yestareWeekDay);
  }

  private int calculateYen(int y) {
    int sign = y >= 0 ? 1 : -1;
    return (int) Math.floor((y - 1) / 144) + sign;
  }

  private int calculateLoa(int y) {
    int loa = 0;
    int sign = y >= 0 ? 1 : -1;
    if (y != 0) {
      loa = ((y - 1) % 144) + sign;
    }
    return loa;
  }

  private int loaBeginningDay(int y) {
    int loa = this.calculateLoa(y);
    int yen = this.calculateYen(y);
    int yestare = this.calculateYestare(yen, loa);
    return yestare;
  }

  private int calculateYestare(int yen, int loa) {
    int yestare;
    // get array for current yen
    int[][] yenMap = YESTARE_MAP[yen - 1];
    // find period of yen for given loa, and get yestare for first range
    int yestareMap = -1;
    boolean exit = false;
    int i = 0;
    int[] loaMap;
    while (i < yenMap.length && !exit) {
      loaMap = yenMap[i];
      if ((loaMap[0] <= loa) && (loa <= loaMap[1])) {
        yestareMap = loaMap[2];
        exit = true;
      }
      i++;
    }
    // calculate appropriate yestare
    int mod = loa % 12;
    if (mod == 0) {
      yestare = yestareMap;
    } else if (mod <= 3) {
      yestare = yestareMap + 3;
    } else if (mod <= 7) {
      yestare = yestareMap + 2;
    } else {
      yestare = yestareMap + 1;
    }
    return yestare;
  }

  private boolean checkIfLeapLoa(int loa) {
    return loa % 12 == 0;
  }

  private int[] calculatePeriodAndDayInPeriod(int days, boolean leap) {
    int[] daysInPeriod = ImladrisCalendar.LENGTH_OF_PERIODS;
    if (leap) {
      daysInPeriod = ImladrisCalendar.LENGTH_OF_PERIODS_LEAP;
    }
    int period = 1;
    boolean end = false;
    while (!end && (period < 10)) {
      if (days <= daysInPeriod[period - 1]) { // is in period 'period'
        end = true;
      } else { // is not in period 'period'
        days -= daysInPeriod[period - 1];
        period++;
      }
    }
    int[] ret = {period, days};
    return ret;
  }

  private boolean periodIsMonth(int period) {
    return (period > 1 && period < 5) || (period > 5 && period < 9);
  }

  private int calculateMonthFromPeriod(int period) {
    return (period < 4) ? period - 1 : period - 2;
  }

  private int calculateDayOfWeekOfYestare(int yen, int loa) {
    int offsetRegular = -1;
    int offsetLeap = 2;
    int firstYestareDayOfWeek = ImladrisCalendar.LOA_1_YEN_I_DAY_OF_WEEK;

    int totalLoas = (yen) * 144 + loa - 1;
    int totalLeapLoas = (int) Math.floor(totalLoas / 12);
    int totalRegularLoas = totalLoas - totalLeapLoas;

    int offsetFromLeap = offsetLeap * totalLeapLoas;
    int offsetFromRegular = offsetRegular * totalRegularLoas;
    int totalOffsetDays = offsetFromLeap + offsetFromRegular;
    int totalOffsetWeekDays = totalOffsetDays % 6;

    int yestareDayOfWeek = (firstYestareDayOfWeek - 1) + totalOffsetWeekDays;
    if (yestareDayOfWeek >= 0) {
      yestareDayOfWeek = yestareDayOfWeek % 6;
    } else {
      yestareDayOfWeek = (6 - ((-yestareDayOfWeek) % 6));
    }
    yestareDayOfWeek += 1;

    return yestareDayOfWeek;
  }

  private int[] calculateWeekAndDayOfWeek(int yen, int loa, int dayOfLoa) {
    // This Loa's Yestare Day Of Week
    int weekDayOfYestare = this.calculateDayOfWeekOfYestare(yen, loa);
    // Current day's day of week
    int dayOfWeek = ((weekDayOfYestare + (dayOfLoa - 1) - 1) % 6) + 1;
    // Week number
    int week = (int) Math.floor((dayOfLoa + (6 - dayOfWeek) + (weekDayOfYestare - 1)) / 6);
    // Group results
    int[] ret = {week, dayOfWeek, weekDayOfYestare};
    return ret;
  }

  private int daysBetweenGregorian(final GregorianCalendar startDate,
                                   final GregorianCalendar endDate) {
    GregorianCalendar dateStart = (GregorianCalendar) startDate.clone();
    dateStart.set(GregorianCalendar.HOUR_OF_DAY, 0);
    dateStart.set(GregorianCalendar.MINUTE, 0);
    dateStart.set(GregorianCalendar.SECOND, 0);
    GregorianCalendar dateEnd = (GregorianCalendar) endDate.clone();
    dateEnd.set(GregorianCalendar.HOUR_OF_DAY, 0);
    dateEnd.set(GregorianCalendar.MINUTE, 0);
    dateEnd.set(GregorianCalendar.SECOND, 0);
    int daysBetween = 1;
    while (dateStart.before(dateEnd)) {
      daysBetween++;
      dateStart.add(GregorianCalendar.DAY_OF_YEAR, 1);
    }
    return daysBetween;
  }

  /**
   * Convert integer number to roman numerals.
   * @param num Integer number to convert
   * @return Converted roman numerals
   */
  public String intToRoman(int num) {
    String[] romanBase = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int[] arabicBase = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String roman = "";
    for (int i = 0; i < romanBase.length; i++) {
      while (num >= arabicBase[i]) {
        num -= arabicBase[i];
        roman += romanBase[i];
      }
    }
    return roman;
  }

  private int romanCharToInt(char roman) {
    int num = 0;
    switch (roman) {
      case 'M':
        num = 1000;
        break;
      case 'D':
        num = 500;
        break;
      case 'C':
        num = 100;
        break;
      case 'L':
        num = 50;
        break;
      case 'X':
        num = 10;
        break;
      case 'V':
        num = 5;
        break;
      case 'I':
        num = 1;
        break;
      default:
        break;
    }
    return num;
  }

  /**
   * Roman numerals string to integer.
   * @param roman Roman numeral to convert
   * @return Convert integer
   */
  public int romanToInt(String roman) {
    roman = roman.toUpperCase();
    int arabic = 0;
    int i = 0;
    while (i < roman.length()) {
      int number = this.romanCharToInt(roman.charAt(i));
      i++;
      if (i == roman.length()) {
        arabic += number;
      } else {
        int nextNumber = this.romanCharToInt(roman.charAt(i));
        if (nextNumber > number) {
          arabic += (nextNumber - number);
          i++;
        } else {
          arabic += number;
        }
      }
    }
    return arabic;
  }

  /**
   * Implementation of toString to serialize the date.
   * @return Serialized date
   */
  public String toString() {
    String str = this.getDayOfWeek() + ", ";
    if (this.isInMonth()) {
      str += this.getPeriodOfLoa() + " " + this.getDayOfPeriod() + ", ";
    } else if (this.getPeriodOfLoaInt() == ImladrisCalendar.ENDERI) {
      str += this.getPeriodOfLoa() + " " + this.getDayOfPeriod() + ", ";
    } else {
      str += this.getPeriodOfLoa() + ", ";
    }
    str += this.getYen() + " " + this.getLoa();
    return str;
  }

}

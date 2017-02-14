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

  private static final String[] PERIODS_OF_LOA = {
      "Yestarë", "Tuilë", "Lairë", "Yávië", "Ender",
      "Quellë", "Hrívë", "Coirë", "Mettarë"
  };
  private static final int[] LENGTH_OF_PERIODS = {
      1, 54, 72, 54, 3, 54, 72, 54, 1
  };
  private static final int[] LENGTH_OF_PERIODS_LEAP = {
      1, 54, 72, 54, 6, 54, 72, 54, 1
  };
  private static final String[] DAYS_OF_WEEK = {
      "Elenya", "Anarya", "Isilya", "Aldúya", "Menelya", "Valanya"
  };
  private static final int LOA_0_YEN_I_DAY_OF_WEEK = ELENYA;
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

  private GregorianCalendar gregorian;
  private boolean sunsetDefined = false;
  private Time sunset;
  private int yenInt;
  private String yen;
  private int loa;
  private int[][][] yestareMap;
  private int loaBeginingDay;
  private int dayOfLoa;
  private boolean leapLoa;
  private int periodOfLoaInt;
  private String periodOfLoa;
  private boolean inMonth;
  private int monthOfLoa;
  private int dayOfPeriod;
  private int weekOfPeriod;
  private int dayOfWeekInt;
  private String dayOfWeek;
  private int yestareWeekDayInt;
  private String yestareWeekDay;

  /* ************** GETTERS & SETTERS ************** */

  public GregorianCalendar getGregorian() {
    return gregorian;
  }

  public void setGregorian(GregorianCalendar gregorian) {
    this.gregorian = gregorian;
  }

  public boolean isSunsetDefined() {
    return sunsetDefined;
  }

  public void setSunsetDefined(boolean sunsetDefined) {
    this.sunsetDefined = sunsetDefined;
  }

  public Time getSunset() {
    return sunset;
  }

  public void setSunset(Time sunset) {
    this.setSunsetDefined(true);
    this.sunset = sunset;
  }

  public int getYenInt() {
    return yenInt;
  }

  public String getYen() {
    return this.yen;
  }

  public int getLoa() {
    return loa;
  }

  public int[][][] getYestareMap() {
    return yestareMap;
  }

  public int getLoaBeginingDay() {
    return loaBeginingDay;
  }

  public int getDayOfLoa() {
    return dayOfLoa;
  }

  public boolean isLeapLoa() {
    return leapLoa;
  }

  public int getPeriodOfLoaInt() {
    return periodOfLoaInt;
  }

  public String getPeriodOfLoa() {
    return periodOfLoa;
  }

  public boolean isInMonth() {
    return inMonth;
  }

  public int getMonthOfLoa() {
    return monthOfLoa;
  }

  public int getDayOfPeriod() {
    return dayOfPeriod;
  }

  public int getWeekOfPeriod() {
    return weekOfPeriod;
  }

  public int getDayOfWeekInt() {
    return dayOfWeekInt;
  }

  public String getDayOfWeek() {
    return dayOfWeek;
  }

  public int getYestareWeekDayInt() {
    return yestareWeekDayInt;
  }

  public String getYestareWeekDay() {
    return yestareWeekDay;
  }

  public void setYenInt(int yenInt) {
    this.setYen(this.intToRoman(yenInt));
    this.yenInt = yenInt;
  }

  public void setYen(String yen) {
    this.yen = yen;
  }

  public void setLoa(int loa) {
    this.loa = loa;
  }

  public void setYestareMap(int[][][] yestareMap) {
    this.yestareMap = yestareMap;
  }

  public void setLoaBeginingDay(int loaBeginingDay) {
    this.loaBeginingDay = loaBeginingDay;
  }

  public void setDayOfLoa(int dayOfLoa) {
    this.dayOfLoa = dayOfLoa;
  }

  public void setLeapLoa(boolean leapLoa) {
    this.leapLoa = leapLoa;
  }

  public void setPeriodOfLoaInt(int periodOfLoaInt) {
    this.setPeriodOfLoa(PERIODS_OF_LOA[periodOfLoaInt - 1]);
    this.periodOfLoaInt = periodOfLoaInt;
  }

  public void setPeriodOfLoa(String periodOfLoa) {
    this.periodOfLoa = periodOfLoa;
  }

  public void setInMonth(boolean inMonth) {
    this.inMonth = inMonth;
  }

  public void setMonthOfLoa(int monthOfLoa) {
    this.monthOfLoa = monthOfLoa;
  }

  public void setDayOfPeriod(int dayOfPeriod) {
    this.dayOfPeriod = dayOfPeriod;
  }

  public void setWeekOfPeriod(int weekOfPeriod) {
    this.weekOfPeriod = weekOfPeriod;
  }

  public void setDayOfWeekInt(int dayOfWeekInt) {
    this.setDayOfWeek(DAYS_OF_WEEK[dayOfWeekInt - 1]);
    this.dayOfWeekInt = dayOfWeekInt;
  }

  public void setDayOfWeek(String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public void setYestareWeekDayInt(int yestareWeekDayInt) {
    this.setYestareWeekDay(DAYS_OF_WEEK[yestareWeekDayInt - 1]);
    this.yestareWeekDayInt = yestareWeekDayInt;
  }

  public void setYestareWeekDay(String yestareWeekDay) {
    this.yestareWeekDay = yestareWeekDay;
  }

  /**
   * Constructor with today's date
   */
  public ImladrisCalendar() {
    this(new GregorianCalendar());
  }

  /**
   * Constructors from Gregorian Dates
   */
  public ImladrisCalendar(GregorianCalendar gregorian) {
    this.setGregorian(new GregorianCalendar(gregorian.get(GregorianCalendar.YEAR), gregorian.get(GregorianCalendar.MONTH), gregorian.get(GregorianCalendar.DAY_OF_MONTH), gregorian.get(GregorianCalendar.HOUR_OF_DAY), gregorian.get(GregorianCalendar.MINUTE), gregorian.get(GregorianCalendar.SECOND)));
    this.convert();
  }

  public ImladrisCalendar(Time sunset, GregorianCalendar gregorian) {
    this.setSunset(sunset);
    this.setGregorian(new GregorianCalendar(gregorian.get(GregorianCalendar.YEAR), gregorian.get(GregorianCalendar.MONTH), gregorian.get(GregorianCalendar.DAY_OF_MONTH), gregorian.get(GregorianCalendar.HOUR_OF_DAY), gregorian.get(GregorianCalendar.MINUTE), gregorian.get(GregorianCalendar.SECOND)));
    this.convert();
  }

  public ImladrisCalendar(int year, int month, int dayOfMonth) { // month is 1-based indexed (1-12|January|December)
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, 0, 0, 1));
    this.convert();
  }

  public ImladrisCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, 1));
    this.convert();
  }

  public ImladrisCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, second));
    this.convert();
  }

  public ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    this.setSunset(sunset);
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, 1));
    this.convert();
  }

  public ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
    this.setSunset(sunset);
    this.setGregorian(new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, second));
    this.convert();
  }

  /**
   * Constructors from Imladris Dates
   */
  // Only for YESTARE & METTARE
  public ImladrisCalendar(String yen, int loa, int period) {
    this(yen, loa, period, 1);
  }

  // For any period, is 1-based indexed (1-9|YESTARE-METTARE)
  public ImladrisCalendar(String yen, int loa, int period, int day) {
    this.setYenInt(this.romanToInt(yen));
    this.setLoa(loa);
    this.setPeriodOfLoaInt(period);
    this.setDayOfPeriod(day);
    this.setGregorian(new GregorianCalendar());
    this.updateFromYenLoaPeriodAndDayOfPeriod();
  }

  /**
   * API Methods
   */
  public boolean before(ImladrisCalendar cal) {
    return !this.same(cal) && !this.after(cal);
  }

  public boolean same(ImladrisCalendar cal) {
    boolean same = false;
    if (this.getYenInt() == cal.getYenInt() && this.getLoa() == cal.getLoa() && this.getDayOfLoa() == cal.getDayOfLoa())
      same = true;
    return same;
  }

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
   *
   * @param field
   * @param amount
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

  /**
   * INTERNAL Methods
   */
  private void convert() {
    this.calculate(this.getGregorian());
  }

  /**
   * With yen, loa, period and dayOfPeriod set, update the rest
   */
  private void updateFromYenLoaPeriodAndDayOfPeriod() {
    int yen = this.getYenInt();
    int loa = this.getLoa();
    int period = this.getPeriodOfLoaInt();
    int dayOfPeriod = this.getDayOfPeriod();
    // initialize data variables
    int loaBeg = 0;
    int daysOfLoa = 0;
    boolean isLeapLoa = false;
    boolean isMonth = false;
    int month = 0;
    int weekOfPeriod = 0;
    int dayOfWeek = 0;
    int yestareWeekDay = 0;

    int y = (yen - 1) * 144 + loa;
    // calculate day of march of year 'y' in which loa begins
    loaBeg = this.loaBeginningDay(y);
    // calculate if is leap loa
    isLeapLoa = this.checkIfLeapLoa(loa);
    // calculate day of Loa
    int[] values = ImladrisCalendar.LENGTH_OF_PERIODS;
    if (isLeapLoa)
      values = ImladrisCalendar.LENGTH_OF_PERIODS_LEAP;
    for (int i = ImladrisCalendar.YESTARE; i < period; i++) {
      daysOfLoa += values[i - 1];
    }
    daysOfLoa += dayOfPeriod;
    // if sunset defined, check if has passed
    if (this.isSunsetDefined()) {
      GregorianCalendar cal = this.getGregorian();
      String str = cal.get(GregorianCalendar.HOUR_OF_DAY) + ":" + cal.get(GregorianCalendar.MINUTE) + ":" + cal.get(GregorianCalendar.SECOND);
      Time time = Time.valueOf(str);
      if (!time.before(this.getSunset())) {
        daysOfLoa++;
      }
    }
    // calculate current week in month (if any), and day of week
    if (this.periodIsMonth(period)) {
      isMonth = true;
      month = this.calculateMonthFromPeriod(period);
    }
    int[] weekInfo = this.calculateWeekAndDayOfWeek(yen, loa, daysOfLoa);
    weekOfPeriod = weekInfo[0];
    dayOfWeek = weekInfo[1];
    yestareWeekDay = weekInfo[2];
    // store data and return
    this.setLoaBeginingDay(loaBeg);
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
   * With yen, loa and dayOfLoa set, update the rest
   */
  private void updateFromYenLoaAndDayOfLoa() {
    int yen = this.getYenInt();
    int loa = this.getLoa();
    int dayOfLoa = this.getDayOfLoa();
    // initialize data variables
    int loaBeg = 0;
    boolean isLeapLoa = false;
    int period = 0;
    int dayOfPeriod = 0;
    boolean isMonth = false;
    int month = 0;
    int weekOfPeriod = 0;
    int dayOfWeek = 0;
    int yestareWeekDay = 0;

    int y = (yen - 1) * 144 + loa;
    // calculate day of march of year 'y' in which loa begins
    loaBeg = this.loaBeginningDay(y);
    // calculate if is leap loa
    isLeapLoa = this.checkIfLeapLoa(loa);
    // calculate current month and day of month
    int[] periodInfo = this.calculatePeriodAndDayInPeriod(dayOfLoa, isLeapLoa);
    period = periodInfo[0];
    dayOfPeriod = periodInfo[1];
    // calculate current week in month (if any), and day of week
    if (this.periodIsMonth(period)) {
      isMonth = true;
      month = this.calculateMonthFromPeriod(period);
    }
    int[] weekInfo = this.calculateWeekAndDayOfWeek(yen, loa, dayOfLoa);
    weekOfPeriod = weekInfo[0];
    dayOfWeek = weekInfo[1];
    yestareWeekDay = weekInfo[2];
    // store data and return
    this.setLoaBeginingDay(loaBeg);
    this.setLeapLoa(isLeapLoa);
    this.setPeriodOfLoaInt(period);
    this.setDayOfPeriod(dayOfPeriod);
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
    // initialize data variables
    int yen = 0;
    int loa = 0;
    int loaBeg = 0;
    int daysOfLoa = 0;
    boolean isLeapLoa = false;
    int period = 0;
    int dayOfPeriod = 0;
    boolean isMonth = false;
    int month = 0;
    int weekOfPeriod = 0;
    int dayOfWeek = 0;
    int yestareWeekDay = 0;

    // if sunset defined, check if has passed
    if (this.isSunsetDefined()) {
      String str = cal.get(GregorianCalendar.HOUR_OF_DAY) + ":" + cal.get(GregorianCalendar.MINUTE) + ":" + cal.get(GregorianCalendar.SECOND);
      Time time = Time.valueOf(str);
      if (!time.before(this.getSunset())) {
        cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
        cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
      }
    }
    int y = cal.get(GregorianCalendar.YEAR);
    // calculate day of march of year 'y' in which loa begins
    loaBeg = this.loaBeginningDay(y);
    GregorianCalendar loaCal = new GregorianCalendar(y, GregorianCalendar.MARCH, loaBeg);
    // if that day hasn't come yet, use previous year's loa
    if (cal.before(loaCal)) {
      y = y - 1;
      loaBeg = this.loaBeginningDay(y);
    }
    // calculate yen
    yen = this.calculateYen(y);
    // calculate loa
    loa = this.calculateLoa(y);
    // calculate if is leap loa
    isLeapLoa = this.checkIfLeapLoa(loa);
    // get amount of days of ongoing loa
    loaCal.set(GregorianCalendar.YEAR, y);
    loaCal.set(GregorianCalendar.DAY_OF_MONTH, loaBeg);
    daysOfLoa = this.daysBetweenGregorian(loaCal, cal);
    // calculate current month and day of month
    int[] periodInfo = this.calculatePeriodAndDayInPeriod(daysOfLoa, isLeapLoa);
    period = periodInfo[0];
    dayOfPeriod = periodInfo[1];
    // calculate current week in month (if any), and day of week
    if (this.periodIsMonth(period)) {
      isMonth = true;
      month = this.calculateMonthFromPeriod(period);
    }
    int[] weekInfo = this.calculateWeekAndDayOfWeek(yen, loa, daysOfLoa);
    weekOfPeriod = weekInfo[0];
    dayOfWeek = weekInfo[1];
    yestareWeekDay = weekInfo[2];
    // store data and return
    this.setYenInt(yen);
    this.setLoa(loa);
    this.setLoaBeginingDay(loaBeg);
    this.setDayOfLoa(daysOfLoa);
    this.setLeapLoa(isLeapLoa);
    this.setPeriodOfLoaInt(period);
    this.setDayOfPeriod(dayOfPeriod);
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
    if (mod == 0)
      yestare = yestareMap;
    else if (mod <= 3)
      yestare = yestareMap + 3;
    else if (mod <= 7)
      yestare = yestareMap + 2;
    else
      yestare = yestareMap + 1;
    return yestare;
  }

  private boolean checkIfLeapLoa(int loa) {
    return loa % 12 == 0;
  }

  private int[] calculatePeriodAndDayInPeriod(int days, boolean leap) {
    int[] daysInPeriod = ImladrisCalendar.LENGTH_OF_PERIODS;
    if (leap)
      daysInPeriod = ImladrisCalendar.LENGTH_OF_PERIODS_LEAP;
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
    int firstYestareDayOfWeek = ImladrisCalendar.LOA_0_YEN_I_DAY_OF_WEEK;

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

  private int daysBetweenGregorian(final GregorianCalendar startDate, final GregorianCalendar endDate) {
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

  public String intToRoman(int num) {
    String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int[] ARABIC = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String roman = "";
    for (int i = 0; i < ROMAN.length; i++) {
      while (num >= ARABIC[i]) {
        num -= ARABIC[i];
        roman += ROMAN[i];
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
    }
    return num;
  }

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

# ImladrisCalendar.java</h1>

## Files
`src/ImladrisCalendar.java` The class that manages de Calendar itself

## Usage

### Constructors

When a new instance is created, the specified date is automatically calculated. This date can be specified using the diferent constructors. 

```java
/**
 * Uses today's date 
 */
ImladrisCalendar();
```

```java
/**
 * Uses the date of the given GregorianCalendar object
 */
ImladrisCalendar(GregorianCalendar gregorian);
```

```java
/**
 * Uses the Gregorian date given.
 * Valid values: year 1-2299 | month 1-12 | day 1-[# of days of month 'month']
 */
ImladrisCalendar(int year, int month, int dayOfMonth);
```

```java
/**
 * Uses Imladris date given.
 * ONLY for Yestare and Mettare.
 * Valid values: yen roman # "I"-"XVI" | loa 1-144 | period 1 OR 9
 */
ImladrisCalendar(String yen, int loa, int period);
```

```java
/**
 * Uses Imladris date given.
 * Valid values: yen roman # "I"-"XVI" | loa 1-144 | period 1-9 | day 1-[# of days of period 'period']
 */
ImladrisCalendar(String yen, int loa, int period, int day);
```

NOTE: loar 140-144 of Yén XVI are not fully tested, and may work wrongly.

# Imladris Calendar Java Library

[ ![Download](https://api.bintray.com/packages/joaquingatica/erutulco/imladris-calendar/images/download.svg) ](https://bintray.com/joaquingatica/erutulco/imladris-calendar/_latestVersion)

Java library for manipulating Imladris Reckoning dates.

## Package and Files
`com.erutulco.utils.ImladrisCalendar` is the package and class name to be used as library.
`src/main/java/com/erutulco/utils/ImladrisCalendar.java` is the class file.

## Setup

Include from `jcenter()` or `mavenCentral()` repositories.
In the following, replace `{version}` with the version you want from the [releases page](https://github.com/joaquingatica/imladris-calendar/releases) without the initial `v` (i.e. 1.2.1-rc).

### Maven

```xml
<dependency>
  <groupId>com.erutulco.utils</groupId>
  <artifactId>imladris-calendar</artifactId>
  <version>{version}</version>
  <type>pom</type>
</dependency>
```

### Gradle

```groovy
compile 'com.erutulco.utils:imladris-calendar:{version}'
```

### Ivy

```xml
<dependency org='com.erutulco.utils' name='imladris-calendar' rev='{version}'>
  <artifact name='imladris-calendar' ext='pom' ></artifact>
</dependency>
```

### Compilation

Clone the repository, and run the gradle command to compile the `jar`:

```bash
gradle jar
```

To compile `jar` files for the Sources, run the corresponding gradle commands:
```bash
gradle sourcesJar
```
The compiled `jar` file is generated in the `build/libs/` folder,

### Javadoc

Run the gradle command to generate the Javadoc:

```bash
gradle javadoc
```

Then open the generated `build/docs/javadoc/index.html` file in your browser.

To compile `jar` files for the Javadoc, run the corresponding gradle commands:
```bash
gradle javadocJar
```
The compiled `jar` file is generated in the `build/libs/` folder,

## Usage

### Importing

Import the package into your class:

```java
import com.erutulco.utils.ImladrisCalendar;
```

### Constructors

When a new instance is created, the specified date is automatically calculated. This date can be specified using the diferent constructors. 

#### From Gregorian date/time

```java
/*
 * Uses today's Gregorian date 
 */
ImladrisCalendar();
```

```java
/*
 * Uses the date of the given GregorianCalendar object
 */
ImladrisCalendar(GregorianCalendar gregorian);
```

```java
/*
 * Uses the Gregorian date given.
 * Valid values: year 1-2299 | month 1-12 | day 1-[# of days of month 'month']
 */
ImladrisCalendar(int year, int month, int dayOfMonth);
```

```java
/*
 * Uses the Gregorian date and time  given.
 */
ImladrisCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) 
```

```java
/*
 * Uses the Gregorian date and time given.
 */
ImladrisCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)
```

##### With sunset

Sunset time for the specified day can also added to the above constructors at the beginning, to be taken into account in the calculation:

```java
ImladrisCalendar(Time sunset)
```

```java
ImladrisCalendar(Time sunset, GregorianCalendar gregorian)
```

```java
ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth);
```

```java
ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth, int hourOfDay, int minute)
```

```java
ImladrisCalendar(Time sunset, int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)
```

#### From Imladris date

```java
/*
 * Uses Imladris date given.
 * ONLY for Yestare and Mettare.
 * Valid values: yen roman # "I"-"XVI" | loa 1-144 | period 1 OR 9
 */
ImladrisCalendar(String yen, int loa, int period);
```

```java
/*
 * Uses Imladris date given.
 * Valid values: yen roman # "I"-"XVI" | loa 1-144 | period 1-9 | day 1-[# of days of period 'period']
 */
ImladrisCalendar(String yen, int loa, int period, int day);
```

NOTE: loar 140-144 of Yén XVI are not fully tested, and may work wrongly.

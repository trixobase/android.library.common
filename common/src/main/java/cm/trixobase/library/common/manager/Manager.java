package cm.trixobase.library.common.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import org.joda.time.DateTime;

import java.net.URL;
import java.util.Calendar;
import java.util.regex.Pattern;

import cm.trixobase.library.common.R;
import cm.trixobase.library.common.constants.BaseName;

/*
 * Powered by Trixobase Enterprise on 11/11/19.
 * updated on 22/02/21.
 */

public class Manager {

    public static class phoneNumber {

        public static String getOperatorOf(String number) {
            number = compute.cleanNumber(number);
            if (number.length() == 9) {
                return findOperator(number);
            }
            return "";
        }

        private static String findOperator(String number) {
            if (number.startsWith("2"))
                return BaseName.OPERATOR_CAMTEL;
            if (number.startsWith("3"))
                return BaseName.OPERATOR_FIXE;
            if (number.startsWith("6")) {
                String suffix = number.substring(1, 3);
                if (suffix.startsWith("9"))
                    return BaseName.OPERATOR_ORANGE;
                if (suffix.startsWith("8") || suffix.startsWith("7"))
                    return BaseName.OPERATOR_MTN;
                if (suffix.startsWith("6"))
                    return BaseName.OPERATOR_NEXTTEL;
                switch (suffix) {
                    case "55":
                    case "56":
                    case "57":
                    case "58":
                    case "59":
                        return BaseName.OPERATOR_ORANGE;
                    case "50":
                    case "51":
                    case "52":
                    case "53":
                    case "54":
                        return BaseName.OPERATOR_MTN;
                }
            }
            return "";
        }
    }

    public static class date {

        public static String getDate() {
            return getDate(getCalendar());
        }

        public static String getDate(Calendar calendar) {
            return calendarToDate(calendar);
        }

        public static String getDate(Long timeInMillis) {
            Calendar calendar = getCalendar();
            calendar.setTimeInMillis(timeInMillis);
            return getDate(calendar);
        }

        public static String getDate(DateTime date) {
            return getDate(date.getMillis());
        }

        public static Calendar getDate(String date) {
            return dateToCalendar(date);
        }

        public static Calendar getDate(String date, String hour) {
            return dateToCalendar(date, hour);
        }

        public static String getDate(int dateCount, int dateMouth, int dateYear) {
            return new StringBuilder()
                    .append(dateCount).append("/")
                    .append(compute.time(dateMouth + 1)).append("/")
                    .append(dateYear).toString();
        }

        public static String getDateFromSql(String sqlDate) {
            return new StringBuilder()
                    .append(sqlDate.substring(8, 10)).append("/")
                    .append(sqlDate.substring(5, 7)).append("/")
                    .append(sqlDate.substring(0, 4)).toString();
        }

        public static DateTime getDateTimeFromSqlDate(String sqlDate) {
            return new DateTime(sqlDateToCalendar(sqlDate).getTimeInMillis());
        }

        public static String getSqlDate() {
            return getSqlDate(getCalendar());
        }

        public static String getSqlDate(DateTime dateTime) {
            return getSqlDate(dateTime.getMillis());
        }

        public static String getSqlDate(Calendar calendar) {
            return calendarToSqlDate(calendar);
        }

        public static String getSqlDate(long timeInMillis) {
            Calendar calendar = getCalendar();
            calendar.setTimeInMillis(timeInMillis);
            return getSqlDate(calendar);
        }

        public static String getSqlDateFromDate(String date, String time) {
            return getSqlDate(dateToCalendar(date, time));
        }

        public static String getSqlDateFromSql(String sqlDate, String time) {
            return getSqlDate(sqlDateToCalendar(sqlDate, time));
        }

        public static String getSqlDateAfterAddMinute(int minuteToAdd) {
            Calendar calendar = getCalendar();
            calendar.add(Calendar.MINUTE, minuteToAdd);
            return getSqlDate(calendar);
        }

        public static String getSqlDateAfterAddMinute(String sqlDate, int minuteToAdd) {
            Calendar calendar = sqlDateToCalendar(sqlDate);
            calendar.add(Calendar.MINUTE, minuteToAdd);
            return getSqlDate(calendar);
        }

        public static String getSqlDateNear(int[] days, int[] hours) {
            Calendar calendar = getNearCalendar(getCalendar(), days, hours);
            return getSqlDate(calendar);
        }

        public static Calendar getDateAfterRemoveMinute(String date, String beginHour, int minuteToRemove) {
            Calendar calendar = dateToCalendar(date);
            calendar.set(Calendar.HOUR_OF_DAY, time.getHour(beginHour));
            calendar.set(Calendar.MINUTE, time.getMinute(beginHour));
            calendar.add(Calendar.MINUTE, -minuteToRemove);
            return calendar;
        }

        public static int computeDateTextColor(String date, String hour) {
            int hourActivity = Integer.valueOf(hour.substring(0, 2));
            int hourToday = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            int color = 3;
            if (getDate(Calendar.getInstance()).equalsIgnoreCase(date)) {
                if ((hourActivity - hourToday) <= 3)
                    color = 2;
            }
            if (getDate(Calendar.getInstance()).equalsIgnoreCase(date)) {
                if ((hourActivity - hourToday) <= 1)
                    color = 1;
            }
            return color;
        }

        public static String getNextDateOf(String date) {
            Calendar calendar = dateToCalendar(date);
            calendar.add(Calendar.DATE, 1);
            return calendarToDate(calendar);
        }

        public static String getNextSqlDateOf(String sqlDate) {
            Calendar calendar = sqlDateToCalendar(sqlDate);
            calendar.add(Calendar.DATE, 1);
            return calendarToSqlDate(calendar);
        }

        public static boolean dateIsPassed(String dateToTest, String hourToTest) {
            return getCalendar().getTimeInMillis() > dateToCalendar(dateToTest, hourToTest).getTimeInMillis();
        }

        public static boolean sqlDateIsPassed(String sqlDate) {
            return getCalendar().getTimeInMillis() > sqlDateToCalendar(sqlDate).getTimeInMillis();
        }

        public static boolean isTodayByDate(String date) {
            return getDate().equalsIgnoreCase(date);
        }

        public static boolean isTomorrowByDate(String date) {
            Calendar calendar = getCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return getDate(calendar).equalsIgnoreCase(date);
        }

        public static boolean isTodayBySqlDate(String sqlDate) {
            return isTodayByDate(getDateFromSql(sqlDate));
        }

        public static boolean isTomorrowBySqlDate(String sqlDate) {
            return isTomorrowByDate(getDateFromSql(sqlDate));
        }

        private static String calendarToDate(Calendar calendar) {
            return new StringBuilder()
                    .append(compute.time(calendar.get(Calendar.DATE)))
                    .append("/").append(compute.time(calendar.get(Calendar.MONTH) + 1))
                    .append("/").append(calendar.get(Calendar.YEAR)).toString();
        }

        private static String calendarToSqlDate(Calendar calendar) {
            return new StringBuilder()
                    .append(calendar.get(Calendar.YEAR))
                    .append("-").append(compute.time(calendar.get(Calendar.MONTH) + 1))
                    .append("-").append(compute.time(calendar.get(Calendar.DAY_OF_MONTH)))
                    .append("T").append(compute.time(calendar.get(Calendar.HOUR_OF_DAY)))
                    .append(":").append(compute.time(calendar.get(Calendar.MINUTE)))
                    .append(":").append(compute.time(calendar.get(Calendar.SECOND))).toString();
        }

        private static Calendar dateToCalendar(String date) {
            return dateToCalendar(date, "00h00");
        }

        private static Calendar dateToCalendar(String date, String mTime) {
            Calendar calendar = getCalendar();
            calendar.set(getYear(date), getMonth(date) - 1, getDayCount(date), time.getHour(mTime), time.getMinute(mTime), 0);
            return calendar;
        }

        private static Calendar sqlDateToCalendar(String sqlDate) {
            return sqlDateToCalendar(sqlDate, "00h00");
        }

        private static Calendar sqlDateToCalendar(String sqlDate, String mTime) {
            return dateToCalendar(getDateFromSql(sqlDate), mTime);
        }

        private static Calendar getNearCalendar(Calendar calendar, int[] days, int[] hours) {
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Calendar nearDate = null;
            do {

                for (int d=0; d<days.length; d++) {
                    if (calendar.get(Calendar.DAY_OF_WEEK) == days[d]) {
                        for (int h=0; h<hours.length; h++) {
                            if (time.getHour(calendar) < hours[h]) {
                                calendar.set(Calendar.HOUR_OF_DAY, hours[h]);
                                nearDate = calendar;
                            }
                            if (nearDate != null) break;
                        }
                    }
                    if (nearDate != null) break;
                }

                if (nearDate == null) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    calendar.set(Calendar.HOUR_OF_DAY, 6);

                }
            } while (nearDate == null);
            return nearDate;
        }

        public static int getDayFromDate(String date) {
            Calendar calendar = dateToCalendar(date);
            return calendar.get(Calendar.DAY_OF_WEEK);
        }

        public static int getDayFromSqlDate(String sqlDate) {
            Calendar calendar = sqlDateToCalendar(sqlDate);
            return calendar.get(Calendar.DAY_OF_WEEK);
        }

        public static String getDayName(Resources resource, String date) {
            Calendar calendar = dateToCalendar(date);
            return getDayName(resource, calendar.get(Calendar.DAY_OF_WEEK));
        }

        public static String getDayName(Resources resource, int DAY_OF_WEEK) {
            switch (DAY_OF_WEEK) {
                case 2:
                    return resource.getString(R.string.label_day_lundi);
                case 3:
                    return resource.getString(R.string.label_day_mardi);
                case 4:
                    return resource.getString(R.string.label_day_mercredi);
                case 5:
                    return resource.getString(R.string.label_day_jeudi);
                case 6:
                    return resource.getString(R.string.label_day_vendredi);
                case 7:
                    return resource.getString(R.string.label_day_samedi);
                case 1:
                    return resource.getString(R.string.label_day_dimanche);
                default:
                    return "Error";
            }
        }

        private static String getMonthName(Resources resource, String date) {
            switch (date.substring(3, 5)) {
                case "01":
                    return resource.getString(R.string.label_month_jan);
                case "02":
                    return resource.getString(R.string.label_month_fev);
                case "03":
                    return resource.getString(R.string.label_month_mar);
                case "04":
                    return resource.getString(R.string.label_month_avr);
                case "05":
                    return resource.getString(R.string.label_month_may);
                case "06":
                    return resource.getString(R.string.label_month_jun);
                case "07":
                    return resource.getString(R.string.label_month_juil);
                case "08":
                    return resource.getString(R.string.label_month_aou);
                case "09":
                    return resource.getString(R.string.label_month_sep);
                case "10":
                    return resource.getString(R.string.label_month_oct);
                case "11":
                    return resource.getString(R.string.label_month_nov);
                case "12":
                    return resource.getString(R.string.label_month_dec);
                default:
                    return "Error";
            }
        }

        private static int getYear(String date) {
            return Integer.valueOf(date.substring(6, 10));
        }

        private static int getYearSql(String sqlDate) {
            return Integer.valueOf(sqlDate.substring(0, 4));
        }

        private static int getMonth(String date) {
            return Integer.valueOf(date.substring(3, 5));
        }

        private static int getMonthSql(String sqlDate) {
            return Integer.valueOf(sqlDate.substring(5, 7));
        }

        static int getDayCount(String date) {
            return Integer.valueOf(date.substring(0, 2));
        }

        static int getDayCountSql(String date) {
            return Integer.valueOf(date.substring(8, 10));
        }

    }

    public static class time {

        public static String getTime(int hour, int minute) {
            return new StringBuilder()
                    .append(compute.time(hour))
                    .append("h")
                    .append(minute).toString();
        }

        public static String getTime() {
            return getTime(Calendar.getInstance());
        }

        public static String getTime(Calendar calendar) {
            return new StringBuilder()
                    .append(compute.time(calendar.get(Calendar.HOUR_OF_DAY)))
                    .append("h")
                    .append(compute.time(calendar.get(Calendar.MINUTE))).toString();
        }

        public static int getHour() {
            return getHour(getCalendar());
        }

        public static int getHour(String time) {
            return Integer.valueOf(time.substring(0, 2));
        }

        public static int getHour(Calendar calendar) {
            return calendar.get(Calendar.HOUR_OF_DAY);
        }

        public static int getMinute(String time) {
            return Integer.valueOf(time.substring(3, 5));
        }

        public static String getTimeFromSqlDate(String sqlDate) {
            return sqlDate.substring(11, 13) + "h" + sqlDate.substring(14, 16);
        }

    }

    public static class compute {

        public static String contactName(String name) {
            if (name == null || name.isEmpty())
                return "Empty Name";

            String[] names = name.split(" ");
            switch (names.length) {
                default:
                case 0:
                    return "Empty Name";
                case 1:
                    return name(names[0]);
                case 2:
                    return name(names[0]).concat(" ").concat(name(names[1]));
            }
        }

        public static String name(String value) {
            if (value == null || value.isEmpty())
                return "";
            return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        }

        public static String nameFromUrl(String url) {
            if (url == null || url.isEmpty())
                return "";

            return url.replace("https://", "")
                    .replace("http://", "")
                    .replace("ftp://", "")
                    .replace("/", "_")
                    .replace(".", "_");
        }

        public static String phoneNumber(String phoneNumber) {
            if (phoneNumber.isEmpty())
                return "";

            phoneNumber = cleanNumber(phoneNumber);
            int numberSize = phoneNumber.length();

            String one, two = "", three = "";

            if (numberSize < 3) {
                one = phoneNumber.substring(0, numberSize);
            } else if (numberSize > 3 && numberSize < 7) {
                one = phoneNumber.substring(0, 3);
                two = phoneNumber.substring(3, numberSize);
            } else {
                one = phoneNumber.substring(0, 3);
                two = phoneNumber.substring(3, 6);
                three = phoneNumber.substring(6, numberSize);
            }
            return one + " " + two + " " + three;
        }

        public static String dateWithDay(Resources resources, String date) {
            return Manager.date.getDayName(resources, date)
                    + " " + Manager.date.getDayCount(date);
        }

        public static String dateWithMonth(Resources resources, String date) {
            return Manager.date.getDayName(resources, date)
                    + " " + Manager.date.getDayCount(date)
                    + " " + Manager.date.getMonthName(resources, date);
        }

        public static String dateWithSqlMonth(Resources resources, String sqlDate) {
            String mDate = date.getDateFromSql(sqlDate);
            return Manager.date.getDayName(resources, mDate)
                    + " " + Manager.date.getDayCount(mDate)
                    + " " + Manager.date.getMonthName(resources, mDate);
        }

        public static String price(int price) {
            return price(String.valueOf(price));
        }

        public static String price(String price) {
            int priceSize = price.length();

            if (priceSize < 3)
                return time(price);

            if (priceSize > 3)
                price = price.substring(0, priceSize - 3).concat(".").concat(price.substring(priceSize - 3, priceSize));

            if (priceSize > 7)
                price = price.substring(0, priceSize - 7).concat(".").concat(price.substring(priceSize - 7, priceSize));

            return price;
        }

        private static String cleanNumber(String number) {
            String numberFormat = number
                    .replace("(", "")
                    .replace(")", "")
                    .replace("-", "")
                    .replace("_", "")
                    .replace(" ", "");

            if (numberFormat.startsWith("00"))
                numberFormat = numberFormat.substring(2);
            if (numberFormat.startsWith("237") && numberFormat.length() == 12)
                numberFormat = numberFormat.substring(3);
            if (numberFormat.startsWith("+237") && numberFormat.length() == 13)
                numberFormat = numberFormat.substring(4);
            return numberFormat;
        }

        public static String time(int value) {
            return time(String.valueOf(value));
        }

        public static String time(String value) {
            if (value == null || value.isEmpty())
                return "00";
            if (value.length() == 1)
                return "0".concat(value);
            return value;
        }

    }
    
    public static class validation {

        public static boolean email(String email) {
            String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            return Pattern.compile(regex).matcher(email).matches();
        }

        public static boolean url(String url) {
            try {
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    return false;

                new URL(url);
                return true;
            } catch (Exception e) {
                showLog(Manager.class, ".validation->url(" + url + "): " + e.getMessage());
                return false;
            }
        }

    }

    public static class maths {

        public static int getPricePromote(int price, int reduction) {
            return reduction > 0 ? computePricePromote(price, reduction) : price;
        }

        private static int computePricePromote(int price, int reduction) {
            double promotion = (double) reduction / 100;
            price = (int) (price - (price * promotion));
            int rest = price % 5;
            price = price - rest;
            return price;
        }

    }
    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void setMetaDada(Context context, String key, String value) {
        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefs.putString(key, value);
        prefs.apply();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void setMetaDada(Context context, String key, boolean value) {
        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefs.putBoolean(key, value);
        prefs.apply();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void setMetaDada(Context context, String key, int value) {
        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefs.putInt(key, value);
        prefs.apply();
    }

    public static String getMetaData(Context context, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    public static int getMetaData(Context context, String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    public static boolean getMetaData(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static void setUpdateDateOf(Context context, String entity) {
        setMetaDada(context, "update_date_".concat(entity), date.getSqlDate());
    }

    public static void showLog(Class java, String error) {
        Log.e(BaseName.LOG, new StringBuilder(java.getSimpleName()).append(error).toString());
    }

}
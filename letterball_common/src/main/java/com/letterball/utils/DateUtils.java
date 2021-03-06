package com.letterball.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtils {
    /**
     * <p>
     * <p>Title:DateUtil.java</p >
     * <p>Description: 用于时间格式化的工具类</p >
     * <p>Date:2020/5/27 11:24</p >
     *
     * @version 1.0
     */
        public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String FORMAT_DAYTIME = "yyyy-MM-dd HH:mm:ss";
        public static final String FORMAT_DAY = "yyyy-MM-dd";
        public static final String FORMAT_TIME = "HH:mm:ss";
        public static final String FORMAT_TIME_SHORT = "HH:mm";
        public static final String FORMAT_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";
        public static final String FORMAT_YYYYMM = "yyyyMM";
        public static final String FORMAT_YYYY = "yyyy";
        public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
        public static final String FORMAT_DAY_CN = "yyyy年MM月dd日";
        public static final String FORMAT_DAY_CN_HM = "yyyy年MM月dd日HH:mm";
        public static final String FORMAT_DAY_SLASH = "yyyy/MM/dd";
        public static final String FORMAT_DAY_CN_MD = "MM月dd日";
        public static final String FORMAT_DAY_CN_MM = "MM月";
        public static final String FORMAT_YYYY_MM = "yyyy-MM";

        public static String addOneDay(String dateStr, String format) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date d = formatter.parse(dateStr, pos);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.DATE, 1);
            d = calendar.getTime();
            return formatter.format(d);
        }

        public static Long getTimeSecond(String timeStr) {
            if (timeStr.length() == 0) {
                return new Long(0);
            }
            Long hour = Long.parseLong(timeStr.substring(0, (timeStr.length() - 6)), 10);
            Long min = Long.parseLong(timeStr.substring((timeStr.length() - 5), (timeStr.length() - 3)), 10);
            Long sec = Long.parseLong(timeStr.substring((timeStr.length() - 2)), 10);
            Long second = hour * 3600 + min * 60 + sec * 1;
            return second;
        }

        // 根据秒计算时分秒的字符串
        public static String getTimeHMSstr(Long second) {
            boolean fu = false;
            if (second < 0) {
                fu = true;
                second = 0 - second;
            }
            long hours = second / (60 * 60);
            long minus = (second - hours * 60 * 60) / 60;
            long secs = second - hours * 60 * 60 - minus * 60;
            String timeStr = "";
            if (hours < 10) {
                timeStr = timeStr + "0";
            }
            timeStr = timeStr + hours;
            timeStr = timeStr + ":";
            if (minus < 10) {
                timeStr = timeStr + "0";
            }
            timeStr = timeStr + minus;
            timeStr = timeStr + ":";
            if (secs < 10) {
                timeStr = timeStr + "0";
                ;
            }
            timeStr = timeStr + secs;
            if (fu) {
                return "-" + timeStr;
            } else {
                return timeStr;
            }
        }

        public static String addDay(String dateStr, String format, int count) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date d = formatter.parse(dateStr, pos);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.DATE, count);
            d = calendar.getTime();
            return formatter.format(d);
        }

        public static Date addOneDay(Date date, String format) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();
            return date;
        }

        public static Date addDay(Date date, int count) {
            Calendar calendar = Calendar.getInstance();
            if (date != null) {
                calendar.setTime(date);
                calendar.add(Calendar.DATE, count);
                date = calendar.getTime();
                return date;
            } else {
                return null;
            }

        }

        public static Date addMinute(Date date, int count) {
            Calendar calendar = Calendar.getInstance();
            if (date != null) {
                calendar.setTime(date);
                calendar.add(Calendar.MINUTE, count);
                date = calendar.getTime();
                return date;
            } else {
                return null;
            }

        }

        // 按照格式将字符串转化成日期
        public static Date stringToDate(String dateStr, String format) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date d = formatter.parse(dateStr, pos);
            return d;
        }

        // 按照格式将日期转化成字符串
        public static String dateToString(Date d, String format) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            if (d == null) {
                return "";
            } else {
                return formatter.format(d);
            }
        }

        // 按照格式将字符串转化为字符串
        public static String stringToString(String temp, String format) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date d = formatter.parse(temp, pos);
            return dateToString(d, format);
        }

        // Date 转化成 Calendar
        public static Calendar dateToCalendar(Date date) {
            if (date == null) {
                return null;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return cal;
            }

        }

        // 按照格式将日期转化成字符串
        public static Date dateToDateByFormat(Date d, String format) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String dateStr = formatter.format(d);
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(dateStr, pos);
            return date;
        }

        public static boolean beforeDay(Date dateCom, Date dateBase) {
            Calendar calendarCom = Calendar.getInstance();
            calendarCom.setTime(dateCom);
            Calendar calendarBase = Calendar.getInstance();
            calendarBase.setTime(dateBase);
            return calendarCom.before(calendarBase);
        }

        public static boolean afterDay(Date dateCom, Date dateBase) {
            Calendar calendarCom = Calendar.getInstance();
            calendarCom.setTime(dateCom);
            Calendar calendarBase = Calendar.getInstance();
            calendarBase.setTime(dateBase);
            return calendarCom.after(calendarBase);
        }

        public static Date addMonth(Date date, int count) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, count);
            Date result = calendar.getTime();
            return result;
        }

        public static Date addYear(Date date, int count) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, count);
            date = calendar.getTime();
            return date;
        }

        public static String addMonth(String dateStr, String format, int count) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date d = formatter.parse(dateStr, pos);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.MONTH, count);
            d = calendar.getTime();
            return formatter.format(d);
        }

        // 获得某一日期的后一天
        public static Date getNextDate(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day + 1);
            return calendar.getTime();
        }

        // 获得某一日期的前一天
        public static Date getPreviousDate(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day - 1);
            return calendar.getTime();
        }

        // 获得某年某月第一天的日期
        public static Date getFirstDayOfMonth(int year, int month) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DATE, 1);
            return calendar.getTime();
        }

        // 获得某月的第一天
        public static Date getFirstDayOfMonth(Date thisDay) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(thisDay);
            calendar.set(Calendar.DATE, 1);
            return calendar.getTime();
        }

        // 获得某年某月最后一天的日期
        public static Date getLastDayOfMonth(int year, int month) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DATE, 1);
            return getPreviousDate(calendar.getTime());
        }

        // 获得某月的最后一天
        public static Date getLastDayOfMonth(Date thisDay) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(thisDay);
            int thisMonth = thisDay.getMonth();
            calendar.set(Calendar.MONTH, thisMonth + 1);
            calendar.set(Calendar.DATE, 1);
            return getPreviousDate(calendar.getTime());
        }

        // 获得当前日期上一个月的最后一天
        public static String getLastDayOfLastMonth() {
            Date thisDay = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(thisDay);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return DateUtils.dateToString(calendar.getTime(), "yyyy-MM-dd");
        }

        // 获得当前日期上一个月的第一天
        public static String getFirstDayOfLastMonth() {
            Date thisDay = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(thisDay);
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return DateUtils.dateToString(calendar.getTime(), "yyyy-MM-dd");
        }

        public static String getLastMonthOfDay() {
            StringBuffer sb = new StringBuffer();
            sb.append(getFirstDayOfLastMonth()).append("@");
            sb.append(getLastDayOfLastMonth());
            return sb.toString();
        }

        // 由年月日构建java.sql.Date类型
        public static Date buildDate(int year, int month, int date) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, date);
            return calendar.getTime();
        }

        // 取得某月的天数
        public static int getDayCountOfMonth(Date thisDay) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(thisDay);
            calendar.set(Calendar.DATE, 0);
            return calendar.get(Calendar.DATE);
        }

        // 取得开始日期和结束日期之间的天数
        public static int getDayCountOfDate(Date startDate, Date endDate) {
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(startDate);
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(endDate);
            return calendarEnd.get(Calendar.DATE) - calendarStart.get(Calendar.DATE) + 1;
        }

        // 计算从一天到某一天的所有日期的LIST
        public static List<Date> getDateList(Date startDate, Date endDate) {
            List<Date> dateList = new ArrayList<Date>();

            if (startDate == null || endDate == null) {
                return dateList;
            }
            String dateFormat = "yyyy-MM-dd";
            long seconds = endDate.getTime() - startDate.getTime();
            long marginDays = seconds / (24 * 60 * 60 * 1000);
            dateList.add(startDate);

            for (int i = 0; i < marginDays; i++) {
                startDate = DateUtils.addOneDay(startDate, dateFormat);
                dateList.add(startDate);
            }
            return dateList;
        }

        // 获得某年某季度的最后一天的日期
        public static Date getLastDayOfQuarter(int year, int quarter) {
            int month = 0;
            if (quarter > 4) {
                return null;
            } else {
                month = quarter * 3;
            }
            return getLastDayOfMonth(year, month);

        }

        // 获得某年某季度的第一天的日期
        public static Date getFirstDayOfQuarter(int year, int quarter) {
            int month = 0;
            if (quarter > 4) {
                return null;
            } else {
                month = (quarter - 1) * 3 + 1;
            }
            return getFirstDayOfMonth(year, month);
        }

        // 获得某年的第一天的日期
        public static Date getFirstDayOfYear(int year) {
            return getFirstDayOfMonth(year, 1);
        }

        // 获得某年的最后一天的日期
        public static Date getLastDayOfYear(int year) {
            return getLastDayOfMonth(year, 12);
        }

        // 判断系统时间是否在开始时间和结束时间范围内
        public static boolean valiSysTimeInScope(Date startDate, Date sysTime, Date endDate) {
            if (endDate.getTime() > sysTime.getTime() && sysTime.getTime() > startDate.getTime()) {
                return true;
            } else {
                return false;
            }
        }

        // 计算两个任意时间中间的间隔天数
        public static int getIntervalDays(Date startday, Date endday) {
            if (startday.after(endday)) {
                Date cal = startday;
                startday = endday;
                endday = cal;
            }
            long sl = startday.getTime();
            long el = endday.getTime();
            long ei = el - sl;
            return (int) (ei / (1000 * 60 * 60 * 24));
        }

        // 获得某天周一的日期
        public static Date getMonday(String date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.stringToDate(date, "yyyy-MM-dd"));
            cal.add(Calendar.DAY_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return cal.getTime();
        }

        public static Date getMonday(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.dateToDateByFormat(date, "yyyy-MM-dd"));
            cal.add(Calendar.DAY_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return cal.getTime();
        }

        // 得到某天是星期几
        public static int getWeekDay(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.dateToDateByFormat(date, "yyyy-MM-dd"));
            cal.add(Calendar.DAY_OF_MONTH, -1);
            int x = cal.get(Calendar.DAY_OF_WEEK);
            return x;
        }

        // 获得某天周日的日期
        public static Date getSunday(String date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.stringToDate(date, "yyyy-MM-dd"));
            cal.add(Calendar.DAY_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.add(Calendar.DAY_OF_MONTH, +6);
            return cal.getTime();
        }

        // 获得最小日期
        public static String getMinDate(String[] dates) {
            if (dates != null && dates.length != 0) {
                Date init = null;
                for (int i = 0; i < dates.length; i++) {
                    Date date = DateUtils.stringToDate(dates[i], DateUtils.FORMAT_DAY);
                    if (i == 0) {
                        init = date;
                    } else {
                        if (init.after(date)) {
                            init = date;
                        }
                    }
                }
                return DateUtils.dateToString(init, DateUtils.FORMAT_DAY);
            }
            return null;
        }

        // 获得最大日期
        public static String getMaxDate(String[] dates) {
            if (dates != null && dates.length != 0) {
                Date init = null;
                for (int i = 0; i < dates.length; i++) {
                    Date date = DateUtils.stringToDate(dates[i], DateUtils.FORMAT_DAY);
                    if (i == 0) {
                        init = date;
                    } else {
                        if (init.before(date)) {
                            init = date;
                        }
                    }
                }
                return DateUtils.dateToString(init, DateUtils.FORMAT_DAY);
            }
            return null;
        }

        public static Date getSunday(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.dateToDateByFormat(date, "yyyy-MM-dd"));
            cal.add(Calendar.DAY_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.add(Calendar.DAY_OF_MONTH, +6);
            return cal.getTime();
        }

        // 将一个时间段按天划分，放入集合
        public static List<Date[]> getDates(Date startTime, Date endTime) {
            List<Date[]> list = new ArrayList<Date[]>();
            String dateFormat = "yyyy-MM-dd";
            Date startDate = DateUtils.dateToDateByFormat(startTime, dateFormat);
            Date endDate = DateUtils.dateToDateByFormat(endTime, dateFormat);
            if (DateUtils.beforeDay(startDate, endDate)) {
                long seconds = endDate.getTime() - startDate.getTime();
                long marginDays = seconds / (24 * 60 * 60 * 1000);
                for (long i = 0; i < marginDays + 1; i++) {
                    Date[] temp = new Date[2];
                    if (i == 0) {
                        temp[0] = startTime;
                    } else {
                        temp[0] = startDate;
                    }
                    if (i == marginDays) {
                        temp[1] = endTime;
                    } else {
                        startDate = DateUtils.addOneDay(startDate, "yyyy-MM-dd");
                        temp[1] = startDate;
                    }
                    list.add(temp);
                }
            } else {
                Date[] onlyOne = new Date[2];
                onlyOne[0] = startTime;
                onlyOne[1] = endTime;
                list.add(onlyOne);
            }
            return list;
        }

        // 根据日期获得是周几
        public static int getDateDayOfWeek(Date date) {
            int count = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            count = day - 1;
            if (count == 0) {
                count = 7;
            }
            return count;
        }

        // 根据日期获得"星期几"
        public static String getWeek(Date date) {
            String week = "";
            switch (DateUtils.getDateDayOfWeek(date)) {
                case 0:
                    week = "星期日";
                    break;
                case 1:
                    week = "星期一";
                    break;
                case 2:
                    week = "星期二";
                    break;
                case 3:
                    week = "星期三";
                    break;
                case 4:
                    week = "星期四";
                    break;
                case 5:
                    week = "星期五";
                    break;
                case 6:
                    week = "星期六";
                    break;
                default:
                    break;
            }
            return week;
        }

        //由日期和时间拼成DATE类型的数据
        public static Date spliceDate(Date date, Date time) {
            return DateUtils.createDateTime(DateUtils.getDateStr(date) + " " + DateUtils.getTimeStr(time));
        }

        /**
         * @param startDay 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
         * @param endDay 被比较的时间
         * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
         * @return 举例：
         *         compareDate("2009-09-12", null, 0);//比较天
         *         compareDate("2009-09-12", null, 1);//比较月
         *         compareDate("2009-09-12", null, 2);//比较年
         */
        public static int compareDate(Date startDay, Date endDay, int stype) {
            int n = 0;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            Date start = null;
            Date end = null;
            if (stype == 1) {
                start = DateUtils.dateToDateByFormat(startDay, "yyyy-MM");
                end = DateUtils.dateToDateByFormat(endDay, "yyyy-MM");
            } else {
                start = DateUtils.dateToDateByFormat(startDay, "yyyy-MM-dd");
                end = DateUtils.dateToDateByFormat(endDay, "yyyy-MM-dd");
            }
            try {
                c1.setTime(start);
                c2.setTime(end);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            //List list = new ArrayList();
            while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
                //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
                n++;
                if (stype == 1) {
                    c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
                } else {
                    c1.add(Calendar.DATE, 1); // 比较天数，日期+1
                }
            }
            n = n - 1;
            if (stype == 2) {
                n = n / 365;
            }
            return n;
        }

        //获取上周周一日期
        public static Date geLastWeekMonday(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getThisWeekMonday(date));
            cal.add(Calendar.DATE, -7);
            return cal.getTime();
        }

        //获取本周周一日期
        public static Date getThisWeekMonday(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            // 获得当前日期是一个星期的第几天
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            // 获得当前日期是一个星期的第几天
            int day = cal.get(Calendar.DAY_OF_WEEK);
            // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            return cal.getTime();
        }

        //获取下周周一日期
        public static Date getNextWeekMonday(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getThisWeekMonday(date));
            cal.add(Calendar.DATE, 7);
            return cal.getTime();
        }

        //获得某天下周日的日期
        public static Date getNextWeekSunday(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getSunday(date));
            cal.add(Calendar.DATE, 7);
            return cal.getTime();
        }

        // 几个简便方法

        public static Date createTime(String time) {
            return DateUtils.stringToDate(time, DateUtils.FORMAT_TIME);
        }

        public static Date createDateTime(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_DAYTIME);
        }

        public static Date createYearTime(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_YYYY_MM);
        }

        public static Date createDateMon(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_DAY_SLASH);
        }

        public static Date createDateFormat(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT);
        }

        public static Date createDateMin(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_DAY_HOUR_MIN);
        }

        public static Date createDateYearMon(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_YYYYMM);
        }

        public static Date createDateYear(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_YYYY);
        }

        public static Date createDateDay(String dateTime) {
            return DateUtils.stringToDate(dateTime, DateUtils.FORMAT_YYYYMMDD);
        }

        public static Date createDate(String date) {
            return DateUtils.stringToDate(date, DateUtils.FORMAT_DAY);
        }

        public static String getTimeStr(Date time) {
            return DateUtils.dateToString(time, DateUtils.FORMAT_TIME);
        }

        public static String getTimeShortStr(Date time) {
            return DateUtils.dateToString(time, DateUtils.FORMAT_TIME_SHORT);
        }

        public static String getDateStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAY);
        }

        public static String getDateTimeYearMonStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_YYYYMM);
        }

        public static String getDateTimeFormatStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT);
        }

        public static String getDateTimeMinStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAY_HOUR_MIN);
        }

        public static String getDateTimeStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAYTIME);
        }

        public static String getDateYearTimeStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_YYYY_MM);
        }

        public static String getDateMonStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAY_SLASH);
        }

        public static String getDateYearStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_YYYY);
        }

        public static String getDateDayStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_YYYYMMDD);
        }

        public static String getDateCNStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAY_CN);
        }

        public static String getDateCNHMStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAY_CN_HM);
        }

        //只显示月和日
        public static String getDateCNMDStr(Date date) {
            return DateUtils.dateToString(date, DateUtils.FORMAT_DAY_CN_MD);
        }
        //只显示月
        public static String getDateCNMMStr(Date date) { return DateUtils.dateToString(date, DateUtils.FORMAT_DAY_CN_MM); }

        /**
         * 当前时间属于上旬中旬还是下旬
         */
        public static String getDateEarly(Date date) {
            String Str;
            int c ;
            //获得当前时间当月的第一天
            Date beginDate = getFirstDayOfMonth(date);
            //计算第一天时间到传入时间的及那个天数
            c = DateUtils.getIntervalDays(beginDate, date);
            Str = getDateCNMMStr(date);
            if(c>=20){ Str = Str+"下旬";
            }else if(c>=10 && c<20){ Str = Str+"中旬";
            }else if(c>=0 && c<10){ Str = Str+"上旬"; }
            return Str;
        }

        // 获得当前日期的上一个月
        public static Date getLastMonthDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -1);
            return calendar.getTime();
        }

        /**
         * 获取参数时间比当前时间的差值
         * @param date  参数时间
         * @param now   当前数据库时间
         * @return
         */

        public static String getPrettyDate(Date date,Date now) {
            try {
                long diff;
                double day_diff;

                diff = ((now.getTime() - date.getTime()) / 1000);

                day_diff = Math.floor(diff / 86400);

                if (day_diff < 0) {
                    return "";
                }

                if (diff < 60) {
                    return "现在";
                } else if (diff < 120) {
                    return "1 分钟前";
                } else if (diff < 3600) {
                    return (int)Math.floor(diff / 60) + " 分钟前";
                } else if (diff < 7200) {
                    return "1 小时前";
                } else if (diff < 86400) {
                    return (int)Math.floor(diff / 3600) + " 小时前";
                } else if (day_diff == 1) {
                    return "昨天";
                } else if (day_diff < 7) {
                    return (int)day_diff + " 天前";
                } else if (day_diff < 31) {
                    return (int)Math.ceil(day_diff / 7) + " 星期前";
                } else if (day_diff >= 31) {
                    return "几个月前";
                } else {
                    return "";
                }
            }catch (Exception e){
                e.printStackTrace();
                return "";
            }
        }

    /**
     * 得到几天前的 字符型时间
     */
    public String getDateBefore(int day){
        Date date = new Date();
        // 往前推的时间天数
        int beforeDay = day;

        Calendar no = Calendar.getInstance();
        no.setTime(date);
        no.set(Calendar.DATE, no.get(Calendar.DATE) - beforeDay);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(no.getTime());
        return format;
    }


    }


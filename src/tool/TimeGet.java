/**
 * 日期相关函数
 */
package tool;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import douler.Public_Var;

public class TimeGet {
	
	String FENGEFU = "-";
	
    public TimeGet() {
    	FENGEFU = Public_Var.FENGEFU;
    }

    public String get(int i, int j) {
        String SystemTime = "";
        //读系统日期 begin
        String strday, strmonth, stryear, strdate, strhour, strmin, strsec,
                strtime;
        int intyear, intmonth, intday, inthour, intmin, intsec;
        Calendar curdate = Calendar.getInstance();
        curdate.add(Calendar.DAY_OF_YEAR, j); //j是与今天的差值
        intyear = curdate.get(curdate.YEAR);
        intmonth = curdate.get(curdate.MONTH) + 1;
        intday = curdate.get(curdate.DAY_OF_MONTH);
        inthour = curdate.get(curdate.HOUR_OF_DAY);
        intmin = curdate.get(curdate.MINUTE);
        intsec = curdate.get(curdate.SECOND);
        String cur = curdate.toString();


        if (intmonth < 10) {
            strmonth = "0" + Integer.toString(intmonth);
        } else {
            strmonth = Integer.toString(intmonth);
        }
        if (intday < 10) {
            strday = "0" + Integer.toString(intday);
        } else {
            strday = Integer.toString(intday);
        }
        stryear = Integer.toString(intyear);

        if (inthour < 10) {
            strhour = "0" + Integer.toString(inthour);
        } else {
            strhour = Integer.toString(inthour);
        }
        if (intmin < 10) {
            strmin = "0" + Integer.toString(intmin);
        } else {
            strmin = Integer.toString(intmin);
        }
        if (intsec < 10) {
            strsec = "0" + Integer.toString(intsec);
        } else {
            strsec = Integer.toString(intsec);
        }
        //读系统日期end
        switch (i) {
        case 1:
            SystemTime = stryear + FENGEFU + strmonth + FENGEFU + strday;
            break;
        case 2:
            SystemTime = strhour + ":" + strmin + ":" + strsec;
            break;
        case 3:
            SystemTime = stryear + FENGEFU + strmonth + FENGEFU + strday + " " + strhour + ":" + strmin + ":" + strsec;
            break;
        default:
            break;
        }
        return SystemTime;
    }

    public String getSun(int i) {
        String SystemTime = "";
        //读系统日期 begin
        String strday, strmonth, stryear, strdate, strhour, strmin, strsec, strtime;
        int intyear, intmonth, intday, inthour, intmin, intsec;
        Calendar curdate = Calendar.getInstance();
        int inttoday = curdate.get(curdate.DAY_OF_WEEK);
        curdate.add(Calendar.DAY_OF_YEAR, (1 - inttoday)); //跳到sunday
        curdate.add(Calendar.DAY_OF_YEAR, (i)); //跳到saturday
        intyear = curdate.get(curdate.YEAR);
        intmonth = curdate.get(curdate.MONTH) + 1;
        intday = curdate.get(curdate.DAY_OF_MONTH);
        inthour = curdate.get(curdate.HOUR_OF_DAY);
        intmin = curdate.get(curdate.MINUTE);
        intsec = curdate.get(curdate.SECOND);

        if (intmonth < 10) {
            strmonth = "0" + Integer.toString(intmonth);
        } else {
            strmonth = Integer.toString(intmonth);
        }
        if (intday < 10) {
            strday = "0" + Integer.toString(intday);
        } else {
            strday = Integer.toString(intday);
        }
        stryear = Integer.toString(intyear);

        if (inthour < 10) {
            strhour = "0" + Integer.toString(inthour);
        } else {
            strhour = Integer.toString(inthour);
        }
        if (intmin < 10) {
            strmin = "0" + Integer.toString(intmin);
        } else {
            strmin = Integer.toString(intmin);
        }
        if (intsec < 10) {
            strsec = "0" + Integer.toString(intsec);
        } else {
            strsec = Integer.toString(intsec);
        }
        //读系统日期end
        SystemTime = stryear + FENGEFU + strmonth + FENGEFU + strday;
        return SystemTime;
    }

    public String getSat(int i) {
        String SystemTime = "";
        //读系统日期 begin
        String strday, strmonth, stryear, strdate, strhour, strmin, strsec,
                strtime;
        int intyear, intmonth, intday, inthour, intmin, intsec;
        Calendar curdate = Calendar.getInstance();
        int inttoday = curdate.get(curdate.DAY_OF_WEEK);
        curdate.add(Calendar.DAY_OF_YEAR, (7 - inttoday)); //跳到saturday
        curdate.add(Calendar.DAY_OF_YEAR, (i)); //跳到saturday
        intyear = curdate.get(curdate.YEAR);
        intmonth = curdate.get(curdate.MONTH) + 1;
        intday = curdate.get(curdate.DAY_OF_MONTH);
        inthour = curdate.get(curdate.HOUR_OF_DAY);
        intmin = curdate.get(curdate.MINUTE);
        intsec = curdate.get(curdate.SECOND);

        if (intmonth < 10) {
            strmonth = "0" + Integer.toString(intmonth);
        } else {
            strmonth = Integer.toString(intmonth);
        }
        if (intday < 10) {
            strday = "0" + Integer.toString(intday);
        } else {
            strday = Integer.toString(intday);
        }
        stryear = Integer.toString(intyear);

        if (inthour < 10) {
            strhour = "0" + Integer.toString(inthour);
        } else {
            strhour = Integer.toString(inthour);
        }
        if (intmin < 10) {
            strmin = "0" + Integer.toString(intmin);
        } else {
            strmin = Integer.toString(intmin);
        }
        if (intsec < 10) {
            strsec = "0" + Integer.toString(intsec);
        } else {
            strsec = Integer.toString(intsec);
        }
        //读系统日期end
        SystemTime = stryear + FENGEFU + strmonth + FENGEFU + strday;
        return SystemTime;
    }

    public String getFestival(String start, String end) {
        String string = "'9999/99/99'";
        String stryear, strmonth, strday, endyear, endmonth, endday;
        int intstryear, intstrmonth, intstrday, intendyear, intendmonth,
                intendday;
        Calendar curdate = Calendar.getInstance();
        Calendar curdate2 = Calendar.getInstance();
        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];
        endyear = end.split(FENGEFU)[0];
        endmonth = end.split(FENGEFU)[1];
        endday = end.split(FENGEFU)[2];

        intstryear = Integer.parseInt(stryear);
        intstrmonth = Integer.parseInt(strmonth);
        intstrday = Integer.parseInt(strday);
        intendyear = Integer.parseInt(endyear);
        intendmonth = Integer.parseInt(endmonth);
        intendday = Integer.parseInt(endday);

        curdate.set(intstryear, intstrmonth - 1, intstrday);
        curdate2.set(intendyear, intendmonth - 1, intendday);

        long day = (curdate2.getTimeInMillis() - curdate.getTimeInMillis()) /
                   1000 / 60 / 60 / 24 + 1;

        for (int i = 1; i <= day; i++) {
            int y = curdate.get(curdate.YEAR);
            int m = curdate.get(curdate.MONTH) + 1;
            int d = curdate.get(curdate.DAY_OF_MONTH);
            String xingqi = curdate.toString().substring(curdate.toString().
                    indexOf(
                            "DAY_OF_WEEK=") + 12,
                    curdate.toString().indexOf("DAY_OF_WEEK=") + 13);
            int xq = Integer.parseInt(xingqi);
            if (xq == 1 || xq == 7
                || (m == 1 && d == 1)
                || (m == 5 && d == 1)
                || (m == 5 && d == 2)
                || (m == 5 && d == 3)
                || (m == 10 && d == 1)
                || (m == 10 && d == 2)
                || (m == 10 && d == 3)
                    ) {
                string += ",'" + y + FENGEFU + m + FENGEFU + d + "'";
            }
            curdate.add(Calendar.DAY_OF_YEAR, 1);
        }
        //string = string.substring(1);
        return string;
    }

    public long getDays(String start, String end) {
        String string = "";
        String stryear, strmonth, strday, endyear, endmonth, endday;
        int intstryear, intstrmonth, intstrday, intendyear, intendmonth,
                intendday;
        Calendar curdate = Calendar.getInstance();
        Calendar curdate2 = Calendar.getInstance();
        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];
        endyear = end.split(FENGEFU)[0];
        endmonth = end.split(FENGEFU)[1];
        endday = end.split(FENGEFU)[2];

        intstryear = Integer.parseInt(stryear);
        intstrmonth = Integer.parseInt(strmonth);
        intstrday = Integer.parseInt(strday);
        intendyear = Integer.parseInt(endyear);
        intendmonth = Integer.parseInt(endmonth);
        intendday = Integer.parseInt(endday);

        int[] ass = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (intendyear % 4 == 0 &&
            (intendyear % 100 != 0 || intendyear % 400 == 0)) {
            ass[1] = 29;
        }
        intstrday = intstrday > ass[intstrmonth - 1] ? ass[intstrmonth - 1] : intstrday;
        intendday = intendday > ass[intendmonth - 1] ? ass[intendmonth - 1] : intendday;

        curdate.set(intstryear, intstrmonth - 1, intstrday);
        curdate2.set(intendyear, intendmonth - 1, intendday);

        long day = (curdate2.getTimeInMillis() - curdate.getTimeInMillis()) / 1000 / 60 / 60 / 24 + 1;

        if (day < 0)
            day = 0 - day;
        return day;
    }

    //新加方法
    public long getDays(String start) {
        long day = 1;
        String stryear, strmonth;
        int intstryear, intstrmonth;
        Calendar curdate = Calendar.getInstance();
        Calendar curdate2 = Calendar.getInstance();
        if (start.length() == 7) {
            stryear = start.split(FENGEFU)[0];
            strmonth = start.split(FENGEFU)[1];

            intstryear = Integer.parseInt(stryear);
            intstrmonth = Integer.parseInt(strmonth);

            int[] ass = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (intstryear % 4 == 0 &&
                (intstryear % 100 != 0 || intstryear % 400 == 0)) {
                ass[1] = 29;
            }

            day = ass[intstrmonth - 1];
        } else if (start.length() == 4) {
            int y = 365;
            intstryear = Integer.parseInt(start);
            if (intstryear % 4 == 0 &&
                (intstryear % 100 != 0 || intstryear % 400 == 0)) {
                y = 366;
            }
            day = y;
        }
        return day;
    }

    public String getLastDay(String start, int i) {
        String stryear, strmonth, strday, endyear, endmonth, endday;
        int intstryear, intstrmonth, intstrday, intendyear, intendmonth,
                intendday;
        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];

        intstryear = Integer.parseInt(stryear);
        intstrmonth = Integer.parseInt(strmonth);
        intstrday = Integer.parseInt(strday);

        int[] ass = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (intstryear % 4 == 0 &&
            (intstryear % 100 != 0 || intstryear % 400 == 0)) {
            ass[1] = 29;
        }
        intstrday = ass[Integer.parseInt(start.substring(5, 7)) - 1];

        Calendar curdate = Calendar.getInstance();
        curdate.set(intstryear, intstrmonth - 1, intstrday);
        curdate.add(Calendar.DAY_OF_YEAR, i);
        intendyear = curdate.get(curdate.YEAR);
        intendmonth = curdate.get(curdate.MONTH) + 1;
        intendday = curdate.get(curdate.DAY_OF_MONTH);
        if (intendmonth < 10) {
            strmonth = "0" + Integer.toString(intendmonth);
        } else {
            strmonth = Integer.toString(intendmonth);
        }
        if (intendday < 10) {
            strday = "0" + Integer.toString(intendday);
        } else {
            strday = Integer.toString(intendday);
        }
        stryear = Integer.toString(intendyear);
        return stryear + FENGEFU + strmonth + FENGEFU + strday;

    }

    public String getDay(String start, int i) {
        String string = "";
        String stryear, strmonth, strday, endyear, endmonth, endday;
        int intstryear, intstrmonth, intstrday, intendyear, intendmonth,
                intendday;
        Calendar curdate = Calendar.getInstance();
        Calendar curdate2 = Calendar.getInstance();
        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];

        intstryear = Integer.parseInt(stryear);
        intstrmonth = Integer.parseInt(strmonth);
        intstrday = Integer.parseInt(strday);

        curdate.set(intstryear, intstrmonth - 1, intstrday);
        curdate.add(Calendar.DAY_OF_YEAR, i);
        intendyear = curdate.get(curdate.YEAR);
        intendmonth = curdate.get(curdate.MONTH) + 1;
        intendday = curdate.get(curdate.DAY_OF_MONTH);
        if (intendmonth < 10) {
            strmonth = "0" + Integer.toString(intendmonth);
        } else {
            strmonth = Integer.toString(intendmonth);
        }
        if (intendday < 10) {
            strday = "0" + Integer.toString(intendday);
        } else {
            strday = Integer.toString(intendday);
        }
        stryear = Integer.toString(intendyear);
        return stryear + FENGEFU + strmonth + FENGEFU + strday;
    }

    public String getSun2(String start, int i) {
        String SystemTime = "";
        //读系统日期 begin
        String strday, strmonth, stryear, strdate, strhour, strmin, strsec, strtime;
        int intyear, intmonth, intday, inthour, intmin, intsec;
        Calendar curdate = Calendar.getInstance();

        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];
        int intstryear = Integer.parseInt(stryear);
        int intstrmonth = Integer.parseInt(strmonth);
        int intstrday = Integer.parseInt(strday);
        curdate.set(intstryear, intstrmonth - 1, intstrday);
        int inttoday = curdate.get(curdate.DAY_OF_WEEK);
        curdate.add(Calendar.DAY_OF_YEAR, (1 - inttoday)); //跳到sunday
        curdate.add(Calendar.DAY_OF_YEAR, (i)); //跳到saturday
        intyear = curdate.get(curdate.YEAR);
        intmonth = curdate.get(curdate.MONTH) + 1;
        intday = curdate.get(curdate.DAY_OF_MONTH);
        inthour = curdate.get(curdate.HOUR_OF_DAY);
        intmin = curdate.get(curdate.MINUTE);
        intsec = curdate.get(curdate.SECOND);
        if (intmonth < 10) {
            strmonth = "0" + Integer.toString(intmonth);
        } else {
            strmonth = Integer.toString(intmonth);
        }
        if (intday < 10) {
            strday = "0" + Integer.toString(intday);
        } else {
            strday = Integer.toString(intday);
        }
        stryear = Integer.toString(intyear);

        if (inthour < 10) {
            strhour = "0" + Integer.toString(inthour);
        } else {
            strhour = Integer.toString(inthour);
        }
        if (intmin < 10) {
            strmin = "0" + Integer.toString(intmin);
        } else {
            strmin = Integer.toString(intmin);
        }
        if (intsec < 10) {
            strsec = "0" + Integer.toString(intsec);
        } else {
            strsec = Integer.toString(intsec);
        }
        //读系统日期end
        SystemTime = stryear + FENGEFU + strmonth + FENGEFU + strday;
        return SystemTime;
    }

    public String getSat2(String start, int i) {
        String SystemTime = "";
        //读系统日期 begin
        String strday, strmonth, stryear, strdate, strhour, strmin, strsec, strtime;
        int intyear, intmonth, intday, inthour, intmin, intsec;
        Calendar curdate = Calendar.getInstance();

        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];
        int intstryear = Integer.parseInt(stryear);
        int intstrmonth = Integer.parseInt(strmonth);
        int intstrday = Integer.parseInt(strday);
        curdate.set(intstryear, intstrmonth - 1, intstrday);
        int inttoday = curdate.get(curdate.DAY_OF_WEEK);
        curdate.add(Calendar.DAY_OF_YEAR, (7 - inttoday)); //跳到sunday
        curdate.add(Calendar.DAY_OF_YEAR, (i)); //跳到saturday
        intyear = curdate.get(curdate.YEAR);
        intmonth = curdate.get(curdate.MONTH) + 1;
        intday = curdate.get(curdate.DAY_OF_MONTH);
        inthour = curdate.get(curdate.HOUR_OF_DAY);
        intmin = curdate.get(curdate.MINUTE);
        intsec = curdate.get(curdate.SECOND);

        if (intmonth < 10) {
            strmonth = "0" + Integer.toString(intmonth);
        } else {
            strmonth = Integer.toString(intmonth);
        }
        if (intday < 10) {
            strday = "0" + Integer.toString(intday);
        } else {
            strday = Integer.toString(intday);
        }
        stryear = Integer.toString(intyear);

        if (inthour < 10) {
            strhour = "0" + Integer.toString(inthour);
        } else {
            strhour = Integer.toString(inthour);
        }
        if (intmin < 10) {
            strmin = "0" + Integer.toString(intmin);
        } else {
            strmin = Integer.toString(intmin);
        }
        if (intsec < 10) {
            strsec = "0" + Integer.toString(intsec);
        } else {
            strsec = Integer.toString(intsec);
        }
        //读系统日期end
        SystemTime = stryear + FENGEFU + strmonth + FENGEFU + strday;
        return SystemTime;
    }

    public int getXqj(String start) {
        String string = "'9999/99/99'";
        String stryear, strmonth, strday;
        int intstryear, intstrmonth, intstrday;
        Calendar curdate = Calendar.getInstance();
        stryear = start.split(FENGEFU)[0];
        strmonth = start.split(FENGEFU)[1];
        strday = start.split(FENGEFU)[2];

        intstryear = Integer.parseInt(stryear);
        intstrmonth = Integer.parseInt(strmonth);
        intstrday = Integer.parseInt(strday);

        curdate.set(intstryear, intstrmonth - 1, intstrday, 0, 0, 0);

        int inttoday = curdate.get(curdate.DAY_OF_WEEK) - 1;
        return inttoday;
    }

    public long tfVisitTime(String firsttime, String secondtime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse(firsttime);
            secondDate = df.parse(secondtime);
        } catch (Exception e) {
            return 0;
        }

        long nDay = (long) (secondDate.getTime() - firstDate.getTime());
        return nDay;
    }

    public String getPeriod(String time){
        String period="";
        if(time==null||time.equals("")){
            period="0";
        }else{
            String[] timeitem=time.split(":");
            period=timeitem[0];
        }


        return period;
    }
    /**
     * presenttime：当前时间
     * settime：规定时间
     * */
    public boolean isTimeCome(String presenttime,String settime){
        String[] time1=presenttime.split(":");
        String[] time2=settime.split(":");
        boolean tf=false;
        int h1=Integer.parseInt(time1[0]);
        int m1=Integer.parseInt(time1[1]);
        int s1=Integer.parseInt(time1[2]);
        int h2=Integer.parseInt(time2[0]);
        int m2=Integer.parseInt(time2[1]);
        int s2=Integer.parseInt(time2[2]);
        if(h1>h2){
            tf = true;
        }else if(h1==h2){
            if(m1>m2){
                tf = true;
            }else if(m1==m2){
                if(s1>=s2){
                    tf=true;
                }else{
                    tf=false;
                }
            }else{
                tf=false;
            }
        }else{
            tf = false;
        }

        return tf;
    }

    public boolean isDateCome(String date1,String date2){
        String[] riqi1=date1.split(FENGEFU);
        String[] riqi2=date2.split(FENGEFU);
        boolean tf=false;
        int y1=Integer.parseInt(riqi1[0]);
        int m1=Integer.parseInt(riqi1[1]);
        int d1=Integer.parseInt(riqi1[2]);
        int y2=Integer.parseInt(riqi2[0]);
        int m2=Integer.parseInt(riqi2[1]);
        int d2=Integer.parseInt(riqi2[2]);
        if(y1<y2){
            tf = true;
        }else if(y1==y2){
            if(m1<m2){
                tf = true;
            }else if(m1==m2){
                if(d1<=d2){
                    tf=true;
                }else{
                    tf=false;
                }
            }else{
                tf=false;
            }
        }else{
            tf = false;
        }

        return tf;
    }

}

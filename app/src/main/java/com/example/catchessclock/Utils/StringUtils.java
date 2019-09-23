package com.example.catchessclock.Utils;

public final class StringUtils {

    public static String getSelectedTimingString(int time) {
        int hours = (int) (time/3600);
        int minutes  = (int) ((time - hours*3600) /60);
        int sec = time - minutes*60 - hours*3600;
        String string;
        if (hours > 0) {
            string = "" + hours + ":" + minutes+ ":" + sec;
        }   else if (minutes > 0) {
            string = "00:" + minutes+ ":" + sec;
        }  else {
            string = "00:00:" + sec;
        }
        return string;
    }

}

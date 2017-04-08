package com.lib.login.utils;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isValidPassword(String str) {
        if (isEmpty(str))
            return false;

        Pattern p = Pattern.compile("^[A-Za-z0-9]{6,16}+$");
        Matcher m = p.matcher(str);
        return m.matches();

    }
    public static boolean isValidNickName(String str){
        String check = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher =regex.matcher(str);
        return matcher.matches();
    }

    public static boolean isValidEmail(String str) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    public static boolean isValidCode(String str) {
        if (isEmpty(str))
            return false;

       if(!isNumeric(str)){
           return false;
       }
        if(str.length()!=6){
            return false;
        }
        return true;

    }
    public static boolean isValidPassword(String... str) {
        String[] array = str;
        if (isEmpty(str))
            return false;
        for (String temp:str){
            Pattern p = Pattern.compile("^[A-Za-z0-9]{6,16}+$");
            Matcher m = p.matcher(temp);
            if(!m.matches()){
                return false;
            }
        }

        return true;

    }

    /**
     * 验证是否是车牌号
     *
     * @param str
     * @return
     */
    public static boolean isValidCarNo(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean ivValidEnString(String str, int length) {
        if (isEmpty(str)) {
            return false;
        }
        if (str.length() < length) {
            return false;
        }
        Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean ifValidSafePassword(String safePwd) {
        if (!isEmpty(safePwd) && safePwd.length() == 6) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String... strTarget) {
        String[] strArray = strTarget;
        boolean isEmpty = false;
        for (String str : strArray) {
            if (StringUtils.isEmpty(str)) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }

    public static boolean isValidCellphone(String mobiles) {
        if (isEmpty(mobiles))
            return false;
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /***
     * 身份证校验
     */
    public static boolean isValidIdCard(String id) {
        if (isEmpty(id))
            return false;
        Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        Matcher m = p.matcher(id);
        return m.matches();
    }


    public static boolean isEmpty(String strTarget) {
        return null == strTarget || strTarget.trim().equals("");
    }

    /***
     * 是否为有效整型
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static String converToStrDate(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date(date);
        return simpleDateFormat.format(dt);
    }

    public static String[] cutOff(String car_no) {
        String[] tempArray = car_no.split("");
        String strList[] = new String[2];
        String temp_no = "";
        String car_lbl = "";
        for (String str : tempArray) {
            if (str.getBytes().length == 1) {
                temp_no +=  str;
            } else {
                car_lbl = str;
            }
        }
        strList[0] = car_lbl;
        strList[1] = temp_no;
        return strList;
    }

    public static int countString(String string) {
        int count=0;
        try {
            count=string.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static String converToSimpleStrDate(long date) {

        long current = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat;
        long offSet = (current - date) / 1000;
        Date dt = new Date(date);
        String returnData;
        if (offSet < 5 * 60) {
            returnData = "刚刚";
        } else if (offSet >= 5 * 60 && offSet < 30 * 60) {
            returnData = offSet / 60 + "分钟前";
        } else if (offSet >= 30 * 60 && offSet < 60 * 60) {
            returnData = "半时前";
        } else if (offSet >= 60 * 60 && offSet < 60 * 60 * 24) {
            returnData = (offSet / 60 / 60) + "小时前";
        } else if (offSet >= 60 * 60 * 24 && offSet < 60 * 60 * 24 * 2) {
            returnData = "昨天";
        } else if (offSet >= 60 * 60 * 24 && offSet < 60 * 60 * 24 * 30) {
            //前天
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            returnData = simpleDateFormat.format(dt);
        } else if (offSet >= 60 * 60 * 24 * 30 && offSet < (60 * 60 * 24 * 30 * 365)) {
            //31天前
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            returnData = simpleDateFormat.format(dt);
        } else {
            //一年前
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnData = simpleDateFormat.format(dt);
        }

        return returnData;
    }

    public static String getSecPhoneNo(String phone) {
        if (!StringUtils.isValidCellphone(phone)) {
            return phone;
        } else {
            String replace = phone.substring(4, 8);
            String result = phone.replace(replace, "****");
            return result;
        }
    }


    public static boolean isUrl(String tag){
        Pattern pattern = Pattern.compile("^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0," + "62})+$");
        Matcher matcher = pattern.matcher(tag);
        return  matcher.matches();
    }


    public static boolean isWebUrl(String tag){
        Pattern pattern = Pattern.compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");
        Matcher matcher = pattern.matcher(tag);
        return  matcher.matches();
    }
}

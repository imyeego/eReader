package com.a16lao.wyh.utils;

import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;

import com.a16lao.wyh.R;
import com.a16lao.wyh.config.Latte;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * date:   2018/5/25 0025 下午 3:00
 * author: caoyan
 * description:
 */

public class StringUtils {
    /**
     * 验证手机号格式
     */
    public static boolean isMobileNumber(String number) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3-9][0-9]{9}$");
        m = p.matcher(number);
        b = m.matches();
        return b;
    }

    public static boolean checkPhoneNumber(String phone) {
        if (phone.length() == 11) {
            if (StringUtils.isMobileNumber(phone)) {
                return false;
            } else {
                ToastUtils.showToast(Latte.getApplication(), "请输入正确的手机号");
                return true;
            }
        } else {
            ToastUtils.showToast(Latte.getApplication(), "手机号长度为11位");
            return true;
        }
    }

    /**
     * 验证密码格式
     */
    public static boolean validatePassword(String password) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("[\\w]{6,16}");
        m = p.matcher(password);
        b = m.matches();
        return b;
    }

    public static SpannableStringBuilder setColoredString(String str, int start, int end, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return builder;
    }

    public static SpannableStringBuilder setClickableString(String str, int start, int end, int color, ClickableSpan clickableSpan) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return builder;
    }


    public static String stringFilter(String str) throws PatternSyntaxException {

        String regEx = "[+/:*?<>|\".]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        return m.replaceAll("");

    }


    /**
     * @param content
     * @param keyword 关键字
     * @return
     */
    public static SpannableString setKeyWordColor(String content, String keyword) {


//        int index = content.indexOf(keyword);
//        int len = keyword.length();
//        String temp = content.substring(0, index) + content.substring(index, index + len) + content.substring(index + len, content.length());
//
//        return temp;


        SpannableString builder = new SpannableString(content);
        Pattern p = Pattern.compile(stringFilter(keyword).trim());
        Matcher m = p.matcher(builder);


        if (content.contains(keyword) && !TextUtils.isEmpty(keyword)) {
            while (m.find()) {

                builder.setSpan(new ForegroundColorSpan(Latte.getApplication().getResources()
                        .getColor(R.color.color_13)), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return builder;
    }

    // 空的时候返回true
    public static boolean isNullString(String str) {

        return (null == str || "".equals(str) || "null".equalsIgnoreCase(str));
    }
}

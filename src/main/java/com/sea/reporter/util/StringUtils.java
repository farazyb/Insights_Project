/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sea.reporter.util;

/**
 * @author navand
 */
public class StringUtils {

    /**
     * Add character {@param c} to left of the {@param s} until final length be equal to {@param len}
     *
     * @param s
     * @param len
     * @param c
     * @return
     */
    public static String padleft(String s, int len, char c) {
        if ((s = s.trim()).length() > len) {
            return s.substring((s = s.trim()).length() - len);
        }
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        while (fill-- > 0) {
            d.append(c);
        }
        d.append(s);
        return d.toString();
    }

    /**
     * Add character {@param c} to right of the {@param s} until final length be equal to {@param len}
     *
     * @param s
     * @param len
     * @param c
     * @return
     */
    public static String padright(String s, int len, char c) {
        if ((s = s.trim()).length() > len) {
            return s.substring(0, len);
        }
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        d.append(s);
        while (fill-- > 0) {
            d.append(c);
        }
        return d.toString();
    }

    public static String getDatePart(String dateTime) {
        return dateTime.split(" ")[0];
    }
}

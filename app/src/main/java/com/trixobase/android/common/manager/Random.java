package com.trixobase.android.common.manager;

import java.util.Calendar;

/*
 * Powered by Trixobase Enterprise on 14/12/19.
 */

public class Random {

    public static class Builder<T> {

        private final T[] elements;

        @SafeVarargs
        Builder(T... choices) {
            this.elements = choices;
        }

        public T build() {
            return elements[getInt(elements.length)];
        }

        private int getInt(int size) {
            int value = 0;
            int t = (Calendar.getInstance().get(Calendar.MILLISECOND));
            for (int i=0 ; i<t ; i++) {
                value++;
                value = value == size ? 0 : value;
            }
            return value;
        }
    }

    public static Builder<String[]> builder(String[] elements) {
        return new Builder<String[]>(elements);
    }

    public static Builder<Long[]> builder(Long[] elements) {
        return new Builder<Long[]>(elements);
    }

    public static Builder<int[]> builder(int[] elements) {
        return new Builder<>(elements);
    }

    public static Builder<double[]> builder(double[] elements) {
        return new Builder<>(elements);
    }

    public static Builder<char[]> builder(char[] elements) {
        return new Builder<>(elements);
    }

    public static String getNewSerial(int size) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder serial = new StringBuilder();

        for (int i=0 ; i<size ; i++) {
            serial.append(chars.charAt(getInt(chars.length() - 1)));
        }
        return serial.toString();
    }

    public static String getNewMatricule(int size) {
        String chars = "0123456789";
        StringBuilder id = new StringBuilder(chars.charAt(1 + getInt(chars.length() - 1)));

        for (int i=0 ; i<size ; i++) {
            id.append(chars.charAt(getInt(chars.length() - 1)));
        }
        return id.toString();
    }

    private static int getInt(int size) {
        java.util.Random generator = new java.util.Random();
        return generator.nextInt(size);
    }

    public static boolean getBoolean() {
        return getInt(2) == 0;
    }

}

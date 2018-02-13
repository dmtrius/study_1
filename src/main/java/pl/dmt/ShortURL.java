package pl.dmt;

/*
 * ShortURL (https://github.com/delight-im/ShortURL)
 * Copyright (c) delight.im (https://www.delight.im/)
 * Licensed under the MIT License (https://opensource.org/licenses/MIT)
 */

//import sun.plugin2.message.helper.URLHelper;

import java.net.URL;

/**
 * ShortURL: Bijective conversion between natural numbers (IDs) and short strings
 * <p>
 * ShortURL.encode() takes an ID and turns it into a short string
 * ShortURL.decode() takes a short string and turns it into an ID
 * <p>
 * Features:
 * + large alphabet (51 chars) and thus very short resulting strings
 * + proof against offensive words (removed 'a', 'e', 'i', 'o' and 'u')
 * + unambiguous (removed 'I', 'l', '1', 'O' and '0')
 * <p>
 * Example output:
 * 123456789 <=> pgK8p
 */
public class ShortURL {

    private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    private static final int BASE = ALPHABET.length();

    public static String encode(int num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt(num % BASE));
            num = num / BASE;
        }
        return str.toString();
    }

    public static int decode(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

}

class UrlShortener {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt(num % BASE));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static int decode(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++)
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        return num;
    }
}

class UrlShortener2 {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt(num % BASE));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static int decode(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++)
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        return num;
    }
}

class UrlShortener2Test {
    public static void main(String[] args) {
        final String url = "http://google.com/?q=123";
        int decode = UrlShortener2.decode(url);
        System.out.println(decode);
        System.out.println(UrlShortener2.encode(decode));
    }
}

class TinyUrl {
    private final String characterMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int charBase = characterMap.length();

    public String covertToCharacter(int num) {
        final StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(characterMap.charAt(num % charBase));
            num /= charBase;
        }

        return sb.reverse().toString();
    }

    public int covertToInteger(final String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++)
            num += characterMap.indexOf(str.charAt(i)) * Math.pow(charBase, (str.length() - (i + 1)));

        return num;
    }
}

class TinyUrlTest {
    public static void main(String... args) {
        final TinyUrl tinyUrl = new TinyUrl();
        int num = 122312215;
        String url = tinyUrl.covertToCharacter(num);
        System.out.println("Tiny url:  " + url);
        System.out.println("Id: " + tinyUrl.covertToInteger(url));
    }
}
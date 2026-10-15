package com.java.corse.rest;

import java.util.Arrays;

public class Exercices {

    public static void main(String[] args) {
        // Test cases
        String w1 = "radar";
        String w2 = "Hello";
        String sentence = "Borrow or rob";

        System.out.println("Is '" + w1 + "' a palindrome? " + isPalindrome(w1)); // Should be true
        System.out.println("Is '" + w2 + "' a palindrome? " + isPalindrome(w2)); // Should be false
        System.out.println("Is '" + sentence + "' a palindrome? " + isPalindrome(sentence)); // Should be false

        //
        String word1 = "Listen";
        String word2 = "Silent";

        String word3 = "Triangle";
        String word4 = "Integral";

        String word5 = "Hello";
        String word6 = "World";

        System.out.println("Are '" + word1 + "' and '" + word2 + "' anagrams? " + isAnagram(word1, word2)); // Should be
                                                                                                            // true
        System.out.println("Are '" + word3 + "' and '" + word4 + "' anagrams? " + isAnagram(word3, word4)); // Should be
                                                                                                            // true
        System.out.println("Are '" + word5 + "' and '" + word6 + "' anagrams? " + isAnagram(word5, word6)); // Should be
                                                                                                            // false
    }

    //
    public static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        // To StringBuffer
        StringBuffer reveresedText = new StringBuffer(text).reverse();

        return reveresedText.toString().equals(text);
    }

    // Write a function in Java that takes two strings as input and returns true if
    // they are anagrams of each other
    // (meaning they contain the exact same characters with the exact same
    // frequency, ignoring spaces and
    // uppercase/lowercase differences), or false otherwise.

    public static String transformString(String str) {

        String cleaned = str.toLowerCase().replaceAll("\\s+", "");

        char[] charStr = cleaned.toCharArray();
        Arrays.sort(charStr);

        return new String(charStr);
    }

    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        return transformString(str1).equals(transformString(str2));

    }
}

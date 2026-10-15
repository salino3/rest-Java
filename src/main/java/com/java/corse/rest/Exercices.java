package com.java.corse.rest;

public class Exercices {

    public static void main(String[] args) {
        // Test cases
        String word1 = "radar";
        String word2 = "Hello";
        String sentence = "Borrow or rob";

        System.out.println("Is '" + word1 + "' a palindrome? " + isPalindrome(word1)); // Should be true
        System.out.println("Is '" + word2 + "' a palindrome? " + isPalindrome(word2)); // Should be false
        System.out.println("Is '" + sentence + "' a palindrome? " + isPalindrome(sentence)); // Should be false
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
}

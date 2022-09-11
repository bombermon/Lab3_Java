package org.example;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Letters {
    private int lower;
    private String  logs = "";
    private int upper;
    private int space;
    private int integer;
    private int sum;
    private int dots;
    private int floats;
    private String temp;
    private String withoutdots;

    private String text;

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public String getLogs(){
        return this.logs;
    }
    public Letters add(Letters letters){
        this.upper += letters.upper;
        this.lower += letters.lower;
        return this;
    }
    public static Letters sum(Letters l1, Letters l2){
        Letters main_elem = new Letters();
        main_elem.add(l1);
        main_elem.add(l2);
        return main_elem;
    }
    public static Letters stringToLettersCount(String s){
        Letters ans = new Letters();
        String[] words = s.split(" ");
        ans.text = s;
        ans.logs += s + "\n";
        for (char elem : s.toCharArray()) {
            if (Character.isUpperCase(elem)) ans.upper++;
            else if (Character.isLowerCase(elem)) ans.lower++;
            else if (Character.isWhitespace(elem)) ans.space++;
            else if (elem == "!".charAt(0)) ans.dots++;
            else if (elem == "\"".charAt(0)) ans.dots++;
            else if (elem == ",".charAt(0)) ans.dots++;
            else if (elem == ".".charAt(0)) ans.dots++;
            else if (elem == "?".charAt(0)) ans.dots++;

        }
        ans.withoutdots = s.replaceAll("[.?!,\"]","");
        ans.logs += "================СПИСОК ПЛАВАЮЩИХ ЧИСЕЛ=================" + "\n";
        System.out.println("================СПИСОК ПЛАВАЮЩИХ ЧИСЕЛ=================");
        for (String i : words) {
            if(Pattern.matches("[+-]?([0-9]+[.])+[0-9]+", i)) {
                ans.floats++;
                ans.logs += String.format("%.2f", Double.valueOf(i)) + "\n";
                System.out.println(String.format("%.2f", Double.valueOf(i)));
            }


        }
        ans.logs += "================СПИСОК ЦЕЛЫХ ЧИСЕЛ=====================" + "\n";
        System.out.println("================СПИСОК ЦЕЛЫХ ЧИСЕЛ=====================");
        for (String i : words) {
            if (Pattern.matches("[0-9]+", i)) {
                ans.integer++;
                ans.logs += String.format("%h", i) + "\n";
                System.out.println(String.format("%h", i));
            }

        }
        ans.sum = ans.lower + ans.upper;
        ans.temp = "UpperCase: " + ans.upper + ", LowerCase: " + ans.lower + ", Sum: " + ans.sum + ", Spaces: " + ans.space + ", Integers: " + ans.integer + ", Float: " + ans.floats + ", Знаки препинания: " + ans.dots;
        ans.logs += "====================РЕЗУЛЬТАТ=====================\n" + ans.temp + "\n";
        ans.logs += "=========================БЕЗ ЗНАКОВ ПРЕПИНАНИЯ==============\n" + ans.withoutdots + "\n";
        System.out.println("=========================БЕЗ ЗНАКОВ ПРЕПИНАНИЯ==============\n" + ans.withoutdots + "\n");
        //System.out.println(ans.logs);
        //System.out.println("------------------------------------");
        return ans;
    }


    public String toString(String wordToFind) {

        System.out.println("====================РЕЗУЛЬТАТ=====================");
        String text = this.text;
        Pattern word = Pattern.compile(wordToFind);
        Matcher match = word.matcher(text);
        while (match.find()) {
            this.logs += "Найдено слово " + wordToFind + " на индексах " + match.start() +" - "+ (match.end()-1) + "\n";
            System.out.println("Найдено слово " + wordToFind + " на индексах " + match.start() +" - "+ (match.end()-1));
        }
        /******************* Writing to a text file ********************/

        try {
            FileWriter myWriter = new FileWriter("Results.txt");
            myWriter.write(this.logs);
            myWriter.close();

        } catch (Exception e) {

            e.printStackTrace();
        }


        return "UpperCase: " + upper + ", LowerCase: " + lower + ", Sum: " + sum + ", Spaces: " + space + ", Integers: " + integer + ", Float: " + floats + ", Знаки препинания: " + dots;
    }


}

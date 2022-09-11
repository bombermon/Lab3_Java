package org.example;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class files {
    public final static String emptyFileName="file1.txt";
    public final static String textFileName="Input.txt";
    String main_elem;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

/******************* Reading from a text file *************************/
// using FileReader
                StringBuilder sb= new StringBuilder();
        try {
            FileReader myReader = new FileReader(textFileName);
            try(Scanner sc = new Scanner(new FileInputStream(textFileName))) {
                int count = 0;
                while (sc.hasNext()) {
                    sc.next();
                    count++;
                }
                System.out.println("Number of words: " + count);
            }
            System.out.print("Введите слово для поиска в тексте: ");
            Scanner sc= new Scanner(System.in);
            String temp_str= sc.nextLine();
            FileReader fr = new FileReader("Input.txt");
            BufferedReader br = new BufferedReader(fr);
            br.lines()
                    .map(Letters::stringToLettersCount)
                    .reduce(Letters::sum)
                    .ifPresent(l -> System.out.println(l.toString(temp_str)));



            br.close();


            int character=myReader.read();
            while(character!=-1)
            {   //System.out.print((char) character);
                sb.append((char) character);
                character=myReader.read();

            }

            myReader.close();

        } catch (Exception e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }

        System.out.println(sb.toString());











    }
}

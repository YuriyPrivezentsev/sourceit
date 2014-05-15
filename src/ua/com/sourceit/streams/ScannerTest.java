package ua.com.sourceit.streams;

import java.util.Scanner;

/**
 * Created by yuriy on 10.04.14.
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            System.out.println(scanner.nextInt());
        }
        scanner.close();
        System.out.format("%d\n", 12);
    }

}

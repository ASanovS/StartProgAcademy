package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the speed of the car");
            var speed = scanner.nextDouble();

            System.out.println("Enter the time of the hours on the road");
            var time = scanner.nextDouble();

            if (speed <= 0 || time <= 0) {
                System.out.println("Speed or time cannot be less than or equal to zero.");
                break;
            }

            var result = speed * time;
            System.out.println("The car will drive " + result + " kilometers");
        }
    }
}
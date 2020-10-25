package ru.ifmo.se.lab1;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.*;

public class Lab1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long d[] = new long[(15 - 3)/2 + 1];
        for (int i = 0; i < d.length; i++)
            d[i] = 15 - 2*i;

        double x[] = new double[17];
        for (int i = 0; i < x.length; i++)
            x[i] = random() * 17 - 8;

        double g[][] = new double[7][17];
        int[] a = {3, 11, 15};

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (d[i] == 9)
                    g[i][j] = pow(pow(pow(2. / 3 * x[j], x[j]) / (1 - log(abs(x[j]))), 3), 4 * (pow(0.5 * sin(x[j]), 2) + 0.25));
                else if (Arrays.binarySearch(a, (int) d[i]) >= 0)
                    g[i][j] = pow(pow(4 / (1 - pow(x[j], x[j])), 2) / 2, 3);
                else
                    g[i][j] = pow(pow((1 - cos(atan((x[j] + 0.5) / 17))) / pow(pow(x[j] / (x[j] - 1), 3), (atan((x[j] + 0.5) / 17) + 1) / pow(3 * x[j], 3)), 2) / 2, 3);
            }
        }

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++)
                System.out.printf("%.2f\t", g[i][j]);
            System.out.println();
        }
        in.close();
    }
}

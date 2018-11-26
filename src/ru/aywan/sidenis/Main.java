package ru.aywan.sidenis;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String[] list;
        int size = Integer.parseInt(bi.readLine());
        MatrixSummator s = new MatrixSummator(size, bi);
        int opCount = Integer.parseInt(bi.readLine());
        for (int k = 0; k < opCount; k++) {
            list = bi.readLine().split(" ");
            s.parseOp(list);
        }
    }
}

class MatrixSummator {
    private static final String MOD = "2";
    private static final String SUM = "1";

    private final int size;
    private final long[][] mtSum;
    private final long[][] mt;

    MatrixSummator(int size, BufferedReader r) throws IOException {
        this.size = size + 1;
        this.mtSum = new long[this.size][this.size];
        this.mt = new long[this.size][this.size];

        String[] list;

        for (int i = 1; i < this.size; i++) {
            list = r.readLine().split(" ");
            for (int j = 1; j < this.size; j++) {
                this.mt[j][i] = Long.parseLong(list[j - 1]);
                this.mtSum[j][i] = this.mt[j][i] + this.mtSum[j][i - 1];
            }
        }
    }

    void parseOp(String[] args) {
        switch (args[0]) {
            case MOD:
                this.modValue(
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Long.parseLong(args[3])
                );
                break;

            case SUM:
                this.printSum(
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]),
                        Integer.parseInt(args[4])
                );
                break;
        }
    }

    void printSum(int x1, int y1, int x2, int y2) {
        System.out.println(this.getSum(x1, y1, x2, y2));
    }

    long getSum(int x1, int y1, int x2, int y2) {
        x2++;
        y2++;

        long sum = 0;

        for (int i = x1 + 1; i <= x2; i++) {
            sum += this.mtSum[i][y2] - this.mtSum[i][y1];
        }

        return sum;
    }

    void modValue(int x, int y, long val) {
        x++;
        y++;

        long diff = val - this.mt[x][y];
        this.mt[x][y] = val;

        for (int i = y; i < this.size; i++) {
            this.mtSum[x][i] += diff;
        }
    }
}

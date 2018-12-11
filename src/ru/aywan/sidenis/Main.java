package ru.aywan.sidenis;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    final static private int BUFFER_SIZE = 1 << 13;
    static private DataInputStream din;
    static private byte[] buffer;
    static private int bufferPointer = 0, bytesRead = 0;

    private static int x1, y1, x2, y2;
    private static long val;

    public static void main(String[] args) throws IOException {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        PrintWriter w = new PrintWriter(new BufferedOutputStream(System.out, BUFFER_SIZE));

        int size = nextInt() + 1;

        long[][] mt = new long[size][size];

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                mt[j][i] = nextLong() + mt[j][i - 1];
            }
        }

        int q = nextInt();

        for (int j = 0; j < q; j++) {
            switch (nextInt()) {

                case 2:

                    x1 = nextInt() + 1;
                    y1 = nextInt() + 1;
                    val = nextLong() - mt[x1][y1] + mt[x1][y1 - 1];

                    for (int i = y1; i < size; i++) {
                        mt[x1][i] += val;
                    }

                    break;

                case 1:

                    x1 = nextInt();
                    y1 = nextInt();

                    x2 = nextInt() + 1;
                    y2 = nextInt() + 1;

                    val = 0;

                    for (int i = x1 + 1; i <= x2; i++) {
                        val += mt[i][y2] - mt[i][y1];
                    }

                    w.println(val);
                    break;
            }
        }

        w.flush();
    }

    private static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();

        while (c <= ' ') {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');

        return ret;
    }

    private static long nextLong() throws IOException {
        long ret = 0;
        byte c = read();

        while (c <= ' ') {
            c = read();
        }

        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');

        return neg ? -ret : ret;
    }

    private static void fillBuffer() throws IOException {
        bufferPointer = 0;
        bytesRead = din.read(buffer, 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private static byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }
}

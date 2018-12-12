package ru.aywan.sidenis;

import java.io.IOException;

public class Main {

    final static private int BUFFER_SIZE = 1 << 13;
    static private byte[] buffer;
    static private byte[] obuffer;
    static private int bufferPointer = 0, bytesRead = 0;
    static private int oBufSize = 0;

    public static void main(String[] args) throws IOException {
        buffer = new byte[BUFFER_SIZE];
        obuffer = new byte[BUFFER_SIZE];

        int size = nextInt() + 1;
        long[][] mt = new long[size][size];

        int x1, y1, x2, y2;
        long val;

        for (int i = 1; i < size; i++) {
            val = 0;
            for (int j = 1; j < size; j++) {
                val += nextLong();
                mt[i][j] = val;
            }
        }

        int q = nextInt();

        for (int j = 0; j < q; j++) {
            switch (nextInt()) {

                case 2:

                    y1 = nextInt() + 1;
                    x1 = nextInt() + 1;
                    val = nextLong() - mt[x1][y1] + mt[x1][y1 - 1];

                    for (int i = y1; i < size; i++) {
                        mt[x1][i] += val;
                    }

                    break;

                case 1:

                    y1 = nextInt();
                    x1 = nextInt();

                    y2 = nextInt() + 1;
                    x2 = nextInt() + 1;

                    val = 0;

                    for (int i = x1 + 1; i <= x2; i++) {
                        val += mt[i][y2] - mt[i][y1];
                    }

                    writeln(val);
                    break;
            }
        }

        flush();
    }

    private static void writeln(long v)
    {
        byte[] s = String.valueOf(v).getBytes();
        if (oBufSize + s.length + 1 >= BUFFER_SIZE) {
            flush();
        }
        System.arraycopy(s, 0, obuffer, oBufSize, s.length);
        oBufSize += s.length;
        obuffer[oBufSize++] = '\n';

    }

    private static void flush()
    {
        System.out.write(obuffer, 0, oBufSize);
        oBufSize = 0;
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
        bytesRead = System.in.read(buffer,0, BUFFER_SIZE);
    }

    private static byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }
}

package ru.aywan.sidenis;

import java.io.IOException;

public class Main {

    final static private int IN_BUFFER_SIZE = 1 << 13;
    final static private int OUT_BUFFER_SIZE = 1 << 11;

    static private byte[] iBuffer = new byte[IN_BUFFER_SIZE];
    static private byte[] oBuffer = new byte[OUT_BUFFER_SIZE];
    static private byte[] itoaBuf = new byte[20];
    static private int iBufPoint = 0;
    static private int oBufPoint = 0;
    static private long[][] mt = new long[551][551];

    public static void main(String[] args) throws IOException {

        itoaBuf[19] = '\n';
        fillBuffer();

        int size = nextInt() + 1;

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
            if (nextNumByte() == '1') {

                y1 = nextInt();
                x1 = nextInt();

                y2 = nextInt() + 1;
                x2 = nextInt() + 1;

                val = 0;

                while (x1 < x2) {
                    x1++;
                    val += mt[x1][y2] - mt[x1][y1];
                }

                writeln(val);

            } else {

                y1 = nextInt() + 1;
                x1 = nextInt() + 1;
                val = nextLong() - mt[x1][y1] + mt[x1][y1 - 1];
                while (y1 < size) {
                    mt[x1][y1++] += val;
                }
            }
        }

        flush();
    }

    private static void writeln(long v) {
        int p = 18;
        boolean neg = v < 0;
        if (neg) {
            v = -v;
        }
        while (v > 0) {
            itoaBuf[p--] = (byte) (v % 10 + '0');
            v /= 10;
        }
        if (neg) {
            itoaBuf[p--] = '-';
        }

        int c = 19 - p;
        if (oBufPoint + c >= OUT_BUFFER_SIZE) {
            flush();
        }
        System.arraycopy(itoaBuf, p + 1, oBuffer, oBufPoint, c);
        oBufPoint += c;
    }

    private static void flush() {
        System.out.write(oBuffer, 0, oBufPoint);
        oBufPoint = 0;
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

    private static byte nextNumByte() throws IOException {
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        return c;
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
        iBufPoint = 0;
        System.in.read(iBuffer, 0, IN_BUFFER_SIZE);
    }

    private static byte read() throws IOException {
        if (iBufPoint == IN_BUFFER_SIZE) {
            fillBuffer();
        }
        return iBuffer[iBufPoint++];
    }
}

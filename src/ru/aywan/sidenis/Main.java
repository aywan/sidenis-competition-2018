package ru.aywan.sidenis;

import java.io.IOException;

public class Main {

    final static private int BUFFER_SIZE = 1 << 13;

    static private byte[] iBuffer = new byte[BUFFER_SIZE];
    static private byte[] oBuffer = new byte[BUFFER_SIZE];
    static private byte[] itoaBuf = new byte[20];
    static private int iBufPoint = 0;
    static private int oBufPoint = 0;
    static private long[][] mt = new long[551][551];

    public static void main(String[] args) throws IOException {

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
            int op = nextInt();

            if (op == 2) {

                y1 = nextInt() + 1;
                x1 = nextInt() + 1;
                val = nextLong() - mt[x1][y1] + mt[x1][y1 - 1];

                for (int i = y1; i < size; i++) {
                    mt[x1][i] += val;
                }
            } else if (op == 1) {

                y1 = nextInt();
                x1 = nextInt();

                y2 = nextInt() + 1;
                x2 = nextInt() + 1;

                val = 0;

                for (int i = x1 + 1; i <= x2; i++) {
                    val += mt[i][y2] - mt[i][y1];
                }

                writeln(val);
            }
        }

        flush();
    }

    private static void writeln(long v) {
        int p = 19;
        int c = 0;
        boolean neg = v < 0;
        if (neg) {
            v = -v;
        }
        while (v > 0) {
            itoaBuf[p--] = (byte) (v % 10 + '0');
            v = v / 10;
            c++;
        }
        if (neg) {
            itoaBuf[p--] = '-';
            c++;
        }

        if (oBufPoint + c + 1 >= BUFFER_SIZE) {
            flush();
        }
        System.arraycopy(itoaBuf, p + 1, oBuffer, oBufPoint, c);
        oBufPoint += c;
        oBuffer[oBufPoint++] = '\n';
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
        System.in.read(iBuffer, 0, BUFFER_SIZE);
    }

    private static byte read() throws IOException {
        if (iBufPoint == BUFFER_SIZE) {
            fillBuffer();
        }
        return iBuffer[iBufPoint++];
    }
}

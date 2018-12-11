package ru.aywan.sidenis;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter w = new PrintWriter(new BufferedOutputStream(System.out, 1 << 10));

        int size = r.nextInt() + 1;

        long[][] mt = new long[size][size];

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                mt[j][i] = r.nextLong() + mt[j][i - 1];
            }
        }

        int q = r.nextInt();

        int x1, y1, x2, y2;
        long val;

        for (int k = 0; k < q; k++) {
            switch (r.nextInt()) {

                case 2:

                    x1 = r.nextInt() + 1;
                    y1 = r.nextInt() + 1;
                    val = r.nextLong() - mt[x1][y1] + mt[x1][y1 - 1];

                    for (int i = y1; i < size; i++) {
                        mt[x1][i] += val;
                    }

                    break;

                case 1:

                    x1 = r.nextInt();
                    y1 = r.nextInt();

                    x2 = r.nextInt() + 1;
                    y2 = r.nextInt() + 1;

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
}

class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    Reader() {
        this.din = new DataInputStream(System.in);
        this.buffer = new byte[this.BUFFER_SIZE];
        this.bufferPointer = 0;
        this.bytesRead = 0;
    }

    int nextInt() throws IOException {
        int ret = 0;
        byte c = this.read();

        while (c <= ' ') {
            c = this.read();
        }

        do {
            ret = ret * 10 + c - '0';
            c = this.read();
        } while (c >= '0' && c <= '9');

        return ret;
    }

    long nextLong() throws IOException {
        long ret = 0;
        byte c = this.read();

        while (c <= ' ') {
            c = this.read();
        }

        boolean neg = (c == '-');
        if (neg) {
            c = this.read();
        }

        do {
            ret = ret * 10 + c - '0';
            c = this.read();
        } while (c >= '0' && c <= '9');

        return neg ? -ret : ret;
    }

    private void fillBuffer() throws IOException {
        this.bufferPointer = 0;
        this.bytesRead = this.din.read(this.buffer, 0, this.BUFFER_SIZE);
        if (this.bytesRead == -1) {
            this.buffer[0] = -1;
        }
    }

    private byte read() throws IOException {
        if (this.bufferPointer == this.bytesRead) {
            this.fillBuffer();
        }
        return this.buffer[this.bufferPointer++];
    }
}

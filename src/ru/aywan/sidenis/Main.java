package ru.aywan.sidenis;

import java.io.DataInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int size = r.nextInt();
        MatrixSummator s = new MatrixSummator(size, r);
        int opCount = r.nextInt();
        for (int k = 0; k < opCount; k++) {
            s.readOp(r);
        }
        r.close();
    }
}

class MatrixSummator {
    private static final int MOD = 2;
    private static final int SUM = 1;

    private final int size;
    private final long[][] mtSum;

    MatrixSummator(int size, Reader r) throws IOException {
        this.size = size + 1;
        this.mtSum = new long[this.size][this.size];

        for (int i = 1; i < this.size; i++) {
            for (int j = 1; j < this.size; j++) {
                this.mtSum[j][i] = r.nextLong() + this.mtSum[j][i - 1];
            }
        }
    }

    void readOp(Reader r) throws IOException {
        int op = r.nextInt();

        switch (op) {
            case MOD:
                this.modValue(r.nextInt(), r.nextInt(), r.nextLong());
                break;

            case SUM:
                this.printSum(r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
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

        long diff = val - this.mtSum[x][y] + this.mtSum[x][y - 1];

        for (int i = y; i < this.size; i++) {
            this.mtSum[x][i] += diff;
        }
    }
}

class Reader {
    final private int BUFFER_SIZE = 1 << 18;
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

    void close() throws IOException {
        if (this.din == null) {
            return;
        }
        this.din.close();
    }
}
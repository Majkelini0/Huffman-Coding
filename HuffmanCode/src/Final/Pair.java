package Final;

public class Pair implements Comparable<Pair> {

    char c;
    int waga;
    Pair leftEl;
    Pair rightEl;

    Pair(char c, int waga) {
        this.c = c;
        this.waga = waga;
    }

    Pair(int waga, Pair leftEl, Pair rightEl) {
        this.waga = waga;
        this.leftEl = leftEl;
        this.rightEl = rightEl;
    }

    public int compareTo(Pair other) {
        return Integer.compare(this.waga, other.waga);
    }

    public int getWaga() {
        return waga;
    }
}

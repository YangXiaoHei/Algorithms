package Ch_4_3_Minimum_Spanning_Trees;

import edu.princeton.cs.algs4.*;

public class MinPQ <T extends Comparable<T>> {
    @SuppressWarnings("unchecked")
    private T keys[] = (T[])new Comparable[9];
    private int size;
    public T delMin() {
        if (isEmpty())
            throw new RuntimeException("underflow!");
        T min = keys[1];
        keys[1] = keys[size--];
        sink(1);
        if (size == (keys.length - 1) >> 2)
            resize(((keys.length - 1) >> 1) + 1);
        return min;
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void insert(T item) {
        if (size == keys.length - 1)
            resize((size << 1) + 1);
        keys[++size] = item;
        swim(size);
    }
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newKeys = (T[])new Comparable[newSize];
        for (int i = 1; i <= size; i++)
            newKeys[i] = keys[i];
        keys = newKeys;
    }
    private void swim(int k) {
        T toSwim = keys[k];
        /* 如果 toSwim 更小，那自然需要浮上去 */
        while (k > 1 && toSwim.compareTo(keys[k >> 1]) < 0) {
            keys[k] = keys[k >> 1]; /* 优化：挪动而非交换 */
            k >>= 1;
        }
        keys[k] = toSwim;
    }
    private void sink(int k) {
        T toSink = keys[k];
        while ((k << 1) <= size) {
            int j = k << 1;
            /* 如果 j == size 直接不走这里
             * 找出两者间最小，那么只要比最小的小，就可保证
             * 自根节点出发到所有叶节点的所有路径都是升序 */
            if (j < size && keys[j + 1].compareTo(keys[j]) < 0) j++;
            if (toSink.compareTo(keys[j]) <= 0) break;
            keys[k] = keys[j]; /* 优化：挪动而非交换 */
            k = j;
        }
        keys[k] = toSink;
    }
    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>();
        int k = 100;
        while (k-- > 0)
            pq.insert(StdRandom.uniform(100));
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }
    // output
    /*
     *  0
        0
        0
        2
        3
        5
        7
        7
        7
        8
        13
        14
        16
        16
        19
        19
        19
        20
        20
        20
        24
        25
        27
        27
        27
        29
        29
        31
        32
        32
        34
        34
        35
        36
        39
        39
        39
        39
        40
        41
        41
        41
        41
        41
        42
        43
        44
        44
        44
        46
        46
        47
        48
        49
        49
        50
        50
        51
        52
        52
        52
        53
        54
        54
        55
        56
        56
        56
        58
        58
        60
        61
        62
        63
        63
        64
        64
        64
        68
        69
        69
        70
        71
        74
        76
        80
        82
        83
        84
        85
        85
        87
        89
        89
        90
        94
        95
        95
        98
        99
     */
}

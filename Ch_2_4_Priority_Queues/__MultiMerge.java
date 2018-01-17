package Ch_2_4_Priority_Queues;

import static Tool.ArrayGenerator.Alphbets.*;
import edu.princeton.cs.algs4.StdOut;

public class __MultiMerge {
    /*
     * 先实现一个最小堆
     */
    static class MinPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] keys = (Key[])new Comparable[2];
        private int size;
        public boolean isEmpty() { return size == 0;}
        @SuppressWarnings("unchecked")
        public void resize(int newSize) {
            Key[] newKeys = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= size; i++)
                newKeys[i] = keys[i];
            keys = newKeys;
        }
        public Key delMin() {
            Key min = keys[1];
            exch(1, size--);
            sink(1);
            assert keys[size + 1] == min;
            keys[size + 1] = null;
            if (size > 0 && size == (keys.length - 1)  >> 2)
                resize((keys.length - 1) >> 1);
            return min;
        }
        public void insert(Key key) {
            if (size == keys.length - 1)
                resize(size * 2);
            keys[++size] = key;
            swim(size);
        }
        private void exch(int i, int j) {
            Key t = keys[i]; keys[i] = keys[j]; keys[j] = t;
        }
        private void swim(int k) {
            while (k > 1 && grea(k >>> 1, k)) {
                exch(k >>> 1, k);
                k >>>= 1;
            }
        }
        private void sink(int k) {
            while ((k << 1) <= size) {
                int j = k << 1;
                if (j < size && grea(j, j + 1)) j++;
                if (lessEqual(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
        private boolean lessEqual(int i, int j) {
            return keys[i].compareTo(keys[j]) <= 0;
        }
        private boolean grea(int i, int j) {
            return keys[i].compareTo(keys[j]) > 0;
        }
    }
    /*
     * 开始归并...就很简单，一股脑插进堆里，顺序就已经调整好了，再依次取出来
     */
    public static String[] merge(String[][] sArr) {
        MinPQ<String> pq = new MinPQ<String>();
        int count = 0;
        for (int i = 0; i < sArr.length; i++) {
            for (int j = 0; j < sArr[i].length; j++) {
                pq.insert(sArr[i][j]);
                count++;
            }
        }
        String[] s = new String[count];
        int k = 0;
        while (!pq.isEmpty())
            s[k++] = pq.delMin();
        return s;
    }
    public static void main(String[] args) {
        StdOut.println("归并前 :");
        String[] s = random(10);
        String[] s1 = random(5);
        String[] s2 = random(6);
        print(s);
        print(s1);
        print(s2);
        String[][] ss = {s, s1, s2};
        String[] merged = merge(ss);
        StdOut.println("归并后 :");
        print(merged);
    }
    // output
    /*
     * 归并前 :
           0   1   2   3   4   5   6   7   8   9
           G   F   V   I   G   H   R   O   O   K
        
           0   1   2   3   4
           A   R   K   N   Y
        
           0   1   2   3   4   5
           G   P   T   G   D   Q
        
        归并后 :
           0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
           A   D   F   G   G   G   G   H   I   K   K   N   O   O   P   Q   R   R   T   V   Y

     */
}

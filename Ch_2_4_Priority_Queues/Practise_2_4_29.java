package Ch_2_4_Priority_Queues;

import static Tool.ArrayGenerator.*;
import java.util.*;
import java.util.Map.Entry;

import edu.princeton.cs.algs4.StdOut;

public class Practise_2_4_29 {
    static class MaxMinPQ<Key extends Comparable<Key>> {
        @SuppressWarnings("unchecked")
        private Key[] maxKeys = (Key[])new Comparable[2];
        @SuppressWarnings("unchecked")
        private Key[] minKeys = (Key[])new Comparable[2];
        private Key min, max;
        private int maxSize;
        private int minSize;
        private int totalSize;
        private int delCount;
        private HashMap<Key, Integer> minMap = new HashMap<>();
        private HashMap<Key, Integer> maxMap = new HashMap<>();
        @SuppressWarnings("unchecked")
        public void maxResize(int newSize) {
            Key[] max = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= maxSize; i++)
                max[i] = maxKeys[i];
            maxKeys = max;
        }
        @SuppressWarnings("unchecked")
        public void minResize(int newSize) {
            Key[] min = (Key[])new Comparable[newSize + 1];
            for (int i = 1; i <= minSize; i++)
                min[i] = minKeys[i];
            minKeys = min;
        }
        public boolean isEmpty() {
            if (delCount != totalSize)
                return false;
            if (maxSize != 0)
                maxKeys = (Key[])new Comparable[2];
            if (minSize != 0)
                minKeys = (Key[])new Comparable[2];
            return true;
        }
        public Key max() { return max; }
        public Key min() { return min; }
        public void insert(Key key) {
            ++totalSize;
            if      (max == null) max = key;
            else if (max.compareTo(key) < 0) max = key;
            if      (min == null) min = key;
            else if (min.compareTo(key) > 0) min = key;
            insertToMaxHeap(key);
            insertToMinHeap(key);
        }
        public void insertToMaxHeap(Key key) {
            if (maxSize == maxKeys.length - 1)
                maxResize(maxSize << 1);
            maxKeys[++maxSize] = key;
            maxMap.put(key, maxSize);
            swim(maxSize, true);
        }
        
        public void insertToMinHeap(Key key) {
            if (minSize == minKeys.length - 1)
                minResize(minSize << 1);
            minKeys[++minSize] = key;
            minMap.put(key, minSize);
            swim(minSize, false);
        }
        public Key delMin() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            ++delCount;
            Key min = minKeys[1];
            minKeys[1] = minKeys[minSize--];
            minMap.put(minKeys[1], 1);
            sink(1, false);
            minKeys[minSize + 1] = null;
            minMap.remove(min);
            this.min = minKeys[1];
            if (minSize > 0 && minSize == (minKeys.length - 1) >> 2)
                minResize((minKeys.length - 1) >> 1);
            delete(maxMap.get(min), true);
            return min;
        }
        public void delete(int k, boolean isMax) {
            if (isMax) {
                Key toDel = maxKeys[k];
                maxKeys[k] = maxKeys[maxSize--];
                maxMap.put(maxKeys[k], k);
                swim(k, true);
                sink(k, true);
                maxKeys[maxSize + 1] = null;
                maxMap.remove(toDel);
                if (maxSize > 0 && maxSize == (maxKeys.length - 1) >> 2)
                    maxResize((maxKeys.length - 1) >> 1);
            } else {
                Key toDel = minKeys[k];
                minKeys[k] = minKeys[minSize--];
                minMap.put(minKeys[k], k);
                swim(k, false);
                sink(k, false);
                minKeys[minSize + 1] = null;
                minMap.remove(toDel);
                if (minSize > 0 && minSize == (minKeys.length - 1) >> 2)
                    minResize((minKeys.length - 1) >> 1);
            }
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            ++delCount;
            Key max = maxKeys[1];
            maxKeys[1] = maxKeys[maxSize--];
            maxMap.put(maxKeys[1], 1);
            sink(1, true);
            maxKeys[maxSize + 1] = null;
            this.max = maxKeys[1];
            maxMap.remove(max);
            if (maxSize > 0 && maxSize == (maxKeys.length - 1) >> 2)
                maxResize((maxKeys.length - 1) >> 1);
            delete(minMap.get(max), false);
            return max;
        }
        private void sink(int k, boolean isMax) {
            if (isMax) {
                Key top = maxKeys[k];
                while ((k << 1) <= maxSize) {
                    int j = k << 1;
                    if (maxKeys[j].compareTo(maxKeys[j + 1]) < 0) j++;
                    if (top.compareTo(maxKeys[j]) >= 0) break;
                    maxKeys[k] = maxKeys[j];
                    maxMap.put(maxKeys[k], k);
                    k = j;
                }
                maxKeys[k] = top;
                maxMap.put(maxKeys[k], k);
            } else {
                Key top = minKeys[k];
                while ((k << 1) <= minSize) {
                    int j = k << 1;
                    if (minKeys[j].compareTo(minKeys[j + 1]) > 0) j++;
                    if (top.compareTo(minKeys[j]) <= 0) break;
                    minKeys[k] = minKeys[j];
                    minMap.put(minKeys[k], k);
                    k = j;
                }
                minKeys[k] = top;
                minMap.put(minKeys[k], k);
            }
        }
        private boolean isIndexMatch() {
            for (Entry<Key, Integer> ent : minMap.entrySet()) 
                if (minKeys[ent.getValue()].compareTo(ent.getKey()) != 0)
                    return false;
            for (Entry<Key, Integer> ent : maxMap.entrySet()) 
                if (maxKeys[ent.getValue()].compareTo(ent.getKey()) != 0)
                    return false;
            return true;
        }
        private void swim(int k, boolean isMax) {
            if (isMax) {
                Key top = maxKeys[k];
                while (k > 1 && top.compareTo(maxKeys[k >> 1]) > 0) {
                    maxKeys[k] = maxKeys[k >> 1];
                    maxMap.put(maxKeys[k], k);
                    k >>= 1;
                }
                maxKeys[k] = top;
                maxMap.put(maxKeys[k], k);
            } else {
                Key top = minKeys[k];
                while (k > 1 && top.compareTo(minKeys[k >> 1]) < 0) {
                    minKeys[k] = minKeys[k >> 1];
                    minMap.put(minKeys[k], k);
                    k >>= 1;
                }
                minKeys[k] = top;
                minMap.put(minKeys[k], k);
            }
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("****************************************");
            sb.append("\n最小堆: \n");
            for (int i = 0; i < minKeys.length; i++)
                sb.append(String.format("%-5d", i));
            sb.append("\n");
            for (int i = 0; i < minKeys.length; i++)
                sb.append(String.format("%-5s", minKeys[i] == null ? "?" : minKeys[i]));
            
            sb.append("\n-------------------------------------\n");
            for (Entry<Key, Integer> ent : minMap.entrySet()) 
                sb.append(String.format("%-5s", ent.getKey() == null ? "?" : ent.getKey()));
            
            sb.append("\n");
            for (Entry<Key, Integer> ent : minMap.entrySet()) 
                sb.append(String.format("%-5d", ent.getValue()));
            sb.append("\n\n最大堆: \n");
            for (int i = 0; i < maxKeys.length; i++)
                sb.append(String.format("%-5d", i));
            sb.append("\n");
            for (int i = 0; i < maxKeys.length; i++)
                sb.append(String.format("%-5s", maxKeys[i] == null ? "?" : maxKeys[i]));
            
            sb.append("\n-------------------------------------\n");
            for (Entry<Key, Integer> ent : maxMap.entrySet()) 
                sb.append(String.format("%-5s", ent.getKey() == null ? "?" : ent.getKey()));
            
            sb.append("\n");
            for (Entry<Key, Integer> ent : maxMap.entrySet()) 
                sb.append(String.format("%-5d", ent.getValue()));
            sb.append("\n");
            sb.append("****************************************\n");
            return sb.toString();
        }
        public void checkAndPrint() {
            if (!isIndexMatch())
                throw new RuntimeException("index not match!");
            StdOut.println(this);
        }
    }
    public static void main(String[] args) {
        MaxMinPQ<Integer> pq = new MaxMinPQ<Integer>();
        int[] arr = ints(1, 10);
        for (int i : arr)
            pq.insert(i);
        
        pq.delMax();
        pq.checkAndPrint();
        pq.delMin();
        pq.checkAndPrint();
        pq.delMax();
        pq.checkAndPrint();
        pq.delMin();
        pq.checkAndPrint();
        pq.delMin();
        pq.checkAndPrint();
        pq.delMax();
        pq.checkAndPrint();
        pq.delMin();
        pq.checkAndPrint();
        pq.delMax();
        pq.checkAndPrint();
        pq.delMin();
        pq.checkAndPrint();
        pq.delMax(); 
        pq.checkAndPrint();
    }
    // output
    /*
     *  ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    1    2    5    3    4    8    9    6    7    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        1    2    3    4    5    6    7    8    9    
        1    2    4    5    3    8    9    6    7    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    9    8    5    7    6    3    4    1    2    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        1    2    3    4    5    6    7    8    9    
        8    9    6    7    3    5    4    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    2    3    5    6    4    8    9    7    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        2    3    4    5    6    7    8    9    
        1    2    5    3    4    8    6    7    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    9    8    5    7    6    3    4    2    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        2    3    4    5    6    7    8    9    
        8    6    7    3    5    4    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    2    3    5    6    4    8    7    ?    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        2    3    4    5    6    7    8    
        1    2    5    3    4    7    6    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    8    7    5    2    6    3    4    ?    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        2    3    4    5    6    7    8    
        4    6    7    3    5    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    3    4    5    6    7    8    ?    ?    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        3    4    5    6    7    8    
        1    2    3    4    5    6    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    8    7    5    4    6    3    ?    ?    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        3    4    5    6    7    8    
        6    4    3    5    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    4    6    5    8    7    ?    ?    ?    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        4    5    6    7    8    
        1    3    2    5    4    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   
        ?    8    7    5    4    6    ?    ?    ?    ?    ?    ?    ?    ?    ?    ?    ?    
        -------------------------------------
        4    5    6    7    8    
        4    3    5    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    
        ?    4    6    5    7    ?    ?    ?    ?    
        -------------------------------------
        4    5    6    7    
        1    3    2    4    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    
        ?    7    6    5    4    ?    ?    ?    ?    
        -------------------------------------
        4    5    6    7    
        4    3    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    5    6    7    8    
        ?    5    6    7    ?    ?    ?    ?    ?    
        -------------------------------------
        5    6    7    
        1    2    3    
        
        最大堆: 
        0    1    2    3    4    5    6    7    8    
        ?    7    6    5    ?    ?    ?    ?    ?    
        -------------------------------------
        5    6    7    
        3    2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    3    4    
        ?    5    6    ?    ?    
        -------------------------------------
        5    6    
        1    2    
        
        最大堆: 
        0    1    2    3    4    
        ?    6    5    ?    ?    
        -------------------------------------
        5    6    
        2    1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    
        ?    6    ?    
        -------------------------------------
        6    
        1    
        
        最大堆: 
        0    1    2    
        ?    6    ?    
        -------------------------------------
        6    
        1    
        ****************************************
        
        ****************************************
        最小堆: 
        0    1    2    
        ?    ?    ?    
        -------------------------------------
        
        
        
        最大堆: 
        0    1    2    
        ?    ?    ?    
        -------------------------------------
        
        
        ****************************************

     */
}

package Ch_3_1_Symbol_Tables;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Text_BinarySearchST<K extends Comparable<K>, V> {
    @SuppressWarnings("unchecked")
    private K[] keys = (K[])new Comparable[1];
    @SuppressWarnings("unchecked")
    private V[] values = (V[])new Object[1];
    private int size;
    private class Cache { K cachedKey; V cachedValue; int cacheIndex; }
    private Cache cache = new Cache();
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        K[] keys = (K[])new Comparable[newSize];
        V[] values = (V[])new Object[newSize];
        for (int i = 0; i < size; i++) {
            keys[i] = this.keys[i];
            values[i] = this.values[i];
        }
        this.values = values;
        this.keys = keys;
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void put(K k, V v) {
        if (k == null) return;
        if (v == null) {
            delete(k);
            return;
        }
        // 如果命中缓存，直接使用缓存的索引，并且记得要更新缓存的值
        if (cache.cachedKey != null && cache.cachedKey.compareTo(k) == 0) {
            values[cache.cacheIndex] = v;
            cache.cachedValue = v;
            return;
        }
        int lo = rank(k); // 如果在符号表中找到这个 key,那么缓存已经由 rank() 方法更新
        if (lo < size && keys[lo].compareTo(k) == 0) {
            values[lo] = v;
            return;
        }
        // 如果是要插入新结点，再检查是否需要扩容
        if (size == keys.length)
            resize(size << 1);
        // 给要插入的键挪出位置
        for (int i = size; i > lo; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        ++size;
        keys[lo] = k;
        values[lo] = v;
        // 更新缓存
        cache.cachedKey = k;
        cache.cachedValue = v;
        cache.cacheIndex = lo;
    }
    public V get(K k) {
        if (k == null) 
            throw new NoSuchElementException("符号表中不会有 null 键");
        if (cache.cachedKey != null && cache.cachedKey.compareTo(k) == 0) 
            return cache.cachedValue;
        int lo = rank(k);
        if (lo >= size || keys[lo].compareTo(k) != 0) return null;
        return values[lo];
    }
    public void delete(K k) {
        if (k == null)  throw new IllegalArgumentException("符号表中不会有 null 键");
        int lo = rank(k);
        if (lo >= size || keys[lo].compareTo(k) != 0) return;
        cache.cachedKey = null;
        cache.cachedValue = null;
        cache.cacheIndex = -1;
        for (int i = lo + 1; i < size; i++) {
            keys[i - 1] = keys[i];
            values[i - 1] = values[i];
        }
        --size;
        keys[size] = null;
        values[size] = null;
        if (size > 0 && size == keys.length >> 2)
            resize(keys.length >> 1);
    }
    public K floor(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中不会有 null 键");
        // 如果命中了缓存
        if (cache.cachedKey != null && 
            cache.cachedKey.compareTo(k) == 0) {
            if (cache.cacheIndex <= 0) return null; // 缓存的索引是0
            // 缓存左边还有值
            int index = cache.cacheIndex - 1;
            cache.cachedKey = keys[index];
            cache.cachedValue = values[index];
            cache.cacheIndex = index;
            return cache.cachedKey;
        }
        int lo = rank(k);
        if (lo == 0) return null; // 如果查找到的索引是0，那么没有比这个键更小的了
        
        // 不管找到还是没找到
        --lo;
        cache.cachedKey = keys[lo];
        cache.cachedValue = values[lo];
        cache.cacheIndex = lo;
        return cache.cachedKey;
    }
    public K ceiling(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中不会有 null 键");
        if (cache.cachedKey != null && cache.cachedKey.compareTo(k) == 0) {
            if (cache.cacheIndex == size - 1) return null;
            int index = cache.cacheIndex + 1;
            cache.cachedKey = keys[index];
            cache.cachedValue = values[index];
            cache.cacheIndex = index;
            return cache.cachedKey;
        }
        int lo = rank(k);
        if (lo >= size - 1) return null;
        cache.cachedKey = keys[lo];
        cache.cachedValue = values[lo];
        cache.cacheIndex = lo;
        return cache.cachedKey;
    }
    public K max() { return size == 0 ? null : keys[size - 1]; }
    public K min() { return size == 0 ? null : keys[0]; }
    public void deleteMax() { delete(max()); }
    public void deleteMin() { delete(min()); }
    public int size(K lo, K hi) {
        if (isEmpty() || 
            lo.compareTo(hi) > 0 || 
            lo.compareTo(max()) > 0 || 
            hi.compareTo(min()) < 0) return 0;
        return rank(hi) - rank(lo);
    }
    public Iterable<K> keys(K lo, K hi) {
        if (isEmpty() || 
            lo.compareTo(hi) > 0 || 
            lo.compareTo(max()) > 0 || 
            hi.compareTo(min()) < 0) return null;
        int loc = -1, hic = -1;
        if (cache.cachedKey != null) {
            if (cache.cachedKey.compareTo(lo) == 0) loc = cache.cacheIndex;
            if (cache.cachedKey.compareTo(hi) == 0) hic = cache.cacheIndex;
        }
        loc = loc == -1 ? rank(lo) : loc;
        hic = hic == -1 ? rank(hi) : hic;
        if (hic >= size || keys[hic].compareTo(hi) != 0) --hic;
        LinkedList<K> list = new LinkedList<K>();
        while (loc <= hic)
            list.add(keys[loc++]);
        return list;
    }
    public Iterable<K> keys() { return keys(min(), max()); }
    public int rank(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中不会有 null 键");
        // 如果命中缓存,直接使用缓存记录的索引
        if (cache.cachedKey != null && cache.cachedKey.compareTo(k) == 0) 
            return cache.cacheIndex;
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int cmp = keys[mid].compareTo(k);
            if      (cmp > 0)  hi = mid - 1;
            else if (cmp < 0)  lo = mid + 1;
            else    { cache.cachedKey = k; // 更新缓存
                      cache.cachedValue = values[mid];
                      cache.cacheIndex = mid;
                      return mid;
                     }
        } 
        return lo;
    }
    public K select(int k) {
        if (k < 0 || k >= size) throw new IllegalArgumentException("k 超出了符号表尺寸");
        if (cache.cachedKey != null && cache.cacheIndex == k) {
            return cache.cachedKey;
        }
        cache.cachedKey = keys[k];
        cache.cachedValue = values[k];
        cache.cacheIndex = k;
        return keys[k];
    }
    public boolean contains(K k) {
        if (k == null) throw new IllegalArgumentException("符号表中不会有 null 键");
        if (cache.cachedKey != null && cache.cachedKey.compareTo(k) == 0) return true;
        int lo = rank(k);
        if (lo >= size || keys[lo].compareTo(k) != 0) return false;
        cache.cachedKey = keys[lo];
        cache.cachedValue = values[lo];
        cache.cacheIndex = lo;
        return true;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) 
            sb.append(String.format("{%s  %s}\n", keys[i], values[i]));
        return sb.toString();
    }
    public static void main(String[] args) {
        Text_BinarySearchST<Integer, String> st = new Text_BinarySearchST<Integer, String>();
        st.put(14, "H");
        st.put(4, "C");
        st.put(34, "R");
        st.put(32, "Q");
        st.put(6, "D");
        st.put(2, "B");
        st.put(10, "F");
        st.put(22, "L");
        st.put(0, "A");
        st.put(16, "I");
        st.put(40, "U");
        st.put(18, "J");
        st.put(20, "K");
        st.put(12, "G");
        st.put(38, "T");
        st.put(24, "M");
        st.put(36, "S");
        st.put(26, "N");
        st.put(28, "O");
        st.put(30, "P");
        st.put(8, "E");
        StdOut.println("1⃣️" + st);
        st.deleteMax();
        st.deleteMax();
        st.deleteMax();  // 删除三次最大值
        st.deleteMin();
        st.deleteMin();  // 删除两次最小值
        StdOut.println("2⃣️" + st);
        st.delete(20);
        st.delete(18);
        st.delete(16);  // 连续删除三个元素
        st.delete(17);
        st.delete(15);  // 删除不存在的键值
        StdOut.println("3⃣️" + st);
        StdOut.println("4⃣️" + st.select(0) + "  " + st.get(st.select(0)));
        StdOut.println(st.select(3) + "  " + st.get(st.select(3))); // select 和 get 测试
        StdOut.println(st.select(5) + "  " + st.get(st.select(5)));
        StdOut.println(st.select(7) + "  " + st.get(st.select(7)));
        StdOut.println(st.select(9) + "  " + st.get(st.select(9)));
        StdOut.println("5⃣️" + "小于键值 28 的数量为 : " + st.rank(28)); // rank 测试
        StdOut.println("小于键值 30 的数量为 : " + st.rank(30));
        StdOut.println("小于键值 15 的数量为 : " + st.rank(15));
        StdOut.println("小于键值 5 的数量为 : " + st.rank(5));
        StdOut.println("小于键值 8 的数量为 : " + st.rank(8));
        StdOut.println("小于键值 10 的数量为 : " + st.rank(10));
        StdOut.println("小于键值 4 的数量为 : " + st.rank(4));
        // 迭代器测试
        StdOut.println("6⃣️");
        for (Integer key : st.keys())
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
        for (Integer key : st.keys(5, 31))
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
        for (Integer key : st.keys(11, 29))
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
        st.delete(14);
        st.delete(26);
        for (Integer key : st.keys(5, 18))
            StdOut.printf("key = %s value = %s\n", key, st.get(key));
        StdOut.println("\n\n");
    }        
}

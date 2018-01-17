package Ch_3_1_Symbol_Tables;

import java.util.*;
/*
 * 使用缓存策略改进过的无序符号表
 */
public class __SequentialSearchST<K, V> {
    private class Node {
        K key; V value;
        Node next;
        Node() {}
        Node(K k, V v, Node n) { key = k; value = v; next = n; }
        public String toString() { return String.format("{ %s %s }", key, value); }
    }
    private Node header = new Node();
    private Node cache = null;
    private int size;
    /*
     * 符号表尺寸
     */
    public int size() { return size; }
    /*
     * 是否为空
     */
    public boolean isEmpty() { return size == 0; }
    /*
     * 放入键值对
     */
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException("符号表中不允许放入 null 键");
        if (v == null) {
            delete(k);
            return;
        }
        // 如果命中缓存，那么就使用缓存
        if (cache.key != null && cache.key.equals(k)) {
            cache.value = v;
            return;
        }
        // 检查符号表中是否有和新插入的键重复的键
        for (Node x = header.next; x != null; x = x.next) {
            if (x.key.equals(k)) {
                x.value = v;
                cache = x;
                return;
            }
        }
        // 尺寸加1，插入新结点，更新缓存
        ++size;
        header.next = new Node(k, v, header.next);
        cache = header.next;
    }
    /*
     * 通过键获取与之关联的值
     */
    public V get(K k) {
        if (k == null) return null;
        // 检查是否命中缓存
        if (cache != null && k.equals(cache.key)) return cache.value;
        // 遍历链表，看是否有匹配的键
        for (Node x = header.next; x != null; x = x.next) {
            if (x.key.equals(k)) {
                cache = x; //如果找到匹配的键，更新缓存
                return cache.value;
            }
        }
        return null;
    }
    /*
     * 删除键以及对应的值
     */
    public void delete(K k) {
        if (k == null)
            throw new NoSuchElementException("符号表中没有 null 键");
        // 找到待删除结点的前后指针
        Node pre, cur;
        for (pre = header, cur = header.next;
             cur != null && cur.key.equals(k);
             cur = cur.next, pre = pre.next);
        // 要删除的键不存在
        if (cur == null) return;
        --size;
        if (cur == cache) cache = null; //如果要删除的键是缓存的键
        cur.key = null;  //便于 GC
        cur.value = null;
        pre.next = pre.next.next; //删除结点
    }
    /*
     * 是否包含键
     */
    public boolean contains(K k) {
        if (k == null) return false;
        //如果命中缓存
        if (cache != null && cache.key.equals(k)) return true;
        // 遍历检查
        for (Node x = header.next; x != null; x = x.next) 
            if (x.key.equals(k)) {
                cache = x;
                return true;
            }
        return false;
    }
    /*
     * 获取可遍历的键集合
     */
    public Iterable<K> keys() {
        if (isEmpty()) return null;
        LinkedList<K> list = new LinkedList<K>();
        for (Node x = header.next; x != null; x = x.next) 
            list.add(x.key);
        return list;
    }
}

package 第二章_归并排序;

import static 第二章_初级排序算法.Text_Array.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_2_21 {
    public static void merge(Comparable[] a) {
        Comparable[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
    }
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        merge(dst, src, lo, mid);
        merge(dst, src, mid + 1, hi);
        if (src[mid].compareTo(src[mid + 1]) < 0) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if      (i > mid)                       dst[k] = src[j++];
            else if (j > hi)                        dst[k] = src[i++];
            else if (src[j].compareTo(src[i]) < 0)  dst[k] = src[j++];
            else                                    dst[k] = src[i++];
    }
    private static void insertion(Comparable[] a, int lo, int hi) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            Comparable t = a[i];
            int j;
            for (j = i - 1; j >= 0 && t.compareTo(a[j]) < 0; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    public static int binarySearch(String[] a, String key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if      (key.compareTo(a[mid]) < 0) hi = mid - 1;
            else if (key.compareTo(a[mid]) > 0) lo = mid + 1;
            else    return mid;
        }
        return -1;
    }
    public static String randomName() { return names[StdRandom.uniform(names.length)]; }
    public static String[] randomNames(int N) {
        String[] names = new String[N];
        for (int i = 0; i < N; i++)
            names[i] = randomName();
        return names;
    }
    private static String[] names = {
            "Willie",
            "Winfred",
            "Wythe",
            "Xavier",
            "Yale",
            "Yehudi",
            "York",
            "Yves",
            "Zachary",
            "Zebulon",
            "Ziv",
            "Bleacher",
            "Beau",
            "Bishop",
            "Alva",
            "Alvin",
            "Arthur",
            "Blair",
            "Blithe",
            "Page",
            "Parker",
            "Paddy",
            "Philip",
            "Porter",
            "Prescott",
            "Primo",
            "Quentin",
            "Quennel",
            "Rachel",
            "Ralap",
            "Valentine",
            "Verne",
            "Vic",
            "Vivian",
            "Wade",
            "Walker",
            "Will",
            "William",
         };
    public static void main(String[] args) {
        int N = 10;
        String[] list1 = randomNames(N),
                 list2 = randomNames(N),
                 list3 = randomNames(N);
        merge(list1); merge(list2); merge(list3);
        print(list1);
        print(list2);
        print(list3);
        int i = 0, j = 0, k = 0;
        String[] common = new String[N];
        while (i < N && j < N) {
            String s1 = list1[i],
                   s2 = list2[j];
            if      (s1.compareTo(s2) < 0) i++;
            else if (s1.compareTo(s2) > 0) j++;
            else    { common[k++] = s1; i++; j++; }
        }
        boolean found = false;
        for (int m = 0; m < N; m++) {
            if (common[m] == null) break;
            if (found = (binarySearch(list3, common[m]) > 0)) {
                StdOut.printf("共同名字是 : %s\n", common[m]);
                break;
            }
        }
        if (!found) StdOut.println("没有共同的名字");
    }
    // output
    /*
     * 
        0      1      2      3      4      5      6      7      8      9      
        QuennelQuennelRalap  Willie WinfredYehudi Yves   ZebulonZebulonZiv    
        
        0      1      2      3      4      5      6      7      8      9      
        Alva   Alvin  Arthur Paddy  Page   Parker QuentinWilliamWillie Wythe  
        
        0      1      2      3      4      5      6      7      8      9      
        Philip Porter QuentinRachel Ralap  Will   Willie Willie Yehudi Zachary
        共同名字是 : Willie

     */
}

package Ch_2_5_Applications;

import static Tool.ArrayGenerator.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class Practise_2_5_23 {
    public static int select(int[] a, int k) {
        if (--k >= a.length || k <= 0)
            throw new IllegalArgumentException("k 不能为 " + k);
        int lo = 0, hi = a.length - 1;
        // 三取样
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (a[mid] < a[lo]) exch(a, mid, lo);
            if (a[hi] < a[lo]) exch(a, lo, hi);
            if (a[mid] < a[hi]) exch(a, mid, hi);
            int pivot = a[hi], i = lo - 1, j = hi;
            while (true) {
                while (a[++i] < pivot);
                while (a[--j] > pivot);
                if (i >= j) break;
                exch(a, i, j);
            }
            exch(a, hi, i);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else    return a[i];
        }
        throw new RuntimeException("unknown error");
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = ints(100, 10, 1000);
        StdOut.println("======= 排序前 ==========");
        print(a);
        int k = 3;
        StdOut.printf("\n第 %d 小的数字是 %d\n", k, select(a, k));
        Arrays.sort(a);
        StdOut.println("\n======= 排序后 ==========");
        print(a);
    }
    // output
    /*
     *  ======= 排序前 ==========

        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43  44  45  46  47  48  49  50  51  52  53  54  55  56  57  58  59  60  61  62  63  64  65  66  67  68  69  70  71  72  73  74  75  76  77  78  79  80  81  82  83  84  85  86  87  88  89  90  91  92  93  94  95  96  97  98  99  
        399 180 363 544 776 736 821 207 515 539 187 15  107 143 814 948 477 72  577 811 975 732 777 73  250 29  97  278 448 316 693 775 64  441 612 162 566 35  826 865 661 581 682 927 387 495 608 586 251 251 939 258 68  144 318 103 177 439 610 575 506 794 84  145 1000518 670 175 878 230 45  516 660 282 885 988 701 506 875 880 424 317 731 325 241 418 654 74  968 472 450 238 856 920 537 468 336 801 459 387 
        
        第 3 小的数字是 35
        
        ======= 排序后 ==========
        
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43  44  45  46  47  48  49  50  51  52  53  54  55  56  57  58  59  60  61  62  63  64  65  66  67  68  69  70  71  72  73  74  75  76  77  78  79  80  81  82  83  84  85  86  87  88  89  90  91  92  93  94  95  96  97  98  99  
        15  29  35  45  64  68  72  73  74  84  97  103 107 143 144 145 162 175 177 180 187 207 230 238 241 250 251 251 258 278 282 316 317 318 325 336 363 387 387 399 418 424 439 441 448 450 459 468 472 477 495 506 506 515 516 518 537 539 544 566 575 577 581 586 608 610 612 654 660 661 670 682 693 701 731 732 736 775 776 777 794 801 811 814 821 826 856 865 875 878 880 885 920 927 939 948 968 975 988 1000

     */
}

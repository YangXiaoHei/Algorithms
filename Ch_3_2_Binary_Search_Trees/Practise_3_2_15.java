package Ch_3_2_Binary_Search_Trees;

import java.util.LinkedList;
public class Practise_3_2_15 {
    public static void main(String[] args) {
        /*
         * floor("Q")  
         * 
         * E -> Q
         * 
         * select(5)
         * 
         * E -> Q
         * 
         * ceiling("Q")
         * 
         * E -> Q
         * 
         * rank("J")
         * 
         * E -> Q -> J
         * 
         * size("D", "T")
         * 
         * E -> D 
         * 
         * E -> Q -> T
         * 
         * keys("D", "T")
         * 
         * private void keys(Node n, LinkedList<K> list, K lo, K hi) {
            if (n == null) return;
            int cmplo = lo.compareTo(n.k);
            int cmphi = n.k.compareTo(hi);
            if (cmplo < 0) keys(n.left, list, lo, hi);
            if (cmplo <= 0 && cmphi <= 0) list.add(n.k);
            if (cmphi < 0) keys(n.right, list, lo, hi);
        }
           
           E -> D -> E -> Q -> J -> J -> M -> Q -> T -> S -> S -> T
        
         */
    }
}

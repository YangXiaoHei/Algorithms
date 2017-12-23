package 第二章_优先队列;

public class Practise_2_4_06 {
    public static void main(String[] args) {
        /*
         * P
         * PQ -> null P
         * 
         * R
         * PQ -> null R P
         * 
         * I
         * PQ -> null R P I
         * 
         * O
         * PQ -> null R P I O
         * 
         * delMax R
         * PQ -> null P O I
         * 
         * R
         * PQ -> null R P I O
         * 
         * delMax R
         * PQ -> null P O I
         * 
         * delMax P
         * PQ -> null O I
         * 
         * I
         * PQ -> null O I I
         * 
         * delMax O
         * PQ -> null I I
         * 
         * T
         * PQ -> null T I I
         * 
         * delMax T
         * PQ -> null I I
         * 
         * Y
         * PQ -> null Y I I
         * 
         * delMax Y
         * PQ -> null I I
         * 
         * delMax I
         * PQ -> null I
         * 
         * delMax I
         * PQ -> null
         * 
         * Q
         * PQ -> null Q
         * 
         * U
         * PQ -> null U Q
         * 
         * E
         * PQ -> null U Q E
         * 
         * delMax U
         * PQ -> null Q E
         * 
         * delMax Q
         * PQ -> null E
         * 
         * delMax E
         * PQ -> null
         * 
         * U
         * PQ -> null U
         * 
         * delMax U
         * PQ -> null
         * 
         * E
         * PQ -> null E
         * 
         * 
         */
    }
}

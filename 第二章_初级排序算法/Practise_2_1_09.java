package 第二章_初级排序算法;

public class Practise_2_1_09 {
    public static void main(String[] args) {
        /*
         * N = 21 N/3 = 7 
         * 
         * 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20
         * E  A  S  Y  S  H  E  L  L  S  O  R  T  Q  U  E  S  T  I  O  N
         * 
         * h = 13
         * 
         * j = 15 j - h = 2
         * E  A  E  Y  S  H  E  L  L  S  O  R  T  Q  U  S  S  T  I  O  N
         * j = 16 j - h = 3
         * E  A  E  E  S  H  E  L  L  S  O  R  T  Q  U  S  Y  T  I  O  N
         * j = 20 j - h = 7
         * E  A  E  E  S  H  E  N  L  S  O  R  T  Q  U  S  Y  T  I  O  L
         * 
         * h = 4
         * 
         * j = 8 j - h = 4 
         * E  A  E  E  L  H  E  N  S  S  O  R  T  Q  U  S  Y  T  I  O  L
         * j = 13 j - h = 9
         * E  A  E  E  L  H  E  N  S  Q  O  R  T  S  U  S  Y  T  I  O  L
         * j = 18 j - h = 14
         * E  A  E  E  L  H  E  N  S  Q  O  R  T  S  I  S  Y  T  U  O  L
         * j = 14 j - h = 10
         * E  A  E  E  L  H  E  N  S  Q  I  R  T  S  O  S  Y  T  U  O  L
         * J = 19 j - h = 15
         * E  A  E  E  L  H  E  N  S  Q  I  R  T  S  O  O  Y  T  U  S  L
         * j = 15 j - h = 11
         * E  A  E  E  L  H  E  N  S  Q  I  O  T  S  O  R  Y  T  U  S  L
         * j = 20 j - h = 16
         * E  A  E  E  L  H  E  N  S  Q  I  O  T  S  O  R  L  T  U  S  Y
         * j = 16 j - h = 12
         * E  A  E  E  L  H  E  N  S  Q  I  O  L  S  O  R  T  T  U  S  Y
         * j = 12 j - h = 8
         * E  A  E  E  L  H  E  N  L  Q  I  O  S  S  O  R  T  T  U  S  Y
         * 
         * h = 1
         * A  E  E  E  L  H  E  N  L  Q  I  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  H  L  E  N  L  Q  I  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  H  E  L  N  L  Q  I  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  L  N  L  Q  I  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  L  L  N  Q  I  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  L  L  N  I  Q  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  L  L  I  N  Q  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  L  I  L  N  Q  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  Q  O  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  Q  S  S  O  R  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  Q  S  O  S  R  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  Q  O  S  S  R  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  O  Q  S  S  R  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  O  Q  S  R  S  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  O  Q  R  S  S  T  T  U  S  Y
         * A  E  E  E  E  H  I  L  L  N  O  O  Q  R  S  S  T  T  S  U  Y
         * A  E  E  E  E  H  I  L  L  N  O  O  Q  R  S  S  T  S  T  U  Y
         * A  E  E  E  E  H  I  L  L  N  O  O  Q  R  S  S  S  T  T  U  Y
         */
    }
}

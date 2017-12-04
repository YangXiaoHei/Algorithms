package 第二章_归并排序;

public class Practise_2_2_02 {
    public static void main(String[] args) {
         /*
          * 0 1 2 3 4 5 6 7 8 9 10 11
          * E A S Y Q U E S T I O  N
          * 
          * 0 1
          * A E
          * 
          * 2 2
          * S
          * 
          * 0 1 2
          * A E - S merge -> A E S
          * 
          * 3 4
          * Q Y
          * 
          * 5 5
          * U
          * 
          * 3 4 5
          * Q Y - U merge -> Q U Y
          * 
          * 0 2 5
          * A E S - Q U Y merge -> A E Q S U Y
          * 
          * 6 7 
          * E S
          * 
          * 8
          * T
          * 
          * 6 7 8
          * E S - T merge -> E S T
          * 
          * 9 10
          * I O
          * 
          * 11
          * N
          * 
          * 9 10 11
          * I O - N  merge -> I N O
          * 
          * 6 8 11
          * E S T - I N O  merge -> E I N O S T
          * 
          * 0 5 11
          * A E Q S U Y - E I N O S T merge -> A E E I N O Q S S T U Y
          * 
          */
    }
}

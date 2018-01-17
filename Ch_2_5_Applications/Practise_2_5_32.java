package Ch_2_5_Applications;

import java.util.*;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Stack;

public class Practise_2_5_32 {
    static class Board {
        private int[][] tiles;
        private static final int EMPTY = 0;
        public Board(int[][] t) { tiles = copy(t); }
        public int dimension() { return tiles.length; }
        public int[][] swap(int r, int c, int r1, int c1) {
            int[][] copy = copy(tiles);
            int tile = copy[r][c];
            copy[r][c] = copy[r1][c1];
            copy[r1][c1] = tile;
            return copy;
        }
        public static Board canBeSolve() {
            int[][] tiles = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 0}
            };
            int M = 30;
            int[] haha = { 1, -1 };
            int nR, nC;
            while (M-- > 0) {
                int[] zero = empty(tiles);
                int zR = zero[0], zC = zero[1];
                if (StdRandom.bernoulli()) {
                    nR = zR + haha[StdRandom.uniform(haha.length)];
                    nC = zC;
                } else {
                    nR = zR;
                    nC = zC + haha[StdRandom.uniform(haha.length)];
                }
                if (nR < 0) nR = 0;
                if (nR == tiles.length) nR = tiles.length - 1;
                if (nC < 0) nC = 0;
                if (nC == tiles.length) nC = tiles.length - 1;
                tiles[zR][zC] = tiles[nR][nC];
                tiles[nR][nC] = 0;
            }
            Board b = new Board(tiles);
            return b;
        }
        public static Board canNotBeSolve() {
            int[][] tiles = {
              { 1, 2, 3 },
              { 4, 5, 6 },
              { 8, 7, 0 }
            };
            Board b = new Board(tiles);
            return b;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++)
                    sb.append(tiles[i][j] + " ");
                sb.append("\n");
            }
            return sb.toString();
        }
        public static boolean notFull(int[][] tiles) {
            for (int r = 0; r < tiles.length; r++)
                for (int c = 0; c < tiles[r].length; c++)
                    if (tiles[r][c] == -1) return true;
            return false;
        }
        public Iterable<Board> neighbor() {
            LinkedList<Board> list = new LinkedList<Board>();
            int[] empty = empty(tiles);
            int eR = empty[0], eC = empty[1];
            if (eR > 0)               list.add(new Board(swap(eR, eC, eR - 1, eC))); // 向上
            if (eR < dimension() - 1) list.add(new Board(swap(eR, eC, eR + 1, eC))); // 向下
            if (eC > 0)               list.add(new Board(swap(eR, eC, eR, eC - 1))); // 向左
            if (eC < dimension() - 1) list.add(new Board(swap(eR, eC, eR, eC + 1))); // 向右
            return list;
        }
        public final int goalFor(int r, int c) { return dimension() * r + c + 1; }
        public final int goalCol(int tile) { return (tile - 1) % dimension(); }
        public final int goalRow(int tile) { return (tile - 1) / dimension(); }
        public int manhattan(int r1, int c1, int r2, int c2) {
            return Math.abs(r1 - r2) + Math.abs(c1 - c2);
        }
        public static int[] empty(int[][] t) {
            int[] e = new int[2];
            for (int r = 0; r < t.length; ++r)
                for (int c = 0; c < t[r].length; ++c)
                    if (t[r][c] == EMPTY) {
                        e[0] = r;
                        e[1] = c;
                    }
            return e;     
        }
        public int[][] copy(int[][] t) {
            int[][] copy = new int[t.length][];
            for (int i = 0; i < t.length; i++) {
                copy[i] = new int[t.length];
                for (int j = 0; j < t.length; j++)
                    copy[i][j] = t[i][j];
            }
            return copy;
        }
        public boolean isGoal() {
            for (int r = 0; r < dimension(); ++r)
                for (int c = 0; c < dimension(); ++c)
                    if (tiles[r][c] != EMPTY)
                        if (goalFor(r, c) != tiles[r][c]) return false;
            return true;
        }
        public Board twin() {
            for (int r = 0; r < dimension(); ++r)
                for (int c = 0; c < dimension() - 1; ++c)
                    if (tiles[r][c] != EMPTY && tiles[r][c + 1] != EMPTY)
                        return new Board(swap(r, c, r, c + 1));
            throw new RuntimeException("haha");
        }
        public int totalManhattan() {
            int sum = 0;
            for (int r = 0; r < dimension(); ++r)
                for (int c = 0; c < dimension(); ++c)
                    if (tiles[r][c] != EMPTY) {
                        int goal = goalFor(r, c);
                        sum += manhattan(goalRow(goal), goalCol(goal), r, c);
                    }
            return sum;
        }
        public boolean equals(Object x) {
            if (x == null) return false;
            if (x == this) return true;
            if (!(x instanceof Board)) return false;
            Board b = (Board)x;
            if (b.dimension() != dimension()) return false;
            for (int i = 0; i < dimension(); i++)
                for (int j = 0; j < dimension(); j++)
                    if (tiles[i][j] != b.tiles[i][j]) return false;
            return true;   
        }
    }
    static class Solver {
        private class Move implements Comparable<Move> {
            private Move previous;
            private Board board;
            private int numOfMoves;
            public Move(Board board) { this.board = board; }
            public Move(Board board, Move previous) {
                this.board = board;
                this.previous = previous;
                this.numOfMoves = previous.numOfMoves + 1;
            }
            public int compareTo(Move that) {
                return board.totalManhattan() - that.board.totalManhattan()
                        + numOfMoves - that.numOfMoves;
            }
        }
        private Move lastMove;
        public Solver(Board initial) {
            MinPQ<Move> pq = new MinPQ<Move>();
            pq.insert(new Move(initial));
            MinPQ<Move> twinpq = new MinPQ<Move>();
            twinpq.insert(new Move(initial.twin()));
            while (true) {
                lastMove = expend(pq);
                if (lastMove != null || expend(twinpq) != null) return;
            }
        }
        public Move expend(MinPQ<Move> pq) {
            if (pq.isEmpty()) return null;
            Move bestMove = pq.delMin();
            if (bestMove.board.isGoal()) return bestMove;
            for (Board nei : bestMove.board.neighbor()) {
                if (bestMove.previous == null || !bestMove.previous.board.equals(nei)) {
                    pq.insert(new Move(nei, bestMove));
                }
            }
            return null;
        }
        public boolean isSolvable() {
            return lastMove != null;
        }
        public int moves() {
            return isSolvable() ? lastMove.numOfMoves : -1;
        }
        public Stack<Board> getSolution() {
            if (!isSolvable()) return null;
            Stack<Board> s = new Stack<Board>();
            while (lastMove != null) {
                s.push(lastMove.board);
                lastMove = lastMove.previous;
            }
            return s;
        }
    }
    public static void main(String[] args) {
        Board b = Board.canBeSolve();
        Solver s = new Solver(b);
        if (!s.isSolvable()) {
            StdOut.println("无解");
            return;
        }
        Stack<Board> sol = s.getSolution();
        while (!sol.isEmpty())
            StdOut.println(sol.pop());
    }
    // output
    /*
     *  1 2 3 
        5 6 0 
        4 7 8 
        
        1 2 3 
        5 0 6 
        4 7 8 
        
        1 2 3 
        0 5 6 
        4 7 8 
        
        1 2 3 
        4 5 6 
        0 7 8 
        
        1 2 3 
        4 5 6 
        7 0 8 
        
        1 2 3 
        4 5 6 
        7 8 0 


     */
}

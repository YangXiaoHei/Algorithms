package Ch_2_5_Applications;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_22 {
    static class Stock {
        private static int counter = 0;
        private final int id = counter++;
        private  double price;
        private  int count;
        public static Stock stockGen() {
            Stock t = new Stock();
            t.price = StdRandom.uniform(1000, 4000);
            t.count = StdRandom.uniform(10, 1000);
            return t;
        }
        public static Comparator<Stock> sortPrice() {
            return new Comparator<Stock>() {
               public int compare(Stock a, Stock b) {
                   return a.price < b.price ? -1 :
                          a.price > b.price ? 1 : 0;
               }
            };
        }
        public static Comparator<Stock> sortCount() {
            return new Comparator<Stock>() {
               public int compare(Stock a, Stock b) {
                   return a.count < b.count ? -1 :
                          a.count > b.count ? 1 : 0;
               }
            };
        }
        public String toString() {
            return String.format("售出价格 : %.3f 数量 : %d", price, count);
        }
    }
    /*
     * 卖家会把价格最少，数量最多的放得更靠橱窗前
     */
    static class Seller {
        private Stock[] ks = new Stock[2];
        private int size;
        private void resize(int newSize) {
            Stock[] ks = new Stock[newSize + 1];
            for (int i = 1; i <= size; i++)
                ks[i] = this.ks[i];
            this.ks = ks;
        }
        public boolean isEmpty() { return size == 0; }
        public void addStock(Stock t) {
            if (size == ks.length - 1)
                resize(size << 1);
            ks[++size] = t;
            swim(size);
        }
        private int compare(Stock a, Stock b) {
            if (Stock.sortPrice().compare(a, b) < 0) return -1;
            if (Stock.sortPrice().compare(a, b) > 0) return 1;
            if (Stock.sortCount().compare(a, b) > 0) return -1;
            if (Stock.sortCount().compare(a, b) < 0) return 1;
            return 0;
        }
        public Stock offHand() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Stock minSellPrice = ks[1];
            ks[1] = ks[size--];
            sink(1);
            ks[size + 1] = null;
            if (size > 0 && size == (ks.length - 1) >> 2)
                resize((ks.length - 1) >> 1);
            return minSellPrice;
        }
        private void swim(int k) {
            Stock t = ks[k];
            while (k > 1 && compare(t, ks[k >> 1]) < 0) {
                ks[k] = ks[k >> 1];
                k >>= 1;
            }
            ks[k] = t;
        }
        private void sink(int k) {
            Stock t = ks[k]; int j;
            while ((j = k << 1) <= size) {
                if (compare(ks[j], ks[j + 1]) > 0) j++;
                if (compare(t, ks[j]) <= 0) break;
                ks[k] = ks[j];
                k = j;
            }
            ks[k] = t;
        }
    }
    /*
     * 买家会希望买到数量最少，价格最低的
     */
    static class Buyer {
        private Stock[] ks = new Stock[2];
        private int size;
        private void resize(int newSize) {
            Stock[] ks = new Stock[newSize + 1];
            for (int i = 1; i <= size; i++)
                ks[i] = this.ks[i];
            this.ks = ks;
        }
        public boolean isEmpty() { return size == 0; }
        public void stockWantToBuy(Stock t) {
            if (size == ks.length - 1)
                resize(size << 1);
            ks[++size] = t;
            swim(size);
        }
        public Stock theMostWantToBuy() {
            if (isEmpty())
                throw new RuntimeException("priority queue underflow");
            Stock most = ks[1];
            ks[1] = ks[size--];
            sink(1);
            ks[size + 1] = null;
            if (size > 0 && size == (ks.length - 1) >> 2)
                resize((ks.length - 1) >> 1);
            return most;
        }
        private int compare(Stock a, Stock b) {
            if (Stock.sortPrice().compare(a, b) < 0) return -1;
            if (Stock.sortPrice().compare(a, b) > 0) return 1;
            if (Stock.sortCount().compare(a, b) < 0) return -1;
            if (Stock.sortCount().compare(a, b) > 0) return 1;
            return 0;
        }
        private void swim(int k) {
            Stock t = ks[k];
            while (k > 1 && compare(t, ks[k >> 1]) < 0) {
                ks[k] = ks[k >> 1];
                k >>= 1;
            }
            ks[k] = t;
        }
        private void sink(int k) {
            Stock t = ks[k]; int j;
            while ((j = k << 1) <= size) {
                if (compare(ks[j], ks[j + 1]) > 0) j++;
                if (compare(t, ks[j]) <= 0) break;
                ks[k] = ks[j];
                k = j;
            }
            ks[k] = t;
        }
    }
    public static void main(String[] args) {
        Seller seller = new Seller();
        Buyer buyer = new Buyer();
        
        // 给卖家一些货
        for (int i = 0; i < 100; i++)
            seller.addStock(Stock.stockGen());
        
        // 给买家分配一些想买的股票
        for (int i = 0; i < 100; i++)
            buyer.stockWantToBuy(Stock.stockGen());
        
        while (!seller.isEmpty() && !buyer.isEmpty()) {
            Stock wantToBuy = buyer.theMostWantToBuy();
            Stock wantToSell = seller.offHand();
            if (wantToBuy.price < wantToSell.price) { 
                seller.addStock(wantToSell);
            } else {
                StdOut.printf("交易成功 ! 买家买了价格为 %.3f 的股票 %d 股\n", 
                        wantToBuy.price,
                        wantToBuy.count > wantToSell.count ? wantToSell.count : wantToBuy.count);
                wantToSell.count -= wantToBuy.count;
                if (wantToSell.count > 0)
                    seller.addStock(wantToSell);
            }
        }
        if (seller.isEmpty())
            StdOut.println("股票都卖光啦");
        if (buyer.isEmpty())
            StdOut.println("想买的股票都买走啦!");
    }
    // output
    /*
     *  交易成功 ! 买家买了价格为 1036.000 的股票 457 股
        交易成功 ! 买家买了价格为 1071.000 的股票 288 股
        交易成功 ! 买家买了价格为 1098.000 的股票 454 股
        交易成功 ! 买家买了价格为 1101.000 的股票 411 股
        交易成功 ! 买家买了价格为 1114.000 的股票 100 股
        交易成功 ! 买家买了价格为 1117.000 的股票 33 股
        交易成功 ! 买家买了价格为 1142.000 的股票 181 股
        交易成功 ! 买家买了价格为 1143.000 的股票 11 股
        交易成功 ! 买家买了价格为 1155.000 的股票 37 股
        交易成功 ! 买家买了价格为 1158.000 的股票 683 股
        交易成功 ! 买家买了价格为 1181.000 的股票 925 股
        交易成功 ! 买家买了价格为 1192.000 的股票 65 股
        交易成功 ! 买家买了价格为 1215.000 的股票 282 股
        交易成功 ! 买家买了价格为 1222.000 的股票 405 股
        交易成功 ! 买家买了价格为 1269.000 的股票 339 股
        交易成功 ! 买家买了价格为 1273.000 的股票 477 股
        交易成功 ! 买家买了价格为 1279.000 的股票 707 股
        交易成功 ! 买家买了价格为 1351.000 的股票 33 股
        交易成功 ! 买家买了价格为 1367.000 的股票 248 股
        交易成功 ! 买家买了价格为 1386.000 的股票 741 股
        交易成功 ! 买家买了价格为 1412.000 的股票 235 股
        交易成功 ! 买家买了价格为 1418.000 的股票 183 股
        交易成功 ! 买家买了价格为 1421.000 的股票 309 股
        交易成功 ! 买家买了价格为 1525.000 的股票 192 股
        交易成功 ! 买家买了价格为 1590.000 的股票 91 股
        交易成功 ! 买家买了价格为 1634.000 的股票 557 股
        交易成功 ! 买家买了价格为 1638.000 的股票 541 股
        交易成功 ! 买家买了价格为 1646.000 的股票 337 股
        交易成功 ! 买家买了价格为 1682.000 的股票 195 股
        交易成功 ! 买家买了价格为 1732.000 的股票 112 股
        交易成功 ! 买家买了价格为 1736.000 的股票 475 股
        交易成功 ! 买家买了价格为 1759.000 的股票 48 股
        交易成功 ! 买家买了价格为 1785.000 的股票 166 股
        交易成功 ! 买家买了价格为 1844.000 的股票 75 股
        交易成功 ! 买家买了价格为 1854.000 的股票 432 股
        交易成功 ! 买家买了价格为 1961.000 的股票 16 股
        交易成功 ! 买家买了价格为 2000.000 的股票 249 股
        交易成功 ! 买家买了价格为 2111.000 的股票 108 股
        交易成功 ! 买家买了价格为 2150.000 的股票 28 股
        交易成功 ! 买家买了价格为 2184.000 的股票 240 股
        交易成功 ! 买家买了价格为 2207.000 的股票 92 股
        交易成功 ! 买家买了价格为 2226.000 的股票 607 股
        交易成功 ! 买家买了价格为 2237.000 的股票 60 股
        交易成功 ! 买家买了价格为 2255.000 的股票 140 股
        交易成功 ! 买家买了价格为 2313.000 的股票 422 股
        交易成功 ! 买家买了价格为 2401.000 的股票 85 股
        交易成功 ! 买家买了价格为 2424.000 的股票 197 股
        交易成功 ! 买家买了价格为 2449.000 的股票 265 股
        交易成功 ! 买家买了价格为 2470.000 的股票 197 股
        交易成功 ! 买家买了价格为 2503.000 的股票 12 股
        交易成功 ! 买家买了价格为 2558.000 的股票 152 股
        交易成功 ! 买家买了价格为 2577.000 的股票 71 股
        交易成功 ! 买家买了价格为 2581.000 的股票 95 股
        交易成功 ! 买家买了价格为 2613.000 的股票 738 股
        交易成功 ! 买家买了价格为 2735.000 的股票 185 股
        交易成功 ! 买家买了价格为 2777.000 的股票 111 股
        交易成功 ! 买家买了价格为 2924.000 的股票 301 股
        交易成功 ! 买家买了价格为 2928.000 的股票 24 股
        交易成功 ! 买家买了价格为 2943.000 的股票 368 股
        交易成功 ! 买家买了价格为 2972.000 的股票 96 股
        交易成功 ! 买家买了价格为 2988.000 的股票 492 股
        交易成功 ! 买家买了价格为 2990.000 的股票 15 股
        交易成功 ! 买家买了价格为 3024.000 的股票 935 股
        交易成功 ! 买家买了价格为 3033.000 的股票 260 股
        交易成功 ! 买家买了价格为 3075.000 的股票 167 股
        交易成功 ! 买家买了价格为 3161.000 的股票 452 股
        交易成功 ! 买家买了价格为 3184.000 的股票 471 股
        交易成功 ! 买家买了价格为 3232.000 的股票 307 股
        交易成功 ! 买家买了价格为 3248.000 的股票 470 股
        交易成功 ! 买家买了价格为 3252.000 的股票 241 股
        交易成功 ! 买家买了价格为 3267.000 的股票 47 股
        交易成功 ! 买家买了价格为 3272.000 的股票 255 股
        交易成功 ! 买家买了价格为 3313.000 的股票 229 股
        交易成功 ! 买家买了价格为 3323.000 的股票 107 股
        交易成功 ! 买家买了价格为 3326.000 的股票 140 股
        交易成功 ! 买家买了价格为 3384.000 的股票 380 股
        交易成功 ! 买家买了价格为 3417.000 的股票 46 股
        交易成功 ! 买家买了价格为 3442.000 的股票 515 股
        交易成功 ! 买家买了价格为 3453.000 的股票 314 股
        交易成功 ! 买家买了价格为 3527.000 的股票 328 股
        交易成功 ! 买家买了价格为 3562.000 的股票 644 股
        交易成功 ! 买家买了价格为 3566.000 的股票 384 股
        交易成功 ! 买家买了价格为 3587.000 的股票 146 股
        交易成功 ! 买家买了价格为 3638.000 的股票 320 股
        交易成功 ! 买家买了价格为 3641.000 的股票 63 股
        交易成功 ! 买家买了价格为 3642.000 的股票 378 股
        交易成功 ! 买家买了价格为 3644.000 的股票 22 股
        交易成功 ! 买家买了价格为 3645.000 的股票 322 股
        交易成功 ! 买家买了价格为 3665.000 的股票 127 股
        交易成功 ! 买家买了价格为 3708.000 的股票 186 股
        交易成功 ! 买家买了价格为 3717.000 的股票 104 股
        交易成功 ! 买家买了价格为 3774.000 的股票 296 股
        交易成功 ! 买家买了价格为 3801.000 的股票 110 股
        交易成功 ! 买家买了价格为 3836.000 的股票 608 股
        交易成功 ! 买家买了价格为 3859.000 的股票 242 股
        交易成功 ! 买家买了价格为 3877.000 的股票 286 股
        交易成功 ! 买家买了价格为 3962.000 的股票 111 股
        交易成功 ! 买家买了价格为 3970.000 的股票 666 股
        想买的股票都买走啦!
     */
}

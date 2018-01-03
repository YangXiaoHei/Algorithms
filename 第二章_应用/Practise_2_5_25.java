package 第二章_应用;

import java.util.*;

public class Practise_2_5_25 {
    static class Point2D {
        public Point2D(double x, double y) {}
        double x() { return 0; }
        double y() { return 0; }
        double r() { return 0; }
        double theta() { return 0; }
        double disTo(Point2D that) { return 0; }
        double disToOrgi() { return 0; }
        double angleTo(Point2D that) { return 0; }
        
        public static Comparator<Point2D> sortX() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return a.x() < b.x() ? -1 :
                           a.x() > b.x() ? 1 : 0;
                }
            };
        }
        public static Comparator<Point2D> sortY() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return a.y() < b.y() ? -1 :
                           a.y() > b.y() ? 1 : 0;
                }
            };
        }
        public static Comparator<Point2D> sortDis() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return a.disToOrgi() < b.disToOrgi() ? -1 :
                           a.disToOrgi() > b.disToOrgi() ? 1 : 0;
                }
            };
        }
        public Comparator<Point2D> sortDisToSelf() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return  disTo(a) < disTo(b) ? -1 :
                            disTo(a) > disTo(b) ? 1 : 0;
                }
            };
        }
        public Comparator<Point2D> sortAngleToSelf() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return  angleTo(a) < angleTo(b) ? -1 :
                            angleTo(a) > angleTo(b) ? 1 : 0;
                }
            };
        }
        
    }
    public static void main(String[] args) {
        
    }
}

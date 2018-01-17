//
//  main.c
//  InsertionSortPerformenceComparasion
//
//  Created by YangHan on 2017/11/28.
//  Copyright © 2017年 YangHan. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef void (*inse_imp)(int *, int);
extern int *source_array(int, int, int);
extern int *array_copy(int *, int);
extern void print_arrary(int *, int);
extern unsigned long time_of_sort(inse_imp, int *, int);
extern void test(int, int);
extern void insertion_sort_array_access_A(int *, int);
extern void insertion_sort_array_access_B(int *, int);

int * source_array(int N, int lo, int hi) {
    int *arr = calloc(N, sizeof(int));
    for (int i = 0; i < N; i++)
        arr[i] = arc4random() % (hi - lo);
    return arr;
}

int *array_copy(int a[], int size) {
    int *arr = calloc(size, sizeof(int));
    for (int i = 0; i < size; i++)
        arr[i] = a[i];
    return arr;
}

void print_arrary(int a[], int size) {
    for (int i = 0; i < size; i++)
        printf("%-5d", i);
    printf("\n");
    for (int i = 0; i < size; i++)
        printf("%-5d", a[i]);
    printf("\n\n");
}

unsigned long time_of_sort(inse_imp imp, int *a, int size) {
    clock_t start = clock();
    (*imp)(a, size);
    clock_t end = clock();
    return end - start;
}

void test(int T, int N) {
    
    inse_imp imp1 = &insertion_sort_array_access_A;
    inse_imp imp2 = &insertion_sort_array_access_B;
    
    long t1, t2;
    t1 = t2 = 0;
    
    for (int i = 0; i < T; i++) {
        int *copy1 = source_array(N, 0, 1000);
        int *copy2 = array_copy(copy1, N);
        
        t1 += time_of_sort(imp1, copy1, N);
        t2 += time_of_sort(imp2, copy2, N);
        
        free(copy1);
        free(copy2);
    }
    printf("改善 / 不改善 = %.3f\n",(double)t1 / t2);
}

void insertion_sort_array_access_A(int a[], int size) {
    for (int i = 1; i < size; i++) {
        for (int j = i; j > 0 && a[j - 1] > a[j]; j--) {
            int t = a[j - 1];
            a[j - 1] = a[j];
            a[j] = t;
        }
    }
}

void insertion_sort_array_access_B(int a[], int size) {
    for (int i = 1; i < size; i++) {
        if (a[i - 1] > a[i]) {
            int j;
            int t = a[i];
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
}

int main(int argc, const char * argv[]) {
    test(5, 20000);
    return 0;
}
// output
/*
 * 改善 / 不改善 = 1.428
 */

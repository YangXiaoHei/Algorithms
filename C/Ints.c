//
//  Ints.c
//  ArrayGenerator
//
//  Created by YangHan on 2018/1/21.
//  Copyright © 2018年 YangHan. All rights reserved.
//

#include "Ints.h"
#include <time.h>
#include <stdlib.h>

int _yh_overload_ yh_random(int n) {
    return rand() % n;
}
int _yh_overload_ yh_random(int lo, int hi) {
    return rand() % (hi - lo) + lo;
}
void _yh_overload_ yh_shuffle(int a[], int sz) {
    srand((unsigned)time(NULL));
    for (int i = 0; i < sz; i++) {
        int r = i + yh_random(sz - i);
        int t = a[r];
        a[r] = a[i];
        a[i] = t;
    }
}
int _yh_overload_ *yh_ints(int lo, int hi) {
    int *a = calloc(hi - lo + 1, sizeof(int));
    int sz = hi - lo + 1, t = lo;
    for (int i = 0; i < sz; i++)
        a[i] = t++;
    yh_shuffle(a, hi - lo + 1);
    return a;
}
int _yh_overload_ *yh_ints(int size) {
    srand((unsigned)time(NULL));
    int *a = calloc(size, sizeof(int));
    for (int i = 0; i < size; i++)
        a[i] = yh_random(-size, size + 1);
    return a;
}
int _yh_overload_ *yh_ints(int size, int lo, int hi) {
    srand((unsigned)time(NULL));
    int *a = calloc(size, sizeof(int));
    for (int i = 0; i < size; i++)
        a[i] = yh_random(lo, hi + 1);
    return a;
}
int _yh_overload_ *yh_ascendInts(int lo, int hi) {
    int sz = hi - lo + 1;
    int *a = calloc(sz, sizeof(int));
    for (int i = 0; i < sz; i++)
        a[i] = lo++;
    return a;
}
int _yh_overload_ *yh_descendInts(int hi, int lo) {
    int sz = hi - lo + 1;
    int *a = calloc(sz, sizeof(int));
    for (int i = 0; i < sz; i++)
        a[i] = hi--;
    return a;
}
void _yh_overload_ yh_print(int *a, int sz) {
    for (int i = 0; i < sz; i++)
        printf("%-4d", a[i]);
    printf("\n");
}

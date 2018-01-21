//
//  Ints.h
//  ArrayGenerator
//
//  Created by YangHan on 2018/1/21.
//  Copyright © 2018年 YangHan. All rights reserved.
//

#ifndef Ints_h
#define Ints_h

#include <stdio.h>

#define _yh_overload_ __attribute__((overloadable))
#define _yh_enter_ printf("\n");

/**
 *  生成 [0, n) 的随机数
 */
int _yh_overload_ yh_random(int n);
/**
 *  生成 [lo, hi) 的随机数
 */
int _yh_overload_ yh_random(int lo, int hi);

/**
 *  打乱一个数组
 */
void _yh_overload_ yh_shuffle(int a[], int sz);
/**
 *  生成 lo, lo+1, lo+2, lo+3 ... hi 的随机置换
 */
int _yh_overload_ *yh_ints(int lo, int hi);
/**
 *  生成一个大小为 size 的 int[] 取值范围是 [-size, size]
 */
int _yh_overload_ *yh_ints(int size);
/**
 *  生成一个大小为 size 的 int[] 取值范围是 [lo, hi]
 */
int _yh_overload_ *yh_ints(int size, int lo, int hi);
/**
 *  生成 lo, lo+1, lo+2, lo+3 ... hi
 */
int _yh_overload_ *yh_ascendInts(int lo, int hi);
/**
 *  生成 hi, hi-1, hi-2, hi-3, hi-4 ... lo
 */
int _yh_overload_ *yh_descendInts(int hi, int lo);
/**
 *  打印数组
 */
void _yh_overload_ yh_print(int *a, int sz);

#endif /* Ints_h */

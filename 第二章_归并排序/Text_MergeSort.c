//
//  main.c
//  MergeSort
//
//  Created by YangHan on 2017/12/5.
//  Copyright © 2017年 YangHan. All rights reserved.
//

#include <stdio.h>
#include <strings.h>
#include <stdlib.h>
#include <time.h>

/**
 *  函数声明
 */
typedef int(*cmp)(const void *, const void *);
void merge(void **a, int size, cmp imp);
void _merge(void **src, void **dst, cmp imp, int size, int lo, int hi);
void _mergeSort(void **src, void **dst, cmp imp, int lo, int mid, int hi);
void _insertion(void **a, cmp imp, int lo, int hi);
/**
 *  函数实现
 */
void merge(void **a, int size, cmp imp) {
    void **aux = calloc(size, sizeof(void *));
    memcpy(aux, a, size * sizeof(void *)); // 浅拷贝
    _merge(aux, a, imp, size, 0, size - 1);
    free(aux);
}
/**
 *  内部归并逻辑
 */
void _merge(void **src, void **dst, cmp imp, int size, int lo, int hi) {
    if (hi - lo <= 7) { // 长度小于 7 时切换到插入排序
        _insertion(dst, imp, lo, hi);
        return;
    }
    int mid = (lo + hi) / 2;
    _merge(dst, src, imp, size, lo, mid);
    _merge(dst, src, imp, size, mid + 1, hi);
    if ((*imp)(src[mid], src[mid + 1]) < 0) { // 避免对不需要原地归并数组的冗余归并操作
        memcpy(dst + lo, src + lo, (hi - lo + 1) * sizeof(void *));
        return;
    }
    _mergeSort(src, dst, imp, lo, mid, hi);
}
/**
 *  小数组时切换到插入排序
 */
void _insertion(void **a, cmp imp, int lo, int hi) {
    for (int i = lo; i <= hi; i++) {
        void *t = a[i]; int j;
        for (j = i - 1; j >= lo && (*imp)(t, a[j]) < 0; j--)
            a[j + 1] = a[j];
        a[j + 1] = t;
    }
}
/**
 *  原地归并方法，基于两个已排序数组的拼接数组
 */
void _mergeSort(void **src, void **dst, cmp imp, int lo, int mid, int hi) {
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++)
        if      (i > mid)                    dst[k] = src[j++];
        else if (j > hi)                     dst[k] = src[i++];
        else if ((*imp)(src[j], src[i]) < 0) dst[k] = src[j++];
        else                                 dst[k] = src[i++];
}
/**
 *  比较函数，用于排序过程
 */
int compare(const void *a,const void *b) {
    return *(int *)a < *(int *)b ? -1 : *(int *)a > *(int *)b ? 1 : 0;
}
/**
 *  是否已经排序，用于检测排序是否成功
 */
int isSorted(void **a, int size, cmp imp) {
    for (int i = 1; i < size; i++)
        if ((*imp)(a[i], a[i - 1]) < 0) return 0;
    return 1;
}

int main(int argc, const char * argv[]) {
    
    // 生成一个随机数组
    int N = 102400000;
    int *b = calloc(N, sizeof(int));
    for (int i = 0; i < N; i++)
        b[i] = arc4random_uniform(100);
    
    // 不改动 b 的情况下，在 a 中进行排序
    int **a = calloc(N, sizeof(int *));
    for (int i = 0; i < N; i++)
        a[i] = b + i;
    
    // 记录时间
    clock_t start = clock();
    
    // 归并排序
    merge((void **)a, N, (cmp)compare);
    
    // 记录结束时间
    clock_t end = clock();
    printf("规模 : %d  耗时 : %.3f 秒\n", N, (float)(end - start) / CLOCKS_PER_SEC);
    
    // 是否成功排序 ?
    if (!isSorted((void **)a, N, (cmp)compare))  printf("排序不成功!\n");
    
    // 清理内存
    free(b);
    free(a);
    b = NULL;
    a = NULL;
    
    return 0;
}
// output
/**
 *  规模 : 102400000  耗时 : 26.974 秒
 */

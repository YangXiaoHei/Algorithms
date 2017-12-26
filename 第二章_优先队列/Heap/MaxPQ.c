//
//  MaxPQ.c
//  Heap
//
//  Created by YangHan on 2017/12/25.
//  Copyright © 2017年 YangHan. All rights reserved.
//

#include "MaxPQ.h"
#include <stdlib.h>
#include <string.h>

static inline int less(MaxPQ *pq, int i, int j) {
    return (*pq->cmp)(pq->keys[i], pq->keys[j]) < 0;
}
static inline int greaEqual(MaxPQ *pq, int i, int j) {
    return (*pq->cmp)(pq->keys[i], pq->keys[j]) >= 0;
}
static inline void swap(MaxPQ *pq, int i, int j) {
    Key t = pq->keys[i]; pq->keys[i] = pq->keys[j]; pq->keys[j] = t;
}
static void print(MaxPQ *pq) {
    for (int i = 0; i < pq->capacity; i++)
        printf("%d ", pq->keys[i]);
    printf("\n");
}

MaxPQ * init(Comparator cmp) {
    MaxPQ * pq = calloc(1, sizeof(MaxPQ));
    pq->cmp = cmp;
    if (pq == NULL) {
        printf("memory alloc failed!");
        exit(EXIT_FAILURE);
    }
    pq->size = 0;
    pq->capacity = 2;
    pq->keys = calloc(2, sizeof(Key));
    if (pq->keys == NULL) {
        printf("memory alloc failed!");
        exit(EXIT_FAILURE);
    }
    return pq;
}

MaxPQ * initWithArray(Key *keys, int size, Comparator cmp) {
    MaxPQ *pq = calloc(1, sizeof(MaxPQ));
    pq->cmp = cmp;
    pq->capacity = size + 1;
    pq->size = size;
    pq->keys = calloc(size + 1, sizeof(Key));
    memcpy(pq->keys + 1, keys, size * sizeof(Key));
    for (int i = (size + 1) >> 1; i > 0; i--) {
        int k = i;
        while ((k << 1) <= size) {
            int j = k << 1;
            if (j < size && less(pq, j, j + 1)) j++;
            if (greaEqual(pq, k, j)) break;
            swap(pq, k, j);
            k = j;
        }
    }
    return pq;
}

static void resize(MaxPQ *pq, int newSize) {
    Key *keys = calloc(newSize + 1, sizeof(Key));
    for (int i = 1; i <= pq->size; i++)
        keys[i] = pq->keys[i];
    pq->capacity = newSize + 1;
    pq->keys = keys;
}

int isEmpty(MaxPQ *pq) { return pq->size == 0; }
void insert(MaxPQ *pq, Key key) {
    if (pq->size == pq->capacity - 1)
        resize(pq, pq->size << 1);
    pq->keys[++(pq->size)] = key;
    int k = pq->size;
    while (k > 1 && less(pq, k >> 1, k)) {
        swap(pq, k >> 1, k);
        k >>= 1;
    }
}

Key delMax(MaxPQ *pq) {
    if (pq->size == 0) {
        printf("priority queue underflow!");
        exit(EXIT_FAILURE);
    }
    Key max = pq->keys[1];
    pq->keys[1] = pq->keys[pq->size--];
    int k = 1;
    while ((k << 1) <= pq->size) {
        int j = k << 1;
        if (less(pq, j, j + 1)) j++;
        if (greaEqual(pq, k, j)) break;
        swap(pq, k, j);
        k = j;
    }
    if (pq->size > 0 && pq->size == (pq->capacity - 1) >> 2)
        resize(pq, (pq->capacity - 1) >> 1);
    return max;
}

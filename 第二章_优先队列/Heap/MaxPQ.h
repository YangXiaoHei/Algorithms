//
//  MaxPQ.h
//  Heap
//
//  Created by YangHan on 2017/12/25.
//  Copyright © 2017年 YangHan. All rights reserved.
//

#ifndef MaxPQ_h
#define MaxPQ_h
#include <stdio.h>

typedef int Key;
typedef int(*Comparator) (Key, Key);
typedef struct _MaxPQ {
    Key *keys;
    int capacity;
    int size;
    Comparator cmp;
} MaxPQ;
MaxPQ * init(Comparator cmp);
MaxPQ * initWithArray(Key *keys, int size, Comparator cmp);
int isEmpty(MaxPQ *pq);
void insert(MaxPQ *pq, Key key);
Key delMax(MaxPQ *pq);

#endif /* MaxPQ_h */

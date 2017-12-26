//
//  main.c
//  Heap
//
//  Created by YangHan on 2017/12/25.
//  Copyright © 2017年 YangHan. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include "MaxPQ.h"

int compare(Key x1, Key x2) {
    return x1 < x2 ? -1 : x1 > x2 ? 1 : 0;
}
int main(int argc, const char * argv[]) {
    Key *keys = calloc(5, sizeof(Key));
    keys[0] = 1;
    keys[1] = 8;
    keys[2] = 3;
    keys[3] = 2;
    keys[4] = 9;
    MaxPQ *pq = initWithArray(keys, 5, &compare);
    insert(pq, 19);
    insert(pq, 11);
    insert(pq, 20);
    while (!isEmpty(pq))
        printf("%d\n", delMax(pq));
    return 0;
}

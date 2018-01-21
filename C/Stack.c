//
//  Stack.c
//  Stack
//
//  Created by YangHan on 2018/1/21.
//  Copyright © 2018年 YangHan. All rights reserved.
//

#include "Stack.h"
#include <stdlib.h>

Stack yh_stack_init(void) {
    Stack S = calloc(1, sizeof(_Stack));
    S->_items = calloc(1, sizeof(void *));
    S->_capacity = 1;
    S->_size = 0;
    return S;
}
static void yh_stack_resize(Stack S, int new_size) {
    void **new_items = calloc(new_size, sizeof(void *));
    for (int i = 0; i < S->_size; i++)
        new_items[i] = S->_items[i];
    free(S->_items);
    S->_capacity = new_size;
    S->_items = new_items;
}
int yh_stack_is_empty(Stack S) {
    return S->_size == 0;
}
int yh_stack_size(Stack S) {
    return S->_size;
}
void yh_stack_push(Stack S, void *item) {
    if (S->_capacity == S->_size)
        yh_stack_resize(S, S->_size << 1);
    S->_items[S->_size++] = item;
}
void yh_stack_print(Stack S, void(*imp)(void *item)) {
    printf("=============  Stack output ===========\n");
    if (S->_size == 0) {
        printf("Empty  Stack");
        return;
    }
    printf("size = %d capcacity = %d\n", S->_size, S->_capacity);
    for (int i = 0; i < S->_size; i++) 
        (*imp)(S->_items[i]);
    printf("\n=====================================\n");
}
void *yh_stack_pop(Stack S) {
    if (S->_size == 0) {
        printf("stack underflow");
        exit(EXIT_FAILURE);
    }
    void *del = S->_items[--S->_size];
    S->_items[S->_size] = NULL;
    if (S->_size > 0 && S->_size == S->_capacity >> 2)
        yh_stack_resize(S, S->_capacity >> 1);
    return del;
}

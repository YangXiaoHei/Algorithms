//
//  Stack.h
//  Stack
//
//  Created by YangHan on 2018/1/21.
//  Copyright © 2018年 YangHan. All rights reserved.
//

#ifndef Stack_h
#define Stack_h

#include <stdio.h>

/**
 *  Test Client
 
 void print(void *item) {
    printf("%-5d", *(int *)item);
 }
 
 int main(int argc, const char * argv[]) {
     int *a = yh_ascendInts(0, 10);
     yh_print(a, 11);
     Stack S = yh_stack_init();
     for (int i = 0; i < 11; i++)
     yh_stack_push(S, a + i);
     yh_stack_print(S, &print);
     return 0;
 }
 */

typedef struct Stack_ {
    void **_items;
    int _capacity;
    int _size;
} _Stack;

typedef _Stack * Stack;

Stack yh_stack_init(void);
int yh_stack_is_empty(Stack S);
int yh_stack_size(Stack S);
void yh_stack_push(Stack S, void *item);
void *yh_stack_pop(Stack S);
void yh_stack_print(Stack S, void(*imp)(void *item));

#endif /* Stack_h */

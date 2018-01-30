//
//  main.c
//  atoi
//
//  Created by YangHan on 2018/1/31.
//  Copyright © 2018年 YangHan. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

int _is_digit(char c) {
    return c >= '0' && c <= '9';
}
int _atoi(const char *s, char **err) {
    int sign = 1;
    if (!_is_digit(*s)) {
        if      (*s == '+') sign = 1;
        else if (*s == '-') sign = -1;
        else    {
            if (err != NULL)
                *err = "invalid characters in string!";
            return 0;
        }
        ++s;
    }
    long sum = 0;
    while (*s != '\0') {
        int cur = *s - '0';
        long rel = sum * 10;
        if ((sign == 1 && rel > 0x7FFFFFFFL) ||
            (sign == -1 && rel > 0x80000000L)) {
            if (err != NULL)
                *err = "int overflow - 1";
            return 0;
        }
        sum = rel + cur;
        if ((sign == 1 && sum > 0x7FFFFFFFL) ||
            (sign == -1 && sum > 0x80000000L)) {
            if (err != NULL)
                *err = "int overflow - 2";
            return 0;
        }
        ++s;
    }
    return (int)(sum * sign);
}
int main(int argc, const char * argv[]) {
    
    char *err = NULL;
    int d = _atoi("-13543234889", &err);
    if (err == NULL) {
        printf("%d\n", d);
    } else {
        printf("cast failed : %s\n", err);
    }
    
    return 0;
}

#include <stdlib.h>
#include <stdio.h>

int ehLetra(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
}

int ehDigito(char c) {
    return c >= '0' && c <= '9';
}

int comprimento(const char *str) {
    int len = 0;
    while (str[len] != '\0') {
        len++;
    }
    return len;
}

int main() {
    char placa[50];
    fgets(placa, 50, stdin);

    int len = comprimento(placa);
    if (len > 0 && placa[len - 1] == '\n') {
        placa[len - 1] = '\0';
        len--;
    }

    int resp = 0;
    if (len == 8) {
        if (
            ehLetra(placa[0]) &&
            ehLetra(placa[1]) &&
            ehLetra(placa[2]) &&
            placa[3] == '-' &&
            ehDigito(placa[4]) &&
            ehDigito(placa[5]) &&
            ehDigito(placa[6]) &&
            ehDigito(placa[7])) {
            resp = 1;
        }
    } else if (len == 7) {
        if (
            ehLetra(placa[0]) &&
            ehLetra(placa[1]) &&
            ehLetra(placa[2]) &&
            ehDigito(placa[3]) &&
            ehLetra(placa[4]) &&
            ehDigito(placa[5]) &&
            ehDigito(placa[6])) {
            resp = 2;
        }
    }

    printf("%d", resp);

    return 0;
}
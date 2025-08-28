#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool ehPalindromoRec(char str[], int i, int j) {
    bool teste = true;

    if (i < j) {
        if (str[i] != str[j]) {
            teste = false;
        } else {
            teste = ehPalindromoRec(str, i + 1, j - 1);
        }
    }

    return teste;
}

int main() {
    char str[1000];
    int len;

    while (fgets(str, sizeof(str), stdin) != NULL) {
        len = strlen(str);

        if (len > 0 && str[len - 1] == '\n') {
            str[--len] = '\0';
        }

        bool resultado = ehPalindromoRec(str, 0, len - 1);

        if (resultado) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    return 0;
}
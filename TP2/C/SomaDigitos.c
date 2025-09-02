#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int somaDigitos(int num) {
    if (num == 0) {
        return 0;
    }
    return (num % 10) + somaDigitos(num / 10);
}

int main() {
    char linha[100];
    int num;

    while (fgets(linha, sizeof(linha), stdin) != NULL) {
        if (strncmp(linha, "FIM", 3) == 0) {
            break;
        }

        if (sscanf(linha, "%d", &num) == 1) {
            if (num > 0) {
                int resultado = somaDigitos(num);
                printf("%d\n", resultado);
            }
        }
    }

    return 0;
}
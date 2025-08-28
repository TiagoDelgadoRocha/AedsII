#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool ehPalindromoRec(char str[], int i, int j) {
    bool teste = true;

    // A recursão continua enquanto o índice da esquerda for menor que o da direita.
    if (i < j) {
        if (str[i] != str[j]) {
            teste = false;
        } else {
            // Se os caracteres das pontas forem iguais, chama a função para o restante da string.
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

        // Remove o caractere de nova linha ('\n') que o fgets lê no final da string.
        if (len > 0 && str[len - 1] == '\n') {
            str[--len] = '\0';
        }

        // Inicia a verificação recursiva, começando pelas extremidades da string.
        bool resultado = ehPalindromoRec(str, 0, len - 1);

        if (resultado) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    return 0;
}
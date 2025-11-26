#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void) {
    int tab[105][105];
    char tok[16];

    while (scanf("%15s", tok) == 1) {
        if (strcmp(tok, "FIM") == 0)
            break;

        int N = atoi(tok);
        int M;
        scanf("%d", &M);

        // lê o tabuleiro
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                scanf("%d", &tab[i][j]);

        // processa e imprime
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tab[i][j] == 1) {
                    putchar('9');
                } else {
                    int c = 0;
                    // 4-vizinhança: cima, baixo, esquerda, direita
                    if (i-1 >= 0     && tab[i-1][j] == 1) c++; // cima
                    if (i+1 < N      && tab[i+1][j] == 1) c++; // baixo
                    if (j-1 >= 0     && tab[i][j-1] == 1) c++; // esquerda
                    if (j+1 < M      && tab[i][j+1] == 1) c++; // direita
                    printf("%d", c);
                }
            }
            putchar('\n');
        }
    }
    return 0;
}

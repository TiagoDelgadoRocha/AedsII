#include <stdio.h>

int main() {
    int vitorias = 0;
    char resultado;

    for (int i = 0; i < 6; i++) {
        scanf(" %c", &resultado);
        if (resultado == 'V') {
            vitorias++;
        }
    }

    if (vitorias == 5 || vitorias == 6) {
        printf("1\n");
    } else if (vitorias == 3 || vitorias == 4) {
        printf("2\n");
    } else if (vitorias == 1 || vitorias == 2) {
        printf("3\n");
    } else {
        printf("-1\n");
    }

    return 0;
}
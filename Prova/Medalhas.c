#include <stdio.h>

#define MAX_PAISES 1000

typedef struct {
    char nome[101];
    int ouro;
    int prata;
    int bronze;
} Medalha;

int comparaMaior(Medalha a, Medalha b) {
    if (a.ouro < b.ouro) return 1;
    if (a.ouro > b.ouro) return 0;
    if (a.prata < b.prata) return 1;
    if (a.prata > b.prata) return 0;
    if (a.bronze < b.bronze) return 1;
    if (a.bronze > b.bronze) return 0;
    int i = 0;
    while (a.nome[i] != '\0' && b.nome[i] != '\0') {
        if (a.nome[i] < b.nome[i]) return 0;
        if (a.nome[i] > b.nome[i]) return 1;
        i++;
    }
    return a.nome[i] != '\0';
}

int main() {
    int N;
    scanf("%d", &N);
    Medalha time[MAX_PAISES];

    for (int i = 0; i < N; i++) {
        scanf("%s %d %d %d", time[i].nome, &time[i].ouro, &time[i].prata, &time[i].bronze);
    }

    for (int i = 0; i < N - 1; i++) {
        for (int j = 0; j < N - i - 1; j++) {
            if (comparaMaior(time[j], time[j + 1])) {
                Medalha temp = time[j];
                time[j] = time[j + 1];
                time[j + 1] = temp;
            }
        }
    }

    for (int i = 0; i < N; i++) {
        printf("%s %d %d %d\n", time[i].nome, time[i].ouro, time[i].prata, time[i].bronze);
    }

    return 0;
}
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

int main() {
    int N, M;
    scanf("%d %d", &N, &M);

    int num[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &num[i]);
    }
    int sub[M];
    for (int i = 0; i < M; i++) {
        scanf("%d", &sub[i]);
    }

    int j = 0;
    for (int i = 0; i < N && j < M; i++) {
        if (num[i] == sub[j]) {
            j++;
        }
    }

    if (j == M) {
        printf("S");
    } else {
        printf("N");
    }
}
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

int main() {
    int numCartelas, numerosPorCartela, numerosSorteados;
    scanf("%d %d %d", &numCartelas, &numerosPorCartela, &numerosSorteados);
    
    int **cartelas = (int**)malloc(numCartelas * sizeof(int*));
    for (int i = 0; i < numCartelas; i++) {
        cartelas[i] = (int*)malloc(numerosPorCartela * sizeof(int));
    }
    
    int *acertos = (int*)malloc(numCartelas * sizeof(int));
    for (int i = 0; i < numCartelas; i++) {
        acertos[i] = 0;
    }
    
    for (int i = 0; i < numCartelas; i++) {
        for (int j = 0; j < numerosPorCartela; j++) {
            scanf("%d", &cartelas[i][j]);
        }
    }
    
    int *sorteados = (int*)malloc(numerosSorteados * sizeof(int));
    for (int i = 0; i < numerosSorteados; i++) {
        scanf("%d", &sorteados[i]);
    }
    
    bool achouVencedor = false;
    bool primeiroVencedor = true;
    
    for (int i = 0; i < numerosSorteados && !achouVencedor; i++) {
        int numeroAtual = sorteados[i];
        
        for (int c = 0; c < numCartelas; c++) {
            for (int k = 0; k < numerosPorCartela; k++) {
                if (cartelas[c][k] == numeroAtual) {
                    acertos[c]++;
                    break;
                }
            }
        }
        
        for (int c = 0; c < numCartelas; c++) {
            if (acertos[c] == numerosPorCartela) {
                achouVencedor = true;
                
                if (primeiroVencedor) {
                    printf("%d", c + 1);
                    primeiroVencedor = false;
                } else {
                    printf(" %d", c + 1);
                }
            }
        }
    }
    
    printf("\n");
    
    for (int i = 0; i < numCartelas; i++) {
        free(cartelas[i]);
    }
    free(cartelas);
    free(acertos);
    free(sorteados);
    
    return 0;
}

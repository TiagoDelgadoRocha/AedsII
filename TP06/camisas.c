#include <stdio.h>

int main() {
    int casos;
    // Lê o número de casos de teste
    scanf("%d", &casos);

    for (int i = 0; i < casos; i++) {
        int numcamisas;
        // Lê o número de alunos
        scanf("%d", &numcamisas);

        int count1 = 0;
        int count2 = 0;

        // Loop para ler o tamanho da camisa de cada aluno
        for (int j = 0; j < numcamisas; j++) {
            int tamanho;
            scanf("%d", &tamanho);
            
            // Se o aluno veste tamanho 1, aumenta a contagem desse tamanho
            if (tamanho == 1) {
                count1++;
            }
            // Se o aluno veste tamanho 2, aumenta a contagem desse tamanho
            if (tamanho == 2) {
                count2++;
            }
        }

        int qnt1, qnt2;
        // Lê a quantidade em estoque dos tamanhos 1 e 2
        scanf("%d", &qnt1);
        scanf("%d", &qnt2);

        // Se a quantidade de alunos vestindo cada tamanho for menor ou igual à em estoque, imprime S
        if (qnt1 >= count1 && qnt2 >= count2) {
            printf("S\n");
        } else {
            // Caso contrário, imprime N
            printf("N\n");
        }
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N;
    scanf("%d", &N);

    for (int i = 0; i < N; i++)
    {
        int L;
        scanf("%d", &L);
        int vagao[L];
        for (int j = 0; j < L; j++)
        {
            scanf("%d", &vagao[j]);
        }
        int contador = 0;
        for (int j = 0; j < L - 1; j++)
        {
                for (int k = 0; k < L - j - 1; k++)
                {
                    if(vagao[k]>vagao[k+1]){
                        int temp=vagao[k];
                        vagao[k]=vagao[k+1];
                        vagao[k+1]=temp;
                        contador++;
                    }
                }
        }

        printf("%d \n",contador);
    }

    return 0;
}
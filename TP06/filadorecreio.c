#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX 1000

void ordenar(int *arr, int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (arr[j] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

int main() {
    int n;
    scanf("%d", &n);
    
    for (int t = 0; t < n; t++) {
        int m;
        scanf("%d", &m);
        
        int original[MAX];
        int ordenado[MAX];
        
        for (int i = 0; i < m; i++) {
            scanf("%d", &original[i]);
            ordenado[i] = original[i];
        }
        
        ordenar(ordenado, m);
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (original[i] == ordenado[i]) {
                count++;
            }
        }
        
        printf("%d\n", count);
    }
    
    return 0;
}

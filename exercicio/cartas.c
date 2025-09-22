#include <stdio.h>

int main() {
	int n, i, top, base, count;
	int fila[55], descartadas[55];
	while (1) {
		scanf("%d", &n);
		if (n == 0) break;
		// Inicializa a fila
		for (i = 1; i <= n; i++) fila[i] = i;
		top = 1; // topo da fila
		base = n; // base da fila
		count = 0; // contador de descartadas
		while (base - top + 1 > 1) {
			descartadas[count++] = fila[top++]; // descarta o topo
			fila[++base] = fila[top++]; // move o próximo para base
		}
		// Imprime descartadas
		printf("Discarded cards:");
		for (i = 0; i < count; i++) {
			if (i == 0) printf(" %d", descartadas[i]);
			else printf(", %d", descartadas[i]);
		}
		printf("\nRemaining card: %d\n", fila[top]);
	}
	return 0;
}

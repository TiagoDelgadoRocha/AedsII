public class HeapSort {

    public void sort(int[] arr) {
        int n = arr.length;

        // ---- Fase 1: Construir o Max Heap ----
        // Começamos do último nó que não é uma folha (n / 2 - 1) e vamos
        // até a raiz (índice 0), aplicando o heapify em cada um.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // ---- Fase 2: Extrair elementos um por um para ordenar ----
        // O loop vai do último elemento até o segundo (índice 1).
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual (o maior elemento) para o final do array
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Chama heapify na heap reduzida (tamanho 'i') a partir da raiz (índice 0)
            // para reestabelecer a propriedade de Max Heap.
            heapify(arr, i, 0);
        }
    }


    void heapify(int[] arr, int n, int i) {
        int maior = i;       // Inicializa o maior como a raiz da subárvore
        int esquerda = 2 * i + 1;  // Índice do filho da esquerda
        int direita = 2 * i + 2;   // Índice do filho da direita

        // Verifica se o filho da esquerda existe e é maior que a raiz atual
        if (esquerda < n && arr[esquerda] > arr[maior]) {
            maior = esquerda;
        }

        // Verifica se o filho da direita existe e é maior que o "maior" encontrado até agora
        if (direita < n && arr[direita] > arr[maior]) {
            maior = direita;
        }

        // Se o maior não for mais a raiz original, troque-os
        if (maior != i) {
            int swap = arr[i];
            arr[i] = arr[maior];
            arr[maior] = swap;

            // Recursivamente, chame heapify na subárvore afetada pela troca
            // para garantir que ela também se torne um Max Heap.
            heapify(arr, n, maior);
        }
    }

    /**
     * Método auxiliar para imprimir um array.
     * @param arr O array a ser impresso.
     */
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // --- Exemplo de Uso ---
    public static void main(String[] args) {
        int[] meuArray = {12, 11, 13, 5, 6, 7, 20, -1};

        System.out.print("Array original: ");
        printArray(meuArray);

        HeapSort hs = new HeapSort();
        hs.sort(meuArray);

        System.out.print("Array ordenado: ");
        printArray(meuArray);
        // Saída esperada: Array ordenado: -1 5 6 7 11 12 13 20 
    }
}
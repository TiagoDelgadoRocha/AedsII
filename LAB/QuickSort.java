import java.util.Random;

public class QuickSort {
    private static Random random = new Random();
    
    // 1. QuickSort usando o primeiro elemento como pivô
    public static void QuickSortFirstPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionFirst(array, left, right);
            QuickSortFirstPivot(array, left, pivotIndex - 1);
            QuickSortFirstPivot(array, pivotIndex + 1, right);
        }
    }
    
    // 2. QuickSort usando o último elemento como pivô
    public static void QuickSortLastPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionLast(array, left, right);
            QuickSortLastPivot(array, left, pivotIndex - 1);
            QuickSortLastPivot(array, pivotIndex + 1, right);
        }
    }
    
    // 3. QuickSort usando um elemento aleatório como pivô
    public static void QuickSortRandomPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionRandom(array, left, right);
            QuickSortRandomPivot(array, left, pivotIndex - 1);
            QuickSortRandomPivot(array, pivotIndex + 1, right);
        }
    }
    
    // 4. QuickSort usando a mediana de três elementos como pivô
    public static void QuickSortMedianOfThree(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionMedianOfThree(array, left, right);
            QuickSortMedianOfThree(array, left, pivotIndex - 1);
            QuickSortMedianOfThree(array, pivotIndex + 1, right);
        }
    }
    
    // Função auxiliar para trocar elementos
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Partição usando o primeiro elemento como pivô
    private static int partitionFirst(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1;
        
        for (int j = left + 1; j <= right; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, left, i - 1);
        return i - 1;
    }
    
    // Partição usando o último elemento como pivô
    private static int partitionLast(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }
    
    // Partição usando um elemento aleatório como pivô
    private static int partitionRandom(int[] array, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(array, randomIndex, right);
        return partitionLast(array, left, right);
    }
    
    // Partição usando a mediana de três elementos como pivô
    private static int partitionMedianOfThree(int[] array, int left, int right) {
        int mid = left + (right - left) / 2;
        
        // Ordena os três elementos: left, mid, right
        if (array[left] > array[mid]) {
            swap(array, left, mid);
        }
        if (array[mid] > array[right]) {
            swap(array, mid, right);
        }
        if (array[left] > array[mid]) {
            swap(array, left, mid);
        }
        
        // Move a mediana para o final e usa a partição padrão
        swap(array, mid, right);
        return partitionLast(array, left, right);
    }
    
    // Método para criar array ordenado
    private static int[] createSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }
    
    // Método para criar array aleatório
    private static int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 2) + 1;
        }
        return array;
    }
    
    // Método para criar array quase ordenado (90% ordenado)
    private static int[] createNearlySortedArray(int size) {
        int[] array = createSortedArray(size);
        int swaps = Math.max(1, size / 10); // 10% de elementos fora de lugar
        
        for (int i = 0; i < swaps; i++) {
            int pos1 = random.nextInt(size);
            int pos2 = random.nextInt(size);
            swap(array, pos1, pos2);
        }
        return array;
    }
    
    // Método para medir tempo de execução com múltiplas execuções
    private static double measureTimeAverage(Runnable algorithm, int iterations) {
        long totalTime = 0;
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            algorithm.run();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return (totalTime / (double) iterations) / 1_000_000.0; // Retorna em ms
    }
    
    // Método para análise de desempenho
    private static void performanceAnalysis() {
        int[] sizes = {100, 1000, 10000};
        String[] arrayTypes = {"Ordenado", "Quase Ordenado", "Aleatório"};
        String[] algorithms = {"Primeiro Pivô", "Último Pivô", "Pivô Aleatório", "Mediana de Três"};
        
        System.out.println("=== ANÁLISE DE DESEMPENHO DO QUICKSORT ===\n");
        System.out.printf("%-15s %-20s %-15s %s\n", "Tamanho", "Tipo do Array", "Algoritmo", "Tempo (ms)");
        System.out.println("─".repeat(80));
        
        for (int size : sizes) {
            for (int typeIndex = 0; typeIndex < arrayTypes.length; typeIndex++) {
                // Criar arrays de teste
                int[] originalArray;
                switch (typeIndex) {
                    case 0: originalArray = createSortedArray(size); break;
                    case 1: originalArray = createNearlySortedArray(size); break;
                    default: originalArray = createRandomArray(size); break;
                }
                
                // Testar cada algoritmo
                for (int algIndex = 0; algIndex < algorithms.length; algIndex++) {
                    double avgTimeMs = 0;
                    int iterations = size <= 1000 ? 5 : 3; // Mais iterações para arrays menores
                    
                    // Executar o algoritmo apropriado e medir o tempo médio
                    switch (algIndex) {
                        case 0:
                            avgTimeMs = measureTimeAverage(() -> {
                                int[] testArray = originalArray.clone();
                                QuickSortFirstPivot(testArray, 0, testArray.length - 1);
                            }, iterations);
                            break;
                        case 1:
                            avgTimeMs = measureTimeAverage(() -> {
                                int[] testArray = originalArray.clone();
                                QuickSortLastPivot(testArray, 0, testArray.length - 1);
                            }, iterations);
                            break;
                        case 2:
                            avgTimeMs = measureTimeAverage(() -> {
                                int[] testArray = originalArray.clone();
                                QuickSortRandomPivot(testArray, 0, testArray.length - 1);
                            }, iterations);
                            break;
                        case 3:
                            avgTimeMs = measureTimeAverage(() -> {
                                int[] testArray = originalArray.clone();
                                QuickSortMedianOfThree(testArray, 0, testArray.length - 1);
                            }, iterations);
                            break;
                    }
                    
                    System.out.printf("%-15d %-20s %-15s %.3f\n", 
                        size, arrayTypes[typeIndex], algorithms[algIndex], avgTimeMs);
                }
                System.out.println();
            }
        }
        
        System.out.println("=== ANÁLISE DOS RESULTADOS ===");
        System.out.println("📊 ARRAYS ORDENADOS (Pior Caso):");
        System.out.println("   • Primeiro/Último Pivô: O(n²) - Muito lento para arrays grandes");
        System.out.println("   • Pivô Aleatório: O(n log n) - Desempenho consistente");
        System.out.println("   • Mediana de Três: O(n log n) - Melhor desempenho geral");
        
        System.out.println("\n🔄 ARRAYS QUASE ORDENADOS (Caso Comum):");
        System.out.println("   • Todos os algoritmos: Desempenho razoável e similar");
        System.out.println("   • Mediana de Três: Ligeiramente melhor na maioria dos casos");
        
        System.out.println("\n🎲 ARRAYS ALEATÓRIOS (Caso Médio):");
        System.out.println("   • Todos os algoritmos: Desempenho similar O(n log n)");
        System.out.println("   • Primeiro/Último Pivô: Funcionam bem quando não há ordenação");
        
        System.out.println("\n🏆 RECOMENDAÇÕES:");
        System.out.println("   • Uso Geral: Mediana de Três (mais estável)");
        System.out.println("   • Dados Aleatórios: Qualquer estratégia funciona bem");
        System.out.println("   • Dados Ordenados: EVITAR Primeiro/Último Pivô");
        System.out.println("   • Performance: Pivô Aleatório ou Mediana de Três");
    }
    
    // Método main para executar análise de desempenho
    public static void main(String[] args) {
        performanceAnalysis();
    }
}
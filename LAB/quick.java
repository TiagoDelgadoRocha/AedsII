import java.util.Random;

public class quick {
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
    
    // Função auxiliar para imprimir o array
    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    // Método main para testar as implementações
    public static void main(String[] args) {
        // Array de teste
        int[] originalArray = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42};
        
        System.out.println("Array original:");
        printArray(originalArray);
        
        // Teste QuickSort com primeiro pivô
        int[] array1 = originalArray.clone();
        System.out.println("\nQuickSort com primeiro pivô:");
        QuickSortFirstPivot(array1, 0, array1.length - 1);
        printArray(array1);
        
        // Teste QuickSort com último pivô
        int[] array2 = originalArray.clone();
        System.out.println("\nQuickSort com último pivô:");
        QuickSortLastPivot(array2, 0, array2.length - 1);
        printArray(array2);
        
        // Teste QuickSort com pivô aleatório
        int[] array3 = originalArray.clone();
        System.out.println("\nQuickSort com pivô aleatório:");
        QuickSortRandomPivot(array3, 0, array3.length - 1);
        printArray(array3);
        
        // Teste QuickSort com mediana de três
        int[] array4 = originalArray.clone();
        System.out.println("\nQuickSort com mediana de três:");
        QuickSortMedianOfThree(array4, 0, array4.length - 1);
        printArray(array4);
    }
}
import java.util.Scanner;

public class filadorecreio {
    
    static void ordenar(int[] arr, int n) {
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
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        for (int t = 0; t < n; t++) {
            int m = sc.nextInt();
            
            int[] original = new int[m];
            int[] ordenado = new int[m];
            
            for (int i = 0; i < m; i++) {
                original[i] = sc.nextInt();
                ordenado[i] = original[i];
            }
            
            ordenar(ordenado, m);
            
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (original[i] == ordenado[i]) {
                    count++;
                }
            }
            
            System.out.println(count);
        }
        
        sc.close();
    }
}

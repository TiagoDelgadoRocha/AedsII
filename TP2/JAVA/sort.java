import java.util.Scanner;

public class sort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            System.out.println(N + " " + M);

            if (N == 0 && M == 0) {
                break;
            }

            int[] numeros = new int[N];
            for (int i = 0; i < N; i++) {
                numeros[i] = sc.nextInt();
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - 1 - i; j++) {
                    if (numeros[j] > numeros[j + 1]) {
                        int temp = numeros[j];
                        numeros[j] = numeros[j + 1];
                        numeros[j + 1] = temp;
                    }
                }
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (numeros[N - j - 1] % M == i && numeros[N - j - 1] % 2 != 0) {
                        System.out.println(numeros[N - j - 1]);
                    }
                }
                for (int j = 0; j < N; j++) {
                    if (numeros[j] % M == i && numeros[j] % 2 == 0) {
                        System.out.println(numeros[j]);
                    }
                }
            }
        }
        
        sc.close();
    }
}
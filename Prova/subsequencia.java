import java.util.Scanner;

public class subsequencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = scanner.nextInt();
        }

        int[] sub = new int[M];
        for (int i = 0; i < M; i++) {
            sub[i] = scanner.nextInt();
        }

        int j = 0;
        for (int i = 0; i < N && j < M; i++) {
            if (num[i] == sub[j]) {
                j++;
            }
        }

        if (j == M) {
            System.out.print("S");
        } else {
            System.out.print("N");
        }

        scanner.close();
    }
}
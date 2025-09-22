import java.util.Scanner;

public class Vagao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int t = 0; t < N; t++) {
            int L = sc.nextInt();
            int[] trem = new int[L];
            for (int i = 0; i < L; i++) {
                trem[i] = sc.nextInt();
            }
            int swaps = 0;
            for (int i = 0; i < L - 1; i++) {
                for (int j = 0; j < L - 1 - i; j++) {
                    if (trem[j] > trem[j + 1]) {
                        int temp = trem[j];
                        trem[j] = trem[j + 1];
                        trem[j + 1] = temp;
                        swaps++;
                    }
                }
            }
            System.out.println("Optimal train swapping takes " + swaps + " swaps.");
        }
        sc.close();
    }
}

import java.util.Scanner;

public class bingo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numCartelas = sc.nextInt();
        int numerosPorCartela = sc.nextInt();
        int numerosSorteados = sc.nextInt();

        int[][] cartelas = new int[numCartelas][numerosPorCartela];
        int[] acertos = new int[numCartelas];

        for (int i = 0; i < numCartelas; i++) {
            for (int j = 0; j < numerosPorCartela; j++) {
                cartelas[i][j] = sc.nextInt();
            }
        }

        int[] sorteados = new int[numerosSorteados];
        for (int i = 0; i < numerosSorteados; i++) {
            sorteados[i] = sc.nextInt();
        }

        String saida = "";
        boolean achouVencedor = false;

        for (int i = 0; i < numerosSorteados && !achouVencedor; i++) {
            int numeroAtual = sorteados[i];

            for (int c = 0; c < numCartelas; c++) {
                for (int k = 0; k < numerosPorCartela; k++) {
                    if (cartelas[c][k] == numeroAtual) {
                        acertos[c]++;
                        break;
                    }
                }
            }

            for (int c = 0; c < numCartelas; c++) {
                if (acertos[c] == numerosPorCartela) {
                    achouVencedor = true;

                    if (saida.isEmpty()) {
                        saida += (c + 1);
                    } else {
                        saida += " " + (c + 1);
                    }
                }
            }
        }

        System.out.println(saida);
        sc.close();
    }
}
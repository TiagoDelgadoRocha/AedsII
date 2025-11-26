import java.util.Scanner;

public class Goteira {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Leitura da Entrada
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine(); // Consumir o \n pendente

        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }

        // 2. Simulação da Água (linha por linha)
        for (int i = 0; i < N; i++) {

            // --- Passagem 1: Fluxo Vertical ---
            // A água cai da célula (i-1, j) para (i, j)
            if (i > 0) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == '.' && grid[i - 1][j] == 'o') {
                        grid[i][j] = 'o';
                    }
                }
            }

            // --- Passagem 2: Fluxo Horizontal (Iterativo) ---
            // Isso só acontece se houver uma "próxima linha" (i+1) para verificar
            // se há prateleiras
            if (i < N - 1) {
                boolean mudou = true;
                while (mudou) {
                    mudou = false;

                    // Propagação da Esquerda -> Direita
                    for (int j = 0; j < M; j++) {
                        if (grid[i][j] == '.') {
                            // Regra 2: c(i, j-1) = 'o' E c(i+1, j-1) = '#'
                            if (j > 0 && grid[i][j - 1] == 'o' && grid[i + 1][j - 1] == '#') {
                                grid[i][j] = 'o';
                                mudou = true;
                            }
                        }
                    }

                    // Propagação da Direita -> Esquerda
                    // (Necessário caso a água venha apenas da direita)
                    for (int j = M - 1; j >= 0; j--) {
                        if (grid[i][j] == '.') {
                            // Regra 3: c(i, j+1) = 'o' E c(i+1, j+1) = '#'
                            if (j < M - 1 && grid[i][j + 1] == 'o' && grid[i + 1][j + 1] == '#') {
                                grid[i][j] = 'o';
                                mudou = true;
                            }
                        }
                    }
                } // Fim do while(mudou)
            } // Fim do if (i < N-1)
        } // Fim do for (linhas)

        // 3. Impressão da Saída
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}
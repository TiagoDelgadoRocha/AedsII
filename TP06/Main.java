import java.util.Scanner;

public class Main { // Em Java, todo código fica dentro de uma classe

    // Função para verificar se o movimento para CIMA é válido
    private static boolean podeMoverCima(int[][] grid) {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                // Verifica se um bloco pode deslizar para um espaço vazio
                if (grid[r][c] == 0 && grid[r + 1][c] != 0) {
                    return true;
                }
                // Verifica se dois blocos podem se fundir
                if (grid[r][c] != 0 && grid[r][c] == grid[r + 1][c]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Função para verificar se o movimento para BAIXO é válido
    private static boolean podeMoverBaixo(int[][] grid) {
        for (int c = 0; c < 4; c++) {
            for (int r = 3; r > 0; r--) {
                // Verifica se um bloco pode deslizar para um espaço vazio
                if (grid[r][c] == 0 && grid[r - 1][c] != 0) {
                    return true;
                }
                // Verifica se dois blocos podem se fundir
                if (grid[r][c] != 0 && grid[r][c] == grid[r - 1][c]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Função para verificar se o movimento para ESQUERDA é válido
    private static boolean podeMoverEsquerda(int[][] grid) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                // Verifica se um bloco pode deslizar para um espaço vazio
                if (grid[r][c] == 0 && grid[r][c + 1] != 0) {
                    return true;
                }
                // Verifica se dois blocos podem se fundir
                if (grid[r][c] != 0 && grid[r][c] == grid[r][c + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Função para verificar se o movimento para DIREITA é válido
    private static boolean podeMoverDireita(int[][] grid) {
        for (int r = 0; r < 4; r++) {
            for (int c = 3; c > 0; c--) {
                // Verifica se um bloco pode deslizar para um espaço vazio
                if (grid[r][c] == 0 && grid[r][c - 1] != 0) {
                    return true;
                }
                // Verifica se dois blocos podem se fundir
                if (grid[r][c] != 0 && grid[r][c] == grid[r][c - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Equivalente ao scanf

        int nCasos = sc.nextInt();

        while (nCasos-- > 0) { // Em Java, --nCasos ou nCasos-- em um loop funciona de forma similar
            int[][] grid = new int[4][4]; // Declaração do array 2D em Java
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            boolean moveu = false; // 'bool' em C é 'boolean' em Java
            
            // As saídas devem ser em ordem alfabética: DOWN, LEFT, RIGHT, UP

            if (podeMoverBaixo(grid)) {
                System.out.print("DOWN"); // printf -> System.out.print
                moveu = true;
            }

            if (podeMoverEsquerda(grid)) {
                if (moveu) System.out.print(" "); // Adiciona espaço se não for o primeiro
                System.out.print("LEFT");
                moveu = true;
            }

            if (podeMoverDireita(grid)) {
                if (moveu) System.out.print(" "); // Adiciona espaço
                System.out.print("RIGHT");
                moveu = true;
            }

            if (podeMoverCima(grid)) {
                if (moveu) System.out.print(" "); // Adiciona espaço
                System.out.print("UP");
                moveu = true;
            }

            // Se nenhum movimento for válido
            if (!moveu) {
                System.out.print("NONE");
            }
            
            System.out.println(); // printf("\n") -> System.out.println()
        }

        sc.close(); // Boa prática fechar o Scanner
    }
}
import java.util.Scanner;

public class paoDeQueijo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] tab = new int[105][105];
        
        while (sc.hasNext()) {
            String linha = sc.next();
            
            if (linha.equals("FIM")) {
                break;
            }
            
            int N = Integer.parseInt(linha);
            int M = sc.nextInt();
            
            // lê o tabuleiro
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tab[i][j] = sc.nextInt();
                }
            }
            
            // processa e imprime
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tab[i][j] == 1) {
                        System.out.print('9');
                    } else {
                        int c = 0;
                        // 4-vizinhança: cima, baixo, esquerda, direita
                        if (i - 1 >= 0 && tab[i - 1][j] == 1) c++; // cima
                        if (i + 1 < N && tab[i + 1][j] == 1) c++;  // baixo
                        if (j - 1 >= 0 && tab[i][j - 1] == 1) c++; // esquerda
                        if (j + 1 < M && tab[i][j + 1] == 1) c++;  // direita
                        System.out.print(c);
                    }
                }
                System.out.println();
            }
        }
        
        sc.close();
    }
}

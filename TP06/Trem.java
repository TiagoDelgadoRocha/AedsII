import java.util.Scanner;

public class Trem {
    
    public static void main(String[] args) {
        
        // Inicializa o Scanner para ler a entrada do console
        Scanner scanner = new Scanner(System.in);
        
        // Lê a primeira linha para obter N e M
        String inicio = scanner.nextLine();
        
        // Converte os caracteres para inteiros
        // '0' é subtraído para obter o valor numérico do char
        int N = inicio.charAt(0) - '0'; // Número de vagões
        int M = inicio.charAt(2) - '0'; // Número de operações
        
        // Cria o trem (array)
        // Em Java, arrays de inteiros são automaticamente inicializados com 0
        int[] trem = new int[N];
        
        // Processa as M operações
        for (int i = 0; i < M; i++) {
            
            // Lê a linha da operação
            String array = scanner.nextLine();
            
            // Obtém o tipo de operação (1 ou 2)
            int operacao = array.charAt(0) - '0';
            
            if (operacao == 1) {
                // Atividade 1: Preencher vagão
                // Pega o índice do vagão (subtrai '0' e -1 para ajustar o índice 0-based)
                int vagaoIndex = array.charAt(2) - '0' - 1;
                // Pega o valor a ser adicionado
                int valor = array.charAt(4) - '0';
                
                // Adiciona o valor ao vagão
                trem[vagaoIndex] += valor;
                
            } else if (operacao == 2) {
                // Atividade 2: Mostrar soma de intervalos
                
                // A variável 'intervalo' é zerada a cada vez que a operação 2 é chamada
                int intervalo = 0;
                
                // Adiciona o valor do primeiro vagão especificado
                intervalo += trem[array.charAt(2) - '0' - 1];
                
                // Verifica se o segundo vagão é diferente do primeiro
                if (array.charAt(4) != array.charAt(2)) {
                    intervalo += trem[array.charAt(4) - '0' - 1];
                }
                
                // Verifica se o terceiro vagão é diferente dos dois primeiros
                if (array.charAt(6) != array.charAt(2) && array.charAt(6) != array.charAt(4)) {
                    intervalo += trem[array.charAt(6) - '0' - 1];
                }
                
                // Verifica se o quarto vagão é diferente dos três primeiros
                if (array.charAt(8) != array.charAt(2) && array.charAt(8) != array.charAt(4) && array.charAt(8) != array.charAt(6)) {
                    intervalo += trem[array.charAt(8) - '0' - 1];
                }
                
                // Imprime o resultado final da soma do intervalo
                System.out.println(intervalo);
            }
        }
        
        // Fecha o scanner para evitar vazamento de recursos
        scanner.close();
    }
}
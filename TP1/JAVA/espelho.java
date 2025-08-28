import java.util.Scanner;

public class espelho {
    public static void mostrarEspelho(int num1, int num2) {
        // Primeira parte: imprime a sequência normal de num1 a num2.
        for (int i = num1; i <= num2; i++) {
            MyIO.print(i);
        }

        // Segunda parte: imprime a sequência com os dígitos de cada número invertidos.
        while (num2 >= num1) {
            int i = num2;
            
            // Lógica para inverter os dígitos de um número.
            while (i > 0) {
                // Pega o último dígito do número.
                MyIO.print(i % 10);
                // Remove o último dígito do número.
                i /= 10;
            }
            num2--;
        }
        MyIO.print("\n");
    }

    public static void main(String[] str) {
        Scanner sc = new Scanner(System.in);
        
        // O loop continua enquanto houver dados na entrada (até o fim do arquivo/EOF).
        while (sc.hasNext()) {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            mostrarEspelho(num1, num2);
        }
        sc.close();
    }
}
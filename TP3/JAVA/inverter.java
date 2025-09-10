import java.util.Scanner;

public class inverter {

    public static void inverteStringRec(String str, int i, int j) {
        if (j >= i) {
            System.out.print(str.charAt(j));
            inverteStringRec(str, i, j - 1);
        }
    }
    
    public static void inverteString(String str) {
        inverteStringRec(str, 0, str.length() - 1);
        
        // Garante a quebra de linha ao final de cada palavra invertida.
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String str = sc.nextLine();
        
        // O loop para quando a entrada é a palavra "FIM".
        while (!str.equals("FIM")) {
            inverteString(str);
            str = sc.nextLine();
        }
        
        sc.close();
    }
}
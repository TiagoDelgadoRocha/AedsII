import java.util.Scanner;

public class placa {

    public static boolean ehLetra(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static boolean ehDigito(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String placa = scanner.nextLine();

        int len = placa.length();

        int resp = 0;
        if (len == 8) {
            if (
                ehLetra(placa.charAt(0)) &&
                ehLetra(placa.charAt(1)) &&
                ehLetra(placa.charAt(2)) &&
                placa.charAt(3) == '-' &&
                ehDigito(placa.charAt(4)) &&
                ehDigito(placa.charAt(5)) &&
                ehDigito(placa.charAt(6)) &&
                ehDigito(placa.charAt(7))
            ) {
                resp = 1;
            }
        } else if (len == 7) {
            if (
                ehLetra(placa.charAt(0)) &&
                ehLetra(placa.charAt(1)) &&
                ehLetra(placa.charAt(2)) &&
                ehDigito(placa.charAt(3)) &&
                ehLetra(placa.charAt(4)) &&
                ehDigito(placa.charAt(5)) &&
                ehDigito(placa.charAt(6))
            ) {
                resp = 2;
            }
        }

        System.out.println(resp);
        scanner.close();
    }
}
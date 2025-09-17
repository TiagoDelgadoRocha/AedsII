import java.util.Scanner;

public class VerificadorDePlacas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String placa = scanner.nextLine();

        int resultado = 0;

        if (placa.length() == 8) {
            if (Character.isLetter(placa.charAt(0)) &&
                Character.isLetter(placa.charAt(1)) &&
                Character.isLetter(placa.charAt(2)) &&
                placa.charAt(3) == '-' &&
                Character.isDigit(placa.charAt(4)) &&
                Character.isDigit(placa.charAt(5)) &&
                Character.isDigit(placa.charAt(6)) &&
                Character.isDigit(placa.charAt(7)))
            {
                resultado = 1;
            }
        }
        else if (placa.length() == 7) {
            if (Character.isLetter(placa.charAt(0)) &&
                Character.isLetter(placa.charAt(1)) &&
                Character.isLetter(placa.charAt(2)) &&
                Character.isDigit(placa.charAt(3)) &&
                Character.isLetter(placa.charAt(4)) &&
                Character.isDigit(placa.charAt(5)) &&
                Character.isDigit(placa.charAt(6)))
            {
                resultado = 2;
            }
        }

        System.out.println(resultado);
        
        scanner.close();
    }
}
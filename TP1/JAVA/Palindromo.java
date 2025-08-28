class Palindromo {
    public static void ehPalindromo(String str) {
        // Assume que a string é um palíndromo até que se prove o contrário.
        boolean teste = true;
        int tam = str.length();

        // Loop que percorre a string apenas até a metade.
        // Compara o caractere do início com o seu correspondente no final.
        for (int i = 0; i < tam / 2; i++) {
            if (str.charAt(i) != str.charAt(tam - i - 1)) {
                teste = false; 
                break;         // Interrompe o loop, pois a verificação já falhou.
            }
        }

        if (teste) {
            MyIO.println("SIM"); 
        } else {
            MyIO.println("NAO"); 
        }
    }
    public static void main(String[] args) {
        String str = MyIO.readLine();

        // Continua o loop enquanto a entrada não for "FIM".
        while (!str.equals("FIM")) {
            ehPalindromo(str);
            str = MyIO.readLine();
        }
    }
}
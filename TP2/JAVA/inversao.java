public class inversao {
    public static void inverteString(String str, int tam) {
        // String vazia que armazenará o resultado invertido.
        String invertida = "";

        // Laço que percorre a string original de trás para frente.
        for (int i = 0; i < tam; i++) {
            // Pega o caractere correspondente na ordem inversa.
            // Ex: para i=0, pega o último caractere (tam-1).
            char letra = str.charAt(tam - (i + 1));
            // Constrói a string invertida adicionando cada letra.
            invertida = invertida + letra;
        }
        MyIO.println(invertida);
    }

    static public void main(String[] args) {
        // Lê a primeira linha de entrada.
        String str = MyIO.readLine();

        // O laço continua até que a entrada seja a palavra "FIM".
        while (!str.equals("FIM")) {
            int tam = str.length();
            inverteString(str, tam);
            str = MyIO.readLine();
        }
    }
}
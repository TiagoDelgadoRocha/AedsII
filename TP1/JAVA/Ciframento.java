class Ciframento {
    public static String cifra(String str, int tamanho) {
        // Inicializa uma string vazia para construir o resultado.
        String palavra = "";

        // Percorre cada caractere da string de entrada.
        for (int i = 0; i < tamanho; i++) {
            // Pega o caractere na posição 'i', soma 3 ao seu valor ASCII e converte de volta para char.
            char c = (char)(str.charAt(i) + 3);
            // Concatena o novo caractere à string de resultado.
            palavra += c;
        }
        return palavra;
    }
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        // Continua executando enquanto a palavra lida não for "FIM".
        while (!palavra.equals("FIM")) {
            int tam = palavra.length();

            // Chama o método de cifragem e imprime o resultado na tela.
            MyIO.println(cifra(palavra, tam));

            // Lê a próxima linha para a próxima iteração do loop.
            palavra = MyIO.readLine();
        }
    }
}
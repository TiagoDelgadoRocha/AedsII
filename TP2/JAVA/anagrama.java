public class anagrama {
    public static void VerificaAnagrama(String str1, String str2) {
        boolean saoIguais = true;
        // Verifica se as strings têm o mesmo tamanho, uma condição básica para serem anagramas.
        if (str1.length() != str2.length()) {
            saoIguais = false;
            MyIO.println("NÃO");
            return;
        }

        if (saoIguais) {
            // Converte as strings para arrays de caracteres para poder ordená-las.
            char[] array1 = str1.toCharArray();
            char[] array2 = str2.toCharArray();
            int tam = str1.length();

            // Ordena o primeiro array de caracteres em ordem decrescente (Bubble Sort).
            for (int i = 0; i < tam - 1; i++) {
                for (int j = 0; j < tam - 1 - i; j++) {
                    if (array1[j] < array1[j + 1]) {
                        char temp = array1[j];
                        array1[j] = array1[j + 1];
                        array1[j + 1] = temp;
                    }
                }
            }

            // Ordena o segundo array da mesma forma.
            for (int i = 0; i < tam - 1; i++) {
                for (int j = 0; j < tam - 1 - i; j++) {
                    if (array2[j] < array2[j + 1]) {
                        char temp = array2[j];
                        array2[j] = array2[j + 1];
                        array2[j + 1] = temp;
                    }
                }
            }

            // Compara os dois arrays ordenados, caractere por caractere.
            for (int i = 0; i < tam; i++) {
                // Se encontrar uma diferença, não são anagramas e o laço é interrompido.
                if (array1[i] != array2[i]) {
                    saoIguais = false;
                    break;
                }
            }
            if (saoIguais) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NÃO");
            }
        }
    }

    public static void main(String[] args) {
        // Lê a linha de entrada completa (ex: "Amor - Roma").
        String linha = MyIO.readLine();

        while (!linha.equals("FIM")) {
            // Quebra a linha no hífen para separar as duas palavras.
            String[] palavras = linha.split("-");

            // Limpa os dados: remove espaços (.trim()) e converte para minúsculo (.toLowerCase()).
            String str1 = palavras[0].trim().toLowerCase();
            String str2 = palavras[1].trim().toLowerCase();

            // Chama a função para verificar se as palavras limpas são anagramas.
            VerificaAnagrama(str1, str2);

            // Lê a próxima linha para continuar o laço.
            linha = MyIO.readLine();
        }
    }
}
import java.util.Scanner;
import java.util.Random;

public class aleatorio {

    // Substitui todas as ocorrências de letra1 por letra2 em uma string.
    public static String alteraString(String word, char letra1, char letra2) {
        // Cria um array de char pois Strings em Java são imutáveis.
        char[] word2 = new char[word.length()];

        // Percorre a string original para construir a nova.
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letra1) {
                word2[i] = letra2;
            } else {
                word2[i] = word.charAt(i);
            }
        }

        // Converte o array de caracteres de volta para uma string.
        return new String(word2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        
        // Define uma semente fixa para que a sequência "aleatória" seja sempre a mesma.
        rd.setSeed(4);
        String word = sc.nextLine();
        while (!word.equals("FIM")) {
            // Gera um caractere aleatório entre 'a' e 'z'.
            char letra1 = (char)('a' + (Math.abs(rd.nextInt()) % 26));
            char letra2 = (char)('a' + (Math.abs(rd.nextInt()) % 26));
            
            System.out.println(alteraString(word, letra1, letra2));
            word = sc.nextLine();
        }
        
        sc.close();
    }
}
public class anagrama {
    public static void VerificaAnagrama(String str1, String str2) {
        boolean saoIguais = true;
        if (str1.length() != str2.length()) {
            saoIguais = false;
            MyIO.println("NÃO");
            return;
        }
        if (saoIguais) {
            char[] array1 = str1.toCharArray();
            char[] array2 = str2.toCharArray();
            int tam = str1.length();
            for (int i = 0; i < tam - 1; i++) {
                for (int j = 0; j < tam - 1 - i; j++) {
                    if (array1[j] < array1[j + 1]) {
                        char temp = array1[j];
                        array1[j] = array1[j + 1];
                        array1[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < tam - 1; i++) {
                for (int j = 0; j < tam - 1 - i; j++) {
                    if (array2[j] < array2[j + 1]) {
                        char temp = array2[j];
                        array2[j] = array2[j + 1];
                        array2[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < tam; i++) {
                if (array1[i] != array2[i]) {
                    saoIguais = false;
                    break;
                }
            }
            if (saoIguais) {
                MyIO.println("SIM");
            } else
                MyIO.println("NÃO");
        }
    }

    public static void main(String[] args) {
    String linha = MyIO.readLine();

    while (!linha.equals("FIM")) {
   
        String[] palavras = linha.split("-");
        String str1 = palavras[0].trim().toLowerCase();
        String str2 = palavras[1].trim().toLowerCase();

        VerificaAnagrama(str1, str2);

        linha = MyIO.readLine();
    }
}
}

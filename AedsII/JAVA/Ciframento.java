class Ciframento{
    public static String cifra(String str, int tamanho){
        String palavra = "";
        for (int i = 0; i < tamanho; i++) {
            char c = (char)(str.charAt(i) + 3);
            palavra += c;
        }
        return palavra;
    }

    public static void main(String[] args){
        String palavra = MyIO.readLine();

        while(!palavra.equals("FIM")){
        int tam = palavra.length();

        MyIO.println(cifra(palavra, tam));

        palavra = MyIO.readLine();

        }
    }
}
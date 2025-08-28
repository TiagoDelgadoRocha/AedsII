class aeds {
    public static void ehPalindromo(String str) {
        boolean teste = true;
        int tam = str.length();

        for (int i = 0; i < tam / 2; i++) {
            if (str.charAt(i) != str.charAt(tam - i - 1)) {
                teste = false;
                break;
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
        while (!str.equals("FIM")) {
            ehPalindromo(str);
            str = MyIO.readLine();
        }
    }
}

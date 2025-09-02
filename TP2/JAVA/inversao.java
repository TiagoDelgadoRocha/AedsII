public class inversao {
    public static void inverteString(String str, int tam){
        String invertida= "";
        for(int i=0;i<tam;i++){
            char letra=str.charAt(tam-(i+1));
            invertida= invertida+letra;
        }
        MyIO.println(invertida);
    }


    static public void main(String[] args){
        String str= MyIO.readLine();
        while (!str.equals("FIM")) {
            
            int tam= str.length();
            inverteString(str,tam);
            str=MyIO.readLine();
        }
    }
}

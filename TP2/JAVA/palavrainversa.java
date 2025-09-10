public class palavrainversa {

    public static void inverteRec(String str, int i, int j){
        if(j>=i){
            MyIO.print(str.charAt(j));
            inverteRec(str,i,j-1);
        }
    }

    public static void inverte(String str){
        inverteRec(str,0,str.length()-1);
    }
    
    public static void main(String[] args){
        String str= MyIO.readLine();
        inverte(str);
    }
}

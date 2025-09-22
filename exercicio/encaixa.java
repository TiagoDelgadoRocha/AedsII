import java.util.Scanner;

public class encaixa{

    public static boolean checaseCabe(String A, String B){
        if(B.length() > A.length()) {
            return false;
        }
        int inicioA = A.length() - B.length();
        for(int i = 0; i < B.length(); i++){
            if(A.charAt(inicioA + i) != B.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for(int i = 0; i < N; i++){
            String A = sc.next();
            String B = sc.next();
            if(checaseCabe(A, B)) System.out.println("encaixa");
            else System.out.println("nao encaixa");
        }

        sc.close();
    }

}
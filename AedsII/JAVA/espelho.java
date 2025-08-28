import java.util.Scanner;

public class espelho {
    public static void mostrarEspelho(int num1, int num2){
        for(int i=num1;i<=num2;i++){
            MyIO.print(i);
        }
        while (num2>=num1 ) {
        int i=num2;
            while (i>0) {
                MyIO.print(i%10);
                i/=10;    
            }
            num2--;
        }
        MyIO.print("\n");
    }

    public static void main(String[] str){
       Scanner sc= new Scanner(System.in);
       while (sc.hasNext()) {
            int num1= sc.nextInt();
            int num2= sc.nextInt();
            mostrarEspelho(num1, num2);
        }
        sc.close();
    }
}

import java.util.Scanner;

public class somador {
    
       public static int somadorDeDigitos(int num) {
        if (num == 0) {
            return 0;
        } else {
            return (num % 10) + somadorDeDigitos(num / 10);
        }
    }

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        
        while(sc.hasNextInt()){
            int num=sc.nextInt();
            int resp= somadorDeDigitos(num);
            System.out.println(resp);
        }
        
        sc.close();
    
    }
}

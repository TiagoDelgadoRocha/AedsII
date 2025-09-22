import java.util.Scanner;

public class JAVA {
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        float N= sc.nextInt();//le quantos diretores vao falar
        float K= sc.nextInt();

        float contadorP=0;
       for(int i=1;i<=N;i++){
        if(i%2==0 && i>2){
          contadorP++;
        }//conta os intervalos
            
       }
       float R= (K-contadorP)/N;//faz a conta do limite de cada fala
       

       System.out.println(R);
        

        sc.close();
    }
}
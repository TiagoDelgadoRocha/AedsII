public class espelho {
    public static void mostrarEspelho(int num1, int num2){
        for(int i=num1;i<num2;i++){
            MyIO.print(i);
        }
        for(int i=num2;i>num1;i--){
            MyIO.print(i);
        }
    }

    public static void main(String[] str){
        int num1= MyIO.readInt();
        int num2= MyIO.readInt();
        while (num1 != 0 || num2 != 0) {
            mostrarEspelho(num1, num2);
            num1 = MyIO.readInt();
            num2 = MyIO.readInt();
        }
    }
}

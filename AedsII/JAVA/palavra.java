public abstract class palavra {
    public static void main(String[] args){
        String str= MyIO.readLine();
        while (!str.equals("FIM")) {
            MyIO.println(str);
            str = MyIO.readLine();
        }
    }
}

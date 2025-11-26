import java.util.Scanner;
public class teclado{
	public static void main(String[]args){
		Scanner sc= new Scanner(System.in);
		while(sc.hasNextLine()){
			String linha=sc.nextLine();
			int abriu=0;
			String dentro="";
			String normal="";
			for(int i=0;i<linha.length();i++){
				if(linha.charAt(i)=='['){
					abriu++;
				}
				if(linha.charAt(i)==']'){
					abriu--;
				}
				if(abriu>0&&linha.charAt(i)!='['){ 
					dentro+=linha.charAt(i);
				}
				if(abriu==0&&(linha.charAt(i)!='['&&linha.charAt(i)!=']')){
					normal+=linha.charAt(i);
				}
			}
			if(dentro.length()>1){
				System.out.println(dentro+normal);
		}
		else{
			System.out.println(normal);
		}
		}
		sc.close();
	}
}


				

import java.util.Scanner;

public class van {
    
    static class Aluno {
        String nome;
        char regiao;
        int distancia;
        
        Aluno(String nome, char regiao, int distancia) {
            this.nome = nome;
            this.regiao = regiao;
            this.distancia = distancia;
        }
    }
    
    static boolean deveVirAntes(Aluno a1, Aluno a2) {
        if (a1.distancia != a2.distancia) {
            return a1.distancia < a2.distancia;
        }
        if (a1.regiao != a2.regiao) {
            return a1.regiao < a2.regiao;
        }
        return a1.nome.compareTo(a2.nome) < 0;
    }
    
    static void ordenarAlunos(Aluno[] alunos, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (deveVirAntes(alunos[j], alunos[i])) {
                    Aluno temp = alunos[i];
                    alunos[i] = alunos[j];
                    alunos[j] = temp;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextInt()) {
            int q = sc.nextInt();
            Aluno[] alunos = new Aluno[q];
            
            for (int i = 0; i < q; i++) {
                String nome = sc.next();
                char regiao = sc.next().charAt(0);
                int distancia = sc.nextInt();
                alunos[i] = new Aluno(nome, regiao, distancia);
            }
            
            ordenarAlunos(alunos, q);
            
            for (int i = 0; i < q; i++) {
                System.out.println(alunos[i].nome);
            }
        }
        
        sc.close();
    }
}

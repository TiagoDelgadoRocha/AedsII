import java.util.Scanner;

class Pais {
    String nome;
    int ouro, prata, bronze;

    Pais(String nome, int ouro, int prata, int bronze) {
        this.nome = nome;
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
    }

    // Método para comparar dois países conforme as regras do quadro de medalhas
    boolean melhorQue(Pais outro) {
        if (this.ouro != outro.ouro)
            return this.ouro > outro.ouro;
        if (this.prata != outro.prata)
            return this.prata > outro.prata;
        if (this.bronze != outro.bronze)
            return this.bronze > outro.bronze;
        return this.nome.compareTo(outro.nome) < 0;
    }
}

public class teste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Pais[] paises = new Pais[N];

        for (int i = 0; i < N; i++) {
            String nome = sc.next();
            int ouro = sc.nextInt();
            int prata = sc.nextInt();
            int bronze = sc.nextInt();
            paises[i] = new Pais(nome, ouro, prata, bronze);
        }

        // Ordenação por seleção usando a classe Pais
        for (int i = 0; i < N - 1; i++) {
            int melhor = i;
            for (int j = i + 1; j < N; j++) {
                if (paises[j].melhorQue(paises[melhor])) {
                    melhor = j;
                }
            }
            // Troca
            Pais tmp = paises[i];
            paises[i] = paises[melhor];
            paises[melhor] = tmp;
        }

        for (int i = 0; i < N; i++) {
            System.out.println(paises[i].nome + " " + paises[i].ouro + " " + paises[i].prata + " " + paises[i].bronze);
        }
        sc.close();
    }
}
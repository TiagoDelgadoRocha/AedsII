import java.util.Scanner;

class Medalha {
    String nome;
    int ouro;
    int prata;
    int bronze;

    public Medalha(String nome, int ouro, int prata, int bronze) {
        this.nome = nome;
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
    }
}

public class Medalhas {

    // retorna true se A deve vir depois de B no ranking (ou seja, precisa trocar)
    public static boolean comparaMaior(Medalha a, Medalha b) {
        if (a.ouro < b.ouro) return true;   // quem tem menos ouro vai pra trás
        if (a.ouro > b.ouro) return false;

        if (a.prata < b.prata) return true;
        if (a.prata > b.prata) return false;

        if (a.bronze < b.bronze) return true;
        if (a.bronze > b.bronze) return false;

        // empate nas medalhas -> ordem alfabética crescente
        int min = (a.nome.length() < b.nome.length() ? a.nome.length() : b.nome.length());

        for (int i = 0; i < min; i++) {
            if (a.nome.charAt(i) < b.nome.charAt(i)) return false;
            if (a.nome.charAt(i) > b.nome.charAt(i)) return true;
        }
        return a.nome.length() > b.nome.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // lê a quantidade de países
        Medalha[] time = new Medalha[N];

        for (int i = 0; i < N; i++) {
            String nome = sc.next();
            int ouro = sc.nextInt();
            int prata = sc.nextInt();
            int bronze = sc.nextInt();
            time[i] = new Medalha(nome, ouro, prata, bronze);
        }

        // bubble sort
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (comparaMaior(time[j], time[j + 1])) {
                    Medalha temp = time[j];
                    time[j] = time[j + 1];
                    time[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(time[i].nome + " " + time[i].ouro + " " +
                               time[i].prata + " " + time[i].bronze);
        }

        sc.close();
    }
}

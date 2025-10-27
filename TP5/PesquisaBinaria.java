import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

class GamePesquisado {
    String name;
    GamePesquisado() {
        this.name = "";
    }
    GamePesquisado(String name) {
        this.name = name;
    }
}

public class PesquisaBinaria {
    public static Scanner sc = new Scanner(System.in);
    public static int comparacoes = 0;

    public static void main(String[] args) {
        // Criando vetor geral
        String linha;
        linha = sc.nextLine();
        String[] ids = new String[1000];
        int idsCount = 0;
        while (!linha.equals("FIM")) {
            ids[idsCount] = linha;
            idsCount++;
            linha = sc.nextLine();
        }
        
        // Criando objeto com ids digitados
        GamePesquisado[] gamesList = new GamePesquisado[idsCount];
        int gamesCount = CriandoObjetos.objetos(ids, idsCount, gamesList);
        
        // Oordenando o array
        if (gamesCount > 0)
            oordenando(gamesList, 0, gamesCount - 1);
        
        // Iniciando contagem de tempo
        long inicio = System.currentTimeMillis();
        
        // Vendo se os nomes digitados são presentes
        linha = sc.nextLine();
        while (!linha.equals("FIM")) {
            boolean achou = false;
            int dir = gamesCount - 1, esq = 0, meio = 0;
            while (esq <= dir) {
                meio = (esq + dir) / 2;
                comparacoes++;
                if (linha.equals(gamesList[meio].name)) {
                    achou = true;
                    esq = dir + 1;
                } else {
                    comparacoes++;
                    if (linha.compareTo(gamesList[meio].name) > 0) {
                        esq = meio + 1;
                    } else {
                        dir = meio - 1;
                    }
                }
            }
            if (achou)
                System.out.println(" SIM");
            else
                System.out.println(" NAO");
            linha = sc.nextLine();
        }
        
        // Finalizando contagem de tempo
        long fim = System.currentTimeMillis();
        double tempoExecucao = (fim - inicio) / 1000.0;
        
        // Criar arquivo de log
        criarLog("885375", tempoExecucao, comparacoes);
        
        // Fechando o scanner
        sc.close();
    }

    // Oordenando o array de String com QuickSort
    static void oordenando(GamePesquisado[] gameList, int esq, int dir) {
        int i = esq, j = dir;
        int meio = (dir + esq) / 2;
        String pivo = gameList[meio].name;
        while (i <= j) {
            while (gameList[i].name.compareTo(pivo) < 0)
                i++;
            while (gameList[j].name.compareTo(pivo) > 0)
                j--;
            if (i <= j) {
                swap(gameList, i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            oordenando(gameList, esq, j);
        if (i < dir)
            oordenando(gameList, i, dir);
    }

    // Trocando posições
    static void swap(GamePesquisado[] gameList, int i, int j) {
        GamePesquisado aux = gameList[i];
        gameList[i] = gameList[j];
        gameList[j] = aux;
    }

    // Criar arquivo de log
    static void criarLog(String matricula, double tempo, int comp) {
        String nomeArquivo = matricula + "_binaria.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            bw.write(matricula + "\t" + tempo + "\t" + comp);
        } catch (Exception e) {
            System.err.println("Erro ao criar arquivo de log: " + e.getMessage());
        }
    }
}

class CriandoObjetos {
    // Variável que pula caracteres das linhas
    static int contador = 0;
    // Scanner
    public static Scanner sc = new Scanner(System.in);
    // Ids de pesquisa
    static String[] ids;
    static int idsCount;

    static int objetos(String[] idArray, int idArrayCount, GamePesquisado[] gamesList) {
        ids = idArray;
        idsCount = idArrayCount;
        int gamesCount = 0;
        
        // Abrindo do arquivo
        InputStream is = null;
        try {
            java.io.File arquivo = new java.io.File("/tmp/games.csv");
            if (!arquivo.exists()) {
                System.out.println("Arquivo 'games.csv' não encontrado na pasta do projeto!");
                return 0;
            }
            is = new FileInputStream(arquivo);
        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
            return 0;
        }

        sc = new Scanner(is);
        // Pula cabeçalho
        if (sc.hasNextLine())
            sc.nextLine();
        // Pesquisa por id
        while (sc.hasNextLine() && idsCount > 0) {
            String linha = sc.nextLine();
            // Capturando outras informações
            int id = capturaId(linha);
            String name = capturaName(linha);
            int idxRemover = igualId(id);
            if (idxRemover != -1) {
                GamePesquisado jogo = new GamePesquisado(name);
                gamesList[gamesCount] = jogo;
                gamesCount++;
                // Remover id encontrado
                for (int i = idxRemover; i < idsCount - 1; i++) {
                    ids[i] = ids[i + 1];
                }
                idsCount--;
            }
            // Adicionando a classe
            contador = 0;
        }
        sc.close();
        return gamesCount;
    }

    // Vendo se id é igual e retorna o índice ou -1
    static int igualId(int id) {
        for (int i = 0; i < idsCount; i++) {
            if (Integer.parseInt(ids[i]) == id) {
                return i;
            }
        }
        return -1;
    }

    // Capturando Id
    static int capturaId(String jogo) {
        int id = 0;
        while (contador < jogo.length() && Character.isDigit(jogo.charAt(contador))) {
            id = id * 10 + (jogo.charAt(contador) - '0');
            contador++;
        }
        return id;
    }

    // Capturando nome
    static String capturaName(String jogo) {
        String name = "";
        while (contador < jogo.length() && jogo.charAt(contador) != ',') {
            contador++;
        }
        contador++;
        while (contador < jogo.length() && jogo.charAt(contador) != ',') {
            name += jogo.charAt(contador);
            contador++;
        }
        return name;
    }
}

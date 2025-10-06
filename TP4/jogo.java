import java.util.Scanner;
import java.io.File;

class Game {
    public int id;
    public String name;
    public String dataLancamento;
    public int estimativaJogadores;
    public float preco;
    public String[] idiomasSuportados;
    public float notaCritica;
    public float pontosUsuario;
    public float quantidadeConquistas;
    public String[] empresasResponsaveis;
    public String[] desenvolvedores;
    public String[] categorias;
    public String[] generos;
    public String[] palavraChave;

    public Game(String linha) {
        String[] campos = quebrarLinhaCSV(linha);
        
        this.id = parseInt(campos[0]);
        this.name = campos[1].replace("\"", "");
        this.dataLancamento = parseDataParaString(campos[2]);
        this.estimativaJogadores = parseInt(campos[3]);
        this.preco = parseFloat(campos[4]);
        this.idiomasSuportados = parseArrayDeString(campos[5]);
        this.notaCritica = parseInt(campos[6]);
        this.pontosUsuario = parseFloat(campos[7]);
        this.quantidadeConquistas = parseInt(campos[8]);
        this.empresasResponsaveis = parseArrayDeString(campos[9]);
        this.desenvolvedores = parseArrayDeString(campos[10]);
        this.categorias = parseArrayDeString(campos[11]);
        this.generos = parseArrayDeString(campos[12]);
        this.palavraChave = parseArrayDeString(campos[13]);
    }

    private String[] quebrarLinhaCSV(String linha) {
        String[] resultado = new String[14];
        boolean dentroDeAspas = false;
        int indice = 0;
        String campoAtual = "";
        
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            
            if (c == '"') {
                dentroDeAspas = !dentroDeAspas;
                campoAtual += c;
            } else if (c == ',' && !dentroDeAspas) {
                resultado[indice] = campoAtual;
                campoAtual = "";
                indice++;
            } else {
                campoAtual += c;
            }
        }
        
        if (indice < 14) {
            resultado[indice] = campoAtual;
        }
        
        return resultado;
    }

    private String parseDataParaString(String strData) {
        if (strData == null || strData.isEmpty() || strData.equals("\"\"")) {
            return "";
        }
        
        strData = strData.replace("\"", "").trim();
        
        if (strData.isEmpty()) {
            return "";
        }
        
        String[] partes = strData.split(" ");
        if (partes.length != 3) {
            return strData;
        }
        
        String mes = partes[0];
        String dia = partes[1].replace(",", "");
        String ano = partes[2];
        
        String mesNumero = "01";
        switch (mes) {
            case "Jan": mesNumero = "01"; break;
            case "Feb": mesNumero = "02"; break;
            case "Mar": mesNumero = "03"; break;
            case "Apr": mesNumero = "04"; break;
            case "May": mesNumero = "05"; break;
            case "Jun": mesNumero = "06"; break;
            case "Jul": mesNumero = "07"; break;
            case "Aug": mesNumero = "08"; break;
            case "Sep": mesNumero = "09"; break;
            case "Oct": mesNumero = "10"; break;
            case "Nov": mesNumero = "11"; break;
            case "Dec": mesNumero = "12"; break;
        }
        
        if (dia.length() == 1) {
            dia = "0" + dia;
        }
        
        return dia + "/" + mesNumero + "/" + ano;
    }

    private String[] parseArrayDeString(String strArray) {
        if (strArray == null || strArray.isEmpty() || strArray.equals("\"\"")) {
            return new String[0];
        }
        
        strArray = strArray.replace("\"", "").trim();
        
        if (strArray.isEmpty() || strArray.equals("[]")) {
            return new String[0];
        }
        
        strArray = strArray.replace("[", "").replace("]", "");
        
        String[] temp = strArray.split(",");
        String[] resultado = new String[temp.length];
        
        for (int i = 0; i < temp.length; i++) {
            resultado[i] = temp[i].trim().replace("'", "");
        }
        
        return resultado;
    }

    private int parseInt(String str) {
        if (str == null || str.isEmpty() || str.equals("\"\"")) {
            return 0;
        }
        str = str.replace("\"", "").trim();
        if (str.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private float parseFloat(String str) {
        if (str == null || str.isEmpty() || str.equals("\"\"")) {
            return 0.0f;
        }
        str = str.replace("\"", "").trim();
        if (str.isEmpty()) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    public void imprimirDetalhes() {
        System.out.println("=> " + this.id + " ## " + this.name + " ## " + this.dataLancamento + 
                          " ## " + this.estimativaJogadores + " ## " + this.preco + 
                          " ## " + formatarArray(this.idiomasSuportados) + " ## " + (int)this.notaCritica + 
                          " ## " + this.pontosUsuario + " ## " + (int)this.quantidadeConquistas + 
                          " ## " + formatarArray(this.empresasResponsaveis) + " ## " + formatarArray(this.desenvolvedores) + 
                          " ## " + formatarArray(this.categorias) + " ## " + formatarArray(this.generos) + 
                          " ## " + formatarArray(this.palavraChave) + " ##");
    }

    private String formatarArray(String[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }
        
        String resultado = "[";
        for (int i = 0; i < array.length; i++) {
            resultado += array[i];
            if (i < array.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }
}

class jogo {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            
            int[] appIDsSolicitados = new int[1000];
            int totalSolicitados = 0;
            
            // Ler AppIDs até "FIM"
            while (sc.hasNextLine()) {
                String linha = sc.nextLine().trim();
                
                if (linha.equals("FIM")) {
                    break;
                }
                
                if (linha.matches("\\d+")) {
                    appIDsSolicitados[totalSolicitados] = Integer.parseInt(linha);
                    totalSolicitados++;
                }
            }
            sc.close();
            
            // Carregar CSV
            Scanner csvScanner = new Scanner(new File("games.csv"));
            Game[] todosJogos = new Game[5000];
            int totalJogos = 0;
            
            while (csvScanner.hasNextLine() && totalJogos < 5000) {
                String linha = csvScanner.nextLine().trim();
                if (!linha.isEmpty() && !linha.contains("AppID,Name")) {
                    try {
                        todosJogos[totalJogos] = new Game(linha);
                        totalJogos++;
                    } catch (Exception e) {
                        // Ignora linhas com erro
                    }
                }
            }
            csvScanner.close();
            
            // Buscar e imprimir jogos
            for (int i = 0; i < totalSolicitados; i++) {
                int appIDProcurado = appIDsSolicitados[i];
                
                for (int j = 0; j < totalJogos; j++) {
                    if (todosJogos[j] != null && todosJogos[j].id == appIDProcurado) {
                        todosJogos[j].imprimirDetalhes();
                        break;
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
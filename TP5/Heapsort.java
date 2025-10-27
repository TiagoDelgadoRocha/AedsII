import java.util.*;
import java.io.*;
import java.util.Locale;

public class Heapsort {
    // ---------- ATRIBUTOS ----------
    public int id;
    public String name;
    public String releaseDate;
    public int estimatedOwners;
    public float price;
    public String[] supportedLanguages;
    public int metacriticScore;
    public float userScore;
    public int achievements;
    public String[] publishers;
    public String[] developers;
    public String[] categories;
    public String[] genres;
    public String[] tags;

    // Contadores para log (comparações entre elementos do array e movimentações)
    public static long comparacoes = 0;
    public static long movimentacoes = 0;

    // ---------- Construtor ----------
    public Heapsort() {}

    // ---------- ParseInt ----------
    public int ParseInt(String s) {
        int acc = 0;
        if (s == null) return 0;
        int pos = 0;
        while (pos < s.length()) {
            char ch = s.charAt(pos);
            if (ch >= '0' && ch <= '9') {
                acc = acc * 10 + (ch - '0');
            }
            pos++;
        }
        return acc;
    }

    // ---------- ParseFloat ----------
    public float ParseFloat(String s) {
        if (s == null) return 0.0f;
        float value = 0.0f;
        boolean seenDot = false;
        float denom = 10.0f;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int d = ch - '0';
                if (!seenDot) value = value * 10 + d;
                else {
                    value += d / denom;
                    denom *= 10;
                }
            } else if (ch == '.') {
                seenDot = true;
            }
        }
        return value;
    }

    // ---------- manualEquals ----------
    public boolean manualEquals(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        for (int i = 0, n = s1.length(); i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }

    // ---------- manualSubstring ----------
    public String manualSubstring(String s, int begin, int end) {
        StringBuilder sb = new StringBuilder(end - begin);
        for (int i = begin; i < end; i++) sb.append(s.charAt(i));
        return sb.toString();
    }

    // ---------- manualIsEmpty ----------
    public boolean manualIsEmpty(String s) {
        return s == null || s.length() == 0;
    }

    // ---------- manualTrim ----------
    public String manualTrim(String s) {
        if (s == null) return "";
        int a = 0;
        int b = s.length() - 1;
        while (a <= b && s.charAt(a) == ' ') a++;
        while (b >= a && s.charAt(b) == ' ') b--;
        if (a > b) return "";
        return manualSubstring(s, a, b + 1);
    }

    // ---------- formatarData ----------
    public String formatarData(String data) {
        if (manualIsEmpty(data)) return "00/00/0000";
        if (data.length() >= 2 && data.charAt(0) == '"' && data.charAt(data.length() - 1) == '"')
            data = manualSubstring(data, 1, data.length() - 1);

        // dividir em partes por espaço (espera: Mes Dia, Ano)
        String[] bits = new String[3];
        int k = 0;
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (ch == ' ') {
                bits[k++] = cur.toString();
                cur.setLength(0);
            } else cur.append(ch);
        }
        bits[k] = cur.toString();

        String month = bits[0];
        String dayWithComma = bits[1];
        String year = bits[2];

        // extrair dia sem vírgula
        StringBuilder daySb = new StringBuilder();
        for (int i = 0; i < dayWithComma.length(); i++) {
            char ch = dayWithComma.charAt(i);
            if (ch != ',') daySb.append(ch);
        }
        String day = daySb.toString();

        // mapa simples de meses
        String numMes = "00";
        if (manualEquals(month, "Jan")) numMes = "01";
        else if (manualEquals(month, "Feb")) numMes = "02";
        else if (manualEquals(month, "Mar")) numMes = "03";
        else if (manualEquals(month, "Apr")) numMes = "04";
        else if (manualEquals(month, "May")) numMes = "05";
        else if (manualEquals(month, "Jun")) numMes = "06";
        else if (manualEquals(month, "Jul")) numMes = "07";
        else if (manualEquals(month, "Aug")) numMes = "08";
        else if (manualEquals(month, "Sep")) numMes = "09";
        else if (manualEquals(month, "Oct")) numMes = "10";
        else if (manualEquals(month, "Nov")) numMes = "11";
        else if (manualEquals(month, "Dec")) numMes = "12";

        if (day.length() == 1) day = "0" + day;
        return day + "/" + numMes + "/" + year;
    }

    // ---------- formatarColchetes ----------
    public String[] formatarColchetes(String campo) {
        if (campo == null) return new String[0];
        if (campo.length() >= 2 && campo.charAt(0) == '"' && campo.charAt(campo.length() - 1) == '"')
            campo = manualSubstring(campo, 1, campo.length() - 1);
        if (campo.length() >= 2 && campo.charAt(0) == '[' && campo.charAt(campo.length() - 1) == ']')
            campo = manualSubstring(campo, 1, campo.length() - 1);

        if (manualIsEmpty(campo)) return new String[0];

        ArrayList<String> acc = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < campo.length(); i++) {
            char ch = campo.charAt(i);
            if (ch == ',' ) {
                String s = manualTrim(cur.toString());
                if (s.length() > 0) acc.add(s);
                cur.setLength(0);
            } else if (ch != '\'') {
                cur.append(ch);
            }
        }
        String last = manualTrim(cur.toString());
        if (last.length() > 0) acc.add(last);

        String[] out = new String[acc.size()];
        for (int i = 0; i < acc.size(); i++) out[i] = acc.get(i);
        return out;
    }

    // ---------- splitManual ----------
    public String[] splitManual(String linha) {
        if (linha == null) return new String[0];
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuote = false;
        boolean inBracket = false;
        for (int i = 0; i < linha.length(); i++) {
            char ch = linha.charAt(i);
            if (ch == '"') {
                inQuote = !inQuote;
                cur.append(ch);
            } else if (ch == '[') {
                inBracket = true;
                cur.append(ch);
            } else if (ch == ']') {
                inBracket = false;
                cur.append(ch);
            } else if (ch == ',' && !inQuote && !inBracket) {
                parts.add(cur.toString());
                cur.setLength(0);
            } else cur.append(ch);
        }
        parts.add(cur.toString());
        String[] arr = new String[parts.size()];
        for (int i = 0; i < parts.size(); i++) arr[i] = parts.get(i);
        return arr;
    }

    // ---------- HeapSort crescente ----------
    public void heapSort(Heapsort[] vetor) {
        int len = vetor.length;
        for (int root = len / 2 - 1; root >= 0; root--) heapify(vetor, len, root);
        for (int end = len - 1; end > 0; end--) {
            swap(vetor, 0, end);
            heapify(vetor, end, 0);
        }
    }

    public void heapify(Heapsort[] vetor, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            Heapsort a = vetor[left];
            Heapsort b = vetor[largest];
            // comparar estimatedOwners
            comparacoes++;
            if (a.estimatedOwners > b.estimatedOwners) {
                largest = left;
            } else {
                // se iguais, comparar id
                comparacoes++;
                if (a.estimatedOwners == b.estimatedOwners) {
                    comparacoes++;
                    if (a.id > b.id) {
                        largest = left;
                    }
                }
            }
        }

        if (right < n) {
            Heapsort a = vetor[right];
            Heapsort b = vetor[largest];
            // comparar estimatedOwners
            comparacoes++;
            if (a.estimatedOwners > b.estimatedOwners) {
                largest = right;
            } else {
                // se iguais, comparar id
                comparacoes++;
                if (a.estimatedOwners == b.estimatedOwners) {
                    comparacoes++;
                    if (a.id > b.id) {
                        largest = right;
                    }
                }
            }
        }

        if (largest != i) {
            swap(vetor, i, largest);
            heapify(vetor, n, largest);
        }
    }

    public void swap(Heapsort[] vetor, int a, int b) {
        Heapsort t = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = t;
        movimentacoes += 3; // contabiliza as 3 atribuições do swap
    }

    // ---------- toString ----------
    @Override
    public String toString() {
        String df = formatarData(this.releaseDate);

        StringBuilder sbSL = new StringBuilder("[");
        for (int i = 0; i < supportedLanguages.length; i++) {
            sbSL.append(supportedLanguages[i]);
            if (i != supportedLanguages.length - 1) sbSL.append(", ");
        }
        sbSL.append(']');

        StringBuilder sbP = new StringBuilder("[");
        for (int i = 0; i < publishers.length; i++) {
            sbP.append(publishers[i]);
            if (i != publishers.length - 1) sbP.append(", ");
        }
        sbP.append(']');

        StringBuilder sbD = new StringBuilder("[");
        for (int i = 0; i < developers.length; i++) {
            sbD.append(developers[i]);
            if (i != developers.length - 1) sbD.append(", ");
        }
        sbD.append(']');

        StringBuilder sbC = new StringBuilder("[");
        for (int i = 0; i < categories.length; i++) {
            sbC.append(categories[i]);
            if (i != categories.length - 1) sbC.append(", ");
        }
        sbC.append(']');

        StringBuilder sbG = new StringBuilder("[");
        for (int i = 0; i < genres.length; i++) {
            sbG.append(genres[i]);
            if (i != genres.length - 1) sbG.append(", ");
        }
        sbG.append(']');

        StringBuilder sbT = new StringBuilder("[");
        for (int i = 0; i < tags.length; i++) {
            sbT.append(tags[i]);
            if (i != tags.length - 1) sbT.append(", ");
        }
        sbT.append(']');

        String priceFormatado = String.format(Locale.US, "%.2f", this.price);
        String userScoreFormatado = String.format(Locale.US, "%.1f", this.userScore);

        return "=> " + id + " ## " + name + " ## " + df + " ## " + estimatedOwners + " ## " + priceFormatado
                + " ## " + sbSL.toString() + " ## " + metacriticScore + " ## " + userScoreFormatado + " ## " + achievements
                + " ## " + sbP.toString() + " ## " + sbD.toString() + " ## " + sbC.toString() + " ## " + sbG.toString() + " ## " + sbT.toString() + " ##";
    }

    // ---------- MAIN ----------
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Heapsort proto = new Heapsort();

        Heapsort[] buffer = new Heapsort[1000];
        int total = 0;

        String line = scanner.nextLine();
        while (!proto.manualEquals(line, "FIM")) {
            BufferedReader br = new BufferedReader(new FileReader("/tmp/games.csv"));
            br.readLine();
            String row;
            while ((row = br.readLine()) != null) {
                String[] fields = proto.splitManual(row);
                if (proto.ParseInt(fields[0]) == proto.ParseInt(line)) {
                    Heapsort item = new Heapsort();
                    item.id = proto.ParseInt(fields[0]);
                    item.name = fields[1];
                    item.releaseDate = fields[2];
                    item.estimatedOwners = proto.ParseInt(fields[3]);
                    item.price = proto.manualEquals(fields[4], "Free to Play") ? 0.0f : proto.ParseFloat(fields[4]);
                    item.supportedLanguages = proto.formatarColchetes(fields[5]);
                    item.metacriticScore = proto.manualIsEmpty(fields[6]) ? -1 : proto.ParseInt(fields[6]);
                    item.userScore = proto.manualIsEmpty(fields[7]) || proto.manualEquals(fields[7], "tbd") ? -1.0f : proto.ParseFloat(fields[7]);
                    item.achievements = proto.manualIsEmpty(fields[8]) ? 0 : proto.ParseInt(fields[8]);
                    item.publishers = proto.formatarColchetes(fields[9]);
                    item.developers = proto.formatarColchetes(fields[10]);
                    item.categories = proto.formatarColchetes(fields[11]);
                    item.genres = proto.formatarColchetes(fields[12]);
                    item.tags = proto.formatarColchetes(fields[13]);
                    buffer[total++] = item;
                    break;
                }
            }
            br.close();
            line = scanner.nextLine();
        }
        scanner.close();

        Heapsort[] vetor = new Heapsort[total];
        for (int i = 0; i < total; i++) vetor[i] = buffer[i];

    // resetar contadores
    Heapsort.comparacoes = 0;
    Heapsort.movimentacoes = 0;

    // medir tempo do algoritmo de ordenação
    long inicio = System.nanoTime();
    proto.heapSort(vetor);
    long fim = System.nanoTime();
    long tempoMillis = (fim - inicio) / 1_000_000;

    // escrever arquivo de log na pasta corrente com a matrícula fornecida
    String matricula = "885375";
    String nomeArquivo = matricula + "_heapsort.txt";
    BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
    bw.write(matricula + "\t" + Heapsort.comparacoes + "\t" + Heapsort.movimentacoes + "\t" + tempoMillis);
    bw.close();

    for (Heapsort h : vetor) System.out.println(h.toString());
    }
}
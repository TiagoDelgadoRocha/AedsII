import java.util.Scanner;

public class infixaPos {

    private static boolean ehOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int precedencia(char op) {
        switch (op) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default:  return -1;
        }
    }

    private static boolean associativoADireita(char op) {
        return op == '^';
    }

    private static class PilhaChar {
        private final char[] dados;
        private int topo = -1;

        PilhaChar(int capacidade) {
            dados = new char[capacidade];
        }
        boolean vazia() { return topo < 0; }
        void empilhar(char c) { dados[++topo] = c; }
        char desempilhar() { return dados[topo--]; }
        char topo() { return dados[topo]; }
    }

    private static String infixaParaPosfixa(String expressao) {
        String saida = "";
        PilhaChar pilha = new PilhaChar(expressao.length());

        int i = 0;
        while (i < expressao.length()) {
            char c = expressao.charAt(i);

            if (Character.isWhitespace(c)) { i++; continue; }

            if (Character.isLetterOrDigit(c)) {
                int j = i;
                while (j < expressao.length() && Character.isLetterOrDigit(expressao.charAt(j))) j++;
                saida += expressao.substring(i, j);
                i = j;
                continue;
            }

            if (c == '(') {
                pilha.empilhar(c);
                i++;
                continue;
            }

            if (c == ')') {
                while (!pilha.vazia() && pilha.topo() != '(') {
                    saida += pilha.desempilhar();
                }
                if (!pilha.vazia() && pilha.topo() == '(') pilha.desempilhar();
                i++;
                continue;
            }

            if (ehOperador(c)) {
                while (!pilha.vazia() && ehOperador(pilha.topo())) {
                    char opTopo = pilha.topo();
                    if (precedencia(opTopo) > precedencia(c) ||
                       (precedencia(opTopo) == precedencia(c) && !associativoADireita(c))) {
                        saida += pilha.desempilhar();
                    } else break;
                }
                pilha.empilhar(c);
                i++;
                continue;
            }

            i++;
        }

        while (!pilha.vazia()) {
            char op = pilha.desempilhar();
            if (op != '(' && op != ')') saida += op;
        }

        return saida;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        if (!entrada.hasNextLine()) {
            entrada.close();
            return;
        }
        int casos = Integer.parseInt(entrada.nextLine().trim());

        int processadas = 0;
        while (processadas < casos && entrada.hasNextLine()) {
            String expr = entrada.nextLine();
            if (expr.trim().isEmpty()) continue;
            System.out.println(infixaParaPosfixa(expr));
            processadas++;
        }

        entrada.close();
    }
}
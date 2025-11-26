class No2 {
    String palavra;
    No2 esq, dir;
    
    public No2(String palavra) {
        this.palavra = palavra;
        this.esq = null;
        this.dir = null;
    }
}

class No {
    char letra;
    No esq, dir;
    No2 raiz; 
    
    public No(char letra) {
        this.letra = letra;
        this.esq = null;
        this.dir = null;
        this.raiz = null;
    }
}

class ArvoreArvore {
    No raiz; 

    public int contarPalavras(String padrao) {
        if (padrao == null || padrao.length() == 0) {
            return 0;
        }

        char primeiraLetra = padrao.charAt(0);
        int tamanhoPadrao = padrao.length();

        No noDaLetra = pesquisarLetra(this.raiz, primeiraLetra);

        if (noDaLetra == null) {
            return 0;
        }

        return contarNaArvoreDePalavras(noDaLetra.raiz, tamanhoPadrao);
    }

    private No pesquisarLetra(No i, char letraProcurada) {
        if (i == null) {
            return null;
        }

        if (letraProcurada == i.letra) {
            return i;
        } else if (letraProcurada < i.letra) {
            return pesquisarLetra(i.esq, letraProcurada);
        } else {
            return pesquisarLetra(i.dir, letraProcurada);
        }
    }

    private int contarNaArvoreDePalavras(No2 i, int tamanhoProcurado) {
        int contagem = 0;

        if (i != null) {
            if (i.palavra.length() == tamanhoProcurado) {
                contagem = 1;
            }
            
            contagem += contarNaArvoreDePalavras(i.esq, tamanhoProcurado);
            contagem += contarNaArvoreDePalavras(i.dir, tamanhoProcurado);
        }

        return contagem;
    }
}
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

// --- FUNÇÕES RECURSIVAS ---

bool eSoVogaisRec(char string[], int i) {
    // Caso base: se chegou ao fim da string, todos os caracteres são vogais.
    if (string[i] == '\0') {
        return true;
    }

    char c = tolower(string[i]);
    // Passo recursivo: se o caractere atual não for vogal, a string inteira falha.
    if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
        return false;
    }
    // Se o caractere atual for válido, verifica o resto da string.
    return eSoVogaisRec(string, i + 1);
}

bool eSoConsoantesRec(char string[], int i) {
    // Caso base: chegou ao fim da string, todos os caracteres são consoantes.
    if (string[i] == '\0') {
        return true;
    }

    char c = tolower(string[i]);
    // Passo recursivo: se não for uma letra ou se for uma vogal, a string falha.
    if (!('a' <= c && c <= 'z') || (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
        return false;
    }
    return eSoConsoantesRec(string, i + 1);
}

bool eSoInteirosRec(char string[], int i) {
    // Caso base: chegou ao fim da string, todos os caracteres são dígitos.
    if (string[i] == '\0') {
        return true;
    }

    // Passo recursivo: se não for um dígito, a string falha.
    if (!isdigit(string[i])) {
        return false;
    }
    return eSoInteirosRec(string, i + 1);
}

// Para verificar números reais, precisamos de um parâmetro extra para contar os pontos.
bool eSoReaisRec(char string[], int i, int pontoCount) {
    // Caso base: chegou ao fim da string, a verificação foi um sucesso.
    if (string[i] == '\0') {
        return true;
    }

    // Passo recursivo: se for um ponto ou vírgula, atualiza o contador.
    if (string[i] == '.' || string[i] == ',') {
        // Se já houver um ponto, a string falha.
        if (pontoCount > 0) {
            return false;
        }
        return eSoReaisRec(string, i + 1, pontoCount + 1);
    }
    // Se não for um dígito (e também não for um ponto), a string falha.
    if (!isdigit(string[i])) {
        return false;
    }
    return eSoReaisRec(string, i + 1, pontoCount);
}

// --- FUNÇÕES "WRAPPER" PARA INICIAR A RECURSÃO ---

bool eSoVogais(char string[]) { return eSoVogaisRec(string, 0); }
bool eSoConsoantes(char string[]) { return eSoConsoantesRec(string, 0); }
bool eSoInteiros(char string[]) { return eSoInteirosRec(string, 0); }
bool eSoReais(char string[]) { return eSoReaisRec(string, 0, 0); }


int main() {
    char str[1000];
    fgets(str, sizeof(str), stdin);
    // Remove o caractere de nova linha ('\n') que o fgets captura.
    str[strcspn(str, "\n")] = '\0';

    // O loop continua até que a entrada seja a palavra "FIM".
    while(strcmp(str, "FIM") != 0) {
        printf(eSoVogais(str) ? "SIM " : "NAO ");
        printf(eSoConsoantes(str) ? "SIM " : "NAO ");
        printf(eSoInteiros(str) ? "SIM " : "NAO ");
        printf(eSoReais(str) ? "SIM\n" : "NAO\n");

        fgets(str, sizeof(str), stdin);
        // Remove o '\n' da próxima string lida.
        str[strcspn(str, "\n")] = '\0';
    }
    return 0;
}
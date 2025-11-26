#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX 1000

typedef struct {
    char dados[MAX];
    int topo;
} PilhaChar;

void inicializarPilha(PilhaChar *pilha) {
    pilha->topo = -1;
}

bool pilhaVazia(PilhaChar *pilha) {
    return pilha->topo < 0;
}

void empilhar(PilhaChar *pilha, char c) {
    pilha->dados[++pilha->topo] = c;
}

char desempilhar(PilhaChar *pilha) {
    return pilha->dados[pilha->topo--];
}

char topoElemento(PilhaChar *pilha) {
    return pilha->dados[pilha->topo];
}

bool ehOperador(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
}

int precedencia(char op) {
    switch (op) {
        case '+':
        case '-': return 1;
        case '*':
        case '/': return 2;
        case '^': return 3;
        default:  return -1;
    }
}

bool associativoADireita(char op) {
    return op == '^';
}

bool ehEspaco(char c) {
    return c == ' ' || c == '\t' || c == '\n' || c == '\r';
}

bool ehAlfanumerico(char c) {
    return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
}

void infixaParaPosfixa(char *expressao, char *saida) {
    PilhaChar pilha;
    inicializarPilha(&pilha);
    int saidaPos = 0;
    int i = 0;
    
    while (i < strlen(expressao)) {
        char c = expressao[i];
        
        if (ehEspaco(c)) {
            i++;
            continue;
        }
        
        if (ehAlfanumerico(c)) {
            int j = i;
            while (j < strlen(expressao) && ehAlfanumerico(expressao[j])) {
                saida[saidaPos++] = expressao[j];
                j++;
            }
            i = j;
            continue;
        }
        
        if (c == '(') {
            empilhar(&pilha, c);
            i++;
            continue;
        }
        
        if (c == ')') {
            while (!pilhaVazia(&pilha) && topoElemento(&pilha) != '(') {
                saida[saidaPos++] = desempilhar(&pilha);
            }
            if (!pilhaVazia(&pilha) && topoElemento(&pilha) == '(') {
                desempilhar(&pilha);
            }
            i++;
            continue;
        }
        
        if (ehOperador(c)) {
            while (!pilhaVazia(&pilha) && ehOperador(topoElemento(&pilha))) {
                char opTopo = topoElemento(&pilha);
                if (precedencia(opTopo) > precedencia(c) ||
                   (precedencia(opTopo) == precedencia(c) && !associativoADireita(c))) {
                    saida[saidaPos++] = desempilhar(&pilha);
                } else {
                    break;
                }
            }
            empilhar(&pilha, c);
            i++;
            continue;
        }
        
        i++;
    }
    
    while (!pilhaVazia(&pilha)) {
        char op = desempilhar(&pilha);
        if (op != '(' && op != ')') {
            saida[saidaPos++] = op;
        }
    }
    
    saida[saidaPos] = '\0';
}

int main() {
    int casos;
    char linha[MAX];
    char resultado[MAX];
    
    if (fgets(linha, MAX, stdin) == NULL) return 0;
    casos = atoi(linha);
    
    int processadas = 0;
    while (processadas < casos && fgets(linha, MAX, stdin) != NULL) {
        linha[strcspn(linha, "\r\n")] = '\0';
        
        if (strlen(linha) == 0) continue;
        
        infixaParaPosfixa(linha, resultado);
        printf("%s\n", resultado);
        processadas++;
    }
    
    return 0;
}

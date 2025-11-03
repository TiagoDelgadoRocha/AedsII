#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct No{
    char* nome;
    struct No* dir;
    struct No* esq;
}No;

No* raiz = NULL;

No* novoNo(char x[]){
    No *novo = (No*) malloc (sizeof(No));
    novo->nome = (char*) malloc(sizeof(x));
    strcpy(novo->nome, x);
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}

No* inserirRec(char x[], No* i){
    if (i == NULL){
        i = novoNo(x);
    } else {
        int cmp = strcmp(x, i->nome);
        if (cmp < 0){
            i->esq = inserirRec(x, i->esq);
        } else if (cmp > 0){
            i->dir = inserirRec(x, i->dir);
        }
    }
    return i;
}

void inserir(char* x){
    raiz = inserirRec(x, raiz);
}


int main(){
    char palavra[50]; //= (char*) malloc(50*sizeof(char));
    scanf("%s", palavra);
    inserir(palavra);
    return 0;
}
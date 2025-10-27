#include <stdlib.h>
#include <stdio.h>

typedef struct Celula{
    int elemento;
    struct Celula *prox;
}Celula;

Celula *novaCelula(int elemento){
    Celula *nova=(Celula*) malloc(sizeof (Celula));
    nova->elemento=elemento;
    nova->prox=NULL;
    return nova;
}
Celula *primeiro;
Celula *ultimo;

void start(){
    primeiro=NULL;
    ultimo=NULL;
}

void inserir(int x){
    Celula *nova=novaCelula(x);
    if(ultimo == NULL){ 
        primeiro = ultimo = nova;
    } else { 
        ultimo->prox = nova;
        ultimo = nova;
    }
}

int remover(){
    if(primeiro==NULL){
        return -1;
    }
    Celula *tmp=primeiro;
    int removido=primeiro->elemento;
    primeiro=primeiro->prox;

    free(tmp);
    return removido;
}

void mostrar(){
     for(Celula *i = primeiro; i != NULL; i = i->prox){
        printf("%d ", i->elemento);
    }
    printf("\n");

}

Celula* inverterRecursivo(Celula *atual, Celula *novoProximo){
    if(atual == NULL) return novoProximo;
    
    Celula *proximoOriginal = atual->prox;
    atual->prox = novoProximo;
    
    return inverterRecursivo(proximoOriginal, atual);
}

void inverter(){
    if(primeiro == NULL || primeiro == ultimo) return; // Fila vazia ou com 1 elemento
    
    ultimo = primeiro; // O primeiro se torna o último
    primeiro = inverterRecursivo(primeiro, NULL); // Inverte recursivamente
}

int main (){

    start(); // Inicializa a fila

    printf("=== TESTANDO FILA ===\n");
    
    // Inserindo elementos
    printf("Inserindo: 10, 20, 30\n");
    inserir(10);
    inserir(20);
    inserir(30);
    
    printf("Fila: ");
    mostrar();
    
    // Removendo (FIFO - primeiro a entrar, primeiro a sair)
    printf("Removendo: %d\n", remover());
    printf("Fila após remoção: ");
    mostrar();
    
    // Testando a função inverter
    printf("\nTestando função inverter:\n");
    inserir(40);
    inserir(50);
    printf("Fila antes de inverter: ");
    mostrar();
    
    inverter();
    printf("Fila após inverter: ");
    mostrar();

    return 0;
}
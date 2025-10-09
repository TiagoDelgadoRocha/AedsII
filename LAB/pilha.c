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
Celula *topo;
void start(){
    topo=NULL;
}

void inserir(int x){
    Celula *tmp=novaCelula(x);
    tmp->prox=topo;
    topo=tmp;
}

void mostrar(){
    for(Celula *i=topo;i!=NULL;i=i->prox){
        printf("%d,",i->elemento);
    }
}

int remover(){
    if(topo == NULL) return -1; 
    Celula *tmp = topo;         
    int removido = topo->elemento;
    topo = topo->prox;          
    free(tmp);                  
    return removido;
}

int main(){
    start(); // Inicializa a pilha
    
    printf("=== TESTANDO PILHA ===\n");
    
    // Inserindo 3 elementos
    printf("Inserindo elementos: 10, 20, 30\n");
    inserir(10);
    inserir(20);
    inserir(30);
    
    // Mostrando a pilha
    printf("Pilha atual: ");
    mostrar();
    printf("\n");
    
    // Removendo elementos
    printf("Removendo elemento: %d\n", remover());
    printf("Pilha apos remocaoo: ");
    mostrar();
    printf("\n");
    
    printf("Removendo elemento: %d\n", remover());
    printf("Pilha apos remocaoo: ");
    mostrar();
    printf("\n");
    
    printf("Removendo elemento: %d\n", remover());
    printf("Pilha apos remocao: ");
    mostrar();
    printf("\n");
    
    // Tentando remover de pilha vazia
    int resultado = remover();
    if(resultado == -1) {
        printf("Pilha vazia - nao eh possivel remover\n");
    }
    
    return 0;
}
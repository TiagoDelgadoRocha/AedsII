
#include <stdio.h>
#include <stdbool.h> // Para usar o tipo 'bool'

// Função para verificar se o movimento para CIMA é válido
bool podeMoverCima(int grid[4][4]) {
    for (int c = 0; c < 4; c++) {
        for (int r = 0; r < 3; r++) {
            // Verifica se um bloco pode deslizar para um espaço vazio
            if (grid[r][c] == 0 && grid[r + 1][c] != 0) {
                return true;
            }
            // Verifica se dois blocos podem se fundir
            if (grid[r][c] != 0 && grid[r][c] == grid[r + 1][c]) {
                return true;
            }
        }
    }
    return false;
}

// Função para verificar se o movimento para BAIXO é válido
bool podeMoverBaixo(int grid[4][4]) {
    for (int c = 0; c < 4; c++) {
        for (int r = 3; r > 0; r--) {
            // Verifica se um bloco pode deslizar para um espaço vazio
            if (grid[r][c] == 0 && grid[r - 1][c] != 0) {
                return true;
            }
            // Verifica se dois blocos podem se fundir
            if (grid[r][c] != 0 && grid[r][c] == grid[r - 1][c]) {
                return true;
            }
        }
    }
    return false;
}

// Função para verificar se o movimento para ESQUERDA é válido
bool podeMoverEsquerda(int grid[4][4]) {
    for (int r = 0; r < 4; r++) {
        for (int c = 0; c < 3; c++) {
            // Verifica se um bloco pode deslizar para um espaço vazio
            if (grid[r][c] == 0 && grid[r][c + 1] != 0) {
                return true;
            }
            // Verifica se dois blocos podem se fundir
            if (grid[r][c] != 0 && grid[r][c] == grid[r][c + 1]) {
                return true;
            }
        }
    }
    return false;
}

// Função para verificar se o movimento para DIREITA é válido
bool podeMoverDireita(int grid[4][4]) {
    for (int r = 0; r < 4; r++) {
        for (int c = 3; c > 0; c--) {
            // Verifica se um bloco pode deslizar para um espaço vazio
            if (grid[r][c] == 0 && grid[r][c - 1] != 0) {
                return true;
            }
            // Verifica se dois blocos podem se fundir
            if (grid[r][c] != 0 && grid[r][c] == grid[r][c - 1]) {
                return true;
            }
        }
    }
    return false;
}

int main() {
    int nCasos;
    scanf("%d", &nCasos);

    while (nCasos--) {
        int grid[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                scanf("%d", &grid[i][j]);
            }
        }

        bool moveu = false;
        
        // As saídas devem ser em ordem alfabética: DOWN, LEFT, RIGHT, UP

        if (podeMoverBaixo(grid)) {
            printf("DOWN");
            moveu = true;
        }

        if (podeMoverEsquerda(grid)) {
            if (moveu) printf(" "); // Adiciona espaço se não for o primeiro
            printf("LEFT");
            moveu = true;
        }

        if (podeMoverDireita(grid)) {
            if (moveu) printf(" "); // Adiciona espaço
            printf("RIGHT");
            moveu = true;
        }

        if (podeMoverCima(grid)) {
            if (moveu) printf(" "); // Adiciona espaço
            printf("UP");
            moveu = true;
        }

        // Se nenhum movimento for válido
        if (!moveu) {
            printf("NONE");
        }
        
        printf("\n");
    }

    return 0;
}
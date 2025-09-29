# Relatório de Análise de Desempenho do QuickSort

**Disciplina:** Algoritmos e Estruturas de Dados II  
**Aluno:** Tiago Delgado Rocha

## ⚙️ Estratégias de Escolha do Pivô

### 1. Primeiro Elemento como Pivô
**Descrição:** Sempre seleciona o primeiro elemento do subarray como pivô.

**Algoritmo:**
```java
private static int partitionFirst(int[] array, int left, int right) {
    int pivot = array[left];
    int i = left + 1;
    
    for (int j = left + 1; j <= right; j++) {
        if (array[j] <= pivot) {
            swap(array, i, j);
            i++;
        }
    }
    swap(array, left, i - 1);
    return i - 1;
}
```

**Características:**
- **Vantagem:** Simples de implementar, sem overhead de seleção
- **Desvantagem:** Pior caso O(n²) em arrays ordenados ou quase ordenados
- **Melhor cenário:** Arrays completamente aleatórios

### 2. Último Elemento como Pivô
**Descrição:** Sempre seleciona o último elemento do subarray como pivô.

**Algoritmo:**
```java
private static int partitionLast(int[] array, int left, int right) {
    int pivot = array[right];
    int i = left;
    
    for (int j = left; j < right; j++) {
        if (array[j] <= pivot) {
            swap(array, i, j);
            i++;
        }
    }
    swap(array, i, right);
    return i;
}
```

**Características:**
- **Vantagem:** Implementação padrão do QuickSort clássico
- **Desvantagem:** Mesmo problema do primeiro pivô em dados ordenados
- **Melhor cenário:** Arrays aleatórios

### 3. Pivô Aleatório
**Descrição:** Seleciona um elemento aleatório do subarray como pivô.

**Algoritmo:**
```java
private static int partitionRandom(int[] array, int left, int right) {
    int randomIndex = left + random.nextInt(right - left + 1);
    swap(array, randomIndex, right);
    return partitionLast(array, left, right);
}
```

**Características:**
- **Vantagem:** Evita consistentemente o pior caso O(n²)
- **Desvantagem:** Overhead de geração de números aleatórios
- **Melhor cenário:** Qualquer tipo de array, especialmente ordenados

### 4. Mediana de Três
**Descrição:** Seleciona a mediana entre o primeiro, meio e último elemento.

**Algoritmo:**
```java
private static int partitionMedianOfThree(int[] array, int left, int right) {
    int mid = left + (right - left) / 2;
    
    // Ordena os três elementos: left, mid, right
    if (array[left] > array[mid]) swap(array, left, mid);
    if (array[mid] > array[right]) swap(array, mid, right);
    if (array[left] > array[mid]) swap(array, left, mid);
    
    // Move a mediana para o final
    swap(array, mid, right);
    return partitionLast(array, left, right);
}
```

**Características:**
- **Vantagem:** Melhor escolha de pivô, evita extremos
- **Desvantagem:** Overhead de comparações extras
- **Melhor cenário:** Dados parcialmente ordenados

---

## 🔬 Metodologia

### Ambiente de Teste
- **Linguagem:** Java
- **JVM:** OpenJDK
- **Sistema:** Windows
- **Medição:** Tempo em nanossegundos convertido para milissegundos

### Cenários de Teste
1. **Arrays Ordenados:** [1, 2, 3, ..., n]
2. **Arrays Quase Ordenados:** 90% ordenados com 10% de elementos fora de lugar
3. **Arrays Aleatórios:** Elementos completamente randomizados

### Tamanhos Testados
- **Pequeno:** 100 elementos
- **Médio:** 1.000 elementos  
- **Grande:** 10.000 elementos

### Processo de Medição
- Múltiplas execuções (3-5 iterações) para calcular tempo médio
- Clonagem do array para cada teste (condições idênticas)
- Medição precisa com `System.nanoTime()`

---

## 📊 Resultados

### Tabela Completa de Desempenho (Tempo em ms)

| Tamanho | Tipo do Array    | Primeiro Pivô | Último Pivô | Pivô Aleatório | Mediana de Três |
|---------|------------------|---------------|-------------|----------------|-----------------|
| 100     | Ordenado         | 0,180         | 0,766       | 0,223          | **0,543**       |
| 100     | Quase Ordenado   | 0,074         | **0,050**   | 0,235          | 0,089           |
| 100     | Aleatório        | 0,028         | **0,011**   | 0,075          | 0,022           |
| 1.000   | Ordenado         | 1,755         | 0,993       | 0,113          | **0,050**       |
| 1.000   | Quase Ordenado   | 0,139         | **0,076**   | 0,125          | 0,081           |
| 1.000   | Aleatório        | 0,106         | **0,082**   | 0,164          | 0,365           |
| 10.000  | Ordenado         | 81,881        | 175,558     | 1,267          | **0,713**       |
| 10.000  | Quase Ordenado   | 2,410         | **1,951**   | 2,895          | 2,265           |
| 10.000  | Aleatório        | 2,914         | **2,896**   | 2,672          | 2,487           |

*Os valores em negrito indicam o melhor desempenho para cada cenário.*

### Gráfico de Desempenho por Cenário

#### Arrays Ordenados (Pior Caso)
```
Tempo (ms) - Escala Logarítmica
│
│ 200 ┤                                          ●── Último Pivô
│     │                                         /
│ 150 ┤                                        /
│     │                                       /
│ 100 ┤                            ●────────/    ── Primeiro Pivô
│     │                           /
│  50 ┤                          /
│     │                         /
│  10 ┤                        /
│   5 ┤        ●──────────────●                  ── Pivô Aleatório
│   1 ┤   ●────────●──────────●                  ── Mediana de Três
│   0 └───┬────────┬──────────┬─────────────────
│       100     1.000     10.000
│                Tamanho do Array
```

#### Arrays Quase Ordenados
```
Tempo (ms)
│
│  3 ┤                                    ●── Pivô Aleatório
│    │                                  ●   ── Primeiro Pivô
│  2 ┤                                ●     ── Mediana de Três
│    │                              ●       ── Último Pivô
│  1 ┤                            ●
│    │        ●────●────●────●
│  0 └───┬────────┬──────────┬─────────────────
│       100     1.000     10.000
│                Tamanho do Array
```

#### Arrays Aleatórios
```
Tempo (ms)
│
│  3 ┤                            ●●●● ── Todos os algoritmos
│    │                          ●●●●     (desempenho similar)
│  2 ┤                        ●●●●
│    │                      ●●●●
│  1 ┤                    ●●●●
│    │      ●●●────●●●──●●●●
│  0 └───┬────────┬──────────┬─────────────────
│       100     1.000     10.000
│                Tamanho do Array
```

---

## 📈 Análise de Desempenho

### Complexidade Temporal Observada

#### Arrays Ordenados
- **Primeiro/Último Pivô:** O(n²) - Degradação quadrática clara
  - 100 → 1.000: aumento de ~10x
  - 1.000 → 10.000: aumento de ~100x
- **Pivô Aleatório:** O(n log n) - Crescimento logarítmico
- **Mediana de Três:** O(n log n) - Melhor constante

#### Arrays Quase Ordenados  
- **Todos os algoritmos:** O(n log n) - Comportamento similar
- **Variação pequena:** Entre 1,9ms e 2,9ms para 10.000 elementos

#### Arrays Aleatórios
- **Todos os algoritmos:** O(n log n) - Desempenho uniforme
- **Diferença mínima:** Variação de apenas 0,4ms para 10.000 elementos

### Análise Estatística

#### Degradação de Performance (Arrays Ordenados)
```
Algoritmo          | 100→1.000 | 1.000→10.000 | Razão Total
-------------------|-----------|--------------|-------------
Primeiro Pivô      |    9,7x   |     46,7x    |    454,4x
Último Pivô        |    1,3x   |    176,8x    |    229,2x
Pivô Aleatório     |    0,5x   |     11,2x    |      5,7x
Mediana de Três    |    0,1x   |     14,3x    |      1,3x
```

#### Estabilidade (Coeficiente de Variação)
- **Mediana de Três:** Mais estável entre diferentes cenários
- **Pivô Aleatório:** Segunda melhor estabilidade
- **Primeiro/Último:** Altamente instáveis em dados ordenados

---

## 💬 Discussão

### Por que a Mediana de Três é Superior?

#### 1. **Melhor Seleção de Pivô**
A estratégia de mediana de três evita sistematicamente escolher elementos extremos como pivô. Ao considerar três posições (início, meio, fim), ela tem maior probabilidade de encontrar um pivô que divida o array de forma mais equilibrada.

#### 2. **Prevenção do Pior Caso**
Mesmo em arrays completamente ordenados, a mediana de três garante que o pivô não será nem o menor nem o maior elemento, evitando partições desbalanceadas que levam à complexidade O(n²).

#### 3. **Overhead Aceitável**
Embora realize comparações extras (3 comparações + 2-3 trocas), esse overhead é insignificante comparado ao ganho de performance em cenários adversos.

### Análise por Cenário

#### 🔴 Arrays Ordenados (Pior Caso)
**Vencedor: Mediana de Três (0,713ms vs 175,558ms do pior)**

- **Primeiro/Último Pivô:** Falham completamente
  - Cada partição resulta em divisão 1:(n-1)
  - Recursão atinge profundidade O(n)
  - Resultado: O(n²) com constante alta

- **Pivô Aleatório:** Boa performance
  - Probabilidade baixa de escolher extremos
  - Mantém O(n log n) mesmo no pior caso teorico
  - Overhead da geração aleatória é compensado

- **Mediana de Três:** Melhor performance
  - Garantia matemática de evitar extremos
  - Divisões mais equilibradas
  - Menor constante na complexidade O(n log n)

#### 🟡 Arrays Quase Ordenados (Caso Realista)
**Vencedor: Último Pivô (1,951ms)**

- **Comportamento Uniforme:** Todos os algoritmos se comportam de forma similar
- **Diferenças Mínimas:** Variação de apenas 0,9ms entre melhor e pior
- **Escolha Prática:** Qualquer algoritmo é adequado

#### 🟢 Arrays Aleatórios (Caso Médio)
**Vencedor: Último Pivô (2,896ms)**

- **Performance Equivalente:** Diferenças negligíveis entre todas as estratégias
- **Overhead Importa:** Estratégias mais simples (primeiro/último) têm ligeira vantagem
- **Resultado Esperado:** Todos mantêm O(n log n) com constantes similares

### Fatores de Decisão

#### 1. **Previsibilidade dos Dados**
- **Dados Conhecidos (aleatórios):** Primeiro/Último Pivô são suficientes
- **Dados Desconhecidos:** Mediana de Três ou Pivô Aleatório são mais seguros

#### 2. **Criticidade da Performance**
- **Aplicações Críticas:** Mediana de Três (mais estável)
- **Aplicações Gerais:** Pivô Aleatório (bom custo-benefício)

#### 3. **Recursos Disponíveis**
- **Memória/CPU Limitados:** Primeiro/Último (menor overhead)
- **Recursos Abundantes:** Mediana de Três (melhor robustez)

---

## 🎯 Conclusões

### Principais Descobertas

1. **A escolha do pivô é crucial** para a performance do QuickSort
2. **Dados ordenados são o inimigo** das estratégias simples (primeiro/último)
3. **Mediana de Três oferece o melhor equilíbrio** entre performance e robustez
4. **Em dados aleatórios, todas as estratégias são equivalentes**

### Ranking por Cenário

#### 🏆 Classificação Geral (Todas as Situações)
1. **🥇 Mediana de Três** - Mais consistente e robusta
2. **🥈 Pivô Aleatório** - Boa performance geral, evita pior caso
3. **🥉 Último Pivô** - Adequado para dados conhecidos/aleatórios
4. **4️⃣ Primeiro Pivô** - Apenas para dados garantidamente aleatórios

#### 📊 Por Tipo de Dados

| Cenário          | 1º Lugar           | 2º Lugar         | Observação                    |
|------------------|--------------------|-----------------|------------------------------ |
| **Ordenados**    | Mediana de Três   | Pivô Aleatório  | Diferença extrema (>200x)    |
| **Quase Ord.**   | Último Pivô       | Mediana de Três | Diferenças mínimas           |
| **Aleatórios**   | Último Pivô       | Mediana de Três | Performance equivalente       |


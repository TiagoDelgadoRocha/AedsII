#include <stdio.h>
#include <string.h>

int main() {
    char linha[1001]; 

    while (fgets(linha, sizeof(linha), stdin) != NULL) {
        int abriu = 0;
        char dentro[1001] = "";
        char normal[1001] = "";
        int dentro_idx = 0;
        int normal_idx = 0;

        linha[strcspn(linha, "\n")] = 0;

        for (int i = 0; i < strlen(linha); i++) {
            if (linha[i] == '[') {
                abriu++;
            }
            if (linha[i] == ']') {
                abriu--;
            }
            if (abriu > 0 && linha[i] != '[') {
                dentro[dentro_idx++] = linha[i];
            }
            if (abriu == 0 && (linha[i] != '[' && linha[i] != ']')) {
                normal[normal_idx++] = linha[i];
            }
        }
        dentro[dentro_idx] = '\0';
        normal[normal_idx] = '\0';

        if (strlen(dentro) > 1) {
            printf("%s%s\n", dentro, normal);
        } else {
            printf("%s\n", normal);
        }
    }

    return 0;
}

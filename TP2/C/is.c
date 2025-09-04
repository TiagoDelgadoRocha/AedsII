#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void checaVogal(char str[]){
    bool teste=true;
     for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] >= 'A' && str[i] <= 'Z') {
            str[i] = str[i] + ('a' - 'A');  
        }
    }
    for(int i=0;str[i]!='\0';i++){
        if(!(str[i]=='a' || str[i]=='e' || str[i]=='i' || str[i]=='o' || str[i]=='u')){
            teste=false;
            break;
        }
    }
    if(teste){
        printf("SIM");
    }else printf("NAO");
}

int main() {
    char str[1000];
    printf("");
    fgets(str,1000,stdin);

    checaVogal(str);

    return 0;
}
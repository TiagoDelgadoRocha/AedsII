#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <string.h>

void ordenacao(int array[], int tam){
    for(int i=1; i<tam; i++){
        int temp=array[i];
        int j=i-1;
        if(temp>0){
            while( (temp<array[j] && j>=0) || array[j]<0){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=temp;
        }
        if(temp<0){
            while((temp<array[j] && j>=0) && array[j]<0){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=temp;
        }

    }
    for(int i=0;i<10;i++){
        printf("num %d: %d \n",i+1, array[i]);
    }

}


int main(){
    int array[10];


    for(int i=0;i<10;i++){
        printf("num %d: ",i+1);
        scanf("%d",&array[i]);
    }
    ordenacao(array,10);
}
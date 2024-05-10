import java.util.Scanner;

public class CaixeiroViajante {

    static int[][] matrizDistancias;
    static String[] cidades;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        cidades = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Insira o nome da cidade " + (i + 1) + ": ");
            cidades[i] = scanner.nextLine();
        }

        matrizDistancias = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    System.out.print("Insira a distância entre " + cidades[i] + " e " + cidades[j] + ": ");
                    matrizDistancias[i][j] = scanner.nextInt();
                } else {
                    matrizDistancias[i][j] = 0;
                }
            }
        }

        int[] melhorRota = calcularMelhorRota();

        System.out.println("\nA melhor rota é:");
        for (int i = 0; i < 4; i++) {
            System.out.print(cidades[melhorRota[i]]);
            if (i < 3) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        
        scanner.close();
    }

    static int[] calcularMelhorRota() {
        int[] rotaAtual = {0, 1, 2, 3}; 
        int[] melhorRota = {0, 1, 2, 3}; 
        int melhorDistancia = calcularDistancia(rotaAtual); 

        while (true) {
            rotaAtual = proximaPermutacao(rotaAtual);
            if (rotaAtual == null) break;
            int distancia = calcularDistancia(rotaAtual);
            if (distancia < melhorDistancia) {
                melhorDistancia = distancia;
                melhorRota = rotaAtual.clone();
            }
        }

        return melhorRota;
    }

    static int calcularDistancia(int[] rota) {
        int distanciaTotal = 0;
        for (int i = 0; i < rota.length - 1; i++) {
            distanciaTotal += matrizDistancias[rota[i]][rota[i + 1]];
        }
        distanciaTotal += matrizDistancias[rota[rota.length - 1]][rota[0]]; 
        return distanciaTotal;
    }

    static int[] proximaPermutacao(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return null;
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return array;
    }
}

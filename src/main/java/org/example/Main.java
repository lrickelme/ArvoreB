package org.example;

public class Main {
    public static void main(String[] args) {
        BTree arvore = new BTree(4);

        int[] chaves = {10, 20, 5, 6, 12, 30, 7, 17};

        for (int chave : chaves) {
            arvore.inserir(chave);
        }

        // Percorre e imprime a árvore
        System.out.println("Percorrendo a árvore B:");
        arvore.percorrer();

        int chaveBusca = 6;
        NodeB resultado = arvore.buscar(chaveBusca);

        if (resultado != null) {
            System.out.println("\nChave " + chaveBusca + " encontrada na árvore.");
        } else {
            System.out.println("\nChave " + chaveBusca + " não encontrada na árvore.");
        }
    }
}

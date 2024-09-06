package org.example;

public class NodeB {
    int[] chaves;
    NodeB[] filhos;
    int numChaves;
    boolean folha;
    int ordem;

    public NodeB(int ordem, boolean folha) {
        this.ordem = ordem;
        this.folha = folha;
        this.chaves = new int[ordem - 1];
        this.filhos = new NodeB[ordem];
        this.numChaves = 0;
    }

    public void percorrer() {
        int i;
        for (i = 0; i < numChaves; i++) {
            if (!folha) {
                filhos[i].percorrer();
            }
            System.out.print(chaves[i] + " ");
        }

        if (!folha) {
            filhos[i].percorrer();
        }
    }

    public NodeB buscar(int chave) {
        int i = 0;
        while (i < numChaves && chave > chaves[i]) {
            i++;
        }
        if (i < numChaves && chaves[i] == chave) {
            return this;
        }
        if (folha) {
            return null;
        }
        return filhos[i].buscar(chave);
    }

    public void inserirNaoCheio(int chave) {
        int i = numChaves - 1;

        if (folha) {
            while (i >= 0 && chaves[i] > chave) {
                chaves[i + 1] = chaves[i];
                i--;
            }
            chaves[i + 1] = chave;
            numChaves++;
        } else {
            while (i >= 0 && chaves[i] > chave) {
                i--;
            }
            i++;

            if (filhos[i].numChaves == ordem - 1) {
                dividirFilho(this, i, filhos[i]);

                if (chaves[i] < chave) {
                    i++;
                }
            }
            filhos[i].inserirNaoCheio(chave);
        }
    }

    private void dividirFilho(NodeB pai, int i, NodeB filhoCheio) {
        NodeB novoFilho = new NodeB(ordem, filhoCheio.folha);
        novoFilho.numChaves = ordem / 2 - 1;

        for (int j = 0; j < ordem / 2 - 1; j++) {
            novoFilho.chaves[j] = filhoCheio.chaves[j + ordem / 2];
        }

        if (!filhoCheio.folha) {
            for (int j = 0; j < ordem / 2; j++) {
                novoFilho.filhos[j] = filhoCheio.filhos[j + ordem / 2];
            }
        }

        filhoCheio.numChaves = ordem / 2 - 1;
        for (int j = pai.numChaves; j >= i + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }

        pai.filhos[i + 1] = novoFilho;

        for (int j = pai.numChaves - 1; j >= i; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }
        pai.chaves[i] = filhoCheio.chaves[ordem / 2 - 1];
        pai.numChaves++;
    }
}

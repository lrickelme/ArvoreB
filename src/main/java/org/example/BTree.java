package org.example;

public class BTree {
    NodeB raiz;
    int ordem;

    public BTree(int ordem) {
        this.raiz = null;
        this.ordem = ordem;
    }

    public void percorrer() {
        if (raiz != null) {
            raiz.percorrer();
        }
    }

    public NodeB buscar(int chave) {
        return (raiz == null) ? null : raiz.buscar(chave);
    }

    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new NodeB(ordem, true);
            raiz.chaves[0] = chave;
            raiz.numChaves = 1;
        } else {
            if (raiz.numChaves == ordem - 1) {
                NodeB novaRaiz = new NodeB(ordem, false);
                novaRaiz.filhos[0] = raiz;

                dividirFilho(novaRaiz, 0, raiz);

                int i = (novaRaiz.chaves[0] < chave) ? 1 : 0;
                novaRaiz.filhos[i].inserirNaoCheio(chave);

                raiz = novaRaiz;
            } else {
                raiz.inserirNaoCheio(chave);
            }
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

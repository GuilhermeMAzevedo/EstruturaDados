package Arvores;

import Filas.FilaDinamica;

public class ArvoreBinaria<T> {
    private static class No<T>{
        private final T dado;
        private No<T> esquerdo;
        private No<T> direito;
        public No(T dado){
            this.dado = dado;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    private No<T> raiz;
    private int quantidadeDados;

    public ArvoreBinaria(){
        this.raiz = null;
        this.quantidadeDados = 0;
    }

    public int getQuantidadeDados() {
        return quantidadeDados;
    }

    public boolean estaVazia(){
        return quantidadeDados == 0;
    }

    public T obterRaiz(){
        return raiz.dado;
    }

    public void limpar(){
        raiz = null;
        quantidadeDados = 0;
    }

    public void inserirRaiz(T dado){
        if (dado == null){
            throw new IllegalArgumentException("Dado nulo");
        } else if (!estaVazia()){
            throw new IllegalStateException("Árvore já tem uma raiz");
        }
        raiz = new No<>(dado);
        quantidadeDados++;
    }

    public void inserirEmLargura(T dado){
        if(dado == null){
            throw new IllegalArgumentException("Dado nulo");
        } else if (estaVazia()) {
            inserirRaiz(dado);
            return;
        }
        FilaDinamica<No<T>> fila = new FilaDinamica<>();
        fila.enfileirar(raiz);

        while (!fila.estaVazia()){
            No<T> atual = fila.desenfileirar();
            if (atual.esquerdo == null){
                atual.esquerdo = new No<>(dado);
                quantidadeDados++;
                return;
            }
            fila.enfileirar(atual.esquerdo);

            if (atual.direito == null){
                atual.direito = new No<>(dado);
                quantidadeDados++;
                return;
            }
            fila.enfileirar(atual.direito);
        }
    }

    public void inserirEsquerda(T dado, T pai){
        if (pai == null || dado == null){
            throw new IllegalArgumentException("Algum dos dados são nulos");
        }
        No<T> noPai = buscarNo(raiz, pai);
        if (noPai == null || noPai.esquerdo != null){
            throw new IllegalArgumentException("Nó não encontrado");
        }
        noPai.esquerdo = new No<>(dado);
        quantidadeDados++;
        
    }

    public void inserirDireita(T dado, T pai){
        if (pai == null || dado == null){
            throw new IllegalArgumentException("Algum dos dados são nulos");
        }
        No<T> noPai = buscarNo(raiz, pai);
        if (noPai == null || noPai.direito != null){
            throw new IllegalArgumentException("Nó não encontrado");
        }
        noPai.direito = new No<>(dado);
        quantidadeDados++;

    }

    public boolean contem(T dado){
        if (dado == null){
            throw new IllegalArgumentException("Dado nulo");
        }
        return buscarNo(raiz, dado) != null;
    }

    public T consultarRaiz(){
        if (estaVazia()){
            return null;
        } else {
            return raiz.dado;
        }
    }

    private No<T> buscarNo(No<T> no, T dado){
        if (no == null || dado == null){
            return null;
        }

        if (no.dado.equals(dado)){
            return no;
        }

        No<T> encontrado = buscarNo(no.esquerdo, dado);
        if (encontrado != null){
            return encontrado;
        } else {
            return buscarNo(no.direito, dado);
        }
    }

    public

}

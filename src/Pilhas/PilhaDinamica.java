package Pilhas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PilhaDinamica<T> implements Iterable<T>{
    private static class No<T> {
        private final T dado;
        private No<T> proximo;
        public No(T dado){
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No<T> topo;
    private int quantidadeDados;
    public PilhaDinamica(){
        this.topo = null;
        this.quantidadeDados = 0;
    }

    public int getQuantidadeDados(){
        return quantidadeDados;
    }

    public boolean estaVazia(){
        return quantidadeDados == 0;
    }

    public void empilhar(T dado){
        No<T> no = new No<>(dado);
        no.proximo = topo;
        topo = no;
        quantidadeDados++;
    }

    public T desempilhar(){
        if (estaVazia()){
            throw new IllegalStateException("Pilha vazia");
        } else {
            T dadoRemovido = consultar();
            topo = topo.proximo;
            quantidadeDados--;
            return dadoRemovido;
        }
    }

    public T consultar(){
        if (estaVazia()){
            return null;
        } else {
            return topo.dado;
        }
    }

    @Override
    public String toString(){
        if (estaVazia()){
            return "Pilha vazia";
        } else {
            StringBuilder stringBuilder = new StringBuilder("Pilha:");
            No<T> atual = topo;
            while (atual != null){
                stringBuilder.append("\n").append(atual.dado);
                atual = atual.proximo;
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            No<T> atual = topo;
            @Override
            public boolean hasNext() {
                return atual.proximo != null;
            }

            @Override
            public T next() {
                if (hasNext()){
                    T data = atual.dado;
                    atual = atual.proximo;
                    return data;
                } else {
                    throw new NoSuchElementException();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
                }
            }
        };
    }
}

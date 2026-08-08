package Filas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilaPrioridadeNaoOrdenada <T extends Comparable<T>> implements Iterable<T> {
    private static class No<T> {
        private final T dado;
        private No<T> anterior;
        private No<T> proximo;
        public No(T dado){
            this.dado = dado;
            this.anterior = null;
            this.proximo = null;
        }
    }

    private No<T> inicio;
    private No<T> fim;
    private int quantidadeDados;

    public FilaPrioridadeNaoOrdenada(){
        this.inicio = null;
        this.fim = null;
        this.quantidadeDados = 0;
    }

    public int getQuantidadeDados() {
        return quantidadeDados;
    }

    public boolean estaVazia(){
        return quantidadeDados == 0;
    }

    public void enfileirar(T dado){
        No<T> no = new No<>(dado);
        if (estaVazia()){
            inicio = no;
        } else {
            fim.proximo = no;
            no.anterior = fim;
        }
        fim = no;
        quantidadeDados++;
    }

    public T desenfileirar(){
        if (estaVazia()){
            throw new IllegalStateException("Fila vazia");
        } else {
            No<T> maior = encontrarMaiorPrioridade();
            removerNo(maior);
            return maior.dado;
        }
    }

    public T consultarFrente(){
        if (estaVazia()){
            throw new IllegalStateException("Fila vazia");
        } else {
            return encontrarMaiorPrioridade().dado;
        }
    }

    private No<T> encontrarMaiorPrioridade(){
        No<T> maior = inicio;
        No<T> atual = inicio.proximo;
        while (atual != null){
            if (atual.dado.compareTo(maior.dado) > 0){
                maior = atual;
            }
            atual = atual.proximo;
        }
        return maior;
    }

    private void removerNo(No<T> no){
        if (no.anterior != null){
            no.anterior.proximo = no.proximo;
        } else {
            inicio = no.proximo;
        }

        if (no.proximo != null){
            no.proximo.anterior = no.anterior;
        } else {
            fim = no.anterior;
        }
        quantidadeDados--;
    }

    @Override
    public String toString() {
        if (estaVazia()){
            return "Fila vazia";
        } else {
            StringBuilder stringBuilder = new StringBuilder("Fila:");
            No<T> atual = inicio;
            while (atual != null){
                stringBuilder.append(" ").append(atual.dado);
                atual = atual.proximo;
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private No<T> atual = inicio;
            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                T dado =  atual.dado;
                atual = atual.proximo;
                return dado;
            }
        };
    }
}

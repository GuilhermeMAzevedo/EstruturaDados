package Filas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilaPrioridadeOrdenada<T extends Comparable<T>> implements Iterable<T> {
    private static class No<T> {
        private final T dado;
        private No<T> proximo;
        public No(T dado){
            this.dado = dado;
            this.proximo = null;
        }
    }
    private No<T> inicio;
    private No<T> fim;
    private int quantidadeDados;

    public FilaPrioridadeOrdenada(){
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
        if (estaVazia()) {
            inicio = no;
            fim = inicio;
        } else if (dado.compareTo(inicio.dado) > 0) {
            no.proximo = inicio;
            inicio = no;
        } else if (dado.compareTo(fim.dado) <= 0) {
            fim.proximo = no;
            fim = no;
        } else {
            No<T> atual = inicio;
            while (atual.proximo != null && atual.proximo.dado.compareTo(no.dado) >= 0){
                atual = atual.proximo;
            }
            no.proximo = atual.proximo;
            atual.proximo = no;
        }
        quantidadeDados++;
    }

    public T desenfileirar(){
        if (estaVazia()){
            throw new IllegalStateException("Fila vazia");
        } else {
            T dadoRemovido = inicio.dado;
            inicio = inicio.proximo;
            if (inicio == null){
                fim = null;
            }
            quantidadeDados--;
            return dadoRemovido;
        }
    }

    public T consultarFrente(){
        if (estaVazia()){
            throw new IllegalStateException("Fila vazia");
        } else {
            return inicio.dado;
        }
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
            public boolean hasNext(){
                return atual != null;
            }
            @Override
            public T next(){
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                T dado = atual.dado;
                atual = atual.proximo;
                return dado;
            }
        };
    }
}

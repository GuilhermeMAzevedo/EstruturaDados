package Listas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDinamicaDupla<T> implements Iterable<T>{
    private static class No<T> {
        private final T dado;
        private No<T> proximo;
        private No<T> anterior;
        public No(T dado){
            this.dado = dado;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private No<T> inicio;
    private No<T> fim;
    private int quantidadeDados;

    public ListaDinamicaDupla(){
        this.inicio = null;
        this.fim = null;
        this.quantidadeDados = 0;
    }

    public int getQuantidadeDados(){
        return quantidadeDados;
    }

    public boolean estaVazia(){
        return quantidadeDados == 0;
    }

    public void inserirInicio(T dado){
        if (estaVazia()){
            inicio = new No<>(dado);
            fim = inicio;
        } else {
            No<T> no = new No<>(dado);
            no.proximo = inicio;
            inicio.anterior = no;
            inicio = no;
        }
        quantidadeDados++;
    }

    public void inserir(T dado, int indice){
        if (indice < 0 || indice > quantidadeDados){
            throw new IllegalArgumentException("Índice inválido");
        } else if (indice == 0){
            inserirInicio(dado);
        } else if (indice == quantidadeDados){
            inserirFinal(dado);
        } else if (indice < quantidadeDados / 2){
            No<T> no = new No<>(dado);
            No<T> atual = inicio;
            for (int i = 0; i < indice - 1; i++) {
                atual = atual.proximo;
            }
            no.proximo = atual.proximo;
            atual.proximo.anterior = no;
            atual.proximo = no;
            no.anterior = atual;
            quantidadeDados++;
        } else {
            No<T> no = new No<>(dado);
            No<T> atual = fim;
            for (int i = quantidadeDados - 1; i > indice; i--) {
                atual = atual.anterior;
            }
            no.proximo = atual;
            no.anterior = atual.anterior;
            atual.anterior.proximo = no;
            atual.anterior = no;
            quantidadeDados++;
        }
    }

    public void inserirFinal(T dado){
        if (estaVazia()){
            inserirInicio(dado);
        } else {
            No<T> no = new No<>(dado);
            fim.proximo = no;
            no.anterior = fim;
            fim = no;
            quantidadeDados++;
        }
    }

    public T removerInicio(){
        if (estaVazia()){
            throw new IllegalStateException("Empty List");
        } else {
            T dadoRemovido = inicio.dado;
            inicio = inicio.proximo;
            if (inicio == null){
                fim = null;
            } else {
                inicio.anterior = null;
            }
            quantidadeDados--;
            return dadoRemovido;
        }
    }

    public T remover(int indice){
        if (estaVazia()){
            throw new IllegalStateException("Lista vazia");
        } else if (indice < 0 || indice >= quantidadeDados){
            throw new IllegalArgumentException("Índice inválido");
        } else if (indice == 0) {
            return removerInicio();
        } else if (indice == quantidadeDados - 1) {
            return removerFinal();
        } else if (indice < quantidadeDados / 2){
            No<T> current = inicio;
            for (int i = 0; i < indice - 1; i++) {
                current = current.proximo;
            }
            T dataRemoved = current.proximo.dado;
            current.proximo = current.proximo.proximo;
            current.proximo.anterior = current;
            quantidadeDados--;
            return dataRemoved;
        } else {
            No<T> current = fim;
            for (int i = quantidadeDados - 1; i > indice + 1; i--) {
                current = current.anterior;
            }
            T dataRemoved = current.anterior.dado;
            current.anterior = current.anterior.anterior;
            current.anterior.proximo = current;
            quantidadeDados--;
            return dataRemoved;
        }
    }

    public T removerFinal(){
        if (estaVazia()){
            throw new IllegalStateException("Lista vazia");
        } else if (quantidadeDados == 1){
            return removerInicio();
        } else {
            T dataRemoved = fim.dado;
            fim = fim.anterior;
            fim.proximo = null;
            quantidadeDados--;
            return dataRemoved;
        }
    }

    public T obter(int indice){
        if (estaVazia()){
            throw new IllegalStateException("Lista vazia");
        } else if (indice < 0 || indice >= quantidadeDados) {
            throw new IllegalArgumentException("Índice inválido");
        } else if (indice < quantidadeDados / 2){
            No<T> atual = inicio;
            for (int i = 0; i < indice; i++) {
                atual = atual.proximo;
            }
            return atual.dado;
        } else {
            No<T> atual = fim;
            for (int i = quantidadeDados - 1; i > indice; i--) {
                atual = atual.anterior;
            }
            return atual.dado;
        }
    }

    public int buscar(T dado){
        No<T> atual = inicio;
        int indice = 0;
        while (atual != null){
            if (atual.dado.equals(dado)){
                return indice;
            }
            indice++;
            atual = atual.proximo;
        }
        return -1;
    }

    public boolean contem(T dado){
        No<T> atual = inicio;
        while (atual != null){
            if (atual.dado.equals(dado)){
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public void limpar(){
        inicio = null;
        fim = null;
        quantidadeDados = 0;
    }

    @Override
    public String toString(){
        if (estaVazia()){
            return "Lista vazia";
        } else {
            StringBuilder stringBuilder = new StringBuilder("Lista:");
            No<T> atual = inicio;
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
            No<T> atual = inicio;
            @Override
            public boolean hasNext() {
                return atual.proximo != null;
            }

            @Override
            public T next() {
                if (hasNext()){
                    T dado = atual.dado;
                    atual = atual.proximo;
                    return dado;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };

    }
}
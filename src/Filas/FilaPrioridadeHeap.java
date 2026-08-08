package Filas;

public class FilaPrioridadeHeap<T extends Comparable<T>> {
    private T[] heap;
    private int quantidadeDados;

    public FilaPrioridadeHeap(int capacidade){
        this.heap = (T[]) new Object[capacidade];
        this.quantidadeDados = 0;
    }

    public int getQuantidadeDados() {
        return quantidadeDados;
    }

    public boolean estaVazio(){
        return quantidadeDados == 0;
    }

    public boolean estaCheio(){
        return quantidadeDados == heap.length;
    }

    public void enfileirar(T dado){
        if (estaCheio()){
            throw new IllegalStateException("Fila cheia");
        } else {
            heap[quantidadeDados] = dado;
            subir(quantidadeDados);
            quantidadeDados++;
        }
    }

    public T desenfileirar(){
        if (estaVazio()){
            throw new IllegalStateException("Fila vazia");
        } else {
            T dadoRemovido = consultar();
            quantidadeDados--;
            trocar(0, quantidadeDados);
            heap[quantidadeDados] = null;
            descer(0);
            return dadoRemovido;
        }
    }

    public T consultar(){
        if (estaVazio()){
            return null;
        } else {
            return heap[0];
        }
    }

    private void subir(int indice){
        while (indice > 0){
            int pai = (indice - 1) / 2;
            if (heap[indice].compareTo(heap[pai]) <= 0) {
                break;
            }
            trocar(pai, indice);
            indice = pai;
        }
    }

    private void descer(int indice){
        while (true) {
            int filhoEsquerdo = 2 * indice + 1;
            int filhoDireito = 2 * indice + 2;
            int maiorIndice = indice;
            if (filhoEsquerdo < quantidadeDados && heap[filhoEsquerdo].compareTo(heap[maiorIndice]) > 0) {
                maiorIndice = filhoEsquerdo;
            }
            if (filhoDireito < quantidadeDados && heap[filhoDireito].compareTo(heap[maiorIndice]) > 0) {
                maiorIndice = filhoDireito;
            }
            if (maiorIndice == indice) {
                break;
            }
            trocar(maiorIndice, indice);
            indice = maiorIndice;
        }
    }

    private void trocar(int i, int j){
        T aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Heap [");
        for (int i = 0; i < quantidadeDados; i++) {
            if (i < quantidadeDados - 1){
                stringBuilder.append(heap[i]).append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

package Filas;

public class FilaDinamica<T> {
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

    public FilaDinamica(){
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
        if (estaVazia()){
            inicio = new No<>(dado);
            fim = inicio;
        } else {
            fim.proximo = new No<>(dado);
            fim = fim.proximo;
        }
        quantidadeDados++;
    }

    public T desenfileirar(){
        if (estaVazia()){
            throw new IllegalStateException("Fila vazia");
        } else {
            T dadoRemovido = consultar();
            inicio = inicio.proximo;
            quantidadeDados--;
            if (inicio == null){
                fim = null;
            }
            return dadoRemovido;
        }
    }

    public T consultar(){
        if (estaVazia()){
            return null;
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
                stringBuilder.append("\n").append(atual.dado);
                atual = atual.proximo;
            }
            return stringBuilder.toString();
        }
    }
}

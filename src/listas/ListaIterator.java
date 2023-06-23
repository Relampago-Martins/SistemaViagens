package listas;

import java.util.Iterator;

public class ListaIterator<T> implements Iterator<T>{

    private int objCorrente = 0;
    private Lista<T> lista;

    public ListaIterator(Lista<T> Lista){
        this.lista = Lista;
    }

    @Override
    public boolean hasNext() {
        return this.objCorrente < this.lista.tamanho();
    }

    @Override
    public T next() {
        return this.lista.get(this.objCorrente++);
    }
    
}

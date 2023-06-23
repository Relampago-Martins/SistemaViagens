package listas;

import excecoes.ExcecaoDeListaCheia;

/**
 * Implementação do design pattern Iterator.
 * 
 *[https://refactoring.guru/design-patterns/iterator]
 */
public interface Lista<T> extends Iterable<T>{
    
    public void incluir(T objeto) throws ExcecaoDeListaCheia;

    public T get(int indice);

    public int tamanho();
}

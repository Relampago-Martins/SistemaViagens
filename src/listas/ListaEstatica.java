package listas;

import java.util.Iterator;

import excecoes.ExcecaoDeListaCheia;

/**
 * Classe wrapper para a listas quaisquer.
 * Implementa funcionalidades extra para a lista.
 * 
 * @author bgmartins
 */
public class ListaEstatica<T> implements Lista<T>{
	private T[] objetos;
	private int capacidade;
    private int tamanho = 0;

    /**
     * Método construtor que determina uma capacidade máxima para a lista.
     * A lista deve ter uma capacidade máxima, mesmo que seja implementada
     * com ArrayList
     * 
     * @param capacidade O número máximo de objetos que a lista pode conter
     */
    @SuppressWarnings("unchecked")
	public ListaEstatica(int capacidade) {
		this.capacidade = capacidade;
		this.objetos = (T[]) new Object[capacidade];
	}
	
    /**
     * Método para incluir um objeto na lista
     * 
     * @return retorna true se o obejto foi incluído sem problemas e
     * false se a capacidade da lista estava esgotada
     */
	public void incluir(T objeto) throws ExcecaoDeListaCheia{
		if (this.tamanho < capacidade && objeto != null ) {
			this.objetos[this.tamanho++] = objeto;
			return;
		}
        throw new ExcecaoDeListaCheia("Lista cheia");
	}

    public T get(int indice){
        T objeto = null;
        if (indice >= 0 && indice < this.tamanho)
            objeto = this.objetos[indice];
        
        return objeto;
    }
    
    public int tamanho(){
        return this.tamanho;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaIterator<T>(this);
    }
}

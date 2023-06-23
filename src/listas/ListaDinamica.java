package listas;

import java.util.ArrayList;
import java.util.Iterator;

import excecoes.ExcecaoDeListaCheia;

/**
 * Classe wrapper para a listas quaisquer.
 * Implementa funcionalidades extra para a lista.
 * 
 * @author bgmartins
 */
public class ListaDinamica<T> implements Lista<T>{
	private ArrayList<T> objetos;
	private int capacidade;

    /**
     * Método construtor que determina uma capacidade máxima para a lista.
     * A lista deve ter uma capacidade máxima, mesmo que seja implementada
     * com ArrayList
     * 
     * @param capacidade O número máximo de objetos que a lista pode conter
     */
	public ListaDinamica(int capacidade) {
		this.capacidade = capacidade;
		this.objetos = new ArrayList<>();
	}
	
    /**
     * Método para incluir um objeto na lista
     * 
     * @return retorna true se o obejto foi incluído sem problemas e
     * false se a capacidade da lista estava esgotada
     */
	public void incluir(T objeto) throws ExcecaoDeListaCheia{
		if (this.objetos.size() < capacidade && objeto != null ) {
			this.objetos.add(objeto);
			return;
		}
        throw new ExcecaoDeListaCheia("Lista cheia");
	}

    public T get(int indice){
        T objeto = null;
        if (indice >= 0 && indice < this.objetos.size())
            objeto = this.objetos.get(indice);
        
        return objeto;
    }
    
    public int tamanho(){
        return this.objetos.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaIterator<T>(this);
    }
}

package interacoes;

import entidades.Cliente;
import excecoes.ExcecaoDeListaCheia;
import listas.Lista;
import listas.ListaDinamica;
import listas.ListaEstatica;


public class UsoListaGenerica{
    public static void main(String[] args) {
        Lista<Cliente> clientes = new ListaEstatica<>(10);
        
        try{
            clientes.incluir(new Cliente("João", "12345678910"));
            clientes.incluir(new Cliente("Maria", "10987654321"));
        }catch (ExcecaoDeListaCheia error){
            error.printStackTrace();
        }

        for (Cliente cliente: clientes){
            System.out.println( cliente.getNome() );
        }
    }
}

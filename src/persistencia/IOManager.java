package persistencia;

import java.util.List;

import entidades.Cliente;

/**
 * Classe que define o comportamento de um gerenciador de IO
 */
public interface IOManager{
    /**
     * Salva uma lista de clientes em um arquivo de persistencia de dados
     * @param clientes
     */
    public void dumpClientes(List<Cliente> clientes);

    /**
     * Carrega uma lista de clientes de um arquivo de persistencia de dados
     * para a memoria
     * @return List<Cliente>
     */
    public List<Cliente> loadClientes();
} 
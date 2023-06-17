package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;


public class TextIOManager implements IOManager{
    @Override
    public void dumpClientes(List<Cliente> clientes){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("data/clientes.txt") );
            
            for(Cliente cliente: clientes){
                bufferedWriter.write(cliente.getNome() + "," + cliente.getCpf());
                bufferedWriter.newLine();
            }
    
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch(IOException e){
            System.out.println("Erro durante o persistencia de dados");
        }
    }

    @Override
    public List<Cliente> loadClientes(){
        List<Cliente> clientes = new ArrayList<>();

        try{
            BufferedReader buferredReader = new BufferedReader( new FileReader("clientes.txt") );
            String nome, cpf, linha;

            linha = buferredReader.readLine();
            while(linha != null){
                String[] atributos = linha.split(",");
                nome = atributos[0];
                cpf = atributos[1];
                clientes.add( new Cliente(nome, cpf) );

                linha = buferredReader.readLine();
            }
            
            buferredReader.close();
        }catch(IOException e){
            System.out.println("Erro durante o carregamento de dados");
        }
        
        return clientes;
    }
}

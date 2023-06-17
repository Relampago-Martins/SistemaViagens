package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;


public class ObjectIOManager implements IOManager{
    
    @Override
    public void dumpClientes(List<Cliente> clientes){
        try{
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream("data/clientes.obj") );

            os.writeObject(clientes);
            
            os.flush();
            os.close();

        }catch(IOException e){
            System.out.println("Erro durante o persistencia de dados");
        }

    }

    @Override
    public List<Cliente> loadClientes(){
        List<Cliente> clientes = new ArrayList<>();
        try{
            ObjectInputStream is = new ObjectInputStream( new FileInputStream("data/clientes.obj") );

            clientes = (List<Cliente>) is.readObject();

            is.close();
        }catch(IOException e){
            System.out.println("Erro durante o carregamento de dados");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro durante o carregamento de dados");
        }

        return clientes;
    }
}

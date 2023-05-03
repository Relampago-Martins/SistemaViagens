package entidades;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Classe wrapper para a lista de reserva.
 * Implementa funcionalidades extra para a lista.
 * 
 * @author bgmartins
 */
public class ListaDeReservas {
	ArrayList<Reserva> reservas;
	int capacidade;

    /**
     * Método construtor que determina uma capacidade máxima para a lista.
     * A lista deve ter uma capacidade máxima, mesmo que seja implementada
     * com ArrayList
     * 
     * @param capacidade O número máximo de reservas que a lista pode conter
     */
	public ListaDeReservas(int capacidade) {
		this.capacidade = capacidade;
		this.reservas = new ArrayList<>();
	}
	
    /**
     * Método para incluir uma reserva na lista.
     * 
     * @return retorna true se a reserva foi incluída sem problemas e
     * false se a capacidade da lista estava esgotada
     */
	public boolean incluirReserva(Reserva reserva) {
		if (this.reservas.size() < capacidade && reserva != null ) {
			this.reservas.add(reserva);
			return true;
		}
		
		return false;
	}
	
    /**
     * Método que retorna um array de Reserva que contém somente as reservas
     * do Cliente com o cpf recebido no parâmetro
     *
     * @param cpf O CPF do Cliente do qual queremos selecionar as reservas
     * @return um array de Reserva com as reservas do Cliente em questão
     */
    public ArrayList<Reserva> reservasDoCliente(String cpf) {
        ArrayList<Reserva> reservasCli = new ArrayList<>();
        
        for (Reserva reserva: this.reservas) {
        	if (reserva.getCliente().getCpf().equals(cpf)) {
        		reservasCli.add(reserva);
        	}
        }

        if (reservasCli.size() != 0) {
        	return reservasCli;
        }
    	return null;
    }
    
    public ArrayList<Reserva> reservasDoDestino(String Destino){
        ArrayList<Reserva> reservasDestino = new ArrayList<>();
        
        for (Reserva reserva: this.reservas) {
        	if (reserva.getDestino().equals(Destino)) {
        		reservasDestino.add(reserva);
        	}
        }

        if (reservasDestino.size() != 0) {
        	return reservasDestino;
        }
    	return null;
    }
}

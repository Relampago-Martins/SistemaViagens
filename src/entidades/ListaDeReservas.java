package entidades;

import java.util.ArrayList;
import excecoes.ExcecaoDeListaCheia;

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
	public void incluirReserva(Reserva reserva) throws ExcecaoDeListaCheia{
		if (this.reservas.size() < capacidade && reserva != null ) {
			this.reservas.add(reserva);
			return;
		}
        throw new ExcecaoDeListaCheia("Lista de reservas cheia");
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

    public Reserva[] reservasDoCliente2 (String cpf){
        int countReservas = 0, i;
        for (Reserva reserva: this.reservas){
            if (reserva.getCliente().getCpf().equals(cpf)){
                countReservas++;
            }
        }
        Reserva[] resrvasCli = new Reserva[countReservas];
        i=0;
        for (Reserva reserva: this.reservas){
            if (reserva.getCliente().getCpf().equals(cpf)){
                resrvasCli[i++] = reserva;
            }
        }
        if (countReservas != 0){
            return resrvasCli;
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

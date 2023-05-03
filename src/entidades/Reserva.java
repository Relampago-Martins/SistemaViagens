package entidades;

public class Reserva {
	private Cliente cliente;
	private double preco;
	private String destino;
	
	public Reserva(Cliente cliente, String destino, double preco) {
		this.setCliente(cliente);
		this.setPreco(preco);
		this.setDestino(destino);
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
}

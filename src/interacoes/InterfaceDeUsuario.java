package interacoes;

import java.util.Scanner;
import java.util.ArrayList;

import entidades.ListaDeReservas;
import entidades.Cliente;
import entidades.Reserva;


public class InterfaceDeUsuario {
	Scanner leitor = new Scanner(System.in);
	enum Menu{
		CRIAR_CLIENTE, CRIAR_RESERVA, DESTINOS_CLIENTE, 
		PRECO_TOTAL_DESTINO, DEFAULT
	}
	ArrayList<Cliente> clientes= new ArrayList<>();
	ListaDeReservas reservas= new ListaDeReservas(30);
	
	public void rodarMenu() {
		boolean continuar =  true;
		
		while (continuar) {
			switch(this.getOption()) {			
			case CRIAR_CLIENTE:
				this.criarCliente();
				break;
			case CRIAR_RESERVA:
				this.criarReserva();
				break;
			case DESTINOS_CLIENTE:
				this.destinosCliente();
				break;
			case PRECO_TOTAL_DESTINO:
				this.precoTotalDestino();
				break;
			default:
				continuar = false;
				System.out.println("Encerrando ...");
				break;
			}
			System.out.print("\n--- \\ \\---\n");
		}
	}
	
	public Menu getOption() {
		System.out.println("Menu:\n"+
						"  (1) Cadastrar clientes\n"+
						"  (2) Cadastrar reservas\n"+
						"  (3) Listar os destinos das reservas de um determinado Cliente\n"+
						"  (4) Informar o preço total das reservas de um destino\n"+
						"  (Outros) Encerrar programa"
						);
		int opcao = this.leitor.nextInt();
		leitor.nextLine();
		
		try {
			return Menu.values()[opcao - 1];
		}catch(ArrayIndexOutOfBoundsException e){
			return Menu.DEFAULT;
		}
	}
	
	public void criarCliente() {
			System.out.print("Nome: ");
			String nome = leitor.nextLine();
			
			System.out.print("CPF: ");
			String cpf = leitor.next();
			leitor.nextLine();
			
			this.clientes.add(
					new Cliente(nome, cpf));
			System.out.println("Cliente criado com sucesso!");
	}
	
	public boolean criarReserva() {
		Cliente clienteFind = this.getInputCliente();
		boolean retorno = false;

		if (clienteFind != null) {
			System.out.print("Destino: ");
			String destino = this.leitor.nextLine();
			System.out.print("Preço: ");
			double preco = this.leitor.nextDouble();
			this.leitor.nextLine();
			
			 retorno = this.reservas.incluirReserva(
					new Reserva(clienteFind, destino, preco));
			
			if (retorno) {
				System.out.println("Reserva Criada com sucesso!");
			}else {
				System.out.println("Problema ao criar reserva.");
			}
			
		}else {
			System.out.println("Cliente não encontrado. Operação cancelada.");			
		}
		
		return retorno;
	}
	
	public void destinosCliente() {
		System.out.print("CPF do cliente: ");
		String cpf = this.leitor.next();
		this.leitor.nextLine();
		
		System.out.printf("Destinos das reservas do cliente %s:\n", cpf);
		ArrayList<Reserva> reservasCli= this.reservas.reservasDoCliente(cpf);
		
		if (reservasCli != null) {			
			for (Reserva reserva: reservasCli) {
				System.out.printf("  -> %s\n", reserva.getDestino());
			}
		}
	}
	
	public void precoTotalDestino() {
		System.out.print("Destino de viagem: ");
		String destino = this.leitor.nextLine();
		
		System.out.printf("Total preço das reservas do destino %s:\n", destino);
		ArrayList<Reserva> reservasDestino= this.reservas.reservasDoDestino(destino);

		double taltalPreco = 0;
		if (reservasDestino != null) {			
			for (Reserva reserva: reservasDestino) {
				taltalPreco += reserva.getPreco();
			}
		}
		System.out.printf("  %.2f reais\n", taltalPreco);
	}
	
	public Cliente getInputCliente() {
		System.out.print("CPF do cliente: ");
		String cpf = this.leitor.next();
		this.leitor.nextLine();
		
		for (Cliente clienteFind: this.clientes) {
			if (clienteFind.getCpf().equals(cpf)) {
				return clienteFind;
			}
		}
		return null;
	}
}

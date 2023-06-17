package interacoes;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import entidades.ListaDeReservas;
import entidades.Cliente;
import entidades.Reserva;
import excecoes.ExcecaoDeListaCheia;
import persistencia.IOManager;
import persistencia.ObjectIOManager;

public class InterfaceDeUsuario {
	Scanner leitor = new Scanner(System.in);
	IOManager ioManager = new ObjectIOManager();
	enum Menu{
		CRIAR_CLIENTE, CRIAR_RESERVA, DESTINOS_CLIENTE, 
		PRECO_TOTAL_DESTINO, LISTAR_CLIENTES, DEFAULT
	}

	List<Cliente> clientes= ioManager.loadClientes();
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
			case LISTAR_CLIENTES:
				this.listarClientes();
				break;
			default:
				continuar = false;
				this.ioManager.dumpClientes(this.clientes);
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
						"  (5) Listar os clientes cadastrados\n"+
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
	
	public void criarReserva() {
		Cliente clienteFind = this.getInputCliente();

		if (clienteFind == null) {
			System.out.println("Cliente não encontrado. Operação cancelada.");			
		}else{
			try{
				System.out.print("Destino: ");
				String destino = this.leitor.nextLine();
				System.out.print("Preço: ");
				double preco = this.leitor.nextDouble();
				this.leitor.nextLine();

				this.reservas.incluirReserva(
					   new Reserva(clienteFind, destino, preco));
				System.out.println("Reserva Criada com sucesso!");

			}catch(ExcecaoDeListaCheia e){
				System.out.printf("Erro: %s\n", e.getMessage());
			} finally {
				System.out.println("Operação cancelada. Tente novamente");
			}
		}
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

	public void listarClientes(){
		System.out.println("Clientes cadastrados: ");
		for(Cliente cliente: this.clientes){
			System.out.printf("  %s - %s\n", cliente.getNome(), cliente.getCpf());
		}
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

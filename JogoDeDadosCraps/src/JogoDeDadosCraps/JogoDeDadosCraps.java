package JogoDeDadosCraps;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Programa em Java que simula um jogo de dados
 * 
 * @author Erick Freire
 * @version 1 - Criado em 08-07-2021 as 17:48
 */

public class JogoDeDadosCraps {

	private static final SecureRandom NUMEROALEATORIO = new SecureRandom();
	static double bankBalance = 1000;

	private enum Status {
		CONTINUE, GANHOU, PERDEU,
	}

	private static final int OLHO_DE_COBRA = 2;
	private static final int TRES = 3;
	private static final int SETE = 7;
	private static final int ONZE = 11;
	private static final int CAIXA_DE_CARROS = 12;

	public static void main(String[] args) {

		int contSete = 0;
		int contOnze = 0;
		int contOlhoDeCobra = 0;
		int contTres = 0;
		int contCaixaDeCarros = 0;

		double aposta = 0;
		Scanner entrada = new Scanner(System.in);

		System.out.print("Informe uma aposta entre 0 e 1000: ");
		aposta = entrada.nextDouble();

		while (aposta > bankBalance || aposta < 0) {
			System.out.print("\nValor inválido, insira outra aposta: ");
			aposta = entrada.nextDouble();
		}

		for (int i = 1; i <= 1000000; i++) {

			int minhaPontuacao = 0;
			Status statusDoJogo;

			int somaRolagemDados = jogadaDeDados();

			switch (somaRolagemDados) {
			case SETE:
			case ONZE:
				if (somaRolagemDados == 7)
					contSete++;
				if (somaRolagemDados == 11)
					contOnze++;
				statusDoJogo = Status.GANHOU;
				bankBalance = aposta + bankBalance;
				System.out.printf("%nO novo saldo é de: %.2f", bankBalance);
				break;

			case OLHO_DE_COBRA:
			case TRES:
			case CAIXA_DE_CARROS:
				if (somaRolagemDados == 2)
					contOlhoDeCobra++;
				if (somaRolagemDados == 3)
					contTres++;
				if (somaRolagemDados == 12)
					contCaixaDeCarros++;
				statusDoJogo = Status.PERDEU;
				bankBalance = aposta - bankBalance;
				System.out.printf("%nO novo saldo é de: %.2f", bankBalance);
				if (bankBalance == 0 || bankBalance < 0)
					System.out.printf("%nDesculpe Mas Você Faliu");
				break;
			default:
				statusDoJogo = Status.CONTINUE;
				minhaPontuacao = somaRolagemDados;
				System.out.printf("Pontuação é de: %d%n", minhaPontuacao);
				break;

			}

			while (statusDoJogo == Status.CONTINUE) {
				somaRolagemDados = jogadaDeDados();

				if (somaRolagemDados == minhaPontuacao) {
					bankBalance = aposta + bankBalance;
					System.out.printf("%nO novo saldo é de: %.2f", bankBalance);
					statusDoJogo = Status.GANHOU;
				}

				else if (somaRolagemDados == SETE) {
					bankBalance = aposta - bankBalance;
					System.out.printf("%nO novo saldo é de: %.2f", bankBalance);
					if (bankBalance == 0 || bankBalance < 0)
						System.out.printf("%nDesculpe Mas Você Faliu");
					statusDoJogo = Status.PERDEU;
				}

			}

			if (statusDoJogo == Status.GANHOU)
				System.out.println("\nVocê Ganhou!");
			else
				System.out.println("\nVocê Perdeu!");
		}

		System.out.printf("\n\n Sete: %d \n", contSete);
		System.out.printf("\n\n Olho de Cobra: %d \n", contOlhoDeCobra);
		System.out.printf("\n\n Tres: %d \n", contTres);
		System.out.printf("\n\n Onze: %d \n", contOnze);
		System.out.printf("\n\n Caixa De Carros: %d \n", contCaixaDeCarros);

	}

	public static int jogadaDeDados() {

		int morreu1 = 1 + NUMEROALEATORIO.nextInt(6);
		int morreu2 = 1 + NUMEROALEATORIO.nextInt(6);

		int soma = morreu1 + morreu2;

		System.out.printf("O Jogador rolou %d + %d = %d%n", morreu1, morreu2, soma);

		return soma;

	}

}

import java.util.Scanner;

public class Sistemapago3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Tipo de pago (efectivo/tarjeta): ");
		String tipoPago = scanner.nextLine().toLowerCase();

		if (tipoPago.equals("efectivo")) {
			procesarPagoEfectivo(scanner);
		} else if (tipoPago.equals("tarjeta")) {
			procesarPagoTarjeta(scanner);
		} else {
			System.out.println("Tipo de pago no reconocido.");
		}

		scanner.close();
	}

	public static void procesarPagoEfectivo(Scanner scanner) {
		System.out.print("Ingrese el total a pagar (€): ");
		double montoTotal = scanner.nextDouble();
		//total a pagar
		System.out.print("Ingrese la cantidad entregada (€): ");
		double cantidadEntregada = scanner.nextDouble();
		//Cantidad que pagas
		if (cantidadEntregada < montoTotal) {
			System.out.println("Cantidad insuficiente para completar el pago.");
		} else {
			//Si entregas menos de lo indicado sacar por concolsa cantidad insuficiente
			double cambio = cantidadEntregada - montoTotal;
			System.out.printf("Cambio a devolver: %.2f€%n", cambio);
			calcularCambio(cambio);
			//Indica el cambio a devolver
		}
	}

	public static void calcularCambio(double cambio) {
		System.out.println(" su cambio:");
		//Indica el cambio

		// Desglose de billetes
		int billetes50 = (int) (cambio / 50);
		cambio -= billetes50 * 50;
		if (billetes50 > 0) {
			System.out.println(billetes50 + " billete(s) de 50€");
		}

		int billetes20 = (int) (cambio / 20);
		cambio -= billetes20 * 20;
		if (billetes20 > 0) {
			System.out.println(billetes20 + " billete(s) de 20€");
		}

		int billetes10 = (int) (cambio / 10);
		cambio -= billetes10 * 10;
		if (billetes10 > 0) {
			System.out.println(billetes10 + " billete(s) de 10€");
		}

		int billetes5 = (int) (cambio / 5);
		cambio -= billetes5 * 5;
		if (billetes5 > 0) {
			System.out.println(billetes5 + " billete(s) de 5€");
		}

		// Desglose de monedas de 1 euro
		int monedas1 = (int) cambio;
		cambio -= monedas1;
		if (monedas1 > 0) {
			System.out.println(monedas1 + " moneda(s) de 1€");
		}

		// Convertimos el cambio restante en céntimos
		cambio = Math.round(cambio * 100);

		// cambio de céntimos
		int centimos50 = (int) (cambio / 50);
		cambio -= centimos50 * 50;
		if (centimos50 > 0) {
			System.out.println(centimos50 + " moneda(s) de 50 céntimos");
		}

		int centimos20 = (int) (cambio / 20);
		cambio -= centimos20 * 20;
		if (centimos20 > 0) {
			System.out.println(centimos20 + " moneda(s) de 20 céntimos");
		}

		int centimos10 = (int) (cambio / 10);
		cambio -= centimos10 * 10;
		if (centimos10 > 0) {
			System.out.println(centimos10 + " moneda(s) de 10 céntimos");
		}

		int centimos5 = (int) (cambio / 5);
		cambio -= centimos5 * 5;
		if (centimos5 > 0) {
			System.out.println(centimos5 + " moneda(s) de 5 céntimos");
		}

		int centimos1 = (int) cambio;
		if (centimos1 > 0) {
			System.out.println(centimos1 + " moneda(s) de 1 céntimo");
		}
	}

	public static void procesarPagoTarjeta(Scanner scanner) {
		scanner.nextLine(); // Limpiar el buffer
		System.out.print("Ingrese el número de tarjeta: ");
		String numeroTarjeta = scanner.nextLine().replace(" ", "");

		if (!numeroTarjeta.matches("\\d+")) {
			System.out.println("Número de tarjeta inválido. Solo se permiten números.");
			return;
		}

		if (validarTarjeta(numeroTarjeta)) {
			System.out.println("Pago con tarjeta aceptado.");
		} else {
			System.out.println("Número de tarjeta inválido o tipo de tarjeta no aceptado.");
		}
	}

	public static boolean validarTarjeta(String numeroTarjeta) {
		// American Express
		if (numeroTarjeta.startsWith("3") && numeroTarjeta.length() == 15) {
			return numeroTarjeta.matches("\\d{4}\\d{6}\\d{5}");
		}

		//  VISA
		if (numeroTarjeta.startsWith("4") && numeroTarjeta.length() == 16) {
			return numeroTarjeta.matches("\\d{4}\\d{4}\\d{4}\\d{4}");
		}

		//  MasterCard
		if (numeroTarjeta.startsWith("5") && numeroTarjeta.length() == 16) {
			return numeroTarjeta.matches("\\d{4}\\d{4}\\d{4}\\d{4}");
		}

		return false;
	}
}

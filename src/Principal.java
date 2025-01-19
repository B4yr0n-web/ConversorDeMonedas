import java.net.http.HttpClient;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        try
                (Scanner entrada = new Scanner(System.in)) {
            HttpClient clienteHttp = HttpClient.newHttpClient();

            while (true) {
                System.out.println("Seleccione la moneda base:");
                String monedaBase = Monedas.seleccionarMoneda(entrada);

                System.out.println("Seleccione la moneda a convertir:");
                String monedaObjetivo = Monedas.seleccionarMoneda(entrada);

                System.out.println("Ingrese la cantidad a convertir:");
                double cantidad = entrada.nextDouble();

                Gson.convertirMoneda(clienteHttp, monedaBase, monedaObjetivo, cantidad);

                System.out.println("¿Desea realizar otra conversión? (s/n)");
                String repetir = entrada.next();
                if (!repetir.equalsIgnoreCase("s")) {
                    break;
                }
            }
            System.out.println("Gracias por usar el conversor de monedas.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
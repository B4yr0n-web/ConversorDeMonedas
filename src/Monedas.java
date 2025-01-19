import java.util.Scanner;

class Monedas extends Gson {
    public static String seleccionarMoneda(Scanner entrada) {
        System.out.println("""
                1- ARS (Peso argentino)
                2- CLP (Peso chileno)
                3- USD (Dólar estadounidense)
                """);
        int opciones = entrada.nextInt();
        return switch (opciones) {
            case 1 -> "ARS";
            case 2 -> "CLP";
            case 3 -> "USD";
            default -> {
                System.out.println("Opción inválida. Intente nuevamente.");
                yield seleccionarMoneda(entrada);
            }
        };
    }
}
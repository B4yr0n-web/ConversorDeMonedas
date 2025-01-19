
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;

class Gson implements Principal {
    public static void convertirMoneda(HttpClient clienteHttp, String monedaBase, String monedaObjetivo, double cantidad) {
        try {
            String urlSolicitud = "https://v6.exchangerate-api.com/v6/22a5093266b2828d2fde68ed/latest/"+ monedaBase;
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(urlSolicitud))
                    .GET()
                    .build();

            HttpResponse<String> respuesta = clienteHttp.send(solicitud, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonRespuesta = JsonParser.parseString(respuesta.body()).getAsJsonObject();

            if (!jsonRespuesta.has("conversion_rates")) {
                System.out.println("No se encontraron tasas de cambio en la respuesta.");
                return;
            }

            JsonObject tasasDeCambio = jsonRespuesta.getAsJsonObject("conversion_rates");

            if (!tasasDeCambio.has(monedaObjetivo)) {
                System.out.println("No se encontró la moneda objetivo en las tasas de cambio.");
                return;
            }

            double tasaCambio = tasasDeCambio.get(monedaObjetivo).getAsDouble();
            double cantidadConvertida = cantidad * tasaCambio;

            System.out.printf("%.2f %s son %.2f %s\n", cantidad, monedaBase, cantidadConvertida, monedaObjetivo);
        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }
}



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("*******************************************");

        System.out.println("Convertidor de Monedas / Currency Exchange Converter");

        List<Exchange> exchanges = new ArrayList<>();

        int exit = 0;
        while (exit != 1) {

            System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("""
                    \n
                    Escoge una de las opciones :
                    1) USD =>> ARS
                    2) ARS =>> USD
                    3) USD =>> BRL
                    4) BRL =>> USD
                    5) USD =>> COP
                    6) COP =>> USD
                    7) Salir
                    8) Historial de Conversor

                    USD: Dolar Estado Unidense
                    ARS: Peso Argentino
                    BRL: Real Brasileño
                    COP: Peso Colombiano
                    """);
            System.out.println("\n*******************************************")

            ;Scanner scanner = new Scanner(System.in);

            System.out.println("Ejecutar Opción: ");

            try {
                int exchangeOption = Integer.parseInt(scanner.nextLine());

                String fromCurrency;
                String toCurrency;
                switch (exchangeOption) {
                    case 1:
                        fromCurrency = "USD";
                        toCurrency = "ARS";
                        break;
                    case 2:
                        fromCurrency = "ARS";
                        toCurrency = "USD";
                        break;
                    case 3:
                        fromCurrency = "USD";
                        toCurrency = "BRL";
                        break;
                    case 4:
                        fromCurrency = "BRL";
                        toCurrency = "USD";
                        break;
                    case 5:
                        fromCurrency = "USD";
                        toCurrency = "COP";
                        break;
                    case 6:
                        fromCurrency = "COP";
                        toCurrency = "USD";
                        break;
                    case 7:
                        exit = 1;
                        fromCurrency = null;
                        toCurrency = null;
                        break;
                    default:
                        fromCurrency = null;
                        toCurrency = null;
                        break;
                }

                if (exchangeOption >=1 && exchangeOption <= 6) {
                    RequestCurrencyExchange request = new RequestCurrencyExchange();
                    CurrencyExchangeRate exchangeRate = request.currencyExchange(fromCurrency, toCurrency);

                    System.out.println("El tipo de cambio actual seria: " + exchangeRate.conversion_rate());

                    System.out.println("¿Cuanto " + fromCurrency + " es lo que deseas convertir? ");
                    Double total = Double.valueOf(scanner.nextLine());

                    double newTotal = total * exchangeRate.conversion_rate();

                    System.out.println("El cambio de " +
                            total +
                            " [" + fromCurrency + "]" +
                            " is " +
                            newTotal +
                            " [" + toCurrency + "]");

                    Exchange exchange = new Exchange(fromCurrency,toCurrency,total,newTotal);
                    exchanges.add(exchange);
                } else if (exchangeOption == 7) {
                    System.out.println("Gracias por usar nuestro convertidor de Monedas!\n");
                } else if (exchangeOption == 8) {
                    exchanges.forEach(System.out::println);
                } else {
                    System.out.println("La Opcion seleccionada (" + exchangeOption + ") es invalida!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("La Opcion seleccionada " + e.getMessage().toLowerCase() + ", es invalida!");
            }
        }
    }
}

package main;

import model.Currency;
import service.CurrencyConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", "US Dollar"));
        currencies.add(new Currency("EUR", "Euro"));
        currencies.add(new Currency("BRL", "Brazilian Real"));

        CurrencyConverter converter = new CurrencyConverter(currencies);

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Moedas disponíveis:");
            currencies.forEach(System.out::println);

            System.out.print("Digite o código da moeda de origem: ");
            String fromCode = scanner.nextLine();

            System.out.print("Digite o código da moeda de destino: ");
            String toCode = scanner.nextLine();

            System.out.print("Digite o valor a ser convertido: ");
            double amount = scanner.nextDouble();

            double result = converter.convert(fromCode, toCode, amount);
            System.out.printf("Valor convertido: %.2f %s%n", result, toCode);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

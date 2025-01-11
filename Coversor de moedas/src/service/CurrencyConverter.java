package service;

import model.Currency;
import util.ExchangeRateAPI;

import java.util.List;
import java.util.Optional;

public class CurrencyConverter {
    private List<Currency> currencies;

    public CurrencyConverter(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Optional<Currency> findCurrencyByCode(String code) {
        return currencies.stream()
                .filter(currency -> currency.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    public double convert(String fromCode, String toCode, double amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double rate = ExchangeRateAPI.getExchangeRate(fromCode, toCode);
        return amount * rate;
    }
}

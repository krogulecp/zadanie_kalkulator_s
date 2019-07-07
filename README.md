# zadanie_kalkulator_s

#### Działanie
* Aplikacja liczy prognozowaną wypłatę na kontrakcie w Niemczech, Wielkiej Brytanii lub Polsce
* Wypłata jest przeliczana na PLN wg. aktualnych kursów
* Aplikacja bierze pod uwagę 22 dni robocze dla każdego miesiąca
* 

#### Użycie
* Dokumentacja API jest dostępna po uruchomieniu aplikacji pod adresem (jeśli uruchomimy lokalnie): `http://localhost:8080/api/v2/api-docs`

* Aplikację można przetestować za pośrednictwem swagger-ui:
`http://localhost:8080/api/swagger-ui.html`

#### Konfiguracja
* Aplikacja pobiera kury walut z API NBP. Bazowy adres można skonfigurować w pliku properties:
`salary.calculator.exchange-rate-url` Do tego adresu jest dopisywany w aplikacji kod danej waluty
* Dla każdego kraju można skonfigurować podatek oraz koszty stałe w pliku properties:
```
salary.calculator.germany.fixed-costs
salary.calculator.germany.tax-percentage
salary.calculator.poland.fixed-costs
salary.calculator.poland.tax-percentage
salary.calculator.uk.fixed-costs
salary.calculator.uk.tax-percentage
```

* Koszty stałe wpisujemy w walucie kraju, do którego się odnoszą

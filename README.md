# Conversor de Monedas 💰➡️💰
## Aplicación web desarrollada con Spring Boot que permite convertir entre diferentes divisas utilizando tasas de cambio actualizadas. Incluye una interfaz sencilla y una API RESTful.

### Características principales:

✅ Conversión en tiempo real entre múltiples monedas con _ExchangeRate-API_.

✅ Listado de monedas soportadas (API + interfaz web).

✅ Manejo de errores personalizado (p. ej., tasas no disponibles).

✅ Interfaz web responsive (HTML/CSS/JS) integrada con Spring Boot.

✅ Deploy en https://warm-pasca-014f6f.netlify.app/ ```if(isCreditExit)```


### Tecnologías utilizadas

* Backend:

![image](https://github.com/user-attachments/assets/e21334be-5a1c-428f-afdf-79d64bdb1cc9)

![image](https://github.com/user-attachments/assets/9e8b0a91-dcbb-42d1-877c-3e35db8dae10)

- Frontend:

![image](https://github.com/user-attachments/assets/949b7f15-4b91-45af-aecc-d5b0b3271b0a)

![image](https://github.com/user-attachments/assets/3079d95f-de41-4d60-a968-d0b686e172e1)

![image](https://github.com/user-attachments/assets/3f7a3871-485a-4ff0-a93e-c4198c74472e)


### Estructura del proyecto
```
/src/main/java/com/ferchool/conversor_moneda/
├── controller/                  # Endpoints API REST
│   └── ConversorController.java
├── advice/exception/            # Manejo de errores
│   └── ExchangeRateException.java
├── model/                       # DTOs y entidades
│   ├── ConversionRequest.java
│   ├── ConversionResponse.java
│   └── SupportedCurrenciesResponse.java
├── service/                     # Lógica de negocio
│   ├── ConversorService.java
│   └── ExchangeRateService.java
└── resources/
    ├── static/                  # Frontend
    │   ├── index.html
    │   ├── script.js
    │   └── styles.css
    └── application.properties   # Configuración

```

### Cómo ejecutarlo

### Requisitos:

* JDK 17+

* Maven

 * Cuenta en Exchange-API

### Pasos:

```
git clone https://github.com/fersho101/conversor-moneda.git
cd conversor-moneda
mvn spring-boot:run

```

Acceder:

Interfaz web: http://localhost:8080

API: http://localhost:8080/api/convert (POST)

### Uso de la API

Convertir moneda (POST /api/convert)

* Request:

```json
{
  "fromCurrency": "USD",
  "toCurrency": "EUR",
  "amount": 100
}
```

* Response:

```json
{
  "fromCurrency": "USD",
  "toCurrency": "EUR",
  "originalAmount": 100,
  "convertedAmount": 93.50,
  "rate": 0.935
}

```

Monedas soportadas (GET /api/currencies)

* Response:


```json
{
  "supportedCurrencies": ["USD", "EUR", "JPY", "GBP"]
}
```

### Captura de pantalla

Interfaz web

![Screenshot from 2025-05-05 10-54-12](https://github.com/user-attachments/assets/a4816f29-2e8b-425f-b640-0b0199e3b3de)


### Próximas mejoras

Añadir historial de conversiones.

Registro de usuarios.

### Licencia
_MIT © fersho101_


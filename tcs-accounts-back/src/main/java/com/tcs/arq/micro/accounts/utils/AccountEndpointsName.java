package com.tcs.arq.micro.accounts.utils;

public class AccountEndpointsName {


    public static class ControllerAccountApi {
        // Endpoints
        public static final String BASE_API = "/api/v1/cuenta";
        public static final String REPORT = "/reporte";

        public static final String CREATE = "/create";
        public static final String CREATE_BY_NAME = "/create-by-name";
        public static final String ALL = "/all";
        public static final String ID_IN_PATH_ACCOUNT = "/{accountNumber}";
        public static final String DEFAULT_PAGE_INIT = "0";
        public static final String DEFAULT_PAGE_FINAL = "10";
        public static final String DEFAULT_SORT_BY = "accountNumber";
        public static final String DEFAULT_DIRECTION = "asc";

    }

    public static class ControllerTransactionApi {
        // Endpoints
        public static final String BASE_API = "/api/v1/cuenta/transaction";
        public static final String ID_IN_PATH_ACCOUNT_NUMBER = "/{accountNumber}";
        public static final String REPORT_ALL = "/reporteAll";
    }

    public static class ExternalService {
        // External service URLs (from application.properties)
        public static final String CUSTOMER_SERVICE_URL = "${customer.service.url}";
    }

    public static class Validation {
        public static final String FROM_DATE_REQUIRED = "La fecha inicial es obligatoria";
        public static final String TO_DATE_REQUIRED = "La fecha final es obligatoria";

        public static final String CLIENT_NAME_REQUIRED = "El nombre del cliente es obligatorio";
        public static final String CLIENT_NAME_MAX_LENGTH = "El nombre del cliente no debe exceder 100 caracteres";

        public static final String ACCOUNT_TYPE_REQUIRED = "El tipo de cuenta es obligatorio";
        public static final String ACCOUNT_TYPE_MAX_LENGTH = "El tipo de cuenta no debe exceder 50 caracteres";

        public static final String INITIAL_BALANCE_REQUIRED = "El saldo inicial es obligatorio";
        public static final String INITIAL_BALANCE_POSITIVE = "El saldo inicial no puede ser negativo";

        public static final String CLIENT_ID_REQUIRED = "El ID del cliente es obligatorio";

        public static final String ACCOUNT_NUMBER_REQUIRED = "El número de cuenta es obligatorio";

        public static final String AMOUNT_REQUIRED = "El monto del movimiento es obligatorio";
        public static final String AMOUNT_POSITIVE = "El monto debe ser mayor a cero";
        public static final String DESCRIPTION_MAX_LENGTH = "La descripción no debe exceder 200 caracteres";
    }
}

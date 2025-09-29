package com.tcs.arq.micro.accounts.utils;

public class AccountMessagesUtil {

    public static class Message {
        public static final String INSUFFICIENT_FUNDS = "Saldo no disponible";
        public static final String ACCOUNT_NOT_FOUND = "Cuenta no encontrada con número: %d";
        public static final String TRANSACTION_QUERY_VALIDATION_ERROR = "Errores de validación en la consulta de transacciones";
        public static final String CUSTOMER_NOT_FOUND = "Cliente no disponible";
        public static final String CUSTOMER_INACTIVE = "El cliente no está activo";
        public static final String CLIENT_ID_OR_NAME_REQUIRED = "Debe proporcionar el ID o el nombre del cliente para crear la cuenta.";

        public static final String NOT_FOUND_ID = "Cuenta no encontrada con número: %d";
        public static final String ALREADY_INACTIVE = "La cuenta con número %d ya está inactiva.";
        public static final String INVALID_ID = "El número de cuenta no puede ser nulo o menor o igual a cero.";
        public static final String ACCOUNT_DELETED_SUCCESSFULLY = "La cuenta ha sido eliminada con éxito";

    }

}

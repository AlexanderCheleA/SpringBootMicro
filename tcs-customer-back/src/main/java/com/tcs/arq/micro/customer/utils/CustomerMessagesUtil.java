package com.tcs.arq.micro.customer.utils;

public class CustomerMessagesUtil {

    public static class Message {
        public static final String NOT_FOUND_ID = "Cliente no encontrado con ID: %d";
        public static final String NOT_FOUND_NAME = "Cliente no encontrado con nombre: %s";
        public static final String NOT_FOUND_CUSTOMERS = "No se encontraron clientes en la base de datos";
        public static final String IDENTIFICATION_ALREADY_EXISTS = "Ya existe un cliente activo con esta identificación: %s";
        public static final String ALREADY_INACTIVE = "El cliente con ID %d ya está inactivo.";
        public static final String INVALID_ID = "El ID del cliente no puede ser nulo o menor o igual a cero.";
        public static final String INVALID_NAME = "El nombre del cliente no tiene data";
        public static final String CUSTOMER_DELETED_SUCCESSFULLY = "El cliente ha sido eliminado con éxito";
    }

}

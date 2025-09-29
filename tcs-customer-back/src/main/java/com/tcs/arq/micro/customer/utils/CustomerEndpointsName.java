package com.tcs.arq.micro.customer.utils;

public class CustomerEndpointsName {

    public static class ControllerApi {
        // Endpoints
        public static final String BASE_API = "/api/v1/clientes";
        public static final String CREATE = "/create";
        public static final String ALL = "/all";
        public static final String PAGINATE = "/paginado";

        // Parámetros por defecto
        public static final String DEFAULT_SORT_BY = "clientId";
        public static final String DEFAULT_DIRECTION = "asc";
        public static final String DEFAULT_PAGE_INIT = "0";
        public static final String DEFAULT_PAGE_FINAL = "10";

        public static final String ID_IN_PATH_CUSTOMER = "/{clientId}";

        public static final String SEARCH = "/buscar";
    }

}

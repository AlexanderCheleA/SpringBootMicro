package com.tcs.arq.micro.accounts.serenity.steps;

import com.tcs.arq.micro.accounts.serenity.utils.RestClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

public class CustomerSteps {
    private static final String BASE_URL = "http://localhost:8081/api/v1/clientes";
    private String lastResponseBody;

    public void getCustomerById(Integer id) {
        try {
            lastResponseBody = RestClient.get(BASE_URL + "/" + id);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar cliente por ID: " + e.getMessage(), e);
        }
    }

    public void validateCustomerResponse() {
        JSONObject response = null;
        try {
            response = new JSONObject(lastResponseBody);
            Assertions.assertEquals(1, response.getInt("clientId"));
            Assertions.assertEquals("Jose Lema", response.getString("name"));
            Assertions.assertEquals("Male", response.getString("gender"));
            Assertions.assertEquals(35, response.getInt("age"));
            Assertions.assertEquals("001", response.getString("identification"));
            Assertions.assertEquals("Otavalo sn y principal", response.getString("address"));
            Assertions.assertEquals("098254785", response.getString("phone"));
            Assertions.assertTrue(response.getBoolean("status"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCustomerWithoutIdentification() {
        String body = """
        {
          "name": "Jose Lema",
          "gender": "Male",
          "age": 30,
          "address": "Otavalo sn y principal",
          "phone": "098254785",
          "password": "1234",
          "status": true
        }
        """;

        try {
            lastResponseBody = RestClient.post(BASE_URL + "/create", body, null);
        } catch (Exception e) {
            lastResponseBody = e.getMessage(); // Captura el error para validación posterior
        }
    }

    public void validateMissingIdentificationError() {
        JSONObject response = null;
        try {
            response = new JSONObject(lastResponseBody);
            Assertions.assertEquals("/api/v1/clientes/create", response.getString("path"));
            Assertions.assertEquals("Bad Request", response.getString("error"));
            Assertions.assertEquals("Validation failed", response.getString("message"));
            Assertions.assertEquals("La identificación es obligatoria", response.getJSONObject("errors").getString("identification"));
            Assertions.assertEquals(400, response.getInt("status"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
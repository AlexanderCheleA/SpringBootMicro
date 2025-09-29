package com.tcs.arq.micro.accounts.serenity.definitions;

import com.tcs.arq.micro.accounts.serenity.steps.CustomerSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;

public class CustomerStepDefinitions {

    @Steps
    CustomerSteps customerSteps;

    @Dado("el servicio de clientes está disponible")
    public void elServicioDeClientesEstaDisponible() {
    }

    @Cuando("consulto el cliente con ID {int}")
    public void consultoElClienteConID(Integer id) {
        customerSteps.getCustomerById(id);
    }

    @Entonces("la respuesta debe contener los datos del cliente esperado")
    public void laRespuestaDebeContenerLosDatosDelClienteEsperado() {
        customerSteps.validateCustomerResponse();
    }

    @Cuando("intento crear un cliente sin identificación")
    public void intentoCrearUnClienteSinIdentificacion() {
        customerSteps.createCustomerWithoutIdentification();
    }

    @Entonces("la respuesta debe contener un error de validación")
    public void laRespuestaDebeContenerUnErrorDeValidacion() {
        customerSteps.validateMissingIdentificationError();
    }
}
Feature: Consultar cliente por ID

  Scenario: Obtener cliente con ID válido
    Given el servicio de clientes está disponible
    When consulto el cliente con ID 1
    Then la respuesta debe contener los datos del cliente esperado

  Scenario: Validación fallida al crear cliente sin identificación
    Given el servicio de clientes está disponible
    When intento crear un cliente sin identificación
    Then la respuesta debe contener un error de validación

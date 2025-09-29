package com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.request;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String name;

    @Size(max = 10, message = "El género no debe exceder 10 caracteres")
    private String gender;

    @Min(value = 0, message = "La edad debe ser un valor positivo")
    @Max(value = 110, message = "La edad no debe exceder 110 años")
    private Integer age;

    @NotBlank(message = "La identificación es obligatoria")
    @Size(max = 20, message = "La identificación no debe exceder 20 caracteres")
    @Pattern(regexp = "\\d+", message = "La identificación debe contener solo números")
    private String identification;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no debe exceder 150 caracteres")
    private String address;

    @NotBlank(message = "El teléfono es obligatoria")
    @Size(max = 15, message = "El teléfono no debe exceder 15 caracteres")
    private String phone;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 100, message = "La contraseña debe tener entre 4 y 100 caracteres")
    private String password;

    @NotNull(message = "El estado es obligatorio")
    private Boolean status;
}


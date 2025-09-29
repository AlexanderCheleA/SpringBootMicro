package com.tcs.arq.micro.customer.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity extends PersonEntity {

    @PrimaryKeyJoinColumn(name = "clientId")
    @Column(name = "clientId", insertable = false, updatable = false)
    private Long clientId;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "status", nullable = false)
    private Boolean status;

}

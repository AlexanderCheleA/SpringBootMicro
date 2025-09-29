package com.tcs.arq.micro.accounts.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionQuery {

    private Long accountNumber;
    private LocalDateTime from;
    private LocalDateTime to;

}

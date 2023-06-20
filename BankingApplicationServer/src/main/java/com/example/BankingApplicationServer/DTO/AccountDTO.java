package com.example.BankingApplicationServer.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AccountDTO {

    private int id;

    @Min(value = 0, message = "Cash should not be less than 0")
    private int cash;

}

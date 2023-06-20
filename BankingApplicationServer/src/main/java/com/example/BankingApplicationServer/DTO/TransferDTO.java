package com.example.BankingApplicationServer.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TransferDTO {
    private int sourceId;
    private int destinationId;

    @Min(value = 0, message = "Cash should not be less than 0")
    private int cash;
}

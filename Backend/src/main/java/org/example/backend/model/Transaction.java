package org.example.backend.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Transaction {

    @NotBlank
    private String transactionId;

    @NotBlank
    private String userId;

    @NotBlank
    private String accountId;

    @NotBlank
    private String timestamp;

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String transactionType; // CREDIT or DEBIT

    @NotBlank
    private String narration;

    @NotNull
    private Double balanceAfter;

    @NotBlank
    private String bankName;
}
package org.example.backend.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class WebHookPayload {

    @NotBlank
    private String consentId;

    @NotBlank
    private String userId;

    @NotBlank
    private String fetchTimestamp;

    @NotBlank
    private String bankName;

    @NotNull
    @NotEmpty
    @Valid
    private List<Transaction> transactions;
}
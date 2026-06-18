package org.example.backend.model;
import lombok.Data;

@Data
public class AnomalyResult {
    private String userId;
    private String anomalyType;
    private Double anomalyScore;
    private Double ratioInflowOutflow;
    private Double emiToIncomeRatio;
    private Double ambDropPercentage;
    private Integer transactionCount;
    private String detectedAt;
}
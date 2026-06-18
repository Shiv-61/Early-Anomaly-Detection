package org.example.backend.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "anomalies")
public class AnomalyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "anomaly_type")
    private String anomalyType;

    @Column(name = "anomaly_score")
    private Double anomalyScore;

    @Column(name = "ratio_inflow_outflow")
    private Double ratioInflowOutflow;

    @Column(name = "emi_to_income_ratio")
    private Double emiToIncomeRatio;

    @Column(name = "amb_drop_percentage")
    private Double ambDropPercentage;

    @Column(name = "transaction_count")
    private Integer transactionCount;

    @Column(name = "detected_at")
    private LocalDateTime detectedAt;
}
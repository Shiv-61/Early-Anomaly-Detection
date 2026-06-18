package org.example.backend.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.AnomalyEntity;
import org.example.backend.model.AnomalyResult;
import org.example.backend.Repository.AnomalyRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnomalyConsumer {

    private final AnomalyRepository anomalyRepository;

    @KafkaListener(topics = "anomalies", groupId = "java-anomaly-writer")
    public void consume(AnomalyResult result) {
        log.info("Received anomaly result for userId={} type={}",
                result.getUserId(), result.getAnomalyType());

        AnomalyEntity entity = new AnomalyEntity();
        entity.setUserId(result.getUserId());
        entity.setAnomalyType(result.getAnomalyType());
        entity.setAnomalyScore(result.getAnomalyScore());
        entity.setRatioInflowOutflow(result.getRatioInflowOutflow());
        entity.setEmiToIncomeRatio(result.getEmiToIncomeRatio());
        entity.setAmbDropPercentage(result.getAmbDropPercentage());
        entity.setTransactionCount(result.getTransactionCount());
        entity.setDetectedAt(LocalDateTime.now());

        anomalyRepository.save(entity);
        log.info("Anomaly saved to PostgreSQL for userId={}", result.getUserId());
    }
}
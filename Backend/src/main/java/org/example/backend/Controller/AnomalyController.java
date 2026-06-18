package org.example.backend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.AnomalyEntity;
import org.example.backend.Repository.AnomalyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anomalies")
@RequiredArgsConstructor
public class AnomalyController {

    private final AnomalyRepository anomalyRepository;

    @GetMapping
    public ResponseEntity<List<AnomalyEntity>> getAllAnomalies() {
        return ResponseEntity.ok(anomalyRepository.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AnomalyEntity>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(anomalyRepository.findByUserId(userId));
    }
}
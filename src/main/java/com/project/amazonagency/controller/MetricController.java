package com.project.amazonagency.controller;

import com.project.amazonagency.entity.Metric;
import com.project.amazonagency.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/metrics")
@CrossOrigin(origins = "http://localhost:5173")
public class MetricController {
    private List<Metric> list = new ArrayList<>(Arrays.asList(
            new Metric("Ad 1", 1760, 230),
            new Metric("Ad 2", 1203, 111),
            new Metric("Ad 3", 1504, 200),
            new Metric("Ad 4", 1139, 99),
            new Metric("Ad 5", 1906, 50),
            new Metric("Ad 6", 1879, 407)
    ));

    @GetMapping
    public ResponseEntity<List<Metric>> getMetrics() {
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Metric> createMetric(@Valid @RequestBody Metric metric) {
        for (Metric m : list) {
            if (Objects.equals(m.getName(), metric.getName())) {
                throw new ResourceNotFoundException("Metric with this name already exists");
            }
        }
        list.add(metric);
        return new ResponseEntity<>(metric, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

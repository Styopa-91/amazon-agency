package com.project.amazonagency.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metric {
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 255)
    private String name;
    @NotNull
    @Min(0)
    private int impressions;
    @NotNull
    @Min(0)
    private int clicks;
}

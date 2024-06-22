package com.project.amazonagency.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetricController.class)
public class MetricControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMetrics() throws Exception {
        mockMvc.perform(get("/api/v1/metrics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ad 1"))
                .andExpect(jsonPath("$[0].impressions").value(1760))
                .andExpect(jsonPath("$[0].clicks").value(230))
                .andExpect(jsonPath("$[1].name").value("Ad 2"))
                .andExpect(jsonPath("$[1].impressions").value(1203))
                .andExpect(jsonPath("$[1].clicks").value(111))
                .andExpect(jsonPath("$[2].name").value("Ad 3"))
                .andExpect(jsonPath("$[2].impressions").value(1504))
                .andExpect(jsonPath("$[2].clicks").value(200))
                .andExpect(jsonPath("$[3].name").value("Ad 4"))
                .andExpect(jsonPath("$[3].impressions").value(1139))
                .andExpect(jsonPath("$[3].clicks").value(99))
                .andExpect(jsonPath("$[4].name").value("Ad 5"))
                .andExpect(jsonPath("$[4].impressions").value(1906))
                .andExpect(jsonPath("$[4].clicks").value(50))
                .andExpect(jsonPath("$[5].name").value("Ad 6"))
                .andExpect(jsonPath("$[5].impressions").value(1879))
                .andExpect(jsonPath("$[5].clicks").value(407));
    }

    @Test
    public void testCreateMetric() throws Exception {
        String newMetricJson = "{\"name\":\"Ad 7\",\"impressions\":2500,\"clicks\":300}";

        mockMvc.perform(post("/api/v1/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newMetricJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ad 7"))
                .andExpect(jsonPath("$.impressions").value(2500))
                .andExpect(jsonPath("$.clicks").value(300));
    }

    @Test
    public void testCreateMetricWithDuplicateName() throws Exception {
        String duplicateMetricJson = "{\"name\":\"Ad 1\",\"impressions\":2500,\"clicks\":300}";

        mockMvc.perform(post("/api/v1/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(duplicateMetricJson))
                .andExpect(status().isNotFound());
    }
}

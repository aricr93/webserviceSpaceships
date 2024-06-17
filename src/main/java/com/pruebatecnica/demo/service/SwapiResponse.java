package com.pruebatecnica.demo.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiResponse {
    private List<SwapiSpaceship> results;
    private String next;

    public List<SwapiSpaceship> getResults() {
        return results;
    }

    public void setResults(List<SwapiSpaceship> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}

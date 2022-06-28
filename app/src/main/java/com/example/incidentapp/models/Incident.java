package com.example.incidentapp.models;

import java.util.ArrayList;

public class Incident {

    private int id;
    private String topic;
    private String description;
    private String comments;
    private String status;
    private User createdBy;
    private String createdAt;


    public static enum STATUS {
        OPEN, ACTIVE, CLOSED
    };

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getComments() {
        return comments;
    }

    public String getStatus() {
        return status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Incident(int id, String topic, String description) {

        this.id = id;
        this.topic = topic;
        this.description = description;

    }

    public Incident setId(int id) {
        this.id = id;
        return this;
    }

    public Incident setComment(String comments) {
        this.comments = comments;
        return this;
    }

    public Incident setStatus(String status) {
        this.status = status;
        return this;
    }

    public Incident setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Incident setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }


    public static Incident getIncidentById(int id) {

        return null;
    }
}

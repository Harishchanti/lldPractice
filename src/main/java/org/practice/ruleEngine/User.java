package org.practice.ruleEngine;

import java.time.Instant;


public class User {
    String userId;
    Instant lastLoginAt;
    Instant createdAt;
    boolean isDeleted;

    public User(String userId, Instant lastLoginAt, Instant createdAt,
            boolean isDeleted) {
        this.userId = userId;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Instant lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

package com.github.orions29.ekspedisi.model.entity;

import java.time.LocalDateTime;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model.entity
 * <p>
 * Model Entity Shipment Log
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: Orions29</td></tr>
 * <tr><td><b>Date</b></td><td>: 30 May 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 10:32</td></tr>
 * </table>
 * <hr>
 *
 * @author Orions29
 * @since 1.0
 */
public class ShipmentLog {
    private int logId;
    private String resiId;
    private String location;
    private String status;
    private LocalDateTime timestamp;
    private String userId; //

    public ShipmentLog() {
    }

    public ShipmentLog(int logId, String resiId, String location, String status, LocalDateTime timestamp, String userId) {
        this.logId = logId;
        this.resiId = resiId;
        this.location = location;
        this.status = status;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getResiId() {
        return resiId;
    }

    public void setResiId(String resiId) {
        this.resiId = resiId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShipmentLog{" +
                "logId=" + logId +
                ", resiId='" + resiId + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", timestamp=" + timestamp +
                ", updatedBy='" + userId + '\'' +
                '}';
    }
}

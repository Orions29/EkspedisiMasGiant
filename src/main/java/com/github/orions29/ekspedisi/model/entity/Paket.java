package com.github.orions29.ekspedisi.model.entity;

import java.time.LocalDateTime;

/**
 * Project: EkspedisiMasGiant
 * Package: com.github.orions29.ekspedisi.model.entity
 * <p>
 * Model Entity untuk Paket
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
public class Paket {
    private String resiId;
    private String senderName;
    private String originCity;
    private String receiverName;
    private String destinationCity;
    private String destinationAddress;
    private double weight;
    private double volume;
    private String typePaket;
    private LocalDateTime createdAt;

    public Paket() {
    }

    public Paket(String resiId, String senderName, String originCity, String receiverName, String destinationCity, String destinationAddress, double weight, double volume, String typePaket, LocalDateTime createdAt) {
        this.resiId = resiId;
        this.senderName = senderName;
        this.originCity = originCity;
        this.receiverName = receiverName;
        this.destinationCity = destinationCity;
        this.destinationAddress = destinationAddress;
        this.weight = weight;
        this.volume = volume;
        this.typePaket = typePaket;
        this.createdAt = createdAt;
    }

    public Paket(String resiId, String senderName, String originCity, String receiverName,
                 String destinationCity, String destinationAddress, double weight,
                 double volume, String typePaket) {
        this.resiId = resiId;
        this.senderName = senderName;
        this.originCity = originCity;
        this.receiverName = receiverName;
        this.destinationCity = destinationCity;
        this.destinationAddress = destinationAddress;
        this.weight = weight;
        this.volume = volume;
        this.typePaket = typePaket;
        // createdAt dibiarkan null karena nanti diisi otomatis oleh MariaDB
    }

    public String getResiId() {
        return resiId;
    }

    public void setResiId(String resiId) {
        this.resiId = resiId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getTypePaket() {
        return typePaket;
    }

    public void setTypePaket(String typePaket) {
        this.typePaket = typePaket;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

//    Bisnis Logic Pricing

    public double calculateVolumetricWeight() {
        return this.volume / 6000.0;
    }

    public double getChargeableWeight() {
        double volWeight = calculateVolumetricWeight();
        return Math.max(this.weight, volWeight);
    }


//    Biar gampang kalau mau ambil sample
    @Override
    public String toString() {
        return "Paket{" +
                "resiId='" + resiId + '\'' +
                ", rute='" + originCity + " -> " + destinationCity + '\'' +
                ", receiver='" + receiverName + '\'' +
                ", address='" + destinationAddress + '\'' +
                ", chargeableWeight=" + String.format("%.2f", getChargeableWeight()) + "Kg" +
                ", type='" + typePaket + '\'' +
                '}';
    }
}

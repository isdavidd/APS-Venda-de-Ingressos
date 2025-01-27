package com.aps.grupo4.ticket_service.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_tickets")
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    private Instant creationTimeStamp;

    @UpdateTimestamp
    private Instant updateTimeStamp;

    public Ticket() {
    }

    public Ticket(String ticketType, String paymentMethod, String buyerName, double price, String status, Instant creationTimeStamp, Instant updateTimeStamp) {
        this.ticketType = ticketType;
        this.paymentMethod = paymentMethod;
        this.buyerName = buyerName;
        this.price = price;
        this.status = status;
        this.creationTimeStamp = creationTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Instant creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public Instant getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Instant updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}

package com.ntnn.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "booking")
@Data
public class BookingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fromDate")
    private Date fromDate;

    @Column(name = "toDate")
    private Date toDate;

    @Column(name = "aadharNumber")
    private String aadharNumber;

    @Column(name = "numOfRooms")
    private int numOfRooms;

    @Column(name = "roomNumbers")
    private String roomNumbers;

    @Column(name = "roomPrice")
    @NotNull(message = "RoomPrice mustn't be null")
    private Integer roomPrice;

    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "bookedOn")
    private Date bookOn;
}

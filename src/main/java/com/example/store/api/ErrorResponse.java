/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String message;
    private Object details;

    public ErrorResponse(Date timestamp, int status, String message, Object details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getDetails() {
        return details;
    }
    
    
}

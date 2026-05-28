package com.example.course.Exception;
import java.time.LocalDateTime;
public class ExceptionHandles {

    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String details;
    private String path;

    public ExceptionHandles(LocalDateTime timeStamp, int status, String error, String details, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.details = details;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getDetails() {
        return details;
    }


    public String getPath() {
        return path;
    }



}

package com.rtsoju.dku_council_homepage.exception;

import org.hibernate.cfg.Environment;

public class BadRequestException extends RuntimeException{
    private String errorCode;


    //400
    public BadRequestException(){

    }

    public BadRequestException(String message){
        super(message);
    }
}

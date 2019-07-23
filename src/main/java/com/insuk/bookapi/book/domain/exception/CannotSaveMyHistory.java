package com.insuk.bookapi.book.domain.exception;

public class CannotSaveMyHistory extends RuntimeException{
    public CannotSaveMyHistory(String message){
        super(message);
    }

    public CannotSaveMyHistory(String message, Throwable exeption){
        super(message, exeption);
    }
}

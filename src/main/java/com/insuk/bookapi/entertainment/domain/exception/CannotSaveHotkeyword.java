package com.insuk.bookapi.entertainment.domain.exception;

public class CannotSaveHotkeyword extends RuntimeException{
    public CannotSaveHotkeyword(String message){
        super(message);
    }

    public CannotSaveHotkeyword(String message, Throwable exeption){
        super(message, exeption);
    }
}

package com.example.SpringBootCRUD.exceptions;

public class AuthenticationFailException extends IllegalArgumentException
{
    public AuthenticationFailException(String msg)
    {
        super(msg);
    }
}

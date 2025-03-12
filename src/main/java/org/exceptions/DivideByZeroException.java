package org.exceptions;

public class DivideByZeroException extends Exception{
    public DivideByZeroException(){
        super("You can't divide by zero");
    }
}

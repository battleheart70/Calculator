package org.exceptions;

public class DivideByZeroException extends Exception{
    public DivideByZeroException(){
        super("Вы не можете делить на ноль!");
    }
}

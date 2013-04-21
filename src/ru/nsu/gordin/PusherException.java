package ru.nsu.gordin;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/2/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */

public class PusherException extends Exception {
    public PusherException(){}

    public PusherException(String message){super(message);}

    public PusherException(String message, Throwable cause) {super(message, cause);}

    public PusherException(Throwable cause) {super(cause);}

}

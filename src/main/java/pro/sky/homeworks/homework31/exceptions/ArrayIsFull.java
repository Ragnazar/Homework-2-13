package pro.sky.homeworks.homework31.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
public class ArrayIsFull extends RuntimeException{
    public ArrayIsFull(String message){
        super(message);
    }
}


package com.clustered.DataWarehouse.Exceptions;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message){
        super(message);
    }
}

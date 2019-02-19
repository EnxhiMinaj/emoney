package com.emoney.core.exception;


import com.emoney.core.model.ResponseObj;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Anil Kumal on 02/02/2019.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the general error case. print the exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObj> handleGenericException(Exception e) {
        e.printStackTrace();
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: " + e.getMessage())
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }


    /**
     * Handles the customize {@link ExpiredJwtException} expired jwt exception error case . print the exception
     *
     * @param runtimeException the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseObj> handleExpiredJwtException(RuntimeException runtimeException) {
        runtimeException.printStackTrace();
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + runtimeException.getCause() + ", message: " + runtimeException.getMessage())
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

    /**
     * Handles the method argument not valid exception. print the exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObj> methodArgumentNotValidException(Exception e) {
        e.printStackTrace();
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: " + e.getMessage())
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);

    }

    /**
     * Handles the Data database level DataIntegrityViolationException. print the exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseObj> constraintViolationException(Exception e) {
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: " + e.getMessage())
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

    /**
     * Handles the Data database level TransientPropertyValueException. print the exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)//TO DO
    public ResponseEntity<ResponseObj> transientPropertyValueException(Exception e) {
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: " + e.getMessage())
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

    /**
     * Handles the Data database level ObjectOptimisticLockingFailureException. print the exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ResponseObj> objectOptimisticLockingFailureException(Exception e) {
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: " + e.getMessage())
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

    /**
     * Handles the Data database level SignatureException. print the exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseObj> signatureException(Exception e) {
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: Sorry!! Someone has already updated the record")
                .responseStatus(false)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }


    /**
     * Handles custom exception
     *
     * @param e the exception not handled by other exception handler methods
     * @return {@link ResponseObj} which contains the error message and response status with JSON format
     */
    @ExceptionHandler(EmoneyException.class)
    public ResponseEntity<ResponseObj> dracException(RuntimeException e) {
        e.printStackTrace();
        Object data = null;
        if (e instanceof EmoneyException) {
            data = ((EmoneyException) e).getData();
        }
        ResponseObj responseObj = new ResponseObj.ResponseObjBuilder()
                .message("cause: " + e.getCause() + ", message: " + e.getMessage())
                .responseStatus(false)
                .result(data)
                .build();
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }
}

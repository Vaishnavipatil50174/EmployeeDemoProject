package com.example.employeedemo.handler;

import com.example.employeedemo.exception.InvalidEmployeeDataException;
import com.example.employeedemo.exception.TryException;
import com.example.employeedemo.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(TryException.class)
    public ProblemDetail tryException(TryException tryException){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"Its Try exception");
        problemDetail.setTitle(tryException.getMessage());
        return problemDetail;
    }


    @ExceptionHandler(UserException.class)
    public ProblemDetail userException(UserException userException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "User exception");
        problemDetail.setTitle(userException.getMessage());
        return problemDetail;

    }
    @ExceptionHandler(InvalidEmployeeDataException.class)
    public ProblemDetail invalidEmployeeDataException(InvalidEmployeeDataException invalidEmployeeDataException){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"Invalid employee data");
        problemDetail.setTitle(invalidEmployeeDataException.getMessage());

        return problemDetail;
    }

//    @ExceptionHandler(UserException.class)
//    public ProblemDetail userException(NullPointerException nullPointerException){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "User exception");
//        problemDetail.setTitle(nullPointerException.getMessage());
//        return problemDetail;
//
//    }
}

package com.freesoft.task.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Publisher could not be found", value = HttpStatus.NOT_FOUND)
public class PublisherNotFoundException extends RuntimeException{
}

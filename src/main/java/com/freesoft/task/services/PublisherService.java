package com.freesoft.task.services;

import com.freesoft.task.dtos.BookDto;
import com.freesoft.task.dtos.PublisherDto;
import com.freesoft.task.entities.Publisher;

public interface PublisherService {

    Publisher getPublisherByName(String name);
    void delete(Publisher publisher);
    void save(Publisher publisher);
}

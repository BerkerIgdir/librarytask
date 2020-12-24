package com.freesoft.task.services.implementations;


import com.freesoft.task.entities.Publisher;
import com.freesoft.task.repositories.PublisherRepository;
import com.freesoft.task.services.PublisherService;
import com.freesoft.task.services.exceptions.PublisherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private final PublisherRepository publisherRepository;

    @Override
    public Publisher getPublisherByName(String name) {
        return publisherRepository.findByName(name).orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public void delete(Publisher publisher) {
        publisherRepository.delete(publisher);
    }

    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }


}

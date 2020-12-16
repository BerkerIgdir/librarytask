package com.freesoft.task.services.implementations;

import com.freesoft.task.dtos.PublisherDto;
import com.freesoft.task.mappers.PublisherMapper;
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
    @Autowired
    private final PublisherMapper publisherMapper;

    @Override
    public PublisherDto getPublisherByName(String name) {
        return publisherMapper.toDto(publisherRepository.findByName(name).orElseThrow(PublisherNotFoundException::new));
    }
}

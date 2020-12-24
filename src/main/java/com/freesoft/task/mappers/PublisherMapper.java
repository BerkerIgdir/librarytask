package com.freesoft.task.mappers;

import com.freesoft.task.dtos.PublisherDto;
import com.freesoft.task.entities.Publisher;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDto toDto(Publisher publisher);
    Publisher toPublisher(PublisherDto publisherDto);

}

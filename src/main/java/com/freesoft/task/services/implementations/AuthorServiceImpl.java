package com.freesoft.task.services.implementations;

import com.freesoft.task.dtos.AuthorDto;
import com.freesoft.task.mappers.AuthorMapper;
import com.freesoft.task.repositories.AuthorRepository;
import com.freesoft.task.services.AuthorService;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto getByNameAndSurname(String name, String surname) {
        return authorMapper.toDto(authorRepository.findByNameAndSurname(name,surname).orElseThrow(BookNotFoundException::new));
    }

    @Override
    public Page<AuthorDto> getAuthorsByName(String name, Pageable pageable) {

        List<AuthorDto> authorDtoList = authorRepository.findByName(name)
                .stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(authorDtoList,pageable,authorDtoList.size());
    }

    @Override
    public Page<AuthorDto> getAuthorsBySurname(String name, Pageable pageable) {

        List<AuthorDto> authorDtoList = authorRepository.findBySurname(name)
                .stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(authorDtoList,pageable,authorDtoList.size());
    }
}

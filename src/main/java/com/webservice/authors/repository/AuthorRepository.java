package com.webservice.authors.repository;

import com.webservice.authors.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Optional<Author> findByNameAndUrl(String name, String url);

}

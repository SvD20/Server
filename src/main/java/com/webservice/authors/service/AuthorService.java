package com.webservice.authors.service;

import com.google.common.collect.Lists;
import com.webservice.authors.entity.Author;
import com.webservice.authors.vo.AuthorVO;
import com.webservice.authors.exception.NoRecordsException;
import com.webservice.authors.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public boolean add(AuthorVO authorVO){
        Author author = new Author();
        BeanUtils.copyProperties(authorVO,author);
        try {
            authorRepository.save(author);
        }
        catch (DataAccessException dae){
            log.error("Error while saving into database:{}", dae.getMessage());
        }
            return true;
        }

    public AuthorVO get(String name, String url){
        Optional<Author> author = authorRepository.findByNameAndUrl(name, url);
        if(!author.isPresent()){
            throw new NoRecordsException("No Records for Author " + name);
        }
        AuthorVO authorVO = new AuthorVO();
        BeanUtils.copyProperties(author.get(),authorVO);
        return authorVO;
    }

    public List<AuthorVO> getAll(){
        List<Author> authors = Lists.newArrayList(authorRepository.findAll());
        if(authors.isEmpty()){
            throw new NoRecordsException("No Authors found");
        }
        List<AuthorVO> authorVOS = new ArrayList<>();
        authors.forEach(author -> {
            AuthorVO authorVO = new AuthorVO();
            BeanUtils.copyProperties(author,authorVO);
            authorVOS.add(authorVO);
        });
        return authorVOS;
    }

    public AuthorVO findById(int id){
        Optional<Author> author = authorRepository.findById(id);
        if(!author.isPresent()){
            throw new NoRecordsException("No Records for Author for ID " + id);
        }
        AuthorVO authorVO = new AuthorVO();
        BeanUtils.copyProperties(author.get(),authorVO);
        return authorVO;
    }


}



package com.webservice.authors.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bpb_author", uniqueConstraints = @UniqueConstraint(columnNames = {"name","url"}))
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String url;
    private String name;
    private String bio;
}

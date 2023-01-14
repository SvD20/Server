package com.webservice.authors.vo;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthorVO {
    @NotEmpty
    private String url;
    @NotEmpty
    private String name;
    @NotEmpty
    private String bio;
}

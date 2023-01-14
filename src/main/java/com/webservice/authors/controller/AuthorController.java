package com.webservice.authors.controller;

import com.webservice.authors.vo.AuthorVO;
import com.webservice.authors.exception.ErrorMessage;
import com.webservice.authors.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    private DiscoveryClient discoveryClient;// будет использовать eureka клиент, потому что другого нет

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(name = "Call API using RestTemplate",value = "/callAPI/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorVO> callApiUsingRestTemplate(@PathVariable @Positive(message = "Invalid ID") int id)
            throws URISyntaxException{
        return restTemplate.
                exchange("http://web-service/author/{id}",HttpMethod.GET,null,AuthorVO.class,id);
    }

    /*@GetMapping(name = "Call API using RestTemplate",value = "/callAPI", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> callApiUsingRestTemplate() throws URISyntaxException {
       ResponseEntity<String> plainResponse = restTemplate.exchange(RequestEntity.get(
                new URI("http://localhost:8081/author/all")).build(), String.class);
        //log.info("Received Plain response from '/all' API:{}", plainResponse.getBody());

        AuthorVO authorVO = new AuthorVO();
        authorVO.setUrl("url-called for PostEntity /callAPI");
        authorVO.setName("name-called for PostForEntity /callAPI");
        authorVO.setBio("bio-called for PostForEntity /callAPI");
        ResponseEntity<Boolean> booleanResponse = restTemplate.
        postForEntity(new URI("http://localhost:8081/author/add"), authorVO, Boolean.class);
        //log.info("Received response from POST API '/add' (PostForEntity):{}", booleanResponse.getBody());

        AuthorVO authorVO1 = new AuthorVO();
        authorVO1.setUrl("url-called for Exchange /callAPI");
        authorVO1.setName("name-called for Exchange /callAPI");
        authorVO1.setBio("bio-called for Exchange /callAPI");
        HttpHeaders headers = new HttpHeaders();
        headers.add("key1", "value1");
        HttpEntity<AuthorVO> httpEntity = new HttpEntity<>(authorVO1, headers);
        ResponseEntity<Boolean> booleanResponse1  = restTemplate.exchange( new URI("http://localhost:8081/author/add"),
                HttpMethod.POST, httpEntity,Boolean.class);
        //log.info("Received response from POST API '/add' (Exchange):{}", booleanResponse1.getBody());

        ResponseEntity<List> pojoResponse = restTemplate.exchange(RequestEntity.get(
                new URI("http://localhost:8081/author/all")).build(), List.class);
        //log.info("Received POJO response from '/all' API:{}", booleanResponse1.getBody());
        return new ResponseEntity<>(null,HttpStatus.OK);
    }*/

    @GetMapping(name = "Get Eureka Service Instances",value = "/service-instances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEurekaServices(){
        System.out.println(discoveryClient.getInstances("web-service"));
        return new ResponseEntity<>(this.discoveryClient.getServices(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, name = "Get Author By Name and URL", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthor(@RequestParam(name = "name") String name, @RequestParam(name = "url") String url){
        try{
            return new ResponseEntity<>(authorService.get(name,url), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "Get Author By ID", value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthorById(@PathVariable @Positive(message = "Invalid ID") int id){
            return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
    }

    @PostMapping(name = "Add Author", value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorVO authorVO){
        try{
            return new ResponseEntity<>(authorService.add(authorVO), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "Get Authors", value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthors (){
        try{
            return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}

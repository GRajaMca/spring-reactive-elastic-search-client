package io.javabeans.springreactiveelasticsearchclient.controller;

import io.javabeans.springreactiveelasticsearchclient.dto.SearchDTO;
import io.javabeans.springreactiveelasticsearchclient.dto.User;
import io.javabeans.springreactiveelasticsearchclient.service.ReactiveElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/reactive")
public class ReactiveElasticSearchController {

  private final ReactiveElasticSearchService reactiveElasticSearchService;

  public ReactiveElasticSearchController(ReactiveElasticSearchService reactiveElasticSearchService) {
    this.reactiveElasticSearchService = reactiveElasticSearchService;
  }

  @GetMapping("{id}")
  public Mono<User> getUser(@PathVariable String id) {
    return this.reactiveElasticSearchService.getUser(id);
  }

  @PostMapping()
  public Mono<User> saveUser(@RequestBody User user) {
    return this.reactiveElasticSearchService.saveUser(user);
  }

  @PostMapping("/search")
  public Flux<User> searchUser(@RequestBody SearchDTO searchDTO) {
    return this.reactiveElasticSearchService.searchUser(searchDTO);
  }
}

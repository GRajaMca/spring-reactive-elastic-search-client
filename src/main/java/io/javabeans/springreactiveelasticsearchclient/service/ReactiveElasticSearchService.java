package io.javabeans.springreactiveelasticsearchclient.service;

import io.javabeans.springreactiveelasticsearchclient.dto.SearchDTO;
import io.javabeans.springreactiveelasticsearchclient.dto.User;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReactiveElasticSearchService {

  public static final String USER_INDEX = "user";
  private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;

  public ReactiveElasticSearchService(ReactiveElasticsearchOperations reactiveElasticsearchOperations) {
    this.reactiveElasticsearchOperations = reactiveElasticsearchOperations;
  }

  public Mono<User> getUser(String id) {
    return this.reactiveElasticsearchOperations.get(id, User.class, IndexCoordinates.of(USER_INDEX));
  }

  public Mono<User> saveUser(User user) {
    return this.reactiveElasticsearchOperations.save(user, IndexCoordinates.of(USER_INDEX));
  }

  public Flux<User> searchUser(SearchDTO searchDTO) {
    NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();

    if (!ObjectUtils.isEmpty(searchDTO.getField()) && !ObjectUtils.isEmpty(searchDTO.getValue())) {

      query.withQuery(QueryBuilders.matchQuery(searchDTO.getField(), searchDTO.getValue()));
    }
    return reactiveElasticsearchOperations.search(
            query.build(),
            User.class,
            IndexCoordinates.of(USER_INDEX)
        )
        .map(SearchHit::getContent)
        .filter(Objects::nonNull);
  }
}

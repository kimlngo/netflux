package kimlngo.springframework.netflux.services;

import kimlngo.springframework.netflux.domain.Movie;
import kimlngo.springframework.netflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovieService {
    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();

    Flux<MovieEvent> streamMovieEvents(String id);
}

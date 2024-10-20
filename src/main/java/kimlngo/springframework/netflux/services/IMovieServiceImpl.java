package kimlngo.springframework.netflux.services;

import kimlngo.springframework.netflux.domain.Movie;
import kimlngo.springframework.netflux.domain.MovieEvent;
import kimlngo.springframework.netflux.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class IMovieServiceImpl implements IMovieService {
    private final IMovieRepository movieRepository;

    @Override
    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String id) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
                       movieEventSynchronousSink.next(new MovieEvent(id, new Date()));
                   })
                   .delayElements(Duration.ofSeconds(1)) ;
    }
}

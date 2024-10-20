package kimlngo.springframework.netflux.bootstrap;

import kimlngo.springframework.netflux.domain.Movie;
import kimlngo.springframework.netflux.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class InitMovies implements CommandLineRunner {
    private final IMovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll()
                       .thenMany(
                               Flux.just("The Shawshank Redemption", "The Godfather", "The Dark Knight",
                                           "12 Angry Men", "The Lord of the Rings: The Return of the King",
                                           "Schindler's List", "Pulp Fiction")
                                       .map(title -> Movie.builder().title(title).build())
                                       .flatMap(movieRepository::save)
                       ).subscribe(null, null, () -> {
                           movieRepository.findAll()
                                          .subscribe(System.out::println);
                       });
    }
}

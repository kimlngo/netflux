package kimlngo.springframework.netflux.repository;

import kimlngo.springframework.netflux.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IMovieRepository extends ReactiveMongoRepository<Movie, String> {
}

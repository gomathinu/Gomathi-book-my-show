package app.bookmyshow.bms_movie_service.repository.impl;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.repository.MovieSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieSearchRepositoryImpl implements MovieSearchRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Movie> searchMovies(Movie movie) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (movie.getTitle() != null) {
            criteriaList.add(Criteria.where("title").regex(movie.getTitle(), "i"));
        }
        if (movie.getGenre() != null) {
            criteriaList.add(Criteria.where("genre").is(movie.getGenre()));
        }
        if (movie.getLanguage() != null) {
            criteriaList.add(Criteria.where("language").is(movie.getLanguage()));
        }
        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }
        Query query = new Query(finalCriteria);
        Pageable pageable = PageRequest.of(
                movie.getPage(),
                movie.getSize(),
                movie.getSortDir().equalsIgnoreCase("asc")
                        ? Sort.by(movie.getSortBy()).ascending()
                        : Sort.by(movie.getSortBy()).descending()
        );
        query.with(pageable);
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        long total = mongoTemplate.count(query.skip(0).limit(0), Movie.class);
        return new PageImpl<>(movies, pageable, total);
    }
}

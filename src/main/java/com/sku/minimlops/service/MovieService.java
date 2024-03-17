package com.sku.minimlops.service;

import com.sku.minimlops.model.dto.response.MovieChartResponse;
import com.sku.minimlops.model.dto.response.MovieTodayCountResponse;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sku.minimlops.model.domain.Movie;
import com.sku.minimlops.model.dto.MovieDTO;
import com.sku.minimlops.model.dto.response.MovieCountResponse;
import com.sku.minimlops.model.dto.response.MovieResponse;
import com.sku.minimlops.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieResponse getMoviesByCollectionDate(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<Movie> movies = movieRepository.findByCollectionDateBetweenOrderByCollectionDateDesc(startDate, endDate, pageable);
        return MovieResponse.builder()
                .movie(movies.getContent().stream().map(MovieDTO::fromMovie).toList())
                .totalPages(movies.getTotalPages())
                .totalElements((int) movies.getTotalElements())
                .first(movies.isFirst())
                .last(movies.isLast())
                .build();
    }

    public MovieChartResponse countMoviesDaily(LocalDate startDate, LocalDate endDate) {
        return MovieChartResponse.builder()
                .id("Data")
                .color("hsl(500, 70%, 50%)")
                .data(movieRepository.getCountByCollectionDateInRange(startDate, endDate))
                .build();
    }

    public MovieTodayCountResponse countMoviesByCollectionDateToday() {
        return MovieTodayCountResponse.builder()
                .totalElements(movieRepository.countMoviesByCollectionDateToday(LocalDate.now()))
                .build();
    }

    @Transactional
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public MovieCountResponse countMoviesByReleaseDate(LocalDate startDate, LocalDate endDate) {
        return MovieCountResponse.builder()
                .totalElements(movieRepository.countMoviesByReleaseDateBetween(startDate, endDate))
                .build();
    }
}

package ru.jabka.filmplus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.Genre;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.film.FilmResponse;
import ru.jabka.filmplus.repository.FilmRepository;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @Test
    public void createfilm_valid(){
        FilmRequest filmRequest = buildFilmRequest("Matrix", "About NEO", LocalDate.of(2001, 4, 7), 143L, Genre.ACTION);
        FilmResponse filmResponse = buildFilmResponse(filmRequest);
        Mockito.when(filmRepository.insert(filmRequest)).thenReturn(filmResponse);
        FilmResponse film = filmService.filmCreate(filmRequest);
        Assertions.assertEquals(filmResponse, film);
        Mockito.verify(filmRepository).insert(filmRequest);
    }

    @Test
    public void shouldReturnError_whenCreateFilmTitleIsEmpty() {
        FilmRequest filmRequest = buildFilmRequest("", "About NEO", LocalDate.of(2001, 4, 7), 143L, Genre.ACTION);
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class, () -> filmService.filmCreate(filmRequest)
        );
        Assertions.assertEquals("Введите имя фильма", exception.getMessage());
    }

    @Test
    public void shouldReturnUser_whenSearch() {
        FilmRequest filmRequest = buildFilmRequest("Matrix", "About NEO", LocalDate.of(2001, 4, 7), 143L, Genre.ACTION);
        FilmResponse filmResponse = buildFilmResponse(filmRequest);
        Mockito.when(filmRepository.search("Matrix", "NEO", null)).thenReturn(List.of(filmResponse));
        List<FilmResponse> film = filmService.search("Matrix", "NEO", null);
        Assertions.assertTrue(film.contains(filmResponse));
        Mockito.verify(filmRepository).search("Matrix", "NEO", null);
    }

    @Test
    public void updatefilm_valid(){
        FilmRequest filmRequest = buildFilmRequest("Matrix", "About NEO", LocalDate.of(2001, 4, 7), 143L, Genre.ACTION);
        FilmResponse filmResponse = buildFilmResponse(filmRequest);
        Mockito.when(filmRepository.update(1L, filmRequest)).thenReturn(filmResponse);
        FilmResponse film = filmService.update(1L, filmRequest);
        Assertions.assertEquals(filmResponse, film);
        Mockito.verify(filmRepository).update(1L, filmRequest);
    }

    private FilmResponse buildFilmResponse(FilmRequest filmRequest) {
        return FilmResponse.builder()
                .id(1L)
                .name(filmRequest.getName())
                .description(filmRequest.getDescription())
                .releaseDate(filmRequest.getReleaseDate())
                .duration(filmRequest.getDuration())
                .genre(filmRequest.getGenre())
                .build();
    }

    private FilmRequest buildFilmRequest(String title, String description, LocalDate releaseDate, Long duration, Genre genre) {
        return FilmRequest.builder()
                .name(title)
                .description(description)
                .releaseDate(releaseDate)
                .duration(duration)
                .genre(genre)
                .build();
    }
}
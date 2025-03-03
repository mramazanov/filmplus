package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.Genre;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.film.FilmResponse;
import ru.jabka.filmplus.repository.FilmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    @Transactional(rollbackFor = Exception.class)
    public FilmResponse filmCreate(final FilmRequest filmRequest) {
        validate(filmRequest);
        return filmRepository.insert(filmRequest);
    }

    @Transactional(readOnly = true)
    public FilmResponse getFilmByid(Long id) {
        return filmRepository.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public FilmResponse update(final long filmId, final FilmRequest filmRequest) {
        validate(filmRequest);
        return filmRepository.update(filmId, filmRequest);
    }

    @Transactional(readOnly = true)
    public List<FilmResponse> search(final String name, final String description, final Genre genre) {
        return filmRepository.search(name, description, genre);
    }

    private void validate(FilmRequest filmRequest) {
        if (filmRequest == null) {
            throw new BadRequestException("Введите информацию о фильме");
        }
        if (!StringUtils.hasText(filmRequest.getName())) {
            throw new BadRequestException("Введите имя фильма");
        }
        if (!StringUtils.hasText(filmRequest.getDescription())) {
            throw new BadRequestException("Введите описание для фильма");
        }
        if (filmRequest.getReleaseDate() == null) {
            throw new BadRequestException("Введите корректную дату выхода фильма");
        }
        if (filmRequest.getDuration() == 0 || filmRequest.getDuration() < 0) {
            throw new BadRequestException("Введите информацию о длительности фильма");
        }
        if (filmRequest.getGenre() == null) {
            throw new BadRequestException("Введите информацию о жанре фильма");
        }
    }
}
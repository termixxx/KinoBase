package ru.zagorovskiy.kinobase.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.kinobase.domain.entiti.Content;
import ru.zagorovskiy.kinobase.domain.enums.Country;
import ru.zagorovskiy.kinobase.domain.enums.Genre;
import ru.zagorovskiy.kinobase.domain.enums.TypeOfContent;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ContentRowMapper implements RowMapper<Content> {
    @Override
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        Content content = new Content();

        content.setId(rs.getLong("id"));
        content.setGenreList(getGenres(rs.getArray("genres")));
        content.setCountryList(getCountries(rs.getArray("countries")));
        content.setTitle(rs.getString("title"));
        content.setDescription(rs.getString("description"));
        content.setType(TypeOfContent.valueOf(rs.getString("type")));
        content.setReleaseYear(LocalDate.of(rs.getInt("release_year"), 1, 1));
        content.setPosterUrl(rs.getString("poster_url"));

        return content;
    }

    private Set<Genre> getGenres(Array array) throws SQLException {
        String[] genres = (String[]) array.getArray();
        return Arrays.stream(genres)
                .map(Genre::valueOf)
                .collect(Collectors.toSet());
    }

    private Set<Country> getCountries(Array array) throws SQLException {
        String[] countries = (String[]) array.getArray();
        return Arrays.stream(countries)
                .map(Country::valueOf)
                .collect(Collectors.toSet());
    }
}

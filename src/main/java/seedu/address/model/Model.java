package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.cinema.Cinema;
import seedu.address.model.cinema.Theater;
import seedu.address.model.cinema.exceptions.CinemaNotFoundException;
import seedu.address.model.cinema.exceptions.DuplicateCinemaException;
import seedu.address.model.movie.Movie;
import seedu.address.model.movie.exceptions.DuplicateMovieException;
import seedu.address.model.movie.exceptions.MovieNotFoundException;
import seedu.address.model.screening.Screening;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Cinema> PREDICATE_SHOW_ALL_CINEMAS = unused -> true;
    Predicate<Movie> PREDICATE_SHOW_ALL_MOVIES = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyMoviePlanner newData);

    /** Returns the MoviePlanner */
    ReadOnlyMoviePlanner getMoviePlanner();

    /** Deletes the given cinema. */
    void deleteCinema(Cinema target) throws CinemaNotFoundException;

    /** Adds the given cinema */
    void addCinema(Cinema cinema) throws DuplicateCinemaException;

    /** Deletes {@code tag} from all {@code Movie}. */
    void deleteTag(Tag tag) throws TagNotFoundException;

    /**
     * Replaces the given cinema {@code target} with {@code editedCinema}.
     *
     * @throws DuplicateCinemaException if updating the cinema's details causes the cinema to be equivalent to
     *      another existing cinema in the list.
     * @throws CinemaNotFoundException if {@code target} could not be found in the list.
     */
    void updateCinema(Cinema target, Cinema editedCinema)
            throws DuplicateCinemaException, CinemaNotFoundException;

    /** Returns an unmodifiable view of the filtered cinema list */
    ObservableList<Cinema> getFilteredCinemaList();

    /** Returns an unmodifiable view of the filtered movie list */
    ObservableList<Movie> getFilteredMovieList();

    /**
     * Updates the filter of the filtered cinema list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCinemaList(Predicate<Cinema> predicate);

    /** Deletes the given movie. */
    void deleteMovie(Movie target) throws MovieNotFoundException;

    /** Adds the given movie */
    void addMovie(Movie movie) throws DuplicateMovieException;

    /**
     * Replaces the given movie {@code target} with {@code editedMovie}.
     *
     * @throws DuplicateMovieException if updating the movie's details causes the cinema to be equivalent to
     *      another existing movie in the list.
     * @throws MovieNotFoundException if {@code target} could not be found in the list.
     */
    void updateMovie(Movie target, Movie editedMovie)
            throws DuplicateMovieException, MovieNotFoundException;

    /**
     * Updates the filter of the filtered movie list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMovieList(Predicate<Movie> predicate);

    /** Adds the given screening to a theater */
    void addScreening(Screening screening, Theater theater);

}

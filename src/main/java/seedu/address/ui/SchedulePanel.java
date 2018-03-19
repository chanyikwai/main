package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.CinemaPanelSelectionChangedEvent;
import seedu.address.model.cinema.Cinema;
import seedu.address.model.cinema.Theater;
import seedu.address.model.movie.Movie;

/**
 * The Browser Panel of the App.
 */
public class SchedulePanel extends UiPart<Region> {

    private static final String FXML = "SchedulePanel.fxml";

    private static final double THEATER_PANEL_MARGIN       = 70.0;
    private static final double DEFAULT_PANEL_PREF_HEIGHT  = 70.0;
    private static final double DEFAULT_PANEL_PREF_WIDTH   = 430.0;

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private ArrayList<Movie> listOfMovies;
    private ArrayList<Theater> listOfTheaters;
    private ArrayList<ArrayList<Movie>> listOfTheaterSchedule;
    private int i = 0;
    private VBox verticalListOfTheater;

    @FXML
    private AnchorPane cinemaPane;

    public SchedulePanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        registerAsAnEventHandler(this);
    }

    /**
     * Loads the selected cinema's schedule with style that matches theme
     */
    private void loadCinemaSchedule(Cinema cinema) {
        if (cinema != null) {
            cinemaPane.getChildren().clear();
            verticalListOfTheater = new VBox();
            cinemaPane.getChildren().add(verticalListOfTheater);
            listOfTheaters = new ArrayList<>();
            listOfTheaters = cinema.getTheaters();
            loadTheaterSchedule(cinema.getName().fullName);
        }
        //TODO: Get movie schedule info from storage
    }

    /**
     * Load the each individual theater's schedule that is in the selected cinema
     */
    public void loadTheaterSchedule(String cinemaFullName) {
        initializeTheaters();
        createNewMovieGUILabel("1", "Tarzan: The wild, the furious and the bold boy of the jungle", 20.0);
        createNewMovieGUILabel("1", "Iron Man 4: The End of Technology", 45.0);
        createNewMovieGUILabel("2", "Iron Man 4: The End of Technology", 45.0);
        createNewMovieGUILabel("3", "Romeo and Juliet: A 3D Romantic Love Story", 65.0);
    }

    /**
     * Displays each theater's schedule
     */
    private void initializeTheaters() {
        listOfTheaterSchedule = new ArrayList<>();
        int numberOfTheaters = listOfTheaters.size();
        for (i = 0; i < numberOfTheaters; i++) {
            addNewTheaterScheduleToTheater();
        }
        // TODO: To be enhanced for easier reading
        for (i = 0; i < numberOfTheaters; i++) {
            Label bgLabel = new Label();
            bgLabel.setStyle("-fx-background-color: black");
            bgLabel.setText("theater" + Integer.toString(i));
            bgLabel.setPrefSize(DEFAULT_PANEL_PREF_WIDTH, DEFAULT_PANEL_PREF_HEIGHT);
            verticalListOfTheater.getChildren().add(bgLabel);
            AnchorPane.setLeftAnchor(bgLabel, 5.0);
            AnchorPane.setTopAnchor(bgLabel, (i + 1) * DEFAULT_PANEL_PREF_HEIGHT);
        }
    }

    private void addNewTheaterScheduleToTheater() {
        listOfMovies = new ArrayList<>();
        listOfTheaterSchedule.add(listOfMovies);
    }

    /**
     * Creates a new movie GUI label with relevant movie information
     */
    private void createNewMovieGUILabel(String theaterNumber, String movieName, double movieLength) {
        Label movieLabel = new Label();
        movieLabel.setStyle("-fx-background-color: blue");
        movieLabel.setText(movieName);
        movieLabel.setAlignment(Pos.CENTER);
        //TODO: Set the prefWidth param to lengthen according to movie length
        movieLabel.setPrefSize(movieLength, DEFAULT_PANEL_PREF_HEIGHT);
        addMovieGUILabelToSchedule(theaterNumber, movieLabel, movieLength * 3.0);
    }

    /**
     * Slots the movie GUI label into the theater's schedule
     */
    private void addMovieGUILabelToSchedule(String theaterNumber, Label movieLabel, double timeSlot) {
        cinemaPane.getChildren().add(movieLabel);
        AnchorPane.setLeftAnchor(movieLabel, timeSlot);
        AnchorPane.setTopAnchor(movieLabel, (Integer.parseInt(theaterNumber) - 1) * THEATER_PANEL_MARGIN);
        movieLabel.toFront();
    }

    //TODO: Create a new function "AddToTheater mv/Movie t/time
    public void addMovieToTheaterScheduleList(String theaterNumber, Movie movieToAdd) {
        listOfTheaterSchedule.get(Integer.parseInt(theaterNumber)).add(movieToAdd);
    }

    @Subscribe
    private void handleCinemaPanelSelectionChangedEvent(CinemaPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadCinemaSchedule(event.getNewSelection().cinema);
    }
}

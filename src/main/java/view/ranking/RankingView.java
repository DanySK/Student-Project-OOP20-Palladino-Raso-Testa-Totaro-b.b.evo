package view.ranking;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Optional;

import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.menu.SceneLoader;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import model.utilities.GameUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.FXMLMenuController;

public class RankingView implements Initializable, FXMLMenuController {

    @FXML
    private AnchorPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private VBox rankingContainer;

    @FXML
    private TableView<Map.Entry<String, Integer>> displayRanking;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> aliasColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> scoreColumn;

    @FXML
    private HBox containerBackButton;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonDelete;

    private final Font fontColumn = Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE);
    private final LeaderboardController controller = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
    private static final String DEFAULT_TABLE_MESSAGE = "Play the game please :)";
    private static final String DEFAULT_ALERT_TITLE = "Delete Leaderboard";
    private static final String DEFAULT_ALERT_CONTENT = "Are you sure to delete the leaderboard?";
    private static final int MULTIPLIER = 2;

     /**
     *  Method that initialize all component of scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.initializeTableView();
        this.loadFont();
        this.loadAnimation();
        this.loadListener();
        this.resizable();
    }


    @SuppressWarnings("unchecked") //Use for delete generic warning parameter.
    private void initializeTableView() {
        this.aliasColumn
            .setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));
        this.scoreColumn
            .setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue()));

        final ObservableList<Map.Entry<String, Integer>> data = FXCollections.observableArrayList(this.controller.viewLeaderboard().entrySet());

        this.displayRanking.setItems(data);
        this.displayRanking.getColumns().setAll(this.aliasColumn, this.scoreColumn);

        this.displayRanking.setPlaceholder(new Label(DEFAULT_TABLE_MESSAGE));

    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadFont() {
        this.lblTitle
        .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.buttonBack
        .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.buttonDelete
        .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadListener() {
        //Button back Listener
        this.buttonBack.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                this.window.getWidth(), 
                                this.window.getHeight(),
                                PersonalStyle.DEFAULT_STYLE.getStylePath());

            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());

         });

        /* Button delete leaderboard */
        this.buttonDelete.setOnAction(event -> {
            this.showAlertDialog();
        });

        /* Change column font */
        this.aliasColumn.setCellFactory(new Callback<TableColumn<Entry<String, Integer>, String>, TableCell<Entry<String, Integer>, String>>() {

            @Override
            public TableCell<Entry<String, Integer>, String> call(final TableColumn<Entry<String, Integer>, String> param) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setText(item);
                            this.setFont(fontColumn);
                        }
                    }
                };
            }
        });

        this.scoreColumn.setCellFactory(new Callback<TableColumn<Entry<String, Integer>, Integer>, TableCell<Entry<String, Integer>, Integer>>() {

            @Override
            public TableCell<Entry<String, Integer>, Integer> call(final TableColumn<Entry<String, Integer>, Integer> param) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(final Integer item, final boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setText(String.valueOf(item));
                            this.setFont(fontColumn);
                        }
                    }
                };
            }
        });
    }

    private void showAlertDialog() {
        //Create alert
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(DEFAULT_ALERT_TITLE);
        alert.setHeaderText(DEFAULT_ALERT_CONTENT);
        alert.setContentText(DEFAULT_ALERT_CONTENT);

        //Create Button in alert
        final ButtonType yesButton = new ButtonType("Yes");
        final ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        //Control the choose
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            this.controller.clearLeaderboard();
            //Refresh all view
            SceneLoader.switchScene((Stage) this.window.getScene().getWindow(), 
                    PersonalViews.SCENE_RANKING.getURL(), 
                    PersonalViews.SCENE_RANKING.getTitleScene(), 
                    this.window.getWidth(), 
                    this.window.getHeight(),
                    PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        } else {
            alert.close();
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void resizable() {

        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());
        this.lblTitle.setWrapText(true);
        this.buttonBack.prefWidthProperty().bind(this.containerBackButton.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.buttonDelete.prefWidthProperty().bind(this.containerBackButton.widthProperty().divide(GameUtilities.CENTER_DIVIDER * MULTIPLIER));
        this.displayRanking.prefHeightProperty().bind(this.rankingContainer.heightProperty());
        this.displayRanking.prefWidthProperty().bind(this.rankingContainer.widthProperty());
    }

}

package view.ranking;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import controller.menu.SceneLoaderSingleton;
import controller.sound.SoundController;
import controller.utilities.IOLeaderboard;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;
import view.utilities.PersonalSounds;
import view.utilities.PersonalViews;

public class ControllerRanking implements Initializable {

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
    private TableView displayRanking;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> aliasColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> scoreColumn;

    @FXML
    private HBox containerBackButton;

    @FXML
    private Button buttonBack;

    private static final int SIZEFONTTITLE = 64;
    private static final int SIZEFONT = 24;
    private static final int SIZEWIDTH = 20;
    private static final int SIZEHEIGHT = 20;
    private static final int CENTER_POSITION = 2;
    private final Font fontColumn = Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), SIZEFONT);

     /**
     *  Method that initialize all component of scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.initializeTableView();
        this.loadFont();
        this.loadImage();
        this.loadAnimation();
        this.loadListener();
        this.resizable();
    }

    private void initializeTableView() {
        this.aliasColumn
            .setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));
        this.scoreColumn
            .setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue()));

        final ObservableList<Map.Entry<String, Integer>> data = FXCollections.observableArrayList(IOLeaderboard.readLeaderboard().entrySet());

        this.displayRanking.setItems(data);
        this.displayRanking.getColumns().setAll(this.aliasColumn, this.scoreColumn);
    }

    private void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    private void loadImage() {
        final ImageView imgBack = new ImageView(
                new Image(PersonalImages.BACK_IMG.getResourceAsStream()));
        imgBack.setFitWidth(SIZEWIDTH);
        imgBack.setFitHeight(SIZEHEIGHT);
        this.buttonBack.setGraphic(imgBack);
    }

    private void loadFont() {
        this.lblTitle
        .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), SIZEFONTTITLE));
        this.buttonBack
        .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), SIZEFONT));
    }

    private void loadListener() {
        //Button back Listener
        this.buttonBack.setOnAction(event -> {
            SceneLoaderSingleton.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                this.window.getWidth(), 
                                this.window.getHeight());

            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());

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

    private void resizable() {

        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());
        this.lblTitle.setWrapText(true);
        this.buttonBack.prefWidthProperty().bind(this.containerBackButton.widthProperty().divide(CENTER_POSITION));
        this.displayRanking.prefHeightProperty().bind(this.rankingContainer.heightProperty());
        this.displayRanking.prefWidthProperty().bind(this.rankingContainer.widthProperty());
    }

}

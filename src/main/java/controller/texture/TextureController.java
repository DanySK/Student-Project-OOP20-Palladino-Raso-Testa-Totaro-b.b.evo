package controller.texture;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;

public class TextureController {

    private static final int COMBOBOX_HEIGHT = 30;

    private ComboBox<String> ballTexture;

    private ComboBox<String> brickTexture;


    /**
     * @param ballTexture
     * @param brickTexture
     */
    public TextureController(final ComboBox<String> ballTexture, final ComboBox<String> brickTexture) {
        super();
        this.ballTexture = ballTexture;
        this.brickTexture = brickTexture;
    }



    /**
     * 
     */
    public void loadBallTexture() {
        this.ballTexture.getItems().addAll(BallTexture.getBallTexturedNames());
        this.ballTexture.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(final ListView<String> p) {
                return new ListCell<String>() {

                    @Override
                    protected void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon;
                            try {
                                final String iconPath = BallTexture.getBallTextureByName(item).getPath();
                                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                            } catch (NullPointerException ex) {
                                // in case the above image doesn't exist, use a default one
                                final String iconPath = "Images/ball/defaultBall.png";
                                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                            }
                            ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(COMBOBOX_HEIGHT);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        });
    }

    /**
     * 
     */
    public void loadBrickTexture() {
        this.brickTexture.getItems().addAll(BrickTexture.getBrickTextureNames());
        this.brickTexture.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(final ListView<String> p) {
                return new ListCell<String>() {

                    @Override
                    protected void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon;
                            try {
                                final String iconPath = BrickTexture.getBrickTextureByName(item);
                                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                            } catch (NullPointerException ex) {
                                // in case the above image doesn't exist, use a default one
                                final String iconPath = "Images/brick/SuperMarioBrickTexture.png";
                                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                            }
                            ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(COMBOBOX_HEIGHT);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        });
    }
}

package lk.ijse.gdse72.shaanfashion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/DashBoardForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shaan Fashion");
        stage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream("/assest/shopping_15018424.png"))
        );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

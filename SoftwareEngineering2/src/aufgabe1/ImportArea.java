package aufgabe1;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ImportArea {

    private final AnchorPane anchorPane = new AnchorPane();

    public ImportArea(Runnable onImport, Runnable onOpenFile){

        final Button importButton = new Button("Import");
        final Button openFileButton = new Button("Open File");

        AnchorPane.setBottomAnchor(importButton, 10.0);
        AnchorPane.setTopAnchor(importButton, 10.0);
        AnchorPane.setLeftAnchor(importButton, 10.0);

        AnchorPane.setBottomAnchor(openFileButton, 10.0);
        AnchorPane.setTopAnchor(openFileButton, 10.0);
        AnchorPane.setLeftAnchor(openFileButton, 90.0);



        anchorPane.getChildren().addAll(importButton, openFileButton);

        importButton.setOnAction(event -> {
            onImport.run();
        });

        openFileButton.setOnAction(event -> {
            onOpenFile.run();
        });

    }

    public AnchorPane getPane() {
        return anchorPane;
    }
}
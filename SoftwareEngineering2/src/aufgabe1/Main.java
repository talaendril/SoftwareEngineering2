package aufgabe1;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	TelefonBook tbook = null;
	TelefonBook secondBook = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		tbook = new TelefonBook(FileSystem.getPathOne());
		secondBook = new TelefonBook(null);
		
		BorderPane bp = createTelefonArea(tbook, true);
		BorderPane bp2 = createTelefonArea(secondBook, false);
		
		root.setTop(createMenuBar());
		root.setCenter(bp);
		root.setRight(bp2);
		
		primaryStage.setTitle("Telefonbuch");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

	private BorderPane createTelefonArea(TelefonBook teleBook, boolean isOwn) {
		BorderPane bp = new BorderPane();
		EntryArea entryArea = new EntryArea(teleBook.getNumbers());
		SearchArea searchArea = new SearchArea();
		
		searchArea.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			entryArea.clearSelection();
			String search = searchArea.getTextField().getText();
			String[] tokens = search.split("\\s+");
			Iterator<TelefonEntry> iterator = teleBook.getNumbers().iterator();
			List<TelefonEntry> selection = new ArrayList<>();
			while(iterator.hasNext()) {
				TelefonEntry entry = iterator.next();
				for(int i = 0; i < tokens.length; i++) {	//this marks all elements where either firstName, lastName or Number is mentioned in any of the tokens
					if(entry.getFirstName().contains(tokens[i]) || entry.getLastName().contains(tokens[i]) || entry.getNumber().contains(tokens[i])) {
						selection.add(entry);
					}
				}
				entryArea.selectEntries(selection);
			}
		});
		
		if(!isOwn) {
			entryArea.getPane().setOnDragOver(e -> {
				e.acceptTransferModes(TransferMode.COPY);
			});
			entryArea.getPane().setOnDragDropped(e -> {
                final Dragboard db = e.getDragboard();
                Path path;

                if(db.hasFiles() && (db.getFiles().get(0).getPath().endsWith(".json") || db.getFiles().get(0).getPath().endsWith(".txt"))){
                    path = Paths.get(db.getFiles().get(0).getPath());

                    secondBook.read(path);

                } else {
                    e.consume();
                }

            });
			bp.setBottom(createImportArea(teleBook, entryArea).getPane());
		} else {
			bp.setBottom(createAddDeleteArea(teleBook, entryArea).getPane());
		}
		bp.setTop(searchArea.getPane());
		bp.setCenter(entryArea.getPane());
		return bp;
	}

	private AddDeleteArea createAddDeleteArea(TelefonBook teleBook, EntryArea entryArea) {
		AddDeleteArea addDeleteArea = new AddDeleteArea(
				() -> {
			teleBook.getNumbers().add(new TelefonEntry());
		}, 
				() -> {
			TelefonEntry selection = entryArea.getSelectedEntry();
			if(teleBook.getNumbers().contains(selection)) {
				teleBook.getNumbers().remove(selection);
			}
		});
		return addDeleteArea;
	}
	
	private ImportArea createImportArea(TelefonBook teleBook, EntryArea entryArea) {
		ImportArea importArea = new ImportArea(
				() -> {
					List<TelefonEntry> entries = entryArea.getSelectedEntries();
					entries.forEach(e -> tbook.add(e));
				}, 
				() -> {
					Stage fileChooserStage = new Stage();
                    fileChooserStage.setTitle("File Chooser Sample");
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(fileChooserStage);
                    if (file != null) {
                        secondBook.read(Paths.get(file.getPath()));
                    }
				});
		return importArea;
	}
	
	private MenuBar createMenuBar() {
		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		MenuItem nothing = new MenuItem("Does Nothing Yet");
		file.getItems().add(nothing);
		mb.getMenus().add(file);
		return mb;
	}
	
	public void stop() {
		tbook.saveTo(FileSystem.getPathOne());
	}

    public static void main(String[] args) {
        launch(args);
    }

}
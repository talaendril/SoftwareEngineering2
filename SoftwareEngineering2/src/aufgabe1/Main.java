package aufgabe1;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	TelefonBook tbook = null;
	TelefonBook secondBook = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		tbook = new TelefonBook();
		
		//AREAS
		EntryArea entryArea = new EntryArea(tbook.getNumbers());
		SearchArea searchArea = new SearchArea();
		AddDeleteArea addDeleteArea = new AddDeleteArea(
				() -> {
			tbook.getNumbers().add(new TelefonEntry());
		}, 
				() -> {
			TelefonEntry selection = entryArea.getSelectedEntry();
			if(tbook.getNumbers().contains(selection)) {
				tbook.getNumbers().remove(selection);
			}
		});
		searchArea.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			entryArea.clearSelection();
			String search = searchArea.getTextField().getText();
			String[] tokens = search.split("\\s+");
			Iterator<TelefonEntry> iterator = tbook.getNumbers().iterator();
			List<TelefonEntry> selection = new ArrayList<>();
			while(iterator.hasNext()) {
				TelefonEntry entry = iterator.next();
				for(int i = 0; i < tokens.length; i++) {	//this marks all elements where either firstName, lastName or Number is mentioned in any of the tokens
					if(entry.getFirstName().equals(tokens[i]) || entry.getLastName().equals(tokens[i]) || entry.getNumber().equals(tokens[i])) {
						selection.add(entry);
					}
				}
				entryArea.selectEntries(selection);
			}
		});
		//Button Implementation ends here
		
		//MENU
		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		MenuItem switchBook = new MenuItem("Switch TelefonBook");
		switchBook.setOnAction(a -> {
			secondBook = new TelefonBook(FileSystem.switchTelefonBook());
			entryArea.setItems(secondBook.getNumbers());
		});
		MenuItem switchBack = new MenuItem("Switch Back");
		switchBack.setOnAction(a -> {
			//secondBook = new TelefonBook(FileSystem.readEntriesFromFile());
			entryArea.setItems(tbook.getNumbers());
		});
		MenuItem importTo = new MenuItem("Import to First");	//can only import second into first Book
		importTo.setOnAction(a -> {
			if(secondBook != null) {
				Iterator<TelefonEntry> secondBookIterator = secondBook.getNumbers().iterator();
				while(secondBookIterator.hasNext()) {
					tbook.add(secondBookIterator.next());
				}
			}
		});
		file.getItems().addAll(switchBook, switchBack, importTo);
		mb.getMenus().add(file);
	
		root.setTop(mb);
		root.setBottom(searchArea.getPane());
		root.setRight(addDeleteArea.getPane());
		root.setCenter(entryArea.getPane());
		
		primaryStage.setTitle("Telefonbuch");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
	
	public void stop() {
		//FileSystem.writeFile(tbook.getNumbers());
	}

    public static void main(String[] args) {
        launch(args);
    }

}
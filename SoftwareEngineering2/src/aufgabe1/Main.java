package aufgabe1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	ObservableList<TelefonEntry> telefonEntries = FXCollections.observableArrayList(new ArrayList<>());
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		List<TelefonEntry> jsonEntries = FileSystem.readEntriesFromFile();
		Iterator<TelefonEntry> jsonIterator = jsonEntries.iterator();
		while(jsonIterator.hasNext()) {
			telefonEntries.add(jsonIterator.next());
		}
		
		SearchArea searchArea = new SearchArea();
		AddDeleteArea addDeleteArea = new AddDeleteArea();
		EntryArea entryArea = new EntryArea(telefonEntries);
		
		/*
		 * 	This is the implementation of what happens when you press a button
		 * 	Might be a good idea to put them in a different class
		 * 
		 * this is how you use the EventHandler on a button without lambda expressions
		 * example:
		 * 
		 * 	addDeleteArea.getAddButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					telefonEntries.add(new TelefonEntry(".", ".", "."));
				}
			});	 
		 */
		addDeleteArea.getAddButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			telefonEntries.add(new TelefonEntry(".", ".", "."));
		});
		
		addDeleteArea.getDeleteButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			TelefonEntry selection = entryArea.getSelectedEntry();
			if(telefonEntries.contains(selection)) {
				telefonEntries.remove(selection);
			}
			
			/*
			 * this approach works with lists and it works until the last element in telefonEntries
			 * at which point if you try to delete it it will throw a NoSuchElementException
			 * 
			List<TelefonEntry> list = entryArea.getSelectedEntries();
			Iterator<TelefonEntry> iterator = list.iterator();
			while(iterator.hasNext()) {
				TelefonEntry entry = iterator.next();
				if(telefonEntries.contains(entry)) {
					telefonEntries.remove(entry);
				}
			}
			*/
			
		});
		
		searchArea.getSearchButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			entryArea.clearSelection();
			String search = searchArea.getTextField().getText();
			String[] tokens = search.split("\\s+");
			Iterator<TelefonEntry> iterator = telefonEntries.iterator();
			List<TelefonEntry> selection = new ArrayList<>();
			while(iterator.hasNext()) {
				TelefonEntry entry = iterator.next();
				for(int i = 0; i < tokens.length; i++) {	//this marks all elements where either firstName, lastName or Number is mentioned in any of the tokens
					if(entry.getFirstName().equals(tokens[i]) || entry.getLastName().equals(tokens[i]) || entry.getNumber().equals(tokens[i])) {
						selection.add(entry);
					}
				}
				entryArea.selectEntries(selection);
				
				/*
				 * this approach tries to get an exact result
				 * but it uses hardcoded array querries to check
				 * whether or not the search input is in the right order
				 * 
				 * example: firstName lastName number would try to search 
				 * an element that fulfills all criteria
				 * 
				 * this marks all elements where 1 attribute is mentioned in the tokens
				if(tokens.length == 1) {
					if(entry.getFirstName().equals(tokens[0]) || entry.getLastName().equals(tokens[0]) || entry.getNumber().equals(tokens[0])) {
						selection.add(entry);
					}
					//this marks all elements where 2 attributes are mentioned in the tokens 
				} else if(tokens.length == 2) {
					if((entry.getFirstName().equals(tokens[0]) && entry.getLastName().equals(tokens[1])) 
							|| (entry.getFirstName().equals(tokens[0]) && entry.getNumber().equals(tokens[1])) 
							|| (entry.getLastName().equals(tokens[0]) && entry.getNumber().equals(tokens[1]))) {
						selection.add(entry);
					}
					//this marks all elements where all 3 attributes are mentioned in the tokens
				} else if(tokens.length == 3) {		
					if(entry.getFirstName().equals(tokens[0]) && entry.getLastName().equals(tokens[1]) && entry.getNumber().equals(tokens[2])) {
						selection.add(entry);
					}
				} else {
					break;
				}
				*/
				
			}
		});
		//Button Implementation ends here
		
		root.setTop(searchArea.getPane());
		root.setBottom(addDeleteArea.getPane());
		root.setCenter(entryArea.getPane());
		
		primaryStage.setTitle("Telefonbuch");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
	
	public void stop() {
		FileSystem.writeFile(telefonEntries);
	}

    public static void main(String[] args) {
        launch(args);
    }

}

package aufgabe1;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelefonBook { 
	private final ObservableList<TelefonNumber> obsTelefonNumbers = FXCollections.observableArrayList(new ArrayList<>());
	
	TelefonBook() {
		
	}
	
	public ObservableList<TelefonNumber> getNumbers() {
		return obsTelefonNumbers;
	}
	
	public List<TelefonNumber> search(String term) {
		return null;
	}
}

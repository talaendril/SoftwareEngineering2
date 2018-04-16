package aufgabe1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class TelefonBook { 
	private final ObservableList<TelefonEntry> obsTelefonEntries = FXCollections.observableArrayList(new ArrayList<>());
	private final FilteredList<TelefonEntry> filteredEntries = new FilteredList<>(obsTelefonEntries);
	
	TelefonBook() {
		List<TelefonEntry> jsonEntries = FileSystem.readEntriesFromFile();
		Iterator<TelefonEntry> jsonIterator = jsonEntries.iterator();
		while(jsonIterator.hasNext()) {
			this.add(jsonIterator.next());
		}
	}
	
	public void add(TelefonEntry entry) {
		if (entry != null) {
			obsTelefonEntries.add(entry);
		}
	}
	
	public ObservableList<TelefonEntry> getNumbers() {
		return obsTelefonEntries;
	}
	
	public ObservableList<TelefonEntry> getFilteredEntries() {
		return filteredEntries;
	}
	
	public void search(String search) {
		filteredEntries.setPredicate(entry -> {
            return (entry.getFirstName().contains(search)) || entry.getLastName().contains(search) || entry.getNumber().contains(search);
        });
    }
}
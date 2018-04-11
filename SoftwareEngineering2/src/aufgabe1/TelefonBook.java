package aufgabe1;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class TelefonBook { 
	private final ObservableList<TelefonEntry> obsTelefonEntrys = FXCollections.observableArrayList(new ArrayList<>());
	private final FilteredList<TelefonEntry> filteredEntries = new FilteredList<>(obsTelefonEntrys);
	
	TelefonBook() {
		
	}
	
	public void add(TelefonEntry entry) {
		if (entry != null) {
			obsTelefonEntrys.add(entry);
		}
	}
	
	public ObservableList<TelefonEntry> getNumbers() {
		return obsTelefonEntrys;
	}
	
	public ObservableList<TelefonEntry> getFilteredEntries() {
		return filteredEntries;
	}
	
	public void search(String search) {
        filteredEntries.setPredicate(telNumber -> {
            return (telNumber.getFirstName().contains(search)) || telNumber.getLastName().contains(search) || telNumber.getNumber().contains(search);
        });
    }
}

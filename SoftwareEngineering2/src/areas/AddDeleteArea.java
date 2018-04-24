package areas;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AddDeleteArea {
	private AnchorPane anchorPane = new AnchorPane();
	private Button deleteButton = new Button("Delete");
	private Button addButton = new Button("Add");
	
	public AddDeleteArea(Runnable onAdd, Runnable onDelete) {
		AnchorPane.setTopAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 10.0);
		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		
		AnchorPane.setTopAnchor(addButton, 10.0);
		AnchorPane.setLeftAnchor(addButton, 10.0);
		AnchorPane.setBottomAnchor(addButton, 10.0);
		
		addButton.setOnAction(event -> {
			onAdd.run();
		});
		
		deleteButton.setOnAction(event -> {
			onDelete.run();
		});
		
		anchorPane.getChildren().addAll(deleteButton, addButton);
	}
	
	public Button getAddButton() {
		return this.addButton;
	}
		
	public Button getDeleteButton() {
		return this.deleteButton;
	}
	
	public Node getPane() {
		return anchorPane;
	}
}

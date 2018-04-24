package windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ErrorWindow {
	
	public ErrorWindow(String errorText, Exception e) {
		BorderPane root = new BorderPane();
		AnchorPane anchorPane = new AnchorPane();
		Label errorLabel = new Label(errorText);
		errorLabel.setAlignment(Pos.CENTER);
		
		AnchorPane.setTopAnchor(errorLabel, 10.0);
		AnchorPane.setRightAnchor(errorLabel, 10.0);
		AnchorPane.setLeftAnchor(errorLabel, 10.0);
		
		Label stackTraceLabel = new Label(this.getCustonStackTrace(e));
		AnchorPane.setBottomAnchor(stackTraceLabel, 10.0);
		AnchorPane.setTopAnchor(stackTraceLabel, 50.0);
		AnchorPane.setRightAnchor(stackTraceLabel, 10.0);
		AnchorPane.setLeftAnchor(stackTraceLabel, 10.0);
		anchorPane.getChildren().addAll(errorLabel, stackTraceLabel);
		root.setCenter(anchorPane);
		
		Stage window = new Stage();
		window.setTitle("Error Window");
		window.setScene(new Scene(root, 400, 300));
		window.setAlwaysOnTop(true);
		window.show();
	}
	
	public String getCustonStackTrace(Exception e) {
		StringBuilder returned = new StringBuilder("");
		for(StackTraceElement ste : e.getStackTrace()) {
			String[] tokens = ste.toString().split("\\.");
			if(!(tokens[0].equals("com") || tokens[0].equals("javafx"))) {	//currently only for catching the jsonParsingException
				returned.append("\n");										//if needed one can always remove the if and return everything
				for(int i = 0; i < tokens.length; i++) {
					returned.append(tokens[i] + ".");
				}
			}
		}
		return returned.toString();
	}
}

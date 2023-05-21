package GraphicalUserInterface;

import java.io.IOException;

public class MainGUI {
	public static void main(String[] args) throws IOException {
		View view = new View();
		view.launch(View.class, args);
	}
}



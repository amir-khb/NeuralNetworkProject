package GraphicalUserInterface;

import java.io.IOException;

public class MainGUI {
	public static void main(String[] args) throws IOException {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(view, model);

		view.launch(View.class, args);
	}
}



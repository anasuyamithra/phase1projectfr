package code;

public class CodeRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WelcomeScreen welcomeScreen = new WelcomeScreen();
        UserInterface userInterface = new UserInterface();

        welcomeScreen.displayWelcomeScreen();
        userInterface.handleUserInput();
	}

}

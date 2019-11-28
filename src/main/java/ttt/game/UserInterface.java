package ttt.game;

public interface UserInterface {
    void displayBoard(Object[] boardState);

    void displayOutput(String message);

    String getAndValidateInput(String[] validInputs, String errorMessage);

    void clear();
}

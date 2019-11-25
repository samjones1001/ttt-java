package ttt.service;

public interface ClientService {
    void displayBoard(Object[] boardState);

    void displayOutput(String message);

    String getAndValidateInput(String[] validInputs, String errorMessage);

    void clear();
}

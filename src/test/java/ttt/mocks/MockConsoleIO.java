package ttt.mocks;

import ttt.service.IOService;

import java.util.ArrayList;

public class MockConsoleIO implements IOService {
    public String lastOutput;
    public int outputCallCount = 0;
    public int inputCallCount = 0;
    public int clearCallCount = 0;
    private ArrayList<String> inputs;

    public MockConsoleIO() {
        this.inputs = null;
    }

    public MockConsoleIO(ArrayList<String> inputs) {
        this.inputs = inputs;
    }

    @Override
    public void output(String message) {
        outputCallCount += 1;
        lastOutput = message;
    }

    @Override
    public String input() {
        inputCallCount += 1;
        return inputs.remove(0);
    }

    @Override
    public void clear() {
        clearCallCount += 1;
    }
}

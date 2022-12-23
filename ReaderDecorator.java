package SystemUsble.User;

import SystemUsble.Operation;

import java.io.File;

public class ReaderDecorator implements Operation {
    private Operation operation;

    public ReaderDecorator(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(File file) {

    }
}
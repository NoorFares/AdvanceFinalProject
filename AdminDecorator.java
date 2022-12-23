package SystemUsble.User;
import SystemUsble.Operation;
import java.io.File;
public class AdminDecorator implements Operation {
    private Operation operation;

    public AdminDecorator(Operation operation) {
        this.operation = operation;
    }



    @Override
    public void execute(File file) {

    }
}

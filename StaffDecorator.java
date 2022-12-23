package SystemUsble.User;

import SystemUsble.ExportOperation;
import SystemUsble.ImportOperation;
import SystemUsble.Operation;

import java.io.File;

public class StaffDecorator implements Operation {
    private Operation operation;

    public StaffDecorator(Operation operation) {
        this.operation = operation;
    }
@Override
    public void execute(File file) {
        if (operation instanceof ImportOperation || operation instanceof ExportOperation) {
            // Staff users are allowed to import and export, so execute the operation

        } else {
       //     throw new UnauthorizedOperationException();
        }
    }
}

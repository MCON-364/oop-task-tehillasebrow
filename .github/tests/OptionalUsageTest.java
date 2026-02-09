package edu.touro.las.mcon364.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionalUsageTest {

    @Test
    void missingTaskMustThrowExceptionNotReturnNull() {
        TaskRegistry registry = new TaskRegistry();

        // Create an UpdateTaskCommand for a non-existent task
        UpdateTaskCommand command = new UpdateTaskCommand(registry, "nonexistent", Priority.HIGH);

        // Executing the command should throw TaskNotFoundException
        Exception ex = assertThrows(
                TaskNotFoundException.class,
                command::execute,
                "Looking up a missing task must throw an exception, not return null"
        );

        assertNotNull(ex.getMessage(), "Exception should have a meaningful message");
        assertTrue(ex.getMessage().contains("nonexistent"),
                "Exception message should mention the task name");
    }
}

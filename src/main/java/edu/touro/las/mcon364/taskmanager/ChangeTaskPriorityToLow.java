package edu.touro.las.mcon364.taskmanager;

public record ChangeTaskPriorityToLow(TaskRegistry registry, String taskName) implements Command {

@Override
    public void execute() {
    Task updated= new Task(taskName, Priority.LOW);
        registry.add(updated);

    }
}

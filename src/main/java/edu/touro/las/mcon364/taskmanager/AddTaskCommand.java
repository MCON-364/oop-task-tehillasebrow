package edu.touro.las.mcon364.taskmanager;

public record AddTaskCommand(TaskRegistry registry, Task task) implements Command {


    @Override
    public void execute() {
        registry.add(task);
    }
}

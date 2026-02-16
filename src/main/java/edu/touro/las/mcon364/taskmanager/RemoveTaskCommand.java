package edu.touro.las.mcon364.taskmanager;

public record RemoveTaskCommand(TaskRegistry registry, String name) implements Command {

@Override
    public void execute() {
        registry.remove(name);
    }
}

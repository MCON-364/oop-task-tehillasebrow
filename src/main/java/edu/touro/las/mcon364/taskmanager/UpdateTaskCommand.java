package edu.touro.las.mcon364.taskmanager;

public record UpdateTaskCommand(TaskRegistry registry, String taskName, Priority newPriority) implements Command {



@Override
    public void execute() {
        // NOTE: This demonstrates old-style null checking
        // Students should refactor to use Optional and custom exceptions
        Task existing = registry.get(taskName).orElseThrow(() -> new TaskNotFoundException("Task "+ taskName+" not found"));

        // Create a new task with updated priority (tasks are immutable)
        Task updated = new Task(existing.name(), newPriority);
        registry.add(updated);  // This replaces the old task
    }
}

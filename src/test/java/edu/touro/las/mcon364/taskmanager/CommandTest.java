package edu.touro.las.mcon364.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Command implementations.
 * After sealing the Command interface and refactoring, these tests should still pass.
 */
class CommandTest {
    private TaskRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new TaskRegistry();
    }

    @Test
    @DisplayName("AddTaskCommand should add task to registry")
    void testAddTaskCommand() {
        Task task = new Task("New task", Priority.MEDIUM);
        Command command = new AddTaskCommand(registry, task);

        command.execute();

        assertNotNull(registry.get("New task"), "Task should be in registry after AddTaskCommand");
        assertEquals(Optional.of(task), registry.get("New task"), "Added task should match");
    }

    @Test
    @DisplayName("AddTaskCommand should replace existing task with same name")
    void testAddTaskCommandReplacement() {
        Task originalTask = new Task("Task", Priority.LOW);
        Task replacementTask = new Task("Task", Priority.HIGH);

        new AddTaskCommand(registry, originalTask).execute();
        new AddTaskCommand(registry, replacementTask).execute();

        assertEquals(Priority.HIGH, registry.get("Task").orElseThrow().priority(),
                "Replacement task should have new priority");
    }

    @Test
    @DisplayName("RemoveTaskCommand should remove task from registry")
    void testRemoveTaskCommand() {
        registry.add(new Task("To be removed", Priority.HIGH));

        Command command = new RemoveTaskCommand(registry, "To be removed");
        command.execute();

        assertEquals(registry.get("To be removed"), Optional.empty());
    }

    @Test
    @DisplayName("RemoveTaskCommand on non-existent task should not throw")
    void testRemoveTaskCommandNonExistent() {
        Command command = new RemoveTaskCommand(registry, "Non-existent");

        assertDoesNotThrow(command::execute,
                "Removing non-existent task should not throw exception");
    }

    @Test
    @DisplayName("UpdateTaskCommand should update existing task priority")
    void testUpdateTaskCommand() {
        registry.add(new Task("Update me", Priority.LOW));

        Command command = new UpdateTaskCommand(registry, "Update me", Priority.HIGH);
        command.execute();

        Task updated = registry.get("Update me").orElseThrow();
        assertNotNull(updated, "Task should still exist after update");
        assertEquals(Priority.HIGH, updated.priority(), "Priority should be updated to HIGH");
    }

    @Test
    @DisplayName("UpdateTaskCommand should preserve task name")
    void testUpdateTaskCommandPreservesName() {
        registry.add(new Task("Important task", Priority.MEDIUM));

        Command command = new UpdateTaskCommand(registry, "Important task", Priority.LOW);
        command.execute();

       Task updated = registry.get("Important task").orElseThrow();
        assertEquals("Important task", updated.name(), "Task name should be preserved");
    }



    @Test
    @DisplayName("UpdateTaskCommand should allow changing priority from HIGH to LOW")
    void testUpdateTaskCommandPriorityDecrease() {
        registry.add(new Task("Flexible", Priority.HIGH));

        new UpdateTaskCommand(registry, "Flexible", Priority.LOW).execute();

        assertEquals(Priority.LOW, registry.get("Flexible").orElseThrow().priority(),
                "Should allow decreasing priority");
    }

    @Test
    @DisplayName("UpdateTaskCommand should allow changing priority from LOW to HIGH")
    void testUpdateTaskCommandPriorityIncrease() {
        registry.add(new Task("Urgent", Priority.LOW));

        new UpdateTaskCommand(registry, "Urgent", Priority.HIGH).execute();

        assertEquals(Priority.HIGH, registry.get("Urgent").orElseThrow().priority(),
                "Should allow increasing priority");
    }
}


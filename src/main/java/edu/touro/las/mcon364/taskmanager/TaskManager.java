package edu.touro.las.mcon364.taskmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskManager {

    public TaskManager(TaskRegistry registry) {
           }

    // TODO: Students must refactor this using pattern-matching switch
    // Current implementation uses old-style instanceof checks
    public void run(Command command) {
        switch (command){
            case AddTaskCommand c->
            command.execute();
            case  RemoveTaskCommand c->
            command.execute();
       case UpdateTaskCommand c->
            command.execute();
            default ->
            throw new CommandNotFoundException(command);
        }
    }

}

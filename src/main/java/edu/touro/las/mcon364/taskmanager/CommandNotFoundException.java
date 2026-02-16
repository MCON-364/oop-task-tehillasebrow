package edu.touro.las.mcon364.taskmanager;

public class CommandNotFoundException extends RuntimeException{
    public CommandNotFoundException(Command c){
    super("Task not found:"+ c.toString());
}}

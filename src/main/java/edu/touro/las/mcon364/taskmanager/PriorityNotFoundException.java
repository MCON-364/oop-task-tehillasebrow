package edu.touro.las.mcon364.taskmanager;

public class PriorityNotFoundException extends RuntimeException{
        public PriorityNotFoundException(Priority p){
            super("Priority not found:"+ p.toString());
        }

}

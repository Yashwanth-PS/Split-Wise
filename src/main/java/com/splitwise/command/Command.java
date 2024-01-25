package com.splitwise.command;

public interface Command {
    boolean canExecute(String input);
    void execute(String input);
}
package ru.darek;

public class AppArrayDataException extends RuntimeException{
    int line;
    int column;

    public AppArrayDataException(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public AppArrayDataException(String message, int line, int column) {
        super(message);
        this.line = line;
        this.column = column;
    }

    public AppArrayDataException(String message, Throwable cause, int line, int column) {
        super(message, cause);
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "AppArrayDataException{" + getMessage() + " строка=" + (line+1) + ", колонка=" + (column+1) + '}';
    }
}

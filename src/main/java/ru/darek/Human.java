package ru.darek;

public class Human {
    public final String name;
    private Transport currentTransport;

    public Human(String name, Transport currentTransport) {
        this.name = name;
        this.currentTransport = currentTransport;
    }

    public Human(String name) {
        this(name, new Walk());
    }

    public void setCurrentTransport(Transport currentTransport) {
        System.out.println("Человек " + name + " оседлал " + currentTransport.toString());
        this.currentTransport = currentTransport;
    }

    public void setCurrentTransport() {
        System.out.println("Человек " + name + " покинул " + currentTransport.toString());
        this.currentTransport = new Walk();
    }

    public boolean moveOnDistance(Terrain terrain, int distance) {
        if (currentTransport.move(terrain, distance)) {
            System.out.println("Человек " + name + " успешно преодолел дистанцию " + distance + " в ланшавте " + terrain + " используя " + currentTransport.toString());
            return true;
        }
        System.out.println("Человек " + name + " не смог преодолеть дистанцию " + distance + " в ланшавте " + terrain + " используя " + currentTransport.toString());
        return false;
    }
}

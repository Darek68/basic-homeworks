package ru.darek;

public class Start {
    public static void main(String[] args) {
        Human human=new Human("Вася");
        human.moveOnDistance(Terrain.PLAIN,25);
        human.setCurrentTransport(new Horse());
        human.moveOnDistance(Terrain.PLAIN,25);
        human.moveOnDistance(Terrain.PLAIN,50);
        human.setCurrentTransport(new Auto());
        human.moveOnDistance(Terrain.PLAIN,50);
        human.moveOnDistance(Terrain.SWAMP,10);
        human.setCurrentTransport(new Vehicle());
        human.moveOnDistance(Terrain.SWAMP,10);
        human.setCurrentTransport(new Bicycle());
        human.moveOnDistance(Terrain.FOREST,50);
    }
}

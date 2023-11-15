package ru.darek;

public class Bicycle implements Transport{
    @Override
    public String toString() {
        return "Bicycle";
    }
    @Override
    public boolean move(Terrain terrain,int distance) {
        if (terrain==Terrain.SWAMP){
            System.out.println("В ландшафте " + terrain.toString() + " велосипед не может быть использован!");
            return false;
        }
        if (distance>80){
            return false;
        }
        return true;
    }
}

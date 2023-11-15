package ru.darek;

public class Auto implements Transport{
    @Override
    public String toString() {
        return "Auto";
    }
    @Override
    public boolean move(Terrain terrain,int distance) {
        if (terrain==Terrain.FOREST||terrain==Terrain.SWAMP){
            System.out.println("В ландшафте " + terrain.toString() + " машина не может быть использована!");
            return false;
        }
        if (distance>500){
            return false;
        }
        return true;
    }
}

package ru.darek;

public class Horse implements Transport{
    @Override
    public String toString() {
        return "Horse";
    }
    @Override
    public boolean move(Terrain terrain,int distance) {
        if (terrain==Terrain.SWAMP){
            System.out.println("В ландшафте " + terrain.toString() + " лошадь не может быть использована!");
            return false;
        }
        if (distance>40){
            return false;
        }
        return true;
    }
}

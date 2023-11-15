package ru.darek;

public class Walk implements Transport{
    @Override
    public String toString() {
        return "Walk";
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        if (terrain==Terrain.SWAMP){
            System.out.println("Человек не пойдет в Болото!!");
            return false;
        }
        if (distance>20){
            return false;
        }
        return true;
    }
}

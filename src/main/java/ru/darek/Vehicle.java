package ru.darek;

public class Vehicle implements Transport{
    @Override
    public String toString() {
        return "Vehicle";
    }
    @Override
    public boolean move(Terrain terrain,int distance) {
        if (distance>50){
            return false;
        }
        return true;
    }
}

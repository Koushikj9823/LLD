package com.koushik.parkmanager.model;

public enum ParkingSpotType {
    SMALL{
        public double getPrice(long duration){
            return duration*0.05;
        }
    },
    COMPACT{
        public double getPrice(long duration){
            return duration*0.075;
        }
    },
    LARGE{
        public double getPrice(long duration){
            return duration*0.10;
        }
    };

    public abstract double getPrice(long duration);
}

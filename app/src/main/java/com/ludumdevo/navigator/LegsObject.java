package com.ludumdevo.navigator;

import java.util.List;
public class LegsObject {
    private List<StepsObject> steps;
    private distanceObject distance;
    private durationObject duration;
    public LegsObject(List<StepsObject> steps, distanceObject distance) {
        this.steps = steps;
        this.distance = distance;
        this.duration = duration;
    }
    public List<StepsObject> getSteps() {
        return steps;
    }
    public distanceObject getDistance() {
        return distance;
    }
    public durationObject getDuration() {
        return duration;
    }
}
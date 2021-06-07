package com.khairetdinova.bashkiria.models;

public class Place {
    private int id;
    private String type;
    private String name;
    private int difficultyTheRoute;
    private int distanceFromUfa;
    private String coordinatesX;
    private String coordinatesY;
    private String images;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficultyTheRoute() {
        return difficultyTheRoute;
    }

    public void setDifficultyTheRoute(int difficultyTheRoute) {
        this.difficultyTheRoute = difficultyTheRoute;
    }

    public int getDistanceFromUfa() {
        return distanceFromUfa;
    }

    public void setDistanceFromUfa(int distanceFromUfa) {
        this.distanceFromUfa = distanceFromUfa;
    }

    public String getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(String coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public String getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(String coordinatesY) {
        this.coordinatesY = coordinatesY;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

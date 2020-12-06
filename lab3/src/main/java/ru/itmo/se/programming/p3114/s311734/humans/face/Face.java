package ru.itmo.se.programming.p3114.s311734.humans.face;

public class Face {
    private int weight = -1; //в см
    private Cheek leftCheek;
    private Cheek rightCheek;
    private Eye leftEye;
    private Eye rightEye;

    public Face() {
    }


    public Face(int weight, Cheek cheek, Eye eye) {
        this.weight = weight;
        leftCheek = cheek;
        rightCheek = cheek;
        leftEye = eye;
        rightEye = eye;
    }

    public Face(int weight, Cheek leftCheek, Cheek rightCheek, Eye leftEye, Eye rightEye) {
        this.weight = weight;
        this.leftCheek = leftCheek;
        this.rightCheek = rightCheek;
        this.leftEye = leftEye;
        this.rightEye = rightEye;
    }

}

package com.example.sprinter.user_story;

public enum PriorityEnum {
    HIGH(3), MEDIUM(2), LOW(1);

    private int priorityVal;

    PriorityEnum(int priorityVal) {
        this.priorityVal = priorityVal;
    }

    public int getPriorityVal() {
        return priorityVal;
    }
}

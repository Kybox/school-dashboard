package fr.kybox.school.model;

public enum RoleEnum {

    STUDENT("student"), TEACHER("teacher");

    private final String text;

    RoleEnum(String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}

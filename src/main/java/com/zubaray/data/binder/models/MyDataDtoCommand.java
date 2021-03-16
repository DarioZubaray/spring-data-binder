package com.zubaray.data.binder.models;

public abstract class MyDataDtoCommand {

    public abstract void setFirstName(String firstName);
    public abstract void setLastName(String lastName);

    public void setFirst_name(String firstName) {
        setFirstName(firstName);
    }

    public void setLast_name(String lastName) {
        setLastName(lastName);
    }
}

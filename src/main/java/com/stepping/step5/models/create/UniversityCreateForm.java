package com.stepping.step5.models.create;

import org.hibernate.validator.constraints.NotEmpty;

public class UniversityCreateForm {

    @NotEmpty
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

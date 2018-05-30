package com.iaspp.prepareyourself.genre;

import com.iaspp.prepareyourself.interfaces.IDTO;

public class GenreDTO implements IDTO {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + " : " + name;
    }
}

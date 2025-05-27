package org.example.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandBaseDTO {
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }
}

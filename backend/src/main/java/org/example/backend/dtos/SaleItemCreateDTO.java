package org.example.backend.dtos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaleItemCreateDTO {
    private Integer id;
    private String model;
    private BrandBaseDTO brand;
    private String description;
    private Integer price;
    private Integer ramGb;
    private BigDecimal screenSizeInch;
    private Integer storageGb;
    private String color;
    private Integer quantity;


    public void setId(Integer id) {
        this.id =  null;
    }

    public void setModel(String model) {
        this.model = model != null ? model.trim() : null;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : null;
    }

    public void setColor(String color) {
        String colorValue = (color != null && !color.trim().isEmpty()) ? color.trim() : null;
        this.color = colorValue;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = (quantity != null && quantity >= 0) ? quantity : 1;
    }
}


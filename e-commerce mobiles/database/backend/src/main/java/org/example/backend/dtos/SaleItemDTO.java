package org.example.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.entities.BrandBase;
import org.example.backend.entities.SaleItemBase;

@Getter
@Setter
public class SaleItemDTO {
    private Integer id;
    private String model;
    private String brandName;
    private Integer price;
    private Integer ramGb;
    private Integer storageGb;
    private String color;
}

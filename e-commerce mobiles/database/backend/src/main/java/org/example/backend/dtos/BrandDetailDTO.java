package org.example.backend.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BrandDetailDTO {
    private Integer id;
    private String name;
    private String websiteUrl;
    private String countryOfOrigin;
    private Boolean isActive;
    private Integer noOfSaleItems;
    private Instant createdOn;
    private Instant updatedOn;

    public void setNoOfSaleItems(Integer noOfSaleItems) {
        this.noOfSaleItems = (noOfSaleItems != null && noOfSaleItems > 0) ? noOfSaleItems : 0;
    }
}



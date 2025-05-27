package org.example.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PageRequestDTO {
    private ArrayList<String> filterBrands;
    private Integer page;
    private Integer size;
    private String sortField;
    private String sortDirection;

    public ArrayList<String> getFilterBrands() {
        return filterBrands != null ? filterBrands : new ArrayList<>();
    }

    public Integer getPage() {
        return page != null && page >= 0 ? page : 0;
    }

    public Integer getSize() {
        return size != null && size > 0 ? size : 10;
    }

    public String getSortField() {
        return (sortField != null && !sortField.trim().isEmpty()) ? sortField : "createdOn";
    }

    public String getSortDirection() {
        if (sortDirection == null) return "ASC";
        String direction = sortDirection.trim().toUpperCase();
        return (direction.equals("ASC") || direction.equals("DESC")) ? direction : "ASC";
    }

    public void setFilterBrands(ArrayList<String> filterBrands) {
        this.filterBrands = filterBrands;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}

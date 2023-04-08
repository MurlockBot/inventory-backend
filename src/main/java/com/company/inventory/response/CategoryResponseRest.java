package com.company.inventory.response;

import com.company.inventory.model.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseRest extends ResponseRest{

    private CategoryResponse categoryResponse = new CategoryResponse();
}

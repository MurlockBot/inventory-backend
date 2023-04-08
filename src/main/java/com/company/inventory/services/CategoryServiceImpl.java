package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDAO;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryDAO categoryDAO;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();

        try{
            List<Category> categories = (List<Category>) categoryDAO.findAll();

            response.getCategoryResponse().setCategory(categories);
            response.setMetadata("OK", "0", "Respuesta exitosa");
        } catch (Exception e) {

            response.setMetadata("NO", "-1","Error al consultar servicio en la base de datos");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}

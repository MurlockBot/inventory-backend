package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDAO;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> lista =  new ArrayList<>();

        try{
            Optional<Category> category = categoryDAO.findById(id);

            if(category.isPresent()){
                lista.add(category.get());
                response.getCategoryResponse().setCategory(lista);
                response.setMetadata("OK", "0", "Respuesta exitosa");
            } else{
                response.setMetadata("NO", "-1","No se encontró la categoría con el ID suministrado.");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            response.setMetadata("OK", "0", "Respuesta exitosa");
        } catch (Exception e) {

            response.setMetadata("NO", "-1","Error al consultar servicio en la base de datos");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}

package com.example.demo.web;

import com.example.demo.repositories.ProductRepository;
import lombok.Data;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController{
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }
    @GetMapping("/suppliers")
    public String suppliers(Model model){
        System.out.println("TATSINDA NDONGUE");
        //on faire la communication entre les deux microservice en envoyant aussi le token directement
        //PagedModel<Supplier> pageSuppliers=keycloakRestTemplate.getForObject("http://localhost:8083/suppliers", PagedModel.class);
        ResponseEntity<PagedModel<Supplier>> response=keycloakRestTemplate.exchange("http://localhost:8083/suppliers",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedModel<Supplier>>() {});
        model.addAttribute("suppliers",response.getBody().getContent());
        return "suppliers";
    }


   /* @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e,Model model)
    {
        model.addAttribute("errorMessage","Probleme d'autorisation");
        return "errors";
    }*/

}
@Data
class Supplier{
    private Long id;
    private String name;
    private String email;
}

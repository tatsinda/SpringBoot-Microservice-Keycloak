package com.example.demo.web;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @Autowired
    private AdapterDeploymentContext adapterDeploymentContext;
    //methode pour le logout, afin de deconnecter l'utilisateur a son compte
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException
    {
        request.logout();//pour fermer une session
        return "redirevt:/";
    }

    //method permettante de changer le password d'un compte user
    @GetMapping("/changePassword")
    public String cpw(RedirectAttributes attributes, HttpServletRequest request,
                      HttpServletResponse response) throws ServletException{

        HttpFacade facade=new SimpleHttpFacade(request,response);
        KeycloakDeployment deployment=adapterDeploymentContext.resolveDeployment(facade);
        attributes.addAttribute("referrer",deployment.getResourceName());
        return "redirect:"+deployment.getAccountUrl()+ "/password";//getAccountUrl() permet de nous fournir le lien permettant de modifier les infos user et ensuite on precise que c'est uniquement le password qu'on souhaite modifier

    }


}

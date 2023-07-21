package it.unipa.community.sferra.sferraproject.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorTypesController implements ErrorController{
 
    @GetMapping("/not-allowed")
    public String accessDenied(){
        return "/errors/not-allowed";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        String page = "/errors/generic";
         
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         
        if (status != null) {
            Integer statusCode = Integer.parseInt(status.toString());
             
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                page = "errors/not-found";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                page = "errors/internal-server-error";        
            }
        }
         
        return page;
    }

}

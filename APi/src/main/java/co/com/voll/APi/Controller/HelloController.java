package co.com.voll.APi.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hello")
public class HelloController {
    @GetMapping
    public String HelloWorld(){
        return "Hola mundooo a la casita del terror";
    }
}

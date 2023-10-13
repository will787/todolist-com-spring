package br.com.williamvieira.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Modificadores
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /*
     * String  (texto) 
     * Integer (int)
     * Double (double) - 0.0000
     * Float (float) - 0.000
     * Char (A)
     * Data (data)
     * void
     */
    /*
     * Body
     */
    
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
                System.out.println(userModel.getUsername());
    }
    
}

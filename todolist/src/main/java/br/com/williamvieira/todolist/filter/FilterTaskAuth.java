package br.com.williamvieira.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.williamvieira.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //clase genérica de gerenciamento essa classe.
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servlerPath = request.getServletPath();

                if(servlerPath.equals("/tasks/")){
                                    //pegar a autenticação que é o usuário e senha
                var authorization = request.getHeader("Authorization");
                var  authEncoded = authorization.substring("Basic".length()).trim();
                
                byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
                var  authString = new String (authDecoded);
                System.out.println("Authorization");
                System.out.println(authString);

                //[usuario, senha]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
                System.out.println(username);
                System.out.println(password);
                
                //depois validar o usuario se existe.
                var user = this.userRepository.findByUsername(username);
                if(user == null){
                    response.sendError(401);
                }else {
                    // validar senha
                    var  passowordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());   
                    if(passowordVerify.verified){
                        //segue viagem        
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendError(401);
                    }
                    //segue viagem        
                }
                } else{
                    filterChain.doFilter(request, response);
                }
        
    }

}

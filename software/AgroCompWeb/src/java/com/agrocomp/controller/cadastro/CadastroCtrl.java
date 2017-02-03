package com.agrocomp.controller.cadastro;

import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.service.ClienteService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroCtrl {

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroPost(String nome, String email, String senha, HttpSession session) throws Exception {

        ModelAndView mv = null;
        Cliente us = new Cliente();
        ClienteService uService = new ClienteService();
        Map<String, Object> fields = new HashMap<>();
        fields.put("nome", nome);
        fields.put("email", email);
        fields.put("senha", senha);
        Map<String, String> errors = uService.validate(fields);
        if (errors.isEmpty()) {
            us.setNome(nome);
            us.setEmail(email);
            us.setSenhaAsPlainText(senha);
            uService.create(us);
            session.setAttribute("usuarioLogin", us);
            mv = new ModelAndView("perfil/home");
        } else {
            mv = new ModelAndView("boasVindas");
            mv.addObject("errors", errors);

        }

        return mv;
    }

}

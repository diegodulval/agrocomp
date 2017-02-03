package com.agrocomp.controller.perfil;

import com.agrocomp.model.criteria.AnuncioCriteria;
import com.agrocomp.model.entity.Anuncio;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.service.AnuncioService;
import com.agrocomp.model.service.ClienteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerfilCtrl {

    @RequestMapping(value = "/meuperfil", method = RequestMethod.GET)
    public ModelAndView getPerfil(HttpSession session, Long limit, Long offset) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/home");
        if (session.getAttribute("usuarioLogin") != null) {
            mv = new ModelAndView("perfil/home");
            if (limit != null && offset != null) {
                Cliente usuario = (Cliente) session.getAttribute("usuarioLogin");
                List<Anuncio> anuncioList = null;
                AnuncioService as = new AnuncioService();
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(AnuncioCriteria.CLIENTE_FK, usuario.getId());
                Long count = as.countByCriteria(criteria);
                anuncioList = as.readByCriteria(criteria, limit, offset);
                mv.addObject("anuncioList", anuncioList);
                mv.addObject("limit", limit);
                mv.addObject("offset", offset);
                mv.addObject("count", count);
            } else {
                String url = "redirect:/meuperfil?limit=3&offset=0";
                mv = new ModelAndView(url);
            }

        }
        return mv;
    }

    @RequestMapping(value = "/meuperfil", method = RequestMethod.POST)
    public ModelAndView postPerfil(HttpSession session, String nome, String estado, String cidade, String telefone, String sobre) throws Exception {

        Cliente cliente = (Cliente) session.getAttribute("usuarioLogin");
        cliente.setNome(nome);
        cliente.setSobre(sobre);
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setTelefone(telefone);
        ClienteService cs = new ClienteService();
        cs.updateUser(cliente);
        cs.update(cliente);
        ModelAndView mv = new ModelAndView("redirect:/home");
        return mv;
    }
    
    @RequestMapping(value = "/novasenha", method = RequestMethod.POST)
    public ModelAndView postPerfilAlterarSenha(HttpSession session,String senha,String senha2) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/home");
        Cliente cliente= (Cliente) session.getAttribute("usuarioLogin");
        ClienteService cs= new ClienteService();
        if(senha.equals(senha2)){
            cliente.setSenha(senha);
            cs.updateUser(cliente);
        }
        return mv;
    }
    

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/home");
        session.removeAttribute("usuarioLogin");
        return mv;
    }

}

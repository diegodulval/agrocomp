package com.agrocomp.controller;

import com.agrocomp.model.criteria.DiscussaoCriteria;
import com.agrocomp.model.criteria.MensagemCriteria;
import com.agrocomp.model.entity.Administrador;
import com.agrocomp.model.entity.Categoria;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Discussao;
import com.agrocomp.model.entity.Noticia;
import com.agrocomp.model.entity.Usuario;
import com.agrocomp.model.service.CategoriaService;
import com.agrocomp.model.service.ClienteService;
import com.agrocomp.model.service.DiscussaoService;
import com.agrocomp.model.service.MensagemService;
import com.agrocomp.model.service.NoticiaService;
import com.agrocomp.model.service.UsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexCtrl {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome(HttpSession session, Long limit, Long offset) throws Exception {
        ModelAndView mv = null;
        if (limit != null && offset != null) {
            mv = new ModelAndView("boasVindas");
            CategoriaService cs = new CategoriaService();
            NoticiaService ns = new NoticiaService();
            Map<Long, Object> criteria = new HashMap<>();
            List<Noticia> noticiaList = ns.readByCriteria(criteria, limit, offset);
            Long count = ns.countByCriteria(criteria);
            mv.addObject("noticiaList", noticiaList);
            List<Categoria> categoriaList = cs.readByCriteria(criteria, 0L, 0L);
            session.setAttribute("categoriaList", categoriaList);
            mv.addObject("count", count);
            mv.addObject("limit", limit);
            mv.addObject("offset", offset);
            if (session.getAttribute("usuarioLogin") != null) {
                if (session.getAttribute("usuarioLogin") instanceof Administrador) {
                    mv = new ModelAndView("administrador/home");
                } else {
                    mv = new ModelAndView("redirect:/meuperfil");
                }
            }
        } else {
            String url = "redirect:/home?limit=4&offset=0";
            mv = new ModelAndView(url);
        }

        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView verificadorLogar(String email, String senha, HttpSession session) throws Exception {
        Usuario us;
        ModelAndView mv = null;
        UsuarioService uService = new UsuarioService();
        ClienteService cs = new ClienteService();
        us = uService.login(email, senha);
        if (us != null) {

            if (us instanceof Administrador) {
                mv = new ModelAndView("administrador/home");

            } else {
                mv = new ModelAndView("redirect:/meuperfil");
                us = cs.readById(us.getId());

            }
            session.setAttribute("usuarioLogin", us);
        } else {
            mv = new ModelAndView("boasVindas");
            mv.addObject("loginFailed", "Email ou senha incorreto");
        }

        return mv;
    }

    @RequestMapping(value = "/sobre", method = RequestMethod.GET)
    public ModelAndView getSobre() {
        ModelAndView mv = new ModelAndView("administrador/sobre/sobre");

        return mv;
    }

    @RequestMapping(value = "/forum", method = RequestMethod.GET)
    public ModelAndView getForum(HttpSession session, String pesquisaDiscussao) throws Exception {
        ModelAndView mv = new ModelAndView("forum/forum");
        DiscussaoService ds = new DiscussaoService();
        MensagemService ms = new MensagemService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Discussao> discussaoList = ds.readByCriteria(criteria, null, null);
        this.setNumeroMensagem(discussaoList);
        if (pesquisaDiscussao != null && !pesquisaDiscussao.trim().isEmpty()) {
            criteria.put(DiscussaoCriteria.DISCUSSAO_ILIKE, pesquisaDiscussao);
            discussaoList = ds.readByCriteria(criteria, null, null);
            if (!discussaoList.isEmpty()) {
                this.setNumeroMensagem(discussaoList);
            }
        }
        mv.addObject("discussaoList", discussaoList);
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogin");
        if (cliente != null) {
            criteria.put(DiscussaoCriteria.CLIENTE_FK, cliente.getId());
            List<Discussao> minhaDiscussaoList = ds.readByCriteria(criteria, null, null);
            mv.addObject("minhaDiscussaoList", minhaDiscussaoList);
        }
        return mv;
    }

    private void setNumeroMensagem(List<Discussao> discussaoList) throws Exception {
        MensagemService ms = new MensagemService();
        Map<Long, Object> criteria = new HashMap<>();
        for (Discussao aux : discussaoList) {
            criteria.put(MensagemCriteria.DISCUSSAO_FK, aux.getId());
            Long num = ms.countByCriteria(criteria);
            aux.setNumMensagem(num);
        }
    }
}

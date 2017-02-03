package com.agrocomp.controller.forum;

import com.agrocomp.model.criteria.DiscussaoCriteria;
import com.agrocomp.model.criteria.MensagemCriteria;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Discussao;
import com.agrocomp.model.entity.Mensagem;
import com.agrocomp.model.service.ClienteService;
import com.agrocomp.model.service.DiscussaoService;
import com.agrocomp.model.service.MensagemService;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForumCtrl {

    @RequestMapping(value = "/forum/discussao/{id}", method = RequestMethod.GET)
    public ModelAndView discussao(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("forum/discussao");
        DiscussaoService ds = new DiscussaoService();
        MensagemService ms = new MensagemService();
        ClienteService cs = new ClienteService();
        Discussao discussao = ds.readById(id);
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(MensagemCriteria.DISCUSSAO_FK, id);
        List<Mensagem> mensagemList = ms.readByCriteria(criteria, null, null);
        Cliente cliente = cs.readById(discussao.getCliente().getId());
        discussao.setCliente(cliente);
        mv.addObject("discussao", discussao);
        mv.addObject("mensagemList", mensagemList);
        return mv;
    }

    @RequestMapping(value = "/forum", method = RequestMethod.POST)
    public ModelAndView postForum(HttpSession session, String titulo, String pergunta) throws Exception {
        ModelAndView mv = new ModelAndView("forum/forum");
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogin");
        DiscussaoService ds = new DiscussaoService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Discussao> discussaoList = ds.readByCriteria(criteria, null, null);
        mv.addObject("discussaoList", discussaoList);
        if (cliente != null) {
            Discussao dc = new Discussao();
            Map<String, Object> fields = new HashMap<>();
            fields.put("titulo", titulo);
            fields.put("pergunta", pergunta);
            Map<String, String> errors = ds.validate(fields);
            if (errors.isEmpty()) {
                dc.setCliente(cliente);
                dc.setPergunta(pergunta);
                dc.setTitulo(titulo);
                dc.setDataHora(new Timestamp(System.currentTimeMillis()));
                ds.create(dc);
                mv = new ModelAndView("redirect:/forum");
            } else {
                criteria.put(DiscussaoCriteria.CLIENTE_FK, cliente.getId());
                List<Discussao> minhaDiscussaoList = ds.readByCriteria(criteria, null, null);
                mv.addObject("minhaDiscussaoList", minhaDiscussaoList);
                mv.addObject("errors", errors);
                mv.addObject("titulo", titulo);
                mv.addObject("pergunta", pergunta);
            }
        }

        return mv;
    }

    @RequestMapping(value = "/forum/discussao/{id}", method = RequestMethod.POST)
    public ModelAndView discussaoPost(HttpSession session, String resposta, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/forum/discussao/" + id);
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogin");
        if (cliente != null) {
            MensagemService ms = new MensagemService();
            Map<String, Object> fields = new HashMap<>();
            fields.put("resposta", resposta);
            Map<String, String> errors = ms.validate(fields);
            if (errors.isEmpty()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setMensagem(resposta);
                mensagem.setCliente(cliente);
                Discussao discussao = new Discussao();
                discussao.setId(id);
                mensagem.setDiscussao(discussao);
                mensagem.setDataHora(new Timestamp(System.currentTimeMillis()));
                ms.create(mensagem);
            } else {
                mv.addObject("errors", errors);
            }

        } else {
            
        }
        return mv;
    }
}

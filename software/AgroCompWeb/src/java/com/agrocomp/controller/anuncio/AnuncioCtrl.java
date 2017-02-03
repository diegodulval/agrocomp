package com.agrocomp.controller.anuncio;

import com.agrocomp.model.criteria.AnuncioCriteria;
import com.agrocomp.model.entity.Anuncio;
import com.agrocomp.model.entity.Categoria;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.MensagemAnuncio;
import com.agrocomp.model.entity.Reporte;
import com.agrocomp.model.entity.Usuario;
import com.agrocomp.model.service.AnuncioService;
import com.agrocomp.model.service.CategoriaService;
import com.agrocomp.model.service.ClienteService;
import com.agrocomp.model.service.MensagemAnuncioService;
import com.agrocomp.model.service.ReporteService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnuncioCtrl {

    @RequestMapping(value = "/classificados", method = RequestMethod.GET)
    public ModelAndView getClassificados(String nome, Long limit, Long offset, BigDecimal maior, BigDecimal menor, String categoria,Long id) throws Exception {
        ModelAndView mv;
        if (limit != null && offset != null) {
            Map<Long, Object> criteria = new HashMap<>();
            if (nome != null && !nome.isEmpty()) {
                criteria.put(AnuncioCriteria.NOME_ILIKE, nome);
            }
            if (maior != null) {
                criteria.put(AnuncioCriteria.MAIOR, maior);

            }
            if (menor != null) {
                criteria.put(AnuncioCriteria.MENOR, menor);
            }
            if (categoria != null && id!=null & id>0) {
                criteria.put(AnuncioCriteria.CATEGORIA_EQ, id);
            }
            AnuncioService as = new AnuncioService();
            Long count = as.countByCriteria(criteria);
            List<Anuncio> anuncioList = as.readByCriteria(criteria, limit, offset);
            mv = new ModelAndView("classificado/produtos");
            mv.addObject("anuncioList", anuncioList);
            mv.addObject("limit", limit);
            mv.addObject("offset", offset);
            mv.addObject("maior", maior);
            mv.addObject("menor", menor);
            mv.addObject("count", count);
            mv.addObject("produtoBusca", nome);

        } else {
            String url = "redirect:/classificados?limit=12&offset=0";
            
            if (nome != null && !nome.isEmpty()) {
                url += "&nome=" + nome;
            }

            if (maior != null && maior.doubleValue() >= 0) {
                url += "&menor=" + menor;
            }
            if (menor != null && menor.doubleValue() >= 0) {
                url += "&maior=" + maior;
            }
            
            
            if (categoria != null && !categoria.isEmpty() && id!=null && id>0) {
                url += "&categoria=" + categoria+"&id="+id;
            }
            mv = new ModelAndView(url);
        }

        return mv;
    }

    @RequestMapping(value = "/meuperfil/anuncios/novo", method = RequestMethod.POST)
    public ModelAndView postAnunciosNovo(HttpSession session, String nome, Integer qtd, BigDecimal preco, String descricao, Long id_categoria, MultipartFile foto) throws Exception {
        Cliente cliente =  (Cliente) session.getAttribute("usuarioLogin");
        ModelAndView mv  = new ModelAndView("perfil/anuncioForm");
        Anuncio a = new Anuncio();
        a.setNome(nome);
        a.setQtd(qtd);
        a.setPreco(preco);
        a.setDescricao(descricao);
        a.setFoto(foto.getBytes());
        Categoria cat = new Categoria();
        cat.setId(id_categoria);
        a.setCategoria(cat);
        a.setCliente(cliente);
        Map<String, Object> fields = new HashMap<>();
        fields.put("anuncio", a);
        AnuncioService as = new AnuncioService();
        Map<String, String> errors = as.validate(fields);
        if (errors.isEmpty()) {
            as.create(a);
            mv = new ModelAndView("redirect:/meuperfil");
        } else {
            CategoriaService cs = new CategoriaService();
            List<Categoria> categoriaList = cs.readByCriteria(null, 0L, 0L);
            mv.addObject("categoriaList", categoriaList);
            mv.addObject("anuncio", a);
            mv.addObject("errors", errors);
            mv.addObject("operacao", "Criar");
        }

        return mv;
    }

    @RequestMapping(value = "/anuncio/{id}/img.jpg", method = RequestMethod.GET)
    public void getAnunciosNovoImg(HttpServletResponse response, @PathVariable Long id) throws Exception {
        AnuncioService as = new AnuncioService();
        Anuncio a = as.readById(id);
        response.setContentType("image/jpg");
        response.getOutputStream().write(a.getFoto());
        response.flushBuffer();

    }

    @RequestMapping(value = "/meuperfil/anuncios/novo", method = RequestMethod.GET)
    public ModelAndView getAnunciosNovo(HttpSession session) throws Exception {
        Map<Long, Object> criteria = new HashMap<>();
        CategoriaService cs = new CategoriaService();
        List<Categoria> categoriaList =  cs.readByCriteria(criteria, 0L, 0L);
        ModelAndView mv = new ModelAndView("perfil/anuncioForm");
        mv.addObject("tipoOperacao", "Criar");
        mv.addObject("categoriaList", categoriaList);
        mv.addObject("operacao", "Criar");
        return mv;
    }

    @RequestMapping(value = "/anuncios/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView getAnunciosDeletar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/home");
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogin");
        if (cliente != null) {
            AnuncioService as = new AnuncioService();
            Anuncio anuncio = as.readById(id);
            if (cliente.getId() == anuncio.getCliente().getId()) {
                mv = new ModelAndView("redirect:/home");
                as.delete(id);
            } else {
                mv = new ModelAndView("erro/erro");
            }

        }

        return mv;
    }

    @RequestMapping(value = "/anuncios/{id}/editar", method = RequestMethod.GET)
    public ModelAndView getAnunciosEditar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/home");
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogin");
        if (cliente != null) {
            AnuncioService as = new AnuncioService();
            Anuncio anuncio = as.readById(id);
            if (cliente.getId() == anuncio.getCliente().getId()) {
                mv = new ModelAndView("perfil/anuncioForm");
                CategoriaService cs = new CategoriaService();
                Map<Long, Object> criteria = new HashMap<>();
                List<Categoria> categoriaList = cs.readByCriteria(criteria, 0L, 0L);
                mv.addObject("anuncio", anuncio);
                mv.addObject("categoriaList", categoriaList);
                mv.addObject("operacao", "Alterar");
            } else {
                mv = new ModelAndView("erro/erro");
            }
        }

        return mv;
    }

    @RequestMapping(value = "/anuncios/{id}/editar", method = RequestMethod.POST)
    public ModelAndView postAnunciosEditar(HttpSession session, @PathVariable Long id, String nome, Integer qtd, BigDecimal preco, String descricao, Long id_categoria, MultipartFile foto) throws Exception {
        ModelAndView mv = new ModelAndView("boasVindas");
        if (session.getAttribute("usuarioLogin") != null) {
            mv = new ModelAndView("redirect:/meuperfil");
            Cliente usuario = (Cliente) session.getAttribute("usuarioLogin");
            Anuncio a = new Anuncio();
            a.setId(id);
            a.setNome(nome);
            a.setQtd(qtd);
            a.setPreco(preco);
            a.setFoto(foto.getBytes());
            a.setDescricao(descricao);
            Categoria cat = new Categoria();
            cat.setId(id_categoria);
            a.setCategoria(cat);
            AnuncioService as = new AnuncioService();
            as.update(a);
        }

        return mv;
    }

    @RequestMapping(value = "/classificados/anuncio/{id}", method = RequestMethod.GET)
    public ModelAndView getClassificadosAnuncio(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("classificado/anuncio");
        AnuncioService as = new AnuncioService();
        ClienteService cs = new ClienteService();
        MensagemAnuncioService mas = new MensagemAnuncioService();
        Anuncio anuncio = as.readById(id);
        if (anuncio == null) {
            mv = new ModelAndView("erro/erro");
        } else {
            Cliente cliente = cs.readById(anuncio.getCliente().getId());
            List<MensagemAnuncio> mensagemAnuncioList = mas.readByIdAnuncio(id);
            mv.addObject("mensagemAnuncioList", mensagemAnuncioList);
            mv.addObject("anuncio", anuncio);
            mv.addObject("cliente", cliente);
        }

        return mv;
    }

    @RequestMapping(value = "/reporte/anuncio/{id}", method = RequestMethod.GET)
    public ModelAndView getReporte(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/classificados");
        ReporteService rs = new ReporteService();
        Reporte reporte = new Reporte();
        Anuncio anuncio = new Anuncio();
        anuncio.setId(id);
        reporte.setAnuncio(anuncio);
        reporte.setDataHora(new Timestamp(System.currentTimeMillis()));
        reporte.setDescricao("sem descrição");
        rs.create(reporte);
        return mv;
    }

    @RequestMapping(value = "/reporte/anuncio/{id}", method = RequestMethod.POST)
    public ModelAndView getReportePost(@PathVariable Long id, String descricao) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/classificados");
        ReporteService rs = new ReporteService();
        Reporte reporte = new Reporte();
        Anuncio anuncio = new Anuncio();
        anuncio.setId(id);
        reporte.setAnuncio(anuncio);
        reporte.setDataHora(new Timestamp(System.currentTimeMillis()));
        if(descricao!= null || descricao.trim().isEmpty()){
            reporte.setDescricao(descricao);
        }else{
            reporte.setDescricao("Sem descricao");
        }
        
        rs.create(reporte);
        return mv;
    }

    @RequestMapping(value = "/criar/mensagem", method = RequestMethod.POST)
    public ModelAndView getMensagemAnuncio(HttpSession session, String mensagem, Long idAnuncio) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/classificados/anuncio/" + idAnuncio);
        MensagemAnuncio ma = new MensagemAnuncio();
        MensagemAnuncioService mas = new MensagemAnuncioService();
        Usuario usuario = (Cliente) session.getAttribute("usuarioLogin");
        Anuncio anuncio = new Anuncio();
        anuncio.setId(idAnuncio);
        ma.setAnuncio(anuncio);
        ma.setMensagem(mensagem);
        ma.setDataHora(new Timestamp(System.currentTimeMillis()));
        ma.setUsuario(usuario);
        mas.create(ma);
        return mv;
    }

    @RequestMapping(value = "/classificados/anuncio/{idAnuncio}/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView getAnunciosDeletar(@PathVariable Long idAnuncio, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/classificados/anuncio/" + idAnuncio);
        MensagemAnuncioService mas = new MensagemAnuncioService();
        mas.delete(id);
        return mv;
    }
}

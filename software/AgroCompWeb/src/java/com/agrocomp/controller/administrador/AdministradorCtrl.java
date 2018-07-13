package com.agrocomp.controller.administrador;

import com.agrocomp.model.entity.Administrador;
import com.agrocomp.model.entity.Categoria;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Noticia;
import com.agrocomp.model.entity.Reporte;
import com.agrocomp.model.service.CategoriaService;
import com.agrocomp.model.service.ClienteService;
import com.agrocomp.model.service.NoticiaService;
import com.agrocomp.model.service.ReporteService;
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
public class AdministradorCtrl {

    @RequestMapping(value = "/administrador/clientes", method = RequestMethod.GET)
    public ModelAndView getClientes() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/clientes");
        ClienteService cs = new ClienteService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Cliente> clienteList = cs.readByCriteria(criteria, null, null);
        mv.addObject("clienteList", clienteList);

        return mv;
    }

    @RequestMapping(value = "/administrador/reportes", method = RequestMethod.GET)
    public ModelAndView getReportes() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/reportes");
        ReporteService rs = new ReporteService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Reporte> reporteList = rs.readByCriteria(criteria, null, null);
        mv.addObject("reporteList", reporteList);

        return mv;
    }

    @RequestMapping(value = "/reportes/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView getReporteDeletar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("boasVindas");
        if (session.getAttribute("usuarioLogin") != null) {
            mv = new ModelAndView("redirect:/administrador/reportes");
            ReporteService rs = new ReporteService();
            rs.delete(id);
        }

        return mv;
    }

    @RequestMapping(value = "/categorias/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView getCategoriaDeletar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/administrador/categorias");
        CategoriaService cs = new CategoriaService();
        cs.delete(id);
        return mv;

    }

    @RequestMapping(value = "/administrador/categorias", method = RequestMethod.GET)
    public ModelAndView getCategorias() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/categorias");
        CategoriaService cs = new CategoriaService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Categoria> categoriaList = cs.readByCriteria(criteria, null, null);
        mv.addObject("categoriaList", categoriaList);

        return mv;
    }

    @RequestMapping(value = "/administrador/categorias/novo", method = RequestMethod.GET)
    public ModelAndView getCategoriasForm() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/categoriaForm");
        return mv;
    }

    @RequestMapping(value = "/administrador/categorias/novo", method = RequestMethod.POST)
    public ModelAndView getCategoriasFormPost(String nome) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/administrador/categorias");
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        CategoriaService cs = new CategoriaService();
        cs.create(categoria);
        return mv;
    }

    @RequestMapping(value = "/categorias/{id}/editar", method = RequestMethod.GET)
    public ModelAndView getCategoriaEditar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("boasVindas");
        if (session.getAttribute("usuarioLogin") != null) {
            CategoriaService cs = new CategoriaService();
            Categoria categoria = cs.readById(id);
            mv = new ModelAndView("administrador/categoriaForm");
            mv.addObject("categoria", categoria);
        }

        return mv;

    }

    @RequestMapping(value = "/administrador/noticias", method = RequestMethod.GET)
    public ModelAndView getNoticias() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/noticias");
        NoticiaService ns = new NoticiaService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Noticia> noticiaList = ns.readByCriteria(criteria, null, null);
        mv.addObject("noticiaList", noticiaList);

        return mv;
    }

    @RequestMapping(value = "/administrador/noticias/novo", method = RequestMethod.GET)
    public ModelAndView getNoticiasForm() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/noticiaForm");
        mv.addObject("operacao", "criar");
        return mv;
    }

    @RequestMapping(value = "/administrador/noticias/novo", method = RequestMethod.POST)
    public ModelAndView getNoticiaFormPost(HttpSession session, String titulo, String link, String descricao) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/administrador/noticias");
        Noticia noticia = new Noticia();
        Administrador adm = (Administrador) session.getAttribute("usuarioLogin");
        noticia.setAdm(adm);
        noticia.setDescricao(descricao);
        noticia.setLink(link);
        noticia.setTitulo(titulo);
        noticia.setDataHora(new Timestamp(System.currentTimeMillis()));
        NoticiaService ns = new NoticiaService();
        ns.create(noticia);
        return mv;
    }

    @RequestMapping(value = "/noticias/{id}/editar", method = RequestMethod.GET)
    public ModelAndView getNoticiasEditar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("boasVindas");
        if (session.getAttribute("usuarioLogin") != null) {
            NoticiaService ns = new NoticiaService();
            Noticia noticia = ns.readById(id);
            mv = new ModelAndView("administrador/noticiaForm");
            mv.addObject("noticia", noticia);
            mv.addObject("operacao", "alterar");
        }

        return mv;

    }

    @RequestMapping(value = "/noticias/{id}/editar", method = RequestMethod.POST)
    public ModelAndView getNoticiasEditarPost(HttpSession session, @PathVariable Long id, String titulo, String link, String descricao) throws Exception {

        NoticiaService ns = new NoticiaService();
        Noticia noticia = new Noticia();
        ModelAndView mv = new ModelAndView("redirect:/administrador/noticias");
        noticia.setDescricao(descricao);
        noticia.setLink(link);
        noticia.setTitulo(titulo);
        noticia.setId(id);
        ns.update(noticia);

        return mv;

    }

    @RequestMapping(value = "/noticias/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView getNoticiaDeletar(HttpSession session, @PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/administrador/noticias");
        NoticiaService ns = new NoticiaService();
        ns.delete(id);
        return mv;

    }

}

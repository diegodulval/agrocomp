package com.agrocomp.controller.relatorio.usuario;

import com.agrocomp.model.ConnectionManager;
import com.agrocomp.model.entity.Administrador;
import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Usuario;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioCtrlRelatorio {

    @RequestMapping(value = "/usuario/pdf", method = RequestMethod.GET)
    public void streamPDF(HttpServletResponse response, HttpSession session) throws Exception {
        //Fonte dados
        Connection conn = ConnectionManager.getInstance().getConnection();
        //Parametros
        Map parameters = new HashMap();
        Usuario usuario = (Administrador) session.getAttribute("usuarioLogin");
        parameters.put("USER", usuario.getNome());

        URL web = getClass().getResource("/com/agrocomp/controller/relatorio/usuario/");
        parameters.put("SUBREPORT_DIR", web.toString());
        
        parameters.put("LOGO", UsuarioCtrlRelatorio.class.getResource("/com/agrocomp/img/LogoAgroComp.png"));

        //Criando o relatório.
        InputStream report = UsuarioCtrlRelatorio.class.getResourceAsStream("/com/agrocomp/controller/relatorio/usuario/relatoriousuario.jasper");
        JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
        //Exportando em PDF
        byte[] pdf = JasperExportManager.exportReportToPdf(print);

        response.setContentType("application/pdf");
        response.getOutputStream().write(pdf);
        response.flushBuffer();
    }
}

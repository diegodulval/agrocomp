package com.agrocomp.interceptor;

import com.agrocomp.model.entity.Cliente;
import com.agrocomp.model.entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AAInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean shallPass = false;
        String uri = request.getRequestURI();
        String context = "/web";
        //resouces
        if (uri.startsWith(context + "/resources")) {
            shallPass = true;
        }
        //livre
        if (uri.equals(context + "/home")) {
            shallPass = true;
        }
        if (uri.equals(context + "/login")) {
            shallPass = true;
        }
        if (uri.equals(context + "/cadastro")) {
            shallPass = true;
        }

        if (uri.equals(context + "/classificados")) {
            shallPass = true;
        }
        if (uri.equals(context + "/sobre")) {
            shallPass = true;
        }
        if (uri.equals(context + "/forum")) {
            shallPass = true;
        }
        if (uri.startsWith(context + "/forum/discussao")) {
            shallPass = true;
        }
        if (uri.startsWith(context + "/classificados/anuncio/")) {
            shallPass = true;
        }
        if (uri.startsWith(context + "/reporte/anuncio/")) {
            shallPass = true;
        }
        if (uri.startsWith(context + "/anuncio/") && uri.endsWith("/img.jpg")) {
            shallPass = true;
        }

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogin");
        if (usuario != null) {
            shallPass = true;
            if (usuario instanceof Cliente) {

                if (uri.startsWith(context + "/categorias/novo")) {
                    shallPass = false;
                }
                if (uri.startsWith(context + "/administrador/reportes")) {
                    shallPass = false;
                }
                if (uri.startsWith(context + "/administrador/clientes")) {
                    shallPass = false;
                }
                if (uri.startsWith(context + "/reportes/") && uri.endsWith("/deletar")) {
                    shallPass = false;
                }
                if (uri.startsWith(context + "/categorias/") && uri.endsWith("/deletar")) {
                    shallPass = false;
                }

                if (uri.startsWith(context + "/categorias/") && uri.endsWith("/editar")) {
                    shallPass = false;
                }

                if (uri.startsWith(context + "/administrador/categorias")) {
                    shallPass = false;
                }
            }
        }

        if (!shallPass) {
            response.sendRedirect(context + "/");
        }
        return shallPass;
    }

}

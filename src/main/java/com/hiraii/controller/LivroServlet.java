package com.hiraii.controller;

import com.hiraii.exception.LivroException;
import com.hiraii.model.Livro;
import com.hiraii.util.GerarHtmlLivro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "livroServlet", value = "/livros")
public class LivroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private List<Livro> livros;

    public LivroServlet() {
        super();
    }

    @Override
    public void init() {
        livros = new ArrayList<Livro>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String htmlLivros = GerarHtmlLivro.gerar(request, livros);
        request.setAttribute("htmlLivros", htmlLivros);
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idParse = Integer.parseInt(id);
                livros.removeIf(livro -> livro.getId() == idParse);
            }
            response.sendRedirect(request.getContextPath() + "/livros");
            return;
        }

        try {
            Livro livro = new Livro();
            livro.setTitulo(request.getParameter("titulo").toUpperCase());
            livro.setAutor(request.getParameter("autor").toUpperCase());
            livro.setAno(Integer.parseInt(request.getParameter("ano")));
            livro.validar();
            livros.add(livro);
            response.sendRedirect(request.getContextPath() + "/livros");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Ano deve ser um número.");
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        } catch (LivroException e) {
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        }

    }

}



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

        // Excluir livro
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
            String titulo = request.getParameter("titulo").toUpperCase();
            String autor = request.getParameter("autor").toUpperCase();
            String anoStr = request.getParameter("ano");

            // Verificação básica de ano
            int ano = Integer.parseInt(anoStr);

            // Verificar se já existe livro com mesmo título e autor
            if (livroDuplicado(titulo, autor)) {
                request.setAttribute("mensagemErro", "Esse livro já foi cadastrado.");
                request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
                request.getRequestDispatcher("/view/index.jsp").forward(request, response);
                return;
            }

            // Criar e validar novo livro
            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAno(ano);
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

    // verificar duplicidade de título e autor
    private boolean livroDuplicado(String titulo, String autor) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo.trim()) &&
                    l.getAutor().equalsIgnoreCase(autor.trim())) {
                return true;
            }
        }
        return false;
    }
}

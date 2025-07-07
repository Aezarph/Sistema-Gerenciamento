package com.hiraii.util;

import com.hiraii.model.Livro;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class GerarHtmlLivro {

    public static String gerar(HttpServletRequest request, List<Livro> livros) {
        StringBuilder htmlLivros = new StringBuilder();

        for (Livro livro : livros) {
            htmlLivros.append("<div class=\"flex flex-wrap items-center justify-between p-4 hover:bg-gray-700 transition-colors duration-150 ease-in-out\">");

            // Título
            htmlLivros.append("<div class=\"flex-1 min-w-[200px] mb-2 md:mb-0\">")
                    .append("<p class=\"text-white font-semibold\">Título: ").append(livro.getTitulo()).append("</p>")
                    .append("</div>");

            // Autor
            htmlLivros.append("<div class=\"flex-1 min-w-[150px] mb-2 md:mb-0\">")
                    .append("<p class=\"text-gray-300\">Autor: ").append(livro.getAutor()).append("</p>")
                    .append("</div>");

            // Ano
            htmlLivros.append("<div class=\"min-w-[80px] mb-2 md:mb-0\">")
                    .append("<p class=\"text-gray-300\">Ano: ").append(livro.getAno()).append("</p>")
                    .append("</div>");

            // ISBN
            htmlLivros.append("<div class=\"min-w-[150px] mb-2 md:mb-0\">")
                    .append("<p class=\"text-gray-300\">ISBN: ").append(livro.getIsbn()).append("</p>")
                    .append("</div>");

            // ID
            htmlLivros.append("<div class=\"min-w-[50px] mb-2 md:mb-0\">")
                    .append("<p class=\"text-gray-300\">ID: ").append(livro.getId()).append("</p>")
                    .append("</div>");

            // Botão de exclusão
            htmlLivros.append("<div class=\"ml-auto\">")
                    .append("<form class=\"inline\" method='post' action='").append(request.getContextPath()).append("/livros'>")
                    .append("<input type='hidden' name='action' value='delete' />")
                    .append("<input type='hidden' name='id' value='").append(livro.getId()).append("' />")
                    .append("<button type='submit' class='bg-red-700 text-white py-1 px-3 rounded-md hover:bg-red-800 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-600 transition duration-150 ease-in-out'>")
                    .append("EXCLUIR</button>")
                    .append("</form>")
                    .append("</div>");

            htmlLivros.append("</div>");
        }

        return htmlLivros.toString();
    }
}

# Sistema de Gestão de Biblioteca 📚

Este projeto é um sistema web simples e funcional, desenvolvido como um **trabalho acadêmico**. O objetivo principal é permitir o gerenciamento do acervo de livros, demonstrando a aplicação de conceitos de programação e estrutura de projetos.

## **Funcionalidades ✨**

O sistema oferece as seguintes funcionalidades:

* **Cadastro de Livros:** Permite adicionar novos livros ao acervo com informações como Título, Autor, Ano de Publicação e ISBN.
* **Listagem de Livros:** Exibe todos os livros cadastrados em uma lista clara e organizada.
* **Exclusão de Livros:** Permite remover um livro do acervo, usando seu ISBN ou ID.

## **Tecnologias Utilizadas 💻**

O sistema foi construído com base nos requisitos da atividade, utilizando as seguintes tecnologias:

* **Linguagem:** Java
* **Servidor:** Apache Tomcat
* **Frameworks e Bibliotecas:** Servlets, JSP e JSF

## **Estrutura do Projeto 📂**

O projeto segue o padrão de arquitetura **MVC** (Model-View-Controller) e está organizado em pacotes para manter a separação da lógica:

* **`model`**: Contém as classes que representam os dados (ex: a classe Livro).
* **`controller`**: Responsável por controlar as requisições e a lógica do sistema (implementado com Servlets).
* **`view`**: Contém a interface do usuário (desenvolvida com JSP e/ou JSF).

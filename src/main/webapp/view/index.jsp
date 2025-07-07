<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>MAPA - Fábrica de Software</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>

    <!-- Fonte Inter -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #0f172a;
            color: #e2e8f0;
        }
    </style>
</head>
<body class="flex flex-col items-center min-h-screen p-4">

<div class="w-full max-w-2xl mx-auto bg-gray-900 shadow-2xl rounded-xl p-6 md:p-8 mt-10 border border-gray-800">

    <% if (request.getAttribute("mensagemErro") != null) { %>
    <div id="popup-erro"
         class="fixed top-6 left-1/2 transform -translate-x-1/2 bg-red-600 text-white px-6 py-3 rounded-lg shadow-lg z-50 animate-slide-down">
        <%= request.getAttribute("mensagemErro") %>
    </div>
    <script>
        setTimeout(() => {
            const popup = document.getElementById("popup-erro");
            if (popup) popup.style.display = "none";
        }, 3000); // Esconde após 3 segundos
    </script>
    <% } %>

    <!-- Formulário -->
    <section class="bg-gray-800 p-6 rounded-lg shadow-xl mb-8">
        <h2 class="text-2xl font-bold text-white mb-6 text-center">Cadastrar Livros</h2>
        <form action="livros" method="post" class="space-y-4">
            <div>
                <label for="titulo" class="block text-sm font-medium text-gray-300 mb-1">Título:</label>
                <input type="text" id="titulo" name="titulo" required
                       class="block w-full px-4 py-2 bg-gray-700 border border-gray-600 rounded-md shadow-sm placeholder-gray-400 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white">
            </div>
            <div>
                <label for="autor" class="block text-sm font-medium text-gray-300 mb-1">Autor:</label>
                <input type="text" id="autor" name="autor" required
                       class="block w-full px-4 py-2 bg-gray-700 border border-gray-600 rounded-md shadow-sm placeholder-gray-400 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white">
            </div>
            <div>
                <label for="ano" class="block text-sm font-medium text-gray-300 mb-1">Ano:</label>
                <input type="number" id="ano" name="ano" required
                       class="block w-full px-4 py-2 bg-gray-700 border border-gray-600 rounded-md shadow-sm placeholder-gray-400 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white">
            </div>
            <div class="flex justify-center mt-6">
                <button type="submit"
                        class="w-full px-6 py-3 bg-gradient-to-r from-emerald-600 to-teal-500 text-white font-semibold rounded-lg shadow-lg hover:from-emerald-700 hover:to-teal-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition duration-300 ease-in-out transform hover:scale-105">
                    Cadastrar
                </button>
            </div>
        </form>
    </section>

    <!-- Lista de Livros -->
    <section class="mt-8">
        <h2 class="text-2xl font-bold text-white mb-6">Livros Cadastrados:</h2>
        <div class="bg-gray-800 rounded-lg shadow-md overflow-hidden border border-gray-700 divide-y divide-gray-700">
            <%= request.getAttribute("htmlLivros") %>
        </div>
    </section>
</div>

</body>
</html>

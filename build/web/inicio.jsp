<%@page import="Logica.Usuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/1b02f6f3b2.js" crossorigin="anonymous"></script>
    <title></title>
</head>
<body class="grid container bg-gray-300 font-sans overflow-x-hidden">
    <%
        HttpSession sesion = request.getSession();
        String usu = (String) sesion.getAttribute("user");
        
        if (usu == null) {
            response.sendRedirect("index.jsp");
        }
    %>
    <!-- Header -->
    <nav>
        <div class="flex flex-grow bg-gray-700 h-36 w-screen justify-center">
            <a class="transform focus:rotate-180 transition duration-1000" href="inicio.jsp"><svg class="fill-current text-gray-900 transform hover:rotate-180 transition duration-700" height="100pt" viewBox="0 -16 512 512" width="100pt" xmlns="http://www.w3.org/2000/svg"><path d="m317 360c-8.285156 0-15 6.714844-15 15 0 7.992188-7.476562 15-16 15h-15v-48.371094c17.984375-8.183594 30-29.382812 30-41.628906 0-17.382812-18.925781-30-45-30s-45 12.617188-45 30c0 12.246094 12.015625 33.445312 30 41.628906v48.371094h-15c-8.523438 0-16-7.007812-16-15 0-8.285156-6.714844-15-15-15s-15 6.714844-15 15c0 12.089844 4.945312 23.472656 13.921875 32.054688 8.730469 8.347656 20.125 12.945312 32.078125 12.945312h60c11.953125 0 23.347656-4.597656 32.078125-12.945312 8.976563-8.582032 13.921875-19.964844 13.921875-32.054688 0-8.285156-6.714844-15-15-15zm-61-60c6.367188 0 11.089844 1.269531 13.808594 2.460938-2.730469 5.261718-9.136719 12.539062-13.808594 12.539062s-11.078125-7.277344-13.808594-12.539062c2.71875-1.191407 7.441406-2.460938 13.808594-2.460938zm0 0"/><path d="m512 90c0-49.625-40.375-90-90-90-32.578125 0-62.375 17.777344-78.195312 45.453125-27.398438-9.996094-56.972657-15.453125-87.804688-15.453125s-60.40625 5.457031-87.804688 15.453125c-15.820312-27.675781-45.617187-45.453125-78.195312-45.453125-49.625 0-90 40.375-90 90 0 27.21875 12.390625 52.78125 33.09375 69.722656-21.054688 37.023438-33.09375 79.773438-33.09375 125.277344 0 142.058594 115.527344 195 256 195 142.070312 0 256-54.042969 256-195 0-45.503906-12.039062-88.253906-33.09375-125.277344 20.703125-16.941406 33.09375-42.503906 33.09375-69.722656zm-482 0c0-33.085938 26.914062-60 60-60 20.601562 0 39.539062 10.660156 50.4375 27.503906-35.996094 18.214844-67.109375 44.664063-90.84375 76.835938-12.3125-11.25-19.59375-27.308594-19.59375-44.339844zm226 360c-123.542969 0-226-41.363281-226-165 0-124.066406 101.382812-225 226-225s226 100.933594 226 225c0 123.519531-102.265625 165-226 165zm206.40625-315.660156c-23.734375-32.171875-54.847656-58.621094-90.84375-76.835938 10.898438-16.84375 29.835938-27.503906 50.4375-27.503906 33.085938 0 60 26.914062 60 60 0 17.03125-7.28125 33.089844-19.59375 44.339844zm0 0"/><path d="m150 255c0 8.285156-6.714844 15-15 15s-15-6.714844-15-15 6.714844-15 15-15 15 6.714844 15 15zm0 0"/><path d="m392 255c0 8.285156-6.714844 15-15 15s-15-6.714844-15-15 6.714844-15 15-15 15 6.714844 15 15zm0 0"/><path d="m204.195312 268.929688c18.046876-43.167969 6.175782-94.964844-34.480468-111.964844-40.832032-17.070313-85.953125 11.152344-103.910156 54.105468-18.046876 43.167969-6.175782 94.964844 34.480468 111.964844 40.933594 17.113282 86.007813-11.28125 103.910156-54.105468zm-57.699218 23.203124c-11.84375 6.46875-24.140625 7.613282-34.640625 3.222657-22.890625-9.570313-31.136719-42.1875-18.375-72.710938 11.585937-27.710937 40.171875-48.242187 64.660156-38 22.894531 9.570313 31.136719 42.1875 18.378906 72.710938-6.417969 15.355469-17.082031 27.703125-30.023437 34.777343zm0 0"/><path d="m342.285156 156.964844c-40.859375 17.082031-52.425781 69.046875-34.480468 111.964844 18.3125 43.800781 63.746093 70.894531 103.910156 54.105468 40.832031-17.070312 52.441406-69.011718 34.480468-111.964844-18.058593-43.199218-63.277343-71.09375-103.910156-54.105468zm57.859375 138.390625c-25.007812 10.457031-53.328125-10.886719-64.664062-38-12.757813-30.523438-4.515625-63.140625 18.378906-72.710938 25-10.457031 53.328125 10.890625 64.660156 38 12.757813 30.523438 4.515625 63.140625-18.375 72.710938zm0 0"/></svg>
            </a>
            <p class="text-green-400 text-4xl ml-10 self-center">Panda</p><p class="text-gray-400 text-4xl self-center">Hotel</p>
        </div>
    </nav>
    <!-- Espaciadora del Header -->
    <div class="flex justify-center bg-gray-900 h-10 w-screen">
        <li class="flex self-center space-x-8 text-gray-400">
            <a class="border-b-2 border-green-300 text-green-300 hover:text-green-300" href="inicio.jsp">Inicio</a>
            <a class="hover:text-green-300" href="registros.jsp">Registros</a>
            <a class="hover:text-green-300" href="consultas.jsp">Consultas</a>
        </li>
    </div>
    <!-- Bienvenida usuario  -->
    <div>
        <p class="text-center ">�Bienvenidx <%=usu%>!</p>
    </div>
    <!-- Noticias empresa --> 

        <h1 class="text-center text-gray-900 text-xl mt-4">Panel de informaci�n</h1>
        <div class="flex justify-self-center bg-blue-900 w-48 h-1"></div>
        
    <div class= "flex flex-wrap flex-row gap-4 justify-around my-12">
        <div class="relative w-64 h-64 flex flex-col gap-4 place-content-center	items-center bg-white rounded shadow-2xl border-t-4 border-blue-900">
            <svg class="-mt-6" enable-background="new 0 0 512 512" height="70" viewBox="0 0 512 512" width="70" xmlns="http://www.w3.org/2000/svg"><g><path d="m95.127 149.209h218c4.142 0 7.5-3.357 7.5-7.5v-48.839c0-4.143-3.358-7.5-7.5-7.5h-218c-4.142 0-7.5 3.357-7.5 7.5v48.839c0 4.143 3.357 7.5 7.5 7.5zm7.5-48.839h203v33.839h-203z"/><path d="m127.65 185.489h-32.523c-4.142 0-7.5 3.357-7.5 7.5v32.523c0 4.143 3.358 7.5 7.5 7.5h32.523c4.142 0 7.5-3.357 7.5-7.5v-32.523c0-4.142-3.358-7.5-7.5-7.5zm-7.5 32.524h-17.523v-17.523h17.523z"/><path d="m165.186 200.489h62.49c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5h-62.49c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5z"/><path d="m127.65 263.241h-32.523c-4.142 0-7.5 3.357-7.5 7.5v32.523c0 4.143 3.358 7.5 7.5 7.5h32.523c4.142 0 7.5-3.357 7.5-7.5v-32.523c0-4.142-3.358-7.5-7.5-7.5zm-7.5 32.524h-17.523v-17.523h17.523z"/><path d="m165.186 278.241h62.49c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5h-62.49c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5z"/><path d="m127.65 340.994h-32.523c-4.142 0-7.5 3.357-7.5 7.5v32.523c0 4.143 3.358 7.5 7.5 7.5h32.523c4.142 0 7.5-3.357 7.5-7.5v-32.523c0-4.142-3.358-7.5-7.5-7.5zm-7.5 32.524h-17.523v-17.523h17.523z"/><path d="m165.186 355.994h62.49c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5h-62.49c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5z"/><path d="m127.65 418.747h-32.523c-4.142 0-7.5 3.357-7.5 7.5v32.523c0 4.143 3.358 7.5 7.5 7.5h32.523c4.142 0 7.5-3.357 7.5-7.5v-32.523c0-4.143-3.358-7.5-7.5-7.5zm-7.5 32.524h-17.523v-17.523h17.523z"/><path d="m165.186 418.747c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5h62.49c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5z"/><path d="m475.714 327.841-68.472-118.598c-6.282-10.412-18.119-16.711-31.26-14.276v-141.313c0-16.457-13.389-29.846-29.846-29.846h-25.509v-16.308c0-4.143-3.358-7.5-7.5-7.5s-7.5 3.357-7.5 7.5v16.309h-203v-16.309c0-4.143-3.358-7.5-7.5-7.5s-7.5 3.357-7.5 7.5v16.309h-25.51c-16.457 0-29.846 13.389-29.846 29.846v428.5c.001 16.456 13.389 29.845 29.846 29.845h284.019c16.457 0 29.846-13.389 29.846-29.846v-109.898h74.089c10.705 0 20.291-5.534 25.643-14.805 5.353-9.27 5.353-20.34 0-29.61zm-114.732 154.313c0 8.186-6.66 14.846-14.846 14.846h-284.019c-8.186 0-14.846-6.66-14.846-14.846v-428.5c0-8.186 6.66-14.846 14.846-14.846h25.509v16.309c0 4.143 3.358 7.5 7.5 7.5s7.5-3.357 7.5-7.5v-16.308h203v16.309c0 4.143 3.358 7.5 7.5 7.5s7.5-3.357 7.5-7.5v-16.309h25.509c8.186 0 14.846 6.66 14.846 14.846v149.115c-1.926 1.883-3.624 4.047-5.026 6.474l-68.472 118.598c-5.353 9.271-5.353 20.34 0 29.61 5.352 9.271 14.938 14.805 25.643 14.805h47.855v109.897zm101.742-132.203c-2.641 4.574-7.371 7.305-12.653 7.305h-136.944c-5.282 0-10.012-2.73-12.653-7.305s-2.641-10.036 0-14.61l68.472-118.598c2.641-4.574 7.371-7.305 12.653-7.305s10.012 2.73 12.653 7.305l68.472 118.598c2.64 4.574 2.641 10.036 0 14.61z"/><path d="m381.599 310.765c4.142 0 7.5-3.357 7.5-7.5v-52.28c0-4.143-3.358-7.5-7.5-7.5s-7.5 3.357-7.5 7.5v52.28c0 4.142 3.358 7.5 7.5 7.5z"/><path d="m296.095 218.013h-130.909c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5h130.909c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5z"/><path d="m165.186 310.765h90.807c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5h-90.807c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5z"/><path d="m275.72 381.018c0-4.143-3.358-7.5-7.5-7.5h-103.034c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5h103.034c4.142 0 7.5-3.358 7.5-7.5z"/><path d="m296.095 451.271h-130.909c-4.142 0-7.5 3.357-7.5 7.5s3.358 7.5 7.5 7.5h130.909c4.142 0 7.5-3.357 7.5-7.5s-3.358-7.5-7.5-7.5z"/><circle cx="381.599" cy="331.68" r="7.5"/></g></svg>
            <div class="mt-2">
                <p class="w-64 text-center">Observe las normas de convivencia de la empresa y est� al tanto de ellas</p>
            </div>
            <div class="bottom-2 left-3 absolute"> 
                <a href="#"><button class="font-bold transform hover:scale-110 transition duration-700 rounded border-2 bg-blue-900 text-white border-blue-900 w-24 h-8 mx-auto">Leer m�s...</button></a>
            </div>  
        </div>
        <div class="relative w-64 h-64 flex flex-col gap-4 place-content-center	items-center bg-white rounded shadow-2xl border-t-4 border-blue-900">
            <svg class="-mt-6" enable-background="new 0 0 512 512" height="70" viewBox="0 0 512 512" width="70" xmlns="http://www.w3.org/2000/svg"><g><path d="m141.994 189.716c1.927 0 3.854-.738 5.319-2.212 1.13-1.137 2.633-1.765 4.231-1.767h.009c1.609 0 3.128.632 4.275 1.78 2.929 2.931 7.678 2.932 10.608.003s2.931-7.677.002-10.607c-3.982-3.984-9.268-6.177-14.885-6.177-.01 0-.02 0-.029 0-5.615.008-10.889 2.206-14.85 6.191-2.921 2.939-2.907 7.687.031 10.608 1.464 1.455 3.376 2.181 5.289 2.181z"/><path d="m222.922 189.716c1.927 0 3.853-.738 5.319-2.212 1.13-1.137 2.634-1.765 4.232-1.767h.008c1.61 0 3.128.632 4.275 1.78 2.928 2.931 7.678 2.932 10.608.003 2.93-2.928 2.932-7.677.003-10.607-3.982-3.984-9.267-6.177-14.885-6.177-.009 0-.02 0-.029 0-5.615.008-10.889 2.206-14.851 6.191-2.921 2.938-2.907 7.687.031 10.607 1.464 1.456 3.376 2.182 5.289 2.182z"/><path d="m153.917 232.961c0 10.64 4.41 19.991 12.411 26.331 6.871 5.44 16.002 8.44 25.703 8.44 18.932 0 38.104-11.94 38.104-34.771 0-7.53-6.121-13.66-13.651-13.66h-48.945c-7.511 0-13.622 6.13-13.622 13.66zm61.187 1.34c-.81 14.51-14.742 18.431-23.073 18.431s-22.263-3.92-23.073-18.431z"/><path d="m436.5 171.505c1.921 0 3.843-.733 5.308-2.2l13.421-13.439v59.337c0 4.143 3.358 7.5 7.501 7.5s7.501-3.357 7.501-7.5v-59.351l13.452 13.456c1.464 1.465 3.385 2.197 5.305 2.197 1.919 0 3.838-.731 5.303-2.195 2.93-2.929 2.931-7.678.002-10.608l-26.258-26.266c-.093-.093-.177-.169-.254-.231-1.334-1.218-3.103-1.967-5.051-1.967-2.072 0-3.948.84-5.305 2.198-.001.001-.002.001-.003.002l-26.229 26.266c-2.927 2.932-2.924 7.68.008 10.608 1.463 1.463 3.38 2.193 5.299 2.193z"/><path d="m474.153 304.223c-22.422-19.731-51.186-29.551-80.979-27.671-2.061.132-4.105.339-6.14.579v-50.581l13.421 13.439c1.465 1.467 3.386 2.2 5.308 2.2 1.918 0 3.836-.73 5.3-2.192 2.931-2.928 2.935-7.676.008-10.608l-26.229-26.265c-.001-.001-.002-.002-.003-.003-.007-.007-.016-.014-.023-.021-.085-.084-.166-.159-.244-.222-1.332-1.21-3.096-1.954-5.037-1.954-1.94 0-3.703.743-5.034 1.952-.084.068-.174.149-.271.246l-26.258 26.265c-2.929 2.93-2.928 7.679.002 10.608 1.465 1.464 3.383 2.195 5.303 2.195s3.84-.732 5.305-2.197l13.452-13.456v53.406c-16.454 4.26-31.654 12.187-44.595 23.341-11.001-5.81-22.803-10.19-34.584-12.74-2.83-2.13-8.011-6.16-13.882-10.73-4.961-3.85-10.351-8.05-15.452-11.97 15.962-15.59 28.323-34.951 34.004-54.662 6.531-1.37 13.111-4.97 18.442-10.23 6.661-6.56 10.471-14.82 10.471-22.661v-10.16c0-9.78-7.961-17.741-17.742-17.741h-7.811v-48.091c0-19.611-7.611-38.261-22.022-53.942-12.531-13.64-30.223-24.821-49.816-31.491-18.362-6.24-37.794-8.32-54.696-5.85-17.772 2.59-32.284 9.97-42.195 21.391-20.142 2.91-50.066 18.221-49.035 83.633.01.65.02 1.18.02 1.58v32.771h-7.781c-9.801 0-17.772 7.96-17.772 17.741v10.16c0 7.83 3.82 16.09 10.471 22.651 5.341 5.27 11.931 8.87 18.452 10.24 5.791 20.011 18.442 39.671 34.754 55.372-5.021 3.87-10.281 7.95-15.112 11.71-5.611 4.36-10.551 8.2-13.311 10.281-20.492 4.43-41.465 14.63-57.806 28.181-3.18 2.64-3.62 7.37-.98 10.56 1.48 1.79 3.62 2.71 5.781 2.71 1.68 0 3.38-.57 4.781-1.73 14.082-11.68 32.024-20.571 49.676-24.691l42.665 31.721c.16.12.32.23.49.34 1.59.99 2.63 1.8 3.46 2.46 2.67 2.08 4.961 2.9 6.981 2.9 3.38 0 6.031-2.28 8.481-4.77 1.2-1.22 2.9-2.95 5.431-5.24l3.97 15.571-24.773 82.503c-.02.05-.04.09-.04.14l-.02.07c-.82 2.37-.64 4.62.55 6.9l13.411 25.391h-75.611v-46.942c0-4.14-3.36-7.5-7.501-7.5-4.15 0-7.501 3.36-7.501 7.5v46.942h-45.215c-.81 0-1.58.01-2.32.02-.54.01-1.15.03-1.73.02-.01-.24-.02-.52-.02-.83v-77.033c0-10.831 2.99-21.451 8.881-31.571 2.08-3.58.87-8.17-2.71-10.26-3.58-2.08-8.171-.87-10.261 2.71-7.242 12.437-10.912 25.598-10.912 39.118v77.033c0 3.53.42 8.63 4.07 12.21 3.83 3.76 8.671 3.68 12.951 3.6.65-.01 1.33-.02 2.05-.02h315.315c19.123 13.981 41.936 21.441 65.698 21.441 2.4 0 4.82-.07 7.241-.23 29.793-1.89 57.086-15.271 76.828-37.691 40.735-46.262 36.255-117.084-10-157.856zm-173.27-113.153v-23.681h7.811c1.49 0 2.74 1.26 2.74 2.74v10.16c0 3.79-2.24 8.27-6.001 11.98-1.47 1.45-3.07 2.69-4.731 3.7.121-1.659.181-3.29.181-4.899zm-222.284 1.2c-3.76-3.71-6.011-8.19-6.011-11.98v-10.16c0-1.48 1.27-2.74 2.77-2.74h7.781v23.681c0 1.6.07 3.24.19 4.89-1.66-1.011-3.27-2.251-4.73-3.691zm191.161 99.383c2.73 2.12 5.241 4.08 7.441 5.79l-37.174 27.641c-.57.36-1.09.7-1.56 1.03-1.61-1.6-3.8-3.7-6.891-6.37l-7.771-7.15c-.23-.24-.47-.46-.73-.67l-13.501-12.43c3.35-.86 6.811-1.95 10.441-3.31 11.221-4.19 22.072-10.49 31.994-18.271 5.73 4.39 12.03 9.3 17.751 13.74zm-59.757 28.631-5.331 20.931h-25.292l-5.021-19.691 18.502-17.021zm-73.308-271.319c2.09-.18 4.01-1.23 5.291-2.89 10.491-13.63 28.073-19.081 46.645-19.081 11.991 0 24.403 2.27 35.584 6.07 28.443 9.69 61.667 34.151 61.667 71.232v40.661h-11.122c-15.802 0-25.923-9.49-37.634-20.471-14.082-13.21-30.043-28.171-58.326-28.171h-32.044c-23.943 0-27.803 18.821-30.623 32.561-3.05 14.88-5.211 22.531-17.992 23.421v-32.681c0-.46-.01-1.07-.02-1.81-.47-29.82 5.841-65.961 38.574-68.841zm-36.924 155.655c-1.08-4.7-1.63-9.26-1.63-13.55v-23.751c25.693-1.33 29.883-21.76 32.694-35.431 3.12-15.24 5.171-20.571 15.922-20.571h32.043c22.352 0 34.844 11.71 48.065 24.111 12.861 12.061 26.163 24.531 47.895 24.531h11.121v31.111c0 4.29-.54 8.85-1.62 13.56-7.311 32.141-37.184 65.452-69.508 77.503-8.511 3.19-15.732 4.68-22.742 4.68-7.001 0-14.232-1.49-22.753-4.69-32.283-12.031-62.156-45.342-69.487-77.503zm61.327 108.714c-.13.11-.25.23-.38.35l-6.591 6.06c-3.09 2.68-5.271 4.77-6.881 6.37-.48-.32-1-.67-1.58-1.03l-37.174-27.651c2.05-1.58 4.361-3.38 6.871-5.33 5.611-4.36 11.791-9.17 17.492-13.54 9.691 7.48 20.242 13.55 31.143 17.61 4.12 1.55 8.021 2.75 11.801 3.65zm68.027 150.225 13.432-25.431c1.21-2.31 1.36-4.62.48-7.07l-5.221-17.331c-1.19-3.97-5.381-6.22-9.341-5.02-3.97 1.19-6.221 5.37-5.021 9.34l4.63 15.37-15.922 30.141h-40.294l-15.922-30.141 23.183-77.203h25.793l8.691 28.981c.97 3.25 3.95 5.34 7.181 5.34.71 0 1.44-.1 2.15-.31 3.97-1.19 6.221-5.37 5.031-9.34l-9.691-32.331 4.271-16.771c3.32 2.92 5.401 5.03 6.801 6.45 2.45 2.49 5.101 4.77 8.461 4.77 2.03 0 4.31-.83 6.971-2.91.84-.66 1.88-1.47 3.46-2.46.18-.11.34-.22.5-.34l42.665-31.721c8.401 1.96 16.812 4.93 24.853 8.78-19.692 22.391-29.493 51.122-27.603 80.893 1.63 25.731 11.821 49.572 28.993 68.312h-88.531zm243.767-11.391c-17.092 19.411-40.715 31.001-66.517 32.631-14.182.9-28.093-1.26-40.975-6.24-9.271-3.58-18.002-8.61-25.903-15.001-1.1-.88-2.18-1.79-3.24-2.73-19.402-17.081-30.983-40.711-32.624-66.532-.13-2.1-.2-4.19-.2-6.27 0-23.551 8.461-46.031 24.163-63.852.69-.79 1.4-1.56 2.12-2.32 3.53-3.77 7.321-7.23 11.341-10.34 15.172-11.77 33.494-18.751 53.066-19.991 2.09-.13 4.18-.2 6.261-.2 23.553 0 46.015 8.46 63.847 24.161 40.055 35.301 43.935 96.623 8.661 136.684z"/><path d="m391.584 363.762h33.262c4.143 0 7.501-3.358 7.501-7.5 0-4.143-3.358-7.5-7.501-7.5h-17.112v-11.448c0-4.143-3.358-7.5-7.501-7.5s-7.501 3.358-7.501 7.5v11.448h-1.148c-12.923 0-23.437 10.516-23.437 23.441 0 12.941 10.514 23.469 23.437 23.469h17.439c4.511 0 8.322 3.865 8.322 8.441 0 4.654-3.784 8.441-8.435 8.441h-33.262c-4.143 0-7.501 3.358-7.501 7.5s3.358 7.5 7.501 7.5h17.056v11.448c0 4.143 3.358 7.5 7.501 7.5s7.501-3.357 7.501-7.5v-11.448h1.204c12.923 0 23.437-10.516 23.437-23.441s-10.463-23.441-23.323-23.441h-17.439c-4.651 0-8.435-3.799-8.435-8.469-.002-4.655 3.783-8.441 8.434-8.441z"/></g></svg>
            <div class="mt-2">
            <p class="w-56 text-center">Ac� podr� encontrar la escala salarial actualizada de la empresa</p>
            </div>
            <div class="bottom-2 left-3 absolute"> 
                <a href="#"><button class="font-bold transform hover:scale-110 transition duration-700 rounded border-2 bg-blue-900 text-white border-blue-900 w-24 h-8 mx-auto">Leer m�s...</button></a>
            </div>  
        </div>
        <div class="relative w-64 h-64 flex flex-col gap-4 place-content-center	items-center bg-white rounded shadow-2xl border-t-4 border-blue-900">
            <svg class="-mt-6" enable-background="new 0 0 512 512" height="70" viewBox="0 0 512 512" width="70" xmlns="http://www.w3.org/2000/svg"><path d="m377.658 372.817c-17.078-9.861-38.439 2.448-38.439 22.193v42.552c0 19.703 21.324 32.072 38.439 22.193l36.851-21.276c17.079-9.859 17.101-34.514 0-44.386zm29.351 52.672-36.851 21.276c-7.083 4.088-15.939-1.015-15.939-9.203v-42.552c0-8.179 8.848-13.297 15.939-9.203l36.851 21.276c7.082 4.09 7.091 14.312 0 18.406zm-295.884-157.219c-4.142 0-7.5 3.358-7.5 7.5v12c0 4.143 3.358 7.5 7.5 7.5s7.5-3.357 7.5-7.5v-12c0-4.143-3.358-7.5-7.5-7.5zm157.349 0c-4.143 0-7.5 3.358-7.5 7.5v12c0 4.143 3.357 7.5 7.5 7.5s7.5-3.357 7.5-7.5v-12c0-4.143-3.357-7.5-7.5-7.5zm210.565 62.858c-35.115-7.353-70.487-10.878-105.85-10.595 11.316-6.578 22.098-14.234 32.031-22.79 12.114-10.438 13.48-28.784 3.044-40.898-5.056-5.869-12.094-9.419-19.819-9.994-7.719-.575-15.212 1.893-21.08 6.949-22.965 19.784-45.702 28.061-59.342 31.474-1.317-24.81-1.687-46.376-1.787-58.032h2.71c15.041 0 28.054-9.678 32.379-24.083 6.923-23.045-9.414-45.907-32.587-47.742 9.024-15.372 15.784-33.003 20.129-52.622 5.397-24.371-19.006-44.87-42.117-35.344-18.786 7.745-34.757 8.786-47.944 6.425 14.806-34.988-11.047-73.876-49.006-73.876-38.061 0-63.771 39.002-49.01 73.877-9.013 1.624-19.385 1.674-31.088-.998-4.038-.924-8.059 1.604-8.981 5.642s1.604 8.06 5.643 8.981c34.124 7.794 58.952-3.853 73.77-14.991 5.782-4.347 13.551-4.347 19.333 0 18.481 13.894 49.615 26.699 93.003 8.809 11.913-4.912 24.544 5.631 21.754 18.233-4.664 21.059-12.628 40.237-23.112 55.731h-202.623c-10.485-15.494-18.449-34.673-23.112-55.731-2.048-9.249 4.09-16.555 10.905-18.733 3.945-1.261 6.122-5.481 4.86-9.427-1.261-3.945-5.481-6.122-9.428-4.86-15.446 4.937-24.467 20.527-20.982 36.264 4.345 19.618 11.105 37.249 20.13 52.621-23.179 1.834-39.509 24.704-32.589 47.742 4.327 14.405 17.339 24.083 32.38 24.083h2.713c-.171 20.938-1.048 50.248-3.073 78.568-3.751 1.701-7.197 4.224-10.043 7.558-10.382 12.161-8.935 30.502 3.226 40.885.52.444 1.083.919 1.676 1.415-2.396 16.597-5.236 29.863-8.465 39.584-22.359.692-41.269 16.161-46.291 38.106l-7.347 32.106c-4.142 18.1 9.619 35.316 28.148 35.316h241.372c.871.248 1.752.475 2.649.663 33.812 7.08 67.86 10.62 101.91 10.62s68.1-3.54 101.911-10.62c10.261-2.148 19.327-8.485 24.876-17.387 2.191-3.516 1.118-8.142-2.397-10.332-3.515-2.191-8.142-1.118-10.332 2.397-3.396 5.448-8.944 9.326-15.222 10.641-65.584 13.734-132.09 13.734-197.674 0-10.889-2.279-18.791-12.009-18.791-23.134v-94.685c0-11.125 7.903-20.854 18.792-23.135 65.582-13.733 132.089-13.733 197.673 0 10.889 2.28 18.792 12.01 18.792 23.135v75.426c0 4.143 3.357 7.5 7.5 7.5s7.5-3.357 7.5-7.5v-75.426c.001-18.184-12.918-34.089-30.717-37.816zm-254.749-261.52c-6.145-2.564-11.417-5.785-15.812-9.088-11.173-8.398-26.187-8.398-37.359 0-4.269 3.21-9.549 6.466-15.815 9.078-11.993-25.152 6.378-54.598 34.496-54.598 28.147 0 46.485 29.47 34.49 54.608zm-153.637 142.634c-8.368 0-15.607-5.384-18.014-13.398-4.277-14.241 6.366-28.561 21.242-28.561h231.836c14.87 0 25.52 14.313 21.242 28.561-2.407 8.014-9.646 13.398-18.014 13.398zm1.004 110.865c5.012-5.872 13.867-6.569 19.738-1.558 20.773 17.736 61.378 46.054 88.465 55.216 7.313 2.474 11.25 10.436 8.777 17.748-2.473 7.313-10.434 11.251-17.748 8.777h.001c-36.506-12.348-82.886-47.82-97.676-60.446-5.87-5.011-6.569-13.866-1.557-19.737zm49.031 72.112h-48.298c2.324-7.984 4.422-17.614 6.276-28.817 11.225 8.559 26.118 19.158 42.022 28.817zm-89.491 90.562c-8.923 0-15.513-8.29-13.526-16.971l7.347-32.106c3.568-15.594 17.233-26.485 33.231-26.485h90.048c6.049 2.847 12.027 5.333 17.787 7.281h.001c10.727 3.628 22.354.642 30.075-7.281h48.34v53.41c0 8.138 2.593 15.815 7.047 22.152zm244.02-154.653c-17.799 3.728-30.717 19.632-30.717 37.816v26.275h-40.595c2.54-13.915-5.45-27.998-19.239-32.662-27.697-9.367-68.238-39.358-83.532-52.415-4.586-3.915-10.051-6.146-15.636-6.757 1.881-27.616 2.704-55.798 2.868-76.143h202.87c.152 17.912.936 59.2 4.785 99.989-6.943 1.153-13.879 2.447-20.804 3.897zm35.661-6.134c-.768-8.224-1.41-16.464-1.947-24.519 14.811-3.384 41.513-12.298 68.227-35.31 2.832-2.44 6.445-3.635 10.175-3.354 3.729.277 7.127 1.99 9.567 4.825 2.441 2.833 3.632 6.446 3.354 10.176-.277 3.73-1.99 7.127-4.824 9.567-18.249 15.722-39.557 28.166-61.682 36.053-7.63.674-15.254 1.53-22.87 2.562zm-121.078-52.302c-13.191 0-23.923 11.608-23.923 25.877s10.732 25.877 23.923 25.877 23.922-11.608 23.922-25.877c0-14.268-10.731-25.877-23.922-25.877zm0 36.754c-4.92 0-8.923-4.879-8.923-10.877s4.003-10.877 8.923-10.877 8.922 4.879 8.922 10.877-4.002 10.877-8.922 10.877z"/></svg>
            <div class="mt-2">
            <p class="w-64 text-center">Observe el tutorial para instruirse sobre el funcionamiento de nuestra aplicaci�n</p>
            </div>
            <div class="bottom-2 left-3 absolute"> 
                <a href="#"><button class="font-bold transform hover:scale-110 transition duration-700 rounded border-2 bg-blue-900 text-white border-blue-900 w-24 h-8 mx-auto">Leer m�s...</button></a>
            </div>  
        </div>
        <div class="relative w-64 h-64 flex flex-col gap-4 place-content-center	items-center bg-white rounded shadow-2xl border-t-4 border-blue-900">
            <svg class="-mt-6" enable-background="new 0 0 512 512" height="70" viewBox="0 0 512 512" width="70" xmlns="http://www.w3.org/2000/svg"><g><path d="m99.979 44.944h54.652v-16.271c0-6.832 2.778-13.039 7.285-17.547 4.479-4.479 10.686-7.285 17.547-7.285 6.831 0 13.04 2.807 17.518 7.285 4.507 4.508 7.313 10.715 7.313 17.547v16.271h54.624v-16.271c0-6.832 2.807-13.039 7.285-17.547 4.507-4.479 10.715-7.285 17.546-7.285 6.832 0 13.04 2.807 17.548 7.285 4.507 4.508 7.285 10.715 7.285 17.547v16.271h54.651v-16.073c0-6.831 2.778-13.04 7.285-17.547 4.478-4.478 10.686-7.284 17.548-7.284 6.831 0 13.039 2.806 17.518 7.284 4.508 4.507 7.314 10.716 7.314 17.547v16.073h8.221c11.594 0 22.11 4.733 29.735 12.331 7.625 7.625 12.359 18.142 12.359 29.734v78.153c0 4.139-3.373 7.512-7.512 7.512s-7.512-3.373-7.512-7.512v-21.629h-199.59c-4.167 0-7.512-3.345-7.512-7.483 0-4.167 3.345-7.512 7.512-7.512h199.59v-41.528c0-7.455-3.033-14.201-7.938-19.106-4.933-4.932-11.679-7.965-19.134-7.965h-8.221v16.045c0 6.831-2.806 13.039-7.314 17.546-4.479 4.507-10.686 7.285-17.518 7.285-6.861 0-13.069-2.778-17.548-7.285-4.507-4.507-7.285-10.715-7.285-17.546v-16.045h-54.651v15.846c0 6.832-2.778 13.039-7.285 17.547-4.508 4.479-10.716 7.285-17.548 7.285-6.831 0-13.039-2.807-17.546-7.285-4.479-4.508-7.285-10.715-7.285-17.547v-15.846h-54.624v15.846c0 6.832-2.806 13.039-7.313 17.547-4.479 4.479-10.687 7.285-17.518 7.285-6.86 0-13.068-2.807-17.547-7.285-4.507-4.508-7.285-10.715-7.285-17.547v-15.846h-54.65v15.846c0 6.832-2.807 13.039-7.285 17.547-4.507 4.479-10.715 7.285-17.547 7.285-6.831 0-13.04-2.807-17.547-7.285-4.479-4.508-7.285-10.715-7.285-17.547v-15.846h-8.249c-7.427 0-14.202 3.033-19.105 7.965-4.904 4.904-7.966 11.65-7.966 19.106v41.529h199.619c4.139 0 7.512 3.345 7.512 7.512 0 4.139-3.373 7.483-7.512 7.483h-199.618v221.473c0 4.139-3.345 7.484-7.483 7.484-4.139 0-7.512-3.346-7.512-7.484v-277.997c-.001-11.593 4.733-22.11 12.358-29.735 7.625-7.598 18.142-12.331 29.707-12.331h8.249v-16.271c0-6.832 2.778-13.039 7.285-17.547 4.507-4.479 10.716-7.285 17.547-7.285 6.832 0 13.039 2.807 17.547 7.285 4.479 4.508 7.285 10.715 7.285 17.547zm298.889 291.518c0-4.139 3.345-7.483 7.483-7.483 4.167 0 7.512 3.345 7.512 7.483v66.048c0 2.041-.794 3.855-2.126 5.217l-45.837 53.914c-2.665 3.176-7.37 3.572-10.545.879-3.146-2.664-3.544-7.369-.879-10.545l44.392-52.186zm-45.723-7.342c-3.374 2.438-8.051 1.673-10.46-1.672-2.438-3.346-1.701-8.022 1.672-10.46 8.845-6.435 18.737-11.509 29.396-14.967 10.291-3.346 21.261-5.16 32.6-5.16 14.882 0 29.027 3.062 41.84 8.618v-106.357c0-4.139 3.373-7.484 7.512-7.484s7.512 3.346 7.512 7.484v114.322c6.463 4.139 12.444 8.958 17.858 14.372 19.105 19.106 30.925 45.525 30.925 74.694s-11.82 55.588-30.927 74.694c-19.134 19.135-45.524 30.955-74.722 30.955-29.169 0-55.56-11.82-74.694-30.955-7.256-7.257-13.464-15.564-18.368-24.69h-274.284c-10.743 0-20.495-4.365-27.553-11.424-7.058-7.086-11.452-16.81-11.452-27.553v-14.57c0-4.139 3.374-7.482 7.512-7.482s7.483 3.344 7.483 7.482v14.57c0 6.576 2.721 12.586 7.059 16.923 4.365 4.365 10.347 7.059 16.951 7.059h267.651c-3.855-10.942-5.953-22.733-5.953-35.009 0-10.771 1.645-21.204 4.678-31.067 3.146-10.177 7.767-19.701 13.606-28.291 2.324-3.43 7.001-4.309 10.402-1.983 3.402 2.324 4.28 7.001 1.956 10.403-5.017 7.398-8.985 15.562-11.679 24.265-2.551 8.391-3.939 17.349-3.939 26.674 0 25.031 10.147 47.708 26.531 64.092 16.413 16.413 39.062 26.561 64.093 26.561 25.03 0 47.707-10.148 64.119-26.561 16.386-16.384 26.534-39.061 26.534-64.092 0-25.03-10.148-47.708-26.534-64.092-16.412-16.413-39.089-26.561-64.119-26.561-9.779 0-19.19 1.56-27.979 4.394-9.127 2.976-17.631 7.341-25.227 12.868zm-32.826-153.753h88.555c4.139 0 7.513 3.345 7.513 7.483v88.556c0 4.167-3.374 7.512-7.513 7.512h-88.555c-4.139 0-7.512-3.345-7.512-7.512v-88.556c.001-4.138 3.374-7.483 7.512-7.483zm81.072 14.995h-73.56v73.561h73.56zm-214.076 111.773h88.556c4.167 0 7.512 3.373 7.512 7.512v88.555c0 4.139-3.345 7.512-7.512 7.512h-88.556c-4.139 0-7.483-3.373-7.483-7.512v-88.555c0-4.139 3.345-7.512 7.483-7.512zm81.073 15.023h-73.56v73.561h73.56zm-81.073-141.791h88.556c4.167 0 7.512 3.345 7.512 7.483v88.556c0 4.167-3.345 7.512-7.512 7.512h-88.556c-4.139 0-7.483-3.345-7.483-7.512v-88.556c0-4.138 3.345-7.483 7.483-7.483zm81.073 14.995h-73.56v73.561h73.56zm-214.076 111.773h88.583c4.139 0 7.484 3.373 7.484 7.512v88.555c0 4.139-3.345 7.512-7.484 7.512h-88.583c-4.139 0-7.483-3.373-7.483-7.512v-88.555c0-4.139 3.345-7.512 7.483-7.512zm81.072 15.023h-73.56v73.561h73.56zm-81.072-141.791h88.583c4.139 0 7.484 3.345 7.484 7.483v88.556c0 4.167-3.345 7.512-7.484 7.512h-88.583c-4.139 0-7.483-3.345-7.483-7.512v-88.556c0-4.138 3.345-7.483 7.483-7.483zm81.072 14.995h-73.56v73.561h73.56zm252.684-171.299c-2.693 0-5.16 1.105-6.946 2.891-1.785 1.786-2.891 4.225-2.891 6.917v23.557 23.557c0 2.692 1.105 5.158 2.891 6.944s4.253 2.892 6.946 2.892c2.692 0 5.131-1.105 6.916-2.892 1.786-1.786 2.892-4.252 2.892-6.944v-23.557-23.557c0-2.692-1.105-5.131-2.892-6.917-1.785-1.785-4.223-2.891-6.916-2.891zm-305.975 2.665c-1.786-1.785-4.252-2.891-6.945-2.891-2.692 0-5.159 1.105-6.945 2.891s-2.891 4.252-2.891 6.945v23.755 23.358c0 2.693 1.105 5.159 2.891 6.945 1.786 1.785 4.253 2.891 6.945 2.891 2.693 0 5.159-1.105 6.945-2.891s2.891-4.252 2.891-6.945v-23.358-23.755c0-2.694-1.106-5.159-2.891-6.945zm97.371-2.891c-2.722 0-5.159 1.105-6.945 2.891s-2.891 4.252-2.891 6.945v23.755 23.358c0 2.693 1.105 5.159 2.891 6.945 1.786 1.785 4.224 2.891 6.945 2.891 2.692 0 5.13-1.105 6.916-2.891s2.892-4.252 2.892-6.945v-23.358-23.755c0-2.693-1.105-5.159-2.892-6.945-1.786-1.786-4.224-2.891-6.916-2.891zm104.287 0c-2.692 0-5.159 1.105-6.944 2.891-1.786 1.786-2.892 4.252-2.892 6.945v23.755 23.358c0 2.693 1.105 5.159 2.892 6.945 1.785 1.785 4.252 2.891 6.944 2.891 2.693 0 5.159-1.105 6.945-2.891s2.892-4.252 2.892-6.945v-23.358-23.755c0-2.693-1.105-5.159-2.892-6.945-1.786-1.786-4.252-2.891-6.945-2.891z"/></g></svg>
            <div class="mt-2">
                <p class="w-64 text-center">Planilla de horarios de ingreso de todo el personal de la empresa actualizado</p>
            </div>
            <div class="bottom-2 left-3 absolute"> 
                <a href="#"><button class="font-bold transform hover:scale-110 transition duration-700 rounded border-2 bg-blue-900 text-white border-blue-900 w-24 h-8 mx-auto">Leer m�s...</button></a>
            </div>  
        </div>
    </div>  
        <!-- Footer -->
    <div class="bg-gray-700 h-12 bottom-0 xl:fixed sm:static md:static w-screen flex justify-center items-center">
        <p class="text-gray-400">Creado con mucho amor por <a class="text-white hover:text-green-300" href="#">Alejandro</a></p>  
    </div>
</body>
</html>
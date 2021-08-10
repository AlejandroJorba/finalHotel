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
    <%
        HttpSession sesion = request.getSession();
        String usu = (String) sesion.getAttribute("user");
        
        if (usu == null) {
            response.sendRedirect("index.jsp");
        }
    %>
<body class="flex flex-col bg-gray-300 w-screen h-screen font-sans overflow-x-hidden">
    <!-- Header -->
        <div class="flex flex-grow bg-gray-700 w-screen justify-center">
            <a class="mb-2.5 transform focus:rotate-180 transition duration-1000" href="inicio.jsp"><svg class="fill-current text-gray-900 transform hover:rotate-180 transition duration-700" height="100pt" viewBox="0 -16 512 512" width="100pt" xmlns="http://www.w3.org/2000/svg"><path d="m317 360c-8.285156 0-15 6.714844-15 15 0 7.992188-7.476562 15-16 15h-15v-48.371094c17.984375-8.183594 30-29.382812 30-41.628906 0-17.382812-18.925781-30-45-30s-45 12.617188-45 30c0 12.246094 12.015625 33.445312 30 41.628906v48.371094h-15c-8.523438 0-16-7.007812-16-15 0-8.285156-6.714844-15-15-15s-15 6.714844-15 15c0 12.089844 4.945312 23.472656 13.921875 32.054688 8.730469 8.347656 20.125 12.945312 32.078125 12.945312h60c11.953125 0 23.347656-4.597656 32.078125-12.945312 8.976563-8.582032 13.921875-19.964844 13.921875-32.054688 0-8.285156-6.714844-15-15-15zm-61-60c6.367188 0 11.089844 1.269531 13.808594 2.460938-2.730469 5.261718-9.136719 12.539062-13.808594 12.539062s-11.078125-7.277344-13.808594-12.539062c2.71875-1.191407 7.441406-2.460938 13.808594-2.460938zm0 0"/><path d="m512 90c0-49.625-40.375-90-90-90-32.578125 0-62.375 17.777344-78.195312 45.453125-27.398438-9.996094-56.972657-15.453125-87.804688-15.453125s-60.40625 5.457031-87.804688 15.453125c-15.820312-27.675781-45.617187-45.453125-78.195312-45.453125-49.625 0-90 40.375-90 90 0 27.21875 12.390625 52.78125 33.09375 69.722656-21.054688 37.023438-33.09375 79.773438-33.09375 125.277344 0 142.058594 115.527344 195 256 195 142.070312 0 256-54.042969 256-195 0-45.503906-12.039062-88.253906-33.09375-125.277344 20.703125-16.941406 33.09375-42.503906 33.09375-69.722656zm-482 0c0-33.085938 26.914062-60 60-60 20.601562 0 39.539062 10.660156 50.4375 27.503906-35.996094 18.214844-67.109375 44.664063-90.84375 76.835938-12.3125-11.25-19.59375-27.308594-19.59375-44.339844zm226 360c-123.542969 0-226-41.363281-226-165 0-124.066406 101.382812-225 226-225s226 100.933594 226 225c0 123.519531-102.265625 165-226 165zm206.40625-315.660156c-23.734375-32.171875-54.847656-58.621094-90.84375-76.835938 10.898438-16.84375 29.835938-27.503906 50.4375-27.503906 33.085938 0 60 26.914062 60 60 0 17.03125-7.28125 33.089844-19.59375 44.339844zm0 0"/><path d="m150 255c0 8.285156-6.714844 15-15 15s-15-6.714844-15-15 6.714844-15 15-15 15 6.714844 15 15zm0 0"/><path d="m392 255c0 8.285156-6.714844 15-15 15s-15-6.714844-15-15 6.714844-15 15-15 15 6.714844 15 15zm0 0"/><path d="m204.195312 268.929688c18.046876-43.167969 6.175782-94.964844-34.480468-111.964844-40.832032-17.070313-85.953125 11.152344-103.910156 54.105468-18.046876 43.167969-6.175782 94.964844 34.480468 111.964844 40.933594 17.113282 86.007813-11.28125 103.910156-54.105468zm-57.699218 23.203124c-11.84375 6.46875-24.140625 7.613282-34.640625 3.222657-22.890625-9.570313-31.136719-42.1875-18.375-72.710938 11.585937-27.710937 40.171875-48.242187 64.660156-38 22.894531 9.570313 31.136719 42.1875 18.378906 72.710938-6.417969 15.355469-17.082031 27.703125-30.023437 34.777343zm0 0"/><path d="m342.285156 156.964844c-40.859375 17.082031-52.425781 69.046875-34.480468 111.964844 18.3125 43.800781 63.746093 70.894531 103.910156 54.105468 40.832031-17.070312 52.441406-69.011718 34.480468-111.964844-18.058593-43.199218-63.277343-71.09375-103.910156-54.105468zm57.859375 138.390625c-25.007812 10.457031-53.328125-10.886719-64.664062-38-12.757813-30.523438-4.515625-63.140625 18.378906-72.710938 25-10.457031 53.328125 10.890625 64.660156 38 12.757813 30.523438 4.515625 63.140625-18.375 72.710938zm0 0"/></svg>
            </a>
            <p class="text-green-400 text-4xl ml-10 self-center">Panda</p><p class="text-gray-400 text-4xl self-center">Hotel</p>
        </div>
    <!-- Espaciadora del Header -->
    <div class="flex justify-center bg-gray-900 h-10w-screen p-2">
        <li class="flex self-center space-x-8 text-gray-400">
            <a class="hover:text-green-300" href="inicio.jsp">Inicio</a>
            <a class="border-b-2 border-green-300 text-green-300" href="registros.jsp">Registros</a>
            <a class="hover:text-green-300" href="consultas.jsp">Consultas</a>
        </li>
    </div>
    <!-- Formulario -->
    <div class="flex justify-center mt-2">
        <p class="px-2 text-center text-xl border-b-2 border-gray-600 text-gray-900">Registro de reservas</p> 
    </div>     
    <div class="pointer-events-none flex flex-row mx-auto items-center h-screen">
            <div class="grid grid-cols-2 divide-y-4 divide-gray-400 items-center mt-4 mb-4 gap-x-4 gap-y-2 bg-white p-4 rounded-3xl shadow-2xl">
                <p class="font-bold text-lg text-green-700 col-span-2 mx-auto">¡Registro satisfactorio!</p>
                <p class="font-bold border-t-2 text-lg">Personas</br><label class="font-bold text-blue-600"><%=session.getAttribute("cant_personas")%></label></p>
                <p class="font-bold text-lg">Número de Habitación</br><label class="font-bold text-blue-600"><%=session.getAttribute("num_habitacion")%></label></p>
                <p class="font-bold text-lg">Check-in</br><label class="font-bold text-blue-600"><%=session.getAttribute("check_in")%></label></p>
                <p class="font-bold text-lg">Check-out</br><label class="font-bold text-blue-600"><%=session.getAttribute("check_out")%></label></p>
                <p class="font-bold text-lg">DNI</br><label class="font-bold text-blue-600"><%=session.getAttribute("dni")%></label></p>
                <p class="font-bold text-lg">Nombre</br><label class="font-bold text-blue-600"><%=session.getAttribute("nombre")%></label></p>
                <p class="font-bold text-lg">Apellido</br><label class="font-bold text-blue-600"><%=session.getAttribute("apellido")%></label></p>
                <p class="font-bold text-lg">Fecha de Nacimiento</br><label class="font-bold text-blue-600"><%=session.getAttribute("nacimiento")%></label></p>
                <p class="font-bold text-lg">Dirección</br><label class="font-bold text-blue-600"><%=session.getAttribute("direccion")%></label></p>
                <p class="font-bold text-lg">Profesión</br><label class="font-bold text-blue-600"><%=session.getAttribute("profesion")%></label></p>
                <p class="font-bold text-lg">Alta hecha por</br><label class="font-bold text-blue-600"><%=session.getAttribute("usuarioRes")%></label></p>
            </div>
        </div>
        <!-- Footer -->
        <div class="bg-gray-700 p-3 flex justify-center items-center">
            <p class="text-gray-400">Creado con mucho amor por <a class="text-white hover:text-green-300" href="#">Alejandro</a></p>  
        </div>
</body>
</html>


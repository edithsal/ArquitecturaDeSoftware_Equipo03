Proyecto Parchis_MVC

Este proyecto implementa el caso de uso “Ejercer Turno” utilizando el modelo MVC (Modelo-Vista-Controlador)
El objetivo es permitir la ejecución de un turno entre dos jugadores conectados mediante sockets TCP/IP

Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

controlador

main

modelo

socket

vista

Dentro del paquete main se encuentran las clases principales:

ServidorMain.java → Clase que ejecuta el servidor del juego

ClienteMain.java → Clase que ejecuta el cliente del jugador

También se incluye la carpeta Documentacion_UML, donde se encuentran los diagramas UML utilizados en el desarrollo del proyecto

Configuración de Red

El proyecto utiliza sockets TCP/IP para permitir la comunicación entre el servidor (Jugador 1) y el cliente (Jugador 2)

El Jugador 1 (Emmanuel) será quien actúe como servidor

En el archivo ClienteMain.java, se encuentra la variable ipServidor, la cual debe contener la dirección IP del equipo de Emmanuel

Ejemplo: ipServidor = "192.168.1.3";

El Jugador 2 (Edith) debe colocar esa misma IP en su proyecto antes de ejecutar su cliente

Pasos para Ejecutar el Proyecto
Paso 1. Ejecutar el Servidor (Jugador 1)

Emmanuel abre el proyecto en NetBeans

En el paquete main, ejecuta la clase ServidorMain.java

Espera el mensaje que indica que el servidor está corriendo y escuchando conexiones

Paso 2. Ejecutar el Cliente del Jugador 1

En el mismo equipo, Emmanuel ejecuta también la clase ClienteMain.java

Esto iniciará la interfaz del Jugador 1 (Servidor conectado)

Paso 3. Ejecutar el Cliente del Jugador 2

Edith abre el proyecto en su computadora

En el archivo ClienteMain.java, revisa que la variable ipServidor contenga la dirección IP del equipo de Emmanuel

Ejecuta la clase ClienteMain.java

Si todo está configurado correctamente, el cliente se conectará al servidor y se podrá jugar

Ejemplo de Ejecución

Emmanuel ejecuta ServidorMain.java

Emmanuel ejecuta ClienteMain.java

Edith ejecuta ClienteMain.java

Ambos jugadores se conectan y pueden realizar el caso de uso “Ejercer Turno”


Documentación UML

En la carpeta Documentacion_UML se encuentran los siguientes diagramas:

Diagrama de clases

Diagrama de casos de uso

Diagrama de secuencia del caso “Ejercer Turno”

Diagrama de actividades

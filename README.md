<h1 align="center"> FOROHUB </h1>

<h3 align="center">
:muscle: Desafio Forohub planteado por Oracle y Alura Latam, donde realizamos una API REST usando Java Spring Boot :muscle:
</h3>

<p>Un lugar donde las personas puedan crear sus tópicos con sus dudas o con sugerencias y que otras personas pueden también responder e interactuar </p>

## :sunglasses: Datos Adicionales 

<p>El desafio esta completamente terminado, tiene end-points para hacer las diferentes acciones del CRUD tanto para cursos, topicos y respuestas. Aunque solo se planteaba realizar
el challenge para Topicos, se vio conveniente realizar las demas tablas propuestas en el diagrama entidad relacion, como cursos, y respuestas, para tener el proyecto mas completo</p>

## :hammer: Configuraciones del proyecto a tener en cuenta

- `Descargar peoyecto`: descargue este proyecto y abralo en IntelliJ IDEA
- `Crear base de datos`: crea una base de datos en MySQL llamada forohubcompleto
- `Crear un Usuario`: Desde mysql Workbench crea un usuario. Al crear la contraseña ve a https://bcrypt-generator.com/ y desde alli, encriptala. Para ejemplo encriptar la pasword 123456 y pegar en tu contrasena de Mysql el hash encriptado
- 
![6  encriptar](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/68e0d5bb-b3fa-401a-a867-a0e15d0e2e1e)

![user](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/a04e95b5-1f37-4d0a-af45-eb949191c635)

- `Correr el proyecto`: en la carpeta com.talesfunmedia.forohubcompleto, dentro de la clase ForoHubCompletoApplication.java, clic derecho Run
- `Abrir documentacion Swagger`: Se implemento Swagger para documentar la aplicación, con el objetivo de generar interfaz amigable. Para esto, en su navegador de preferencia, ingresa la url:  http://localhost:8080/swagger-ui/index.html#/
  
![1  url](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/66e2b77c-95f0-44b8-94f3-9db9a3861b1c)

`loguearnos`: nos logueamos con el email y la contrasena. Desde aqui no ponemos la password hasheada sino la contraseña normal: ejm: 123456 Para esto en autenticationController, damos clic en el botón  Try it out, ingresamos el correo y la contraseña y damos clic en el botón execute. aquí un ejemplo:

![autnticar](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/4e960e23-2940-487f-b1d4-225ef762a457)

- `Copiar token`: Copiamos el token generado sin las comillas de inicio y fin  (“”)

![3  token generado](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/b669fae6-11cc-4122-b258-5049926ca812)

- `Authorize`: En la parte superior derecha de la documentación, al inicio de esta, ingresa al botón Authorize

![4  clic boton autorizar](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/2f43c99e-4523-4c5a-990d-dd574fad1384)

- `guardar Token`: Copiamos el token y damos clic en authorize, luego en close para que quede nuetro token guardado

![guardarTokenFinal](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/8f6e4611-710e-4fda-9c97-d13b71673e26)


- `Usar la app`: Ya podemos usar nuestra API foroHub creando, modificando, eliminando y mostrando cursos, topicos y respuestas. Por ejemplo listemos los topicos:

![5  listadoTopicos](https://github.com/WilberBallesteros/forohubcompleto/assets/91759897/5984b2fa-2608-44fb-bf2d-caa6ba5f2774)



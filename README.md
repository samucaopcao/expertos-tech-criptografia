# Sejam bem vindos(as) ao meu repositório : expertos-tech-criptografia

<br>
😃&nbsp&nbspEste repositório baseia-se em um BootCamp do Santander o qual cria uma API REST de Gerenciamento de Pessoas.
<br><br>
<h4>&nbspNesse projeto utilizamos:</h4>
<br>
<blockquote>*&nbspJava 11;
<br>
*&nbspMaven 3.6.3;
<br>
*&nbspHeroku (aplicação de nome expertos-tech-criptografia);
<br>
*&nbspGitHub (repositório samucaopcao/expertos-tech-criptografia);</blockquote>
<br>
<h4>:sunglasses:&nbsp Lembretes:</h4>
<br>
*&nbspLink para acesso da aplicação na plataforma Heroku:
<br>https://dashboard.heroku.com/apps 
<br>https://expertos-tech-criptografia.herokuapp.com/
<br>
<h4>&nbspCriação de Modelo de Dados</h4>
<br>
*&nbspRealizamos a configuração do BD H2 no arquivo application.properties, uma boa fonte<br>&nbsp&nbsp para modificações no h2 estão localizadas no site a seguir: https://gasparbarancelli.com/post/banco-de-dados-h2-com-spring-boot
<br><br>
*&nbspAo acessar o link http://localhost:8080/h2-console devemos preencher os dados na tela abaixo conforme os<br>&nbsp&nbspdados presentes no application.properties 
<img width="342" alt="Conexao_H2" src="https://user-images.githubusercontent.com/59769434/144733814-e9f94ea4-f641-4c8c-8ceb-c140f8d55982.png" align="center">
<br>
*&nbspPara a criação do GUID utilizamos o site https://www.guidgenerator.com/

<h4>&nbsp&nbspEnd Points - PostMan</h4>
<br>
*&nbsp<b>GET - Listar Todos:</b> http://localhost:8080/api/usuario/listarTodos
<br><br>
*&nbsp<b>GET - Validar Senha:</b> http://localhost:8080/api/usuario/validarSenha?login=sasuke&password=teste
<br><br>
*&nbsp<b>POST - Salvar:</b> http://localhost:8080/api/usuario/salvar<br>
*&nbspExemplo de Body:
<br>
{<br>
"login": "sasuke",<br>
"password": "teste"<br>
}<br>
<br>
&nbsp<b>POST - Obter Token:</b> http://localhost:8080/login

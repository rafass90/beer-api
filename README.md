# Beer-api:beers:  

Ferramentas utilizadas no desenvolvimento do app:
  - Eclipse
  - JDK8
  - Gradle
  - Postman
  - Docker
  - Gradle

Soluções Cloud para hospedagem da solução:
- Heroku(https://www.heroku.com/)
- mLab(https://mlab.com/)

Frameworks utilizados:
  - Springboot(http://spring.io/projects/spring-boot)
  - SpotifyAPI(https://github.com/thelinmichael/spotify-web-api-java)
  - Swagger(https://springfox.github.io/springfox/)
  - JUnit(https://junit.org/junit5/)


A api está disponível para consumo na plataforma cloud Heroku, através link:
> https://ciclic-beer-api.herokuapp.com/

Há um arquivo na raiz do projeto(cyclic.postman.json) que pode ser importado no Postman para testes usando interface gráfica da API(necessário alterar a url).
Para executar os testes unitários, executar o comando ``` ./gradle test``` na raiz do projeto, ou via IDE.
Através da URL `https://ciclic-beer-api.herokuapp.com/swagger-ui.html`, pode ser acessada a documentação dos endpoints gerada pelo Swagger.

Para executar o projeto localmente, inserir via linha de comando no diretrio raiz do projeto
  Requisitos: JDK8, Gradle
  
  Comando: ``` ./gradle build``` para compilar a aplicação.
  
  Comando: ``` ./gradle bootRun``` para iniciar o tomcat.

Para executar o projeto sem necessidade de instalar JDK, Gradle/maven:

  Requisitos: docker instalado

  Comando: ``` docker run -p 5000:8080 rafass90/spring-boot-beer-api:1.0```

Após download da imagem e start da aplicaço, a mesma ficará disponível no endereço `http://127.0.0.1:5000`.

Para esta aplicação, não foi adicionada nenhuma restrição de segurança/CORS para facilitar os testes

**IMPORTANTE**

As branches develop e master estão utilizando base de dados diferentes. Ambas hospedadas no mLab.

**Para deploy da aplicação(no Heroku), basta publicar alteração na branch _MASTER_**

- Commit no README.md :innocent:

- Merge develop -> master :innocent:





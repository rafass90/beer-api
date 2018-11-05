# beer-api
##### Teste java utilizando Java, microsserviço com integração com Spotify

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


O micro serviço está disponível para consumo na plataforma cloud Heroku, através link:
> https://ciclic-beer-api.herokuapp.com/

Há um arquivo na raiz do projeto(cyclic.postman.json) que pode ser importado no Postman para testes usando interface gráfica da API(necessário alterar a url).
Para executar os testes unitários, executar o comando ``` ./gradle test``` na raiz do projeto, ou via IDE.

Para executar o projeto localmente executar o comando abaixo no diretrio raiz do projeto
  Requisitos: JDK8, Gradle
  Comando: ``` ./gradle build``` para compilar a aplicação.
  Comando: ``` ./gradle bootRun``` para iniciar o tomcat.

Para executar o projeto sem necessidade de instalar JDK, Gradle/maven:
  Requisitos: docker instalado
  Comando: ``` docker run -p 5000:8080 rafass90/spring-boot-beer-api:0.0.2```
Após download da imagem e execução do serviço, a aplicação ficará disponível no endereço `http://127.0.0.1:5000`.





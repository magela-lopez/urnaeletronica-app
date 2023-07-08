# Urna Eletrônica - Sistema de Votação

Este é um sistema de urna eletrônica desenvolvido em Java usando o framework Spring Boot. O sistema permite realizar eleições com votação para presidente, governador, senador, deputado federal e deputado estadual. Ele também calcula automaticamente os resultados do primeiro turno e, se aplicável, do segundo turno.

# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Swagger
- Hibernate
  
## Funcionalidades

O sistema possui as seguintes funcionalidades:

- **Candidatos**: Os candidatos são carregados previamente no sistema por meio de um script SQL. As informações dos candidatos, como nome, partido e número eleitoral, são armazenadas no banco de dados.

- **Eleições**: As eleições, tanto o primeiro turno como o segundo turno, também são carregadas previamente no sistema por meio de um script SQL. O sistema armazena informações sobre as eleições, como a data e o tipo (primeiro turno ou segundo turno).

- **Votação Primeiro Turno**: O sistema permite o envio de votos para o sistema de forma automática. Cada requisição representa um voto para presidente, governador, senador, deputado federal e deputado estadual. O payload de um voto contém a escolha para cada cargo. É possível votar em branco ou nulo.

- **Cálculo do resultado do primeiro turno**: O sistema oferece uma API para calcular o resultado do primeiro turno. Após o cálculo, o sistema retorna os deputados federais eleitos, os deputados estaduais eleitos e os senadores. Para governador e presidente, o sistema verifica se houve um candidato eleito no primeiro turno ou se haverá segundo turno.

- **Votação do segundo turno**: O sistema permite a votação no segundo turno apenas para presidente e governador. O payload de um voto no segundo turno contém a escolha para presidente e governador. Essa funcionalidade só é ativada caso não tenha sido eleito um candidato no primeiro turno para um dos cargos.

- **Cálculo do segundo turno**: O sistema permite calcular o resultado do segundo turno e retornar o ganhador da eleição para governador e presidente, caso tenham ocorrido no segundo turno.

## Configuração

Para executar o projeto, siga as etapas abaixo:

1. Certifique-se de ter o Java 17 instalado em sua máquina.

2. Configure o banco de dados MySQL e atualize as informações de conexão no arquivo `application.properties`.

3. Para garantir que o script SQL será executado corretamente verifique que a linha 'spring.sql.init.mode= ' esteja em modo 'always' após o primeiro uso poderá modificar para 'embedded'. Se preferir execute os scripts SQL fornecidos para carregar os candidatos, os partidos e os cargos no banco de dados.

4. Compile e execute o projeto usando o Maven ou sua IDE preferida.

## Endpoints da API

A API oferece os seguintes endpoints:

- `/candidatos`: Permite listar todos os candidatos.
- `/candidatos/findbypartido/{nomepartido}`: Retorna uma lista de candidatos de um partido específico.
- `/candidatos/findbycargo/{nomecargo}`: Retorna uma lista de candidatos para um cargo específico.
- `/candidatos/findbyname/{nome}`: Retorna uma lista de candidatos com base em um nome específico.
- `/candidatos/getByNumber/{numeroEleitoral}`: Retorna uma lista de candidatos com base em um número eleitoral específico.

- `/votosCandidatos`: Permite inserir votos para os candidatos de forma automatica. O usuário não irá adicionar os votos manualmente
- `/votosCandidatos/resultado`: Retorna o resultado da votação.
- `/votosCandidatos/resultadoCandidato/{candidato}`: Retorna o resultado da votação para um candidato específico.
- `/votosCandidatos/resultadoCargo/{cargo}`: Retorna o resultado da votação para um cargo específico.

- `/eleicao`: Permite inserir um novo turno de eleição.
- `/eleicao`: Retorna os resultados das eleições.

## Estrutura do Projeto

O projeto está estruturado da seguinte maneira:

- `src/main/java`: Contém o código-fonte principal do projeto.
  - `com.simulado.urna`: Pacote raiz do projeto.
    - `controller`: Contém os controladores (controllers) responsáveis por lidar com as requisições HTTP.
    - `model`: Contém as classes de modelo que representam as entidades do sistema, como Candidatos, Cargos, Partidos, etc.
    - `repository`: Contém as interfaces de repositório (repositories) que definem as operações de acesso aos dados.
    - `service`: Contém as classes de serviço (services) que implementam a lógica de negócio do sistema.
- `src/main/resources`: Contém os recursos do projeto.
  - `templates`: Contém os templates HTML usados para a renderização das páginas da interface do usuário.
  - `application.properties`: Arquivo de configuração do Spring Boot que define as configurações do banco de dados, servidor, entre outros.
  - `data.sql`: Arquivo de script SQL para inserção de dados iniciais no banco de dados.
- `pom.xml`: Arquivo de configuração do Maven que define as dependências e plugins do projeto.

Essa é a estrutura básica do projeto, seguindo as convenções do Spring Boot. No pacote `controller`, você encontrará os controladores que definem os endpoints da API. No pacote `model`, estão as classes que representam as entidades do sistema. O pacote `repository` contém as interfaces de repositório que permitem acessar os dados do banco de dados. Por fim, no pacote `service`, estão as classes que implementam a lógica de negócio.


## Executando o Projeto

Para executar o projeto, siga as etapas abaixo:

1. Certifique-se de ter o Java Development Kit (JDK) instalado em sua máquina. Você pode baixar o JDK mais recente em: [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

2. Certifique-se de ter o Apache Maven instalado em sua máquina. Você pode baixar o Maven em: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi). Siga as instruções de instalação para o seu sistema operacional.

3. Certifique-se de ter um servidor MySQL instalado e em execução em sua máquina. Você pode baixar o MySQL Community Server em: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/). Siga as instruções de instalação para o seu sistema operacional.

4. Crie um banco de dados chamado "urna" no seu servidor MySQL.

5. No arquivo `src/main/resources/application.properties`, verifique e atualize as configurações do banco de dados, se necessário. Certifique-se de fornecer o nome de usuário e senha corretos para acessar o banco de dados.

6. No diretório raiz do projeto, execute o seguinte comando para construir o projeto e gerar o pacote JAR:

   ```shell
   mvn clean package
   ```

7. Após o sucesso da construção, execute o seguinte comando para iniciar o aplicativo:

   ```shell
   java -jar target/urnaeletronica-app-0.0.1-SNAPSHOT.jar
   ```

8. O aplicativo será iniciado e estará disponível em [http://localhost:9090](http://localhost:9090).


## Contribuição

Sinta-se à vontade para contribuir com melhorias, correções de bugs e novas funcionalidades. Basta enviar um pull request para ser revisado.


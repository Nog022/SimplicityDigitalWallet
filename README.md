# Simplicity Digital Wallet

O **SimplicityDigitalWallet** é um projeto desenvolvido em Java utilizando o framework Spring Boot, implementando um sistema de carteira digital. O sistema permite que os usuários criem suas carteiras para guardar valores monetários, realizem transações financeiras, consultem saldos e gerenciem suas finanças de forma simples e eficiente. Este projeto foi desenvolvido como parte da disciplina de **Desenvolvimento Web Avançado** da **Universidade Federal Fluminense (UFF)**, com o objetivo de aplicar conceitos de desenvolvimento web, segurança de aplicações web e integração com bancos de dados relacionais.

A arquitetura segue boas práticas de desenvolvimento de software, com separação clara de responsabilidades entre as camadas de controle, serviço e persistência de dados. Além disso, a aplicação conta com documentação interativa via Swagger UI para facilitar o uso e testes da API.

__Documentação Inicial do Projeto:__ [Clique aqui](https://docs.google.com/document/d/1p8bw2B7PS5jwL4zy6NnebLbMIKKLujDMHNHPsuI73rU/edit?usp=sharing)

## Índice

- [Funcionalidades Principais](#funcionalidades-principais)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Ferramentas e Frameworks utilizados no Back-End](#ferramentas-e-frameworks-utilizados-no-back-end)
- [Execute o Projeto](#execute-o-projeto)
- [Contatos](#contatos)
- [Licença](#licença)

## Funcionalidades Principais

- **Criação de Usuário**: Criação e manutenção do perfil do usuário.
- **Gerenciamento de Carteira**: Criação e manutenção de carteiras digitais.
- **Transações Financeiras**: Suporte para depósitos, saque e transferências entre contas.
- **Consultas de Saldo**: Verificação do saldo atual da conta.
- **Segurança**: Implementação de autenticação e autorização utilizando Spring Security.
- **Documentação da API**: Disponível através do Swagger UI para facilitar a interação e testes com a API.

## Estrutura do Projeto

A estrutura do projeto segue a convenção padrão do Maven:

```
SimplicityDigitalWallet/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/simplicity/wallet/digital/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   │       │       └── serviceImpl/
│   │   │       └──dto
│   │   │       └── config
│   │   │       └── exception
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
├── pom.xml
└── README.md
```
## Ferramentas e Frameworks utilizados no Back-End

<img align="center" alt="Java" height="30" width="40" src="https://github.com/devicons/devicon/blob/master/icons/java/java-original.svg">  
<img align="center" alt="SpringBoot" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/spring/spring-original-wordmark.svg">
<img align="center" alt="MYSQL" height="60" width="60" src= https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/mysql/mysql-original.svg>
<img align="center" alt="jwt" height="50" width="50" src="https://img.icons8.com/?size=512&id=rHpveptSuwDz&format=png">

## Execute o Projeto

### Backend 🧱
__Pré-requisitos:__ Java 21, Maven 3.8.1 ou superior e MySQL 8.0.33.

__Clone o repositório do projeto__
```bash
git clone https://github.com/Nog022/SimplicityDigitalWallet.git
```
### Configurando o projeto local 🏠

__Configurando o ambiente:__

- Navegue até o diretório do projeto.
```bash
cd SimplicityDigitalWallet
```
__Acesse o diretório do projeto, utilize o comando mvn install para instalar todas as dependências necessárias:__

- Para sistemas Unix/Lunix
```bash
./mvnw clean install
```
- Para sistemas Windows
```bash
mvnw.cmd clean install
```
### Propriedades
Antes de executar a aplicação, configure as propriedades necessárias em: `src/main/resources/application.properties`.

#### Banco de Dados
Ao configurar as propriedades do banco de dados basta criar a database no MySQL. O Hibernate irá gerer automaticamente as tabelas ao executar a aplicação.
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/simplicity_wallet
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

#### JPA e Hibernate
Defina as propriedades relacionadas ao JPA e Hibernate para o gerenciamento das entidades:
```bash
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### JPA e Hibernate
Configure as propriedades relacionadas à segurança, como chaves para JWT, no arquivo `application.properties`:
```bash
jwt.secret=seu_segredo_jwt
```
### Execução

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

Para acessar a documentação interativa da API, utilize o Swagger UI em `http://localhost:8080/swagger-ui.html`.

## Contatos

Para mais informações sobre o projeto ou para entrar em contato, você pode me encontrar através dos canais abaixo:

### Breno Braido
<div style="display: inline_block">
<a href="mailto:brenosilvabraido1998@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
</div>

### Rodrigo Nogueira
<div style="display: inline_block">
<a href="mailto:nogueiragm22@hotmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
</div>

### Lucas Magalhães
<div style="display: inline_block">
<a href="mailto:" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
</div>

## Licença

Este projeto está licenciado sob os termos da licença XXX.

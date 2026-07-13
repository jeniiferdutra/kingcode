# 🧛 KingCode - Stephen King Catalogo

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![JPA / Hibernate](https://img.shields.io/badge/JPA%20%2F%20Hibernate-Persist%C3%AAncia-blue?logo=hibernate)](https://hibernate.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Banco_de_Dados-blue?logo=postgresql)](https://www.postgresql.org/)

**KingCode** é uma aplicação robusta desenvolvida em Java com Spring Boot que atua como um catálogo interativo do universo literário de Stephen King (o "Macroverso"). O sistema integra-se a uma API externa pública para buscar dados brutos de livros e vilões, salvando-os de forma inteligente em um banco de dados relacional local.

O projeto oferece **duas formas de interação**: uma interface interativa via console (CLI) e uma API Web RESTful de alto desempenho.

---

## 🚀 Diferenciais Técnicos & Boas Práticas (Recruiter Zone)

Este projeto foi construído aplicando padrões modernos de arquitetura de software para o ecossistema Java corporativo:

*   **Separação Estrita de DTOs (Data Transfer Objects):** Uso de Java Records distintos para desserialização de payloads de APIs externas (`DadosLivro`, `DadosVilao`) e para entrega de dados limpos ao cliente via endpoints Web (`LivroDTO`). Isso previne vazamento de dados e acoplamento desnecessário.
*   **Mapeamento Otimizado com `@ElementCollection`:** Em vez de complicar o modelo de dados com relacionamentos bidirecionais pesados para dados de texto simples, a lista de vilões associada a cada livro é persistida dinamicamente usando coleções de elementos do JPA, garantindo consultas rápidas.
*   **JPQL Customizado com Case Insensitivity:** Resolução de limitação nativa do Spring Data em coleções através de query JPQL customizada (`@Query`) acoplada a funções de banco de dados (`LOWER` e `CONCAT`), permitindo buscas dinâmicas e insensíveis a maiúsculas/minúsculas.
*   **Inversão de Controle e Injeção de Dependências:** Arquitetura limpa estruturada nas camadas clássicas do Spring (Controller -> Service -> Repository -> Entity), delegando totalmente o ciclo de vida dos componentes ao Spring IoC.

---

## 🛠️ Tecnologias Utilizadas

*   **Java 17** (com recursos modernos como Records e Pattern Matching)
*   **Spring Boot 3.x**
*   **Spring Data JPA**
*   **Jackson** (para mapeamento avançado de JSON usando `@JsonAlias` e `@JsonIgnoreProperties`)
*   **Java HttpClient** (para consumo assíncrono de APIs externas)
*   **PostgreSQL** (como banco de dados relacional de produção)

---

## 🎨 Funcionalidades da Aplicação

### 🖥️ 1. Interface de Console (CLI)
Um menu dinâmico no terminal que permite ao usuário interagir com o sistema:
1.  **Buscar livro na API externa:** Consulta o título na API do Stephen King, filtra, valida duplicatas no banco de dados e realiza o salvamento local.
2.  **Listar todos os livros salvos:** Retorna a lista local ordenada alfabeticamente.
3.  **Filtrar por ano de lançamento:** Busca rápida indexada no banco de dados.
4.  **Pesquisar vilões:** Encontra todos os livros em que determinado vilão espalhou o terror, aceitando buscas parciais e ignorando maiúsculas/minúsculas.

### 🌐 2. API REST (Endpoints Web)
Uma camada web preparada para integração com frontends modernos:
*   `GET /books`: Retorna todos os livros cadastrados localmente com seus respectivos vilões, editoras e ISBNs formatados através do `LivroDTO`.

---

## 📋 Como Executar o Projeto

### Pré-requisitos
*   Java JDK 17 ou superior instalado.
*   Maven instalado (ou utilizando o wrapper `./mvnw`).
*   Banco de dados PostgreSQL (ou MySQL) ativo.

### 1. Configuração do Banco de Dados
No arquivo `src/main/resources/application.properties`, configure as credenciais do seu banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/kingcode_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

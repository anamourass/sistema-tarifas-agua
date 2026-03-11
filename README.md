# Tarifas Água API

API REST desenvolvida em **Spring Boot 3.2.3** que fornece dois conjuntos de
funcionalidades principais:

1. CRUD de **tabelas tarifárias** (cada tabela contém categorias e faixas de
   consumo).
2. Cálculo progressivo do valor a pagar com base em categoria e consumo
   informado.

O projeto utiliza **PostgreSQL** para persistência e está estruturado conforme
o padrão de camadas do Spring (controller → service → repository).

---

## 🔧 Tecnologias

- Java 17 (OpenJDK ou Oracle JDK)
- Spring Boot 3.2.3 (Web, Data JPA, Validation)
- PostgreSQL 14+ (testado localmente)
- Maven 3.6+
- HikariCP (conexões), Hibernate ORM
- JUnit / Spring Boot Test para testes unitários

---

## 🚀 Pré‑requisitos

1. **Java 17** instalado e `JAVA_HOME` configurado.
2. **Maven** (ou outro build tool capaz de ler o `pom.xml`).
3. **PostgreSQL** em execução (local ou remoto):
    - banco `tabela_tarifaria` (criar conforme passo abaixo)
    - usuário e senha configuráveis; por padrão `postgres`/`110216`
4. (Opcional) IDE com suporte a Maven/Spring (IntelliJ, VSCode, Eclipse).

---

## 🗄️ Configuração do banco de dados

As propriedades que apontam o datasource estão em
`src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tabela_tarifaria
spring.datasource.username=postgres
spring.datasource.password=110216

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

> **Atenção:** em produção troque `ddl-auto` para `update` ou `validate` e
> externalize credenciais via variáveis de ambiente ou *secrets*.

### Criar o esquema e popular dados

Execute os scripts SQL incluídos ou rode manualmente no psql:

```sql
CREATE DATABASE tabela_tarifaria;
\c tabela_tarifaria
\i src/main/resources/schema.sql   -- cria tabelas
\i src/main/resources/data.sql     -- insere exemplo de tabela e faixas
```

Os scripts definem as tabelas `tabela_tarifaria`, `categoria_tarifaria` e
`faixa_consumo` e inserem uma tabela tarifária com faixas para COMERCIAL,
INDUSTRIAL, PARTICULAR e PÚBLICO.

---

## 🏗️ Instalação & execução

No diretório raiz do projeto:

```bash
# clonar (se ainda não estiver em local)
git clone <url> tarifas-agua-api
cd tarifas-agua-api

# compilar
mvn clean package

# iniciar a aplicação (usando PostgreSQL configurado)
mvn spring-boot:run
# ou, após o _package_
java -jar target/tarifas-agua-api-1.0.0.jar
```

A aplicação sobe na **porta 8080** (configurável via `server.port`).

---

## 📦 Endpoints REST

### 1. Tabela Tarifária

| Método | URI                          | Descrição                     |
|--------|------------------------------|-------------------------------|
| POST   | `/api/tabelas-tarifarias`    | Criar tabela tarifária        |
| GET    | `/api/tabelas-tarifarias`    | Listar todas as tabelas       |
| DELETE | `/api/tabelas-tarifarias/{id}` | Remover tabela por ID        |

#### Exemplo (criação)

```http
POST /api/tabelas-tarifarias
Content-Type: application/json

{
  "nome": "Tabela teste",
  "dataVigencia": "2026-01-01",
  "categorias": [
    {
      "categoria": "COMERCIAL",
      "faixas": [
        { "consumoMinimo": 0, "consumoMaximo": 10, "valorTarifa": 2.5 },
        { "consumoMinimo": 11, "consumoMaximo": 20, "valorTarifa": 3.75 }
      ]
    }
  ]
}
```

> O JSON corresponde à estrutura de entidades JPA (TabelaTarifaria →
> CategoriaTarifaria → FaixaConsumo).

**Resposta 200** (exemplo):

```json
{
  "id": 2,
  "nome": "Tabela teste",
  "dataVigencia": "2026-01-01",
  "categorias": [
    {
      "id": 5,
      "categoria": "COMERCIAL",
      "faixas": [
        { "id": 12, "consumoMinimo": 0, "consumoMaximo": 10, "valorTarifa": 2.5 },
        { "id": 13, "consumoMinimo": 11, "consumoMaximo": 20, "valorTarifa": 3.75 }
      ]
    }
  ]
}
```

Outros exemplos:

- `GET /api/tabelas-tarifarias` → retorno de array JSON;
- `DELETE /api/tabelas-tarifarias/1` → status `204 No Content`.

---

### 2. Cálculo tarifário

| Método | URI            | Descrição                          |
|--------|----------------|------------------------------------|
| POST   | `/api/calculos` | Calcula valor conforme consumo     |

**Requisição**:

```json
{
  "categoria": "INDUSTRIAL",
  "consumo": 18
}
```

**Resposta**:

```json
{
  "categoria": "INDUSTRIAL",
  "consumoTotal": 18,
  "valorTotal": 26.00,
  "detalhamento": [
    {
      "faixa": { "inicio": 0, "fim": 10 },
      "m3Cobrados": 10,
      "valorUnitario": 1.00,
      "subtotal": 10.00
    },
    {
      "faixa": { "inicio": 11, "fim": 20 },
      "m3Cobrados": 8,
      "valorUnitario": 2.00,
      "subtotal": 16.00
    }
  ]
}
```

> O serviço percorre as faixas da categoria (ordenadas por `consumoMinimo`)
> e aplica progressividade, somando subtotais até atingir o consumo total.

---

## 🧪 Testes

### Unitários

```bash
mvn test
```

Há casos básicos de validação de faixas em `src/test`.

### Manual / integração

Utilize `curl`, Postman ou similar. Exemplo com curl:

```bash
curl -X POST http://localhost:8080/api/calculos \
  -H "Content-Type: application/json" \
  -d '{"categoria":"COMERCIAL","consumo":15}'
```

Verifique também `GET /api/tabelas-tarifarias` para inspecionar os dados
pré‑cadastrados.

Logs Hibernate são visíveis no console graças a `spring.jpa.show-sql=true`.

---

## 📁 Estrutura do projeto

```
src/
 ├─ main/java/com/ras/tarifas/
 │    ├─ controller/        # REST controllers
 │    ├─ dto/               # Data-transfer objects
 │    ├─ exception/         # handlers e exceções customizadas
 │    ├─ model/             # entidades JPA e serviços de domínio
 │    ├─ repository/        # interfaces Spring Data
 │    └─ service/           # lógica de negócio
 └─ resources/
      ├─ application.properties
      ├─ schema.sql
      └─ data.sql
```

---

## 📝 Observações finais

- Ajuste `spring.jpa.hibernate.ddl-auto` em ambientes não‑desenvolvimento.
- Senhas e URLs de DB devem ser externalizadas via **environment variables**.
- A validação de faixas de consumo é feita em `ValidacaoFaixasService` e
  pode ser reutilizada ou expandida.

---

## 🛠️ Como o projeto foi feito (passo a passo)

1. **Definição do escopo**
   - Identificou-se a necessidade de armazenar tabelas tarifárias e calcular
     valores de água em faixas progressivas por categoria.
   - Modelagem inicial: TabelaTarifaria → categorias → faixas de consumo.

2. **Criação do esqueleto Maven/Spring Boot**
   - `mvn archetype:generate` ou usando o Spring Initializr para gerar um
     projeto com dependências `spring-boot-starter-web`,
     `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`.
   - Ajuste do `pom.xml` adicionando driver PostgreSQL e plugin do
     Spring Boot.

3. **Configuração do banco**
   - Definidas as propriedades de conexão em
     `application.properties`
   - Habilitação do `ddl-auto=create` para desenvolvimento e arquivos de
     inicialização (`schema.sql`/`data.sql`) para criar o esquema e inserir
     dados de exemplo.

4. **Modelagem das entidades JPA**
   - `TabelaTarifaria` com `@OneToMany` para `CategoriaTarifaria`.
   - `CategoriaTarifaria` enumera `CategoriaConsumidor` e possui lista de
     `FaixaConsumo` (cascade ALL para persistência em cascata).
   - `FaixaConsumo` define limites e valor unitário; relaciona-se com categoria.
   - Enum `CategoriaConsumidor` para evitar strings livres.

5. **Repositórios Spring Data**
   - Interfaces estendendo `JpaRepository` para cada entidade.
   - Método customizado em `FaixaConsumoRepository` para buscar por categoria
     (utilizado no cálculo).

6. **Serviços de negócio**
   - `TabelaTarifariaService` com operações CRUD básicas.
   - `CalculoService` realiza o cálculo progressivo lendo faixas do banco
     e construindo `CalculoResponseDTO`.
   - `ValidacaoFaixasService` implementa regras de negócio sobre listas de
     faixas (começo em 0, contíguas, sem sobreposição) e é utilizada no
     projeto (especialmente em versões mais completas do serviço de tabelas).

7. **DTOs e validação**
   - Criação de DTOs para requisições e respostas (`CalculoRequestDTO`,
     `CalculoResponseDTO`, `TabelaTarifariaRequestDTO`, etc.), mantendo as
     entidades limpas.
   - Anotações Bean Validation (`@NotBlank`, `@NotEmpty`, `@Valid`) nos
     campos de entrada.

8. **Controllers REST**
   - `TabelaTarifariaController` expõe endpoints para criar, listar e excluir
     tabelas.
   - `CalculoController` expõe endpoint `/api/calculos` que delega ao
     serviço de cálculo.
   - Exceções personalizadas e `GlobalExceptionHandler` para transformar
     erros em respostas HTTP claras (400, 404 etc.).

9. **Testes**
   - Implementados testes unitários sobre a validação de faixas
     (`ValidacaoFaixasServiceTest`) e outros componentes.
   - Usados `mvn test` para garantir regressões.

10. **Iterações e ajustes**
    - Adicionaram-se logs, mostradores SQL pelo Hibernate para facilitar
      depuração.
    - Melhorias de UX como mensagens de erro de negócio personalizadas e a
      manipulação de entidades no controlador.

11. **Documentação**
    - Redação do README com instruções completas de instalação, execução,
      banco, endpoints e exemplos.

O desenvolvimento foi incremental: começar pelo domínio e persistência,
em seguida adicionar a lógica de cálculo e expor via REST, e por fim testar e
documentar.

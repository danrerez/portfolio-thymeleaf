# Portfólio com Spring Boot e Thymeleaf

Aplicação web do portfólio de Daniel Cardoso, feita com **Spring Boot 3.5 + Thymeleaf**. O Controller monta os dados (perfil, habilidades, experiências e projetos) e envia para o template HTML, que renderiza tudo com expressões Thymeleaf.

## Como rodar

Requisitos: Java 21.

```bash
./mvnw spring-boot:run
```

No Windows: `mvnw.cmd spring-boot:run`. Depois acesse [http://localhost:8080](http://localhost:8080).

## Estrutura

```
src/main/java/br/com/danielcardoso/portfolio
├── PortfolioApplication.java        # Classe principal
├── controller/PortfolioController   # Rotas GET / e POST /contato
├── service/PortfolioService         # Dados do portfólio e mensagens recebidas
└── model/                           # Perfil, Habilidade, Experiencia, Projeto, MensagemContato

src/main/resources
├── templates/index.html             # Página Thymeleaf
├── static/css/style.css             # Estilos
├── static/js/script.js              # Interatividade
└── application.properties
```

## Rotas

| Método | Rota       | O que faz |
|--------|------------|-----------|
| GET    | `/`        | Adiciona os dados no `Model` e renderiza `index.html` |
| POST   | `/contato` | Recebe o formulário (`th:object`), valida e redireciona com mensagem de sucesso ou erro (flash attribute) |

## Recursos do Thymeleaf usados

- `th:text`, `th:each`, `th:if`, `th:classappend`, `th:attr`
- `th:href="@{...}"` e `th:src="@{...}"` para arquivos estáticos
- `th:action`, `th:object` e `th:field` no formulário
- Utilitários `#lists.size` e `#strings.listJoin`

## Interatividade (JavaScript)

- Efeito de digitação com as funções vindas do Controller
- Filtro de projetos por categoria
- Barras de habilidade animadas e animação de entrada ao rolar
- Menu responsivo no celular e destaque do link da seção atual
- Validação e contador de caracteres no formulário de contato

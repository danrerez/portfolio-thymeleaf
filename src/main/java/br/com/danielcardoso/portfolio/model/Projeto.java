package br.com.danielcardoso.portfolio.model;

import java.util.List;

/**
 * Projeto exibido no grid de projetos. A categoria é usada pelo filtro em JavaScript.
 */
public record Projeto(
        String titulo,
        String descricao,
        String categoria,
        String icone,
        List<String> tecnologias,
        boolean destaque) {
}

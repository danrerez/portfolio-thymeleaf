package br.com.danielcardoso.portfolio.model;

import java.util.List;

/**
 * Dados pessoais exibidos no topo e na seção "Sobre".
 */
public record Perfil(
        String nome,
        String iniciais,
        String titulo,
        List<String> funcoes,
        String resumo,
        int idade,
        String curso,
        String semestre,
        String empresa,
        String email) {
}

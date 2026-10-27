package br.com.danielcardoso.portfolio.model;

import java.time.LocalDateTime;

/**
 * Objeto ligado ao formulário de contato via th:object / th:field.
 */
public class MensagemContato {

    private String nome;
    private String email;
    private String mensagem;
    private LocalDateTime recebidaEm;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getRecebidaEm() {
        return recebidaEm;
    }

    public void setRecebidaEm(LocalDateTime recebidaEm) {
        this.recebidaEm = recebidaEm;
    }
}

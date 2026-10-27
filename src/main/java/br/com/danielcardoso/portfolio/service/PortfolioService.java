package br.com.danielcardoso.portfolio.service;

import br.com.danielcardoso.portfolio.model.Experiencia;
import br.com.danielcardoso.portfolio.model.Habilidade;
import br.com.danielcardoso.portfolio.model.MensagemContato;
import br.com.danielcardoso.portfolio.model.Perfil;
import br.com.danielcardoso.portfolio.model.Projeto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Centraliza os dados do portfólio. O Controller busca tudo aqui e envia para o template.
 */
@Service
public class PortfolioService {

    private final List<MensagemContato> mensagens = Collections.synchronizedList(new ArrayList<>());

    public Perfil getPerfil() {
        return new Perfil(
                "Daniel Cardoso",
                "DC",
                "Desenvolvedor Web & Marketing com IA",
                List.of("Desenvolvedor Web", "Estrategista de Marketing", "Entusiasta de IA"),
                "Estudante de Sistemas de Informação e responsável pelas áreas de marketing, "
                        + "desenvolvimento e inteligência artificial da Dalsun Energia Solar. "
                        + "Gosto de transformar ideias em produtos digitais que geram resultado real para o negócio.",
                21,
                "Sistemas de Informação",
                "8º semestre",
                "Dalsun Energia Solar",
                "seu.email@exemplo.com");
    }

    /**
     * Habilidades agrupadas por área. LinkedHashMap mantém a ordem de exibição.
     */
    public Map<String, List<Habilidade>> getHabilidades() {
        Map<String, List<Habilidade>> grupos = new LinkedHashMap<>();
        grupos.put("Front-end", List.of(
                new Habilidade("HTML & CSS", 90),
                new Habilidade("JavaScript", 85),
                new Habilidade("Bootstrap / Tailwind", 80),
                new Habilidade("React / Next.js", 70)));
        grupos.put("Back-end", List.of(
                new Habilidade("Node.js / Express", 75),
                new Habilidade("Java / Spring Boot", 65),
                new Habilidade("PostgreSQL", 70),
                new Habilidade("APIs REST", 80)));
        grupos.put("Marketing & IA", List.of(
                new Habilidade("Tráfego pago (Meta / Google)", 85),
                new Habilidade("Automação com IA", 80),
                new Habilidade("Analytics (GA4)", 75),
                new Habilidade("CRM & Funil de vendas", 75)));
        return grupos;
    }

    public List<Experiencia> getExperiencias() {
        return List.of(
                new Experiencia("Atual", "Marketing, Desenvolvimento e IA", "Dalsun Energia Solar",
                        "Gestão de campanhas de tráfego pago, criação de conteúdo, desenvolvimento de sistemas "
                                + "internos e automações com inteligência artificial."),
                new Experiencia("Em andamento", "Bacharelado em Sistemas de Informação", "8º semestre",
                        "Formação em desenvolvimento de software, banco de dados, engenharia de software "
                                + "e programação web."));
    }

    public List<Projeto> getProjetos() {
        return List.of(
                new Projeto("Portal de Indicações Dalsun",
                        "Plataforma onde parceiros indicam novos clientes e acompanham cada indicação, "
                                + "com cadastro por código de verificação, login com Google e integração com CRM.",
                        "Profissional", "☀️",
                        List.of("Next.js", "TypeScript", "Tailwind", "PostgreSQL"), true),
                new Projeto("CRUD Busca CPF",
                        "Sistema de cadastro, consulta, edição e exclusão de registros por CPF, "
                                + "com páginas separadas para cada operação.",
                        "Full-stack", "🗂️",
                        List.of("Node.js", "Express", "JSON Server"), false),
                new Projeto("Painel de Cursos",
                        "Painel interativo de cursos publicado na nuvem, com deploy pelo GitHub e Render.",
                        "Full-stack", "📚",
                        List.of("Express", "Bootstrap", "Render"), false),
                new Projeto("Cotação do Dólar",
                        "Consulta da cotação do dólar em tempo real consumindo uma API pública.",
                        "Front-end", "💵",
                        List.of("JavaScript", "Fetch API", "CSS"), false),
                new Projeto("To-Do List",
                        "Lista de tarefas com adição, conclusão e remoção de itens manipulando o DOM.",
                        "Front-end", "✅",
                        List.of("HTML", "CSS", "JavaScript"), false),
                new Projeto("PFC - Projeto Final de Curso",
                        "Landing page do projeto final de curso com navbar responsiva e carrossel.",
                        "Front-end", "🎓",
                        List.of("Bootstrap", "HTML", "CSS"), false));
    }

    /**
     * Lista de categorias distintas, usada para montar os botões de filtro.
     */
    public List<String> getCategorias() {
        return getProjetos().stream().map(Projeto::categoria).distinct().toList();
    }

    public void registrarMensagem(MensagemContato mensagem) {
        mensagem.setRecebidaEm(LocalDateTime.now());
        mensagens.add(mensagem);
    }

    public int getTotalMensagens() {
        return mensagens.size();
    }
}

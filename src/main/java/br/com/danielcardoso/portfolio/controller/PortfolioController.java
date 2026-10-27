package br.com.danielcardoso.portfolio.controller;

import br.com.danielcardoso.portfolio.model.MensagemContato;
import br.com.danielcardoso.portfolio.service.PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Year;

@Controller
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Rota principal: monta o Model com todos os dados e renderiza templates/index.html.
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("perfil", portfolioService.getPerfil());
        model.addAttribute("habilidades", portfolioService.getHabilidades());
        model.addAttribute("experiencias", portfolioService.getExperiencias());
        model.addAttribute("projetos", portfolioService.getProjetos());
        model.addAttribute("categorias", portfolioService.getCategorias());
        model.addAttribute("totalMensagens", portfolioService.getTotalMensagens());
        model.addAttribute("ano", Year.now().getValue());

        // Se voltou de um envio com erro, o formulário já vem preenchido pelo flash attribute
        if (!model.containsAttribute("contato")) {
            model.addAttribute("contato", new MensagemContato());
        }
        return "index";
    }

    /**
     * Recebe o formulário de contato, valida e redireciona de volta para a página (padrão Post/Redirect/Get).
     */
    @PostMapping("/contato")
    public String enviarContato(@ModelAttribute("contato") MensagemContato contato,
                                RedirectAttributes redirect) {
        if (!StringUtils.hasText(contato.getNome())
                || !StringUtils.hasText(contato.getEmail())
                || !StringUtils.hasText(contato.getMensagem())) {
            redirect.addFlashAttribute("erro", "Preencha nome, e-mail e mensagem para enviar.");
            redirect.addFlashAttribute("contato", contato);
            return "redirect:/#contato";
        }

        portfolioService.registrarMensagem(contato);
        redirect.addFlashAttribute("sucesso",
                "Obrigado, " + contato.getNome().trim() + "! Sua mensagem foi recebida.");
        return "redirect:/#contato";
    }
}

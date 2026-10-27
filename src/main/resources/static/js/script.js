// ===== Navbar: fundo ao rolar e menu mobile =====
const navbar = document.getElementById("navbar");
const menuToggle = document.getElementById("menuToggle");
const navLinks = document.getElementById("navLinks");

window.addEventListener("scroll", () => {
    navbar.classList.toggle("scrolled", window.scrollY > 40);
});

menuToggle.addEventListener("click", () => {
    const aberto = navLinks.classList.toggle("open");
    menuToggle.classList.toggle("open", aberto);
    menuToggle.setAttribute("aria-expanded", aberto);
});

navLinks.querySelectorAll("a").forEach((link) => {
    link.addEventListener("click", () => {
        navLinks.classList.remove("open");
        menuToggle.classList.remove("open");
        menuToggle.setAttribute("aria-expanded", false);
    });
});

// ===== Link ativo conforme a seção visível =====
const secoes = document.querySelectorAll("main section[id]");

const observadorSecoes = new IntersectionObserver((entradas) => {
    entradas.forEach((entrada) => {
        if (entrada.isIntersecting) {
            navLinks.querySelectorAll("a").forEach((link) => {
                link.classList.toggle("active", link.getAttribute("href") === "#" + entrada.target.id);
            });
        }
    });
}, { rootMargin: "-45% 0px -50% 0px" });

secoes.forEach((secao) => observadorSecoes.observe(secao));

// ===== Efeito de digitação com as funções enviadas pelo Controller =====
const typed = document.getElementById("typed");
const funcoes = typed.dataset.funcoes.split("|");
let indiceFuncao = 0;
let indiceLetra = funcoes[0].length;
let apagando = true;

function digitar() {
    const atual = funcoes[indiceFuncao];
    typed.textContent = atual.substring(0, indiceLetra);

    if (apagando) {
        indiceLetra--;
        if (indiceLetra < 0) {
            apagando = false;
            indiceFuncao = (indiceFuncao + 1) % funcoes.length;
            indiceLetra = 0;
        }
    } else {
        indiceLetra++;
        if (indiceLetra > funcoes[indiceFuncao].length) {
            apagando = true;
            setTimeout(digitar, 1800);
            return;
        }
    }
    setTimeout(digitar, apagando ? 40 : 80);
}

setTimeout(digitar, 2000);

// ===== Animação de entrada e barras de habilidade =====
const observadorReveal = new IntersectionObserver((entradas) => {
    entradas.forEach((entrada) => {
        if (!entrada.isIntersecting) return;

        entrada.target.classList.add("visivel");
        entrada.target.querySelectorAll(".skill-fill").forEach((barra) => {
            barra.style.width = barra.dataset.nivel + "%";
        });
        observadorReveal.unobserve(entrada.target);
    });
}, { threshold: 0.15 });

document.querySelectorAll(".reveal").forEach((el) => observadorReveal.observe(el));

// ===== Filtro de projetos por categoria =====
const botoesFiltro = document.querySelectorAll(".filtro");
const cards = document.querySelectorAll(".projeto-card");

botoesFiltro.forEach((botao) => {
    botao.addEventListener("click", () => {
        botoesFiltro.forEach((b) => b.classList.remove("active"));
        botao.classList.add("active");

        const filtro = botao.dataset.filtro;
        cards.forEach((card) => {
            const mostrar = filtro === "todos" || card.dataset.categoria === filtro;
            card.classList.toggle("oculto", !mostrar);
        });
    });
});

// ===== Formulário de contato: contador e validação antes de enviar ao backend =====
const form = document.getElementById("formContato");
const mensagem = document.getElementById("mensagem");
const charCount = document.getElementById("charCount");

function atualizarContador() {
    charCount.textContent = mensagem.value.length + "/" + mensagem.maxLength;
}

mensagem.addEventListener("input", atualizarContador);
atualizarContador();

form.addEventListener("submit", (evento) => {
    let valido = true;

    form.querySelectorAll("input, textarea").forEach((campo) => {
        const vazio = campo.value.trim() === "";
        const emailInvalido = campo.type === "email" && !campo.checkValidity();
        const erro = vazio || emailInvalido;
        campo.classList.toggle("invalido", erro);
        if (erro) valido = false;
    });

    if (!valido) {
        evento.preventDefault();
    }
});

// Remove a mensagem de sucesso depois de alguns segundos
const alertaSucesso = document.querySelector(".alerta.sucesso");
if (alertaSucesso) {
    setTimeout(() => alertaSucesso.remove(), 6000);
}

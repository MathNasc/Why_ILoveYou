# O Porquê Eu Te Amo ❤️

Um aplicativo Android minimalista e romântico desenvolvido como um presente digital especial para o Dia dos Namorados. O aplicativo exibe frases aleatórias que completam a sentença "Eu te amo porque...", permitindo que você compartilhe esses sentimentos como imagens personalizadas.

## ✨ Funcionalidades

- **Frases Aleatórias**: Mais de 100 frases românticas pré-definidas que mudam a cada abertura ou ao clicar no botão de atualização.
- **Compartilhamento como Imagem**: Gera dinamicamente uma imagem estilizada com a frase e uma assinatura personalizada ("Com amor, Matheus ❤️") para compartilhar em redes sociais.
- **Design Minimalista**: Interface limpa com tons suaves (rosa, bege e vermelho) e elementos decorativos (corações, estrelas e flores).
- **Botão de Atualização**: Mude a frase atual instantaneamente com um clique.
- **Funcionamento Offline**: O aplicativo não requer conexão com a internet nem permissões desnecessárias.
- **Ícones Adaptativos**: Ícone personalizado que se ajusta ao estilo do sistema Android.

## 🚀 Tecnologias Utilizadas

- **Linguagem**: [Kotlin](https://kotlinlang.org/)
- **IDE**: [Android Studio](https://developer.android.com/studio)
- **UI**: XML Layouts com Material Design Components
- **Arquitetura**: Clean and Simple Activity-based
- **Geração de Imagem**: Canvas API do Android
- **Compartilhamento**: FileProvider para compartilhamento seguro de arquivos

## 🛠️ Como Instalar e Executar

1.  **Clone o repositório**:
    ```bash
    git clone https://github.com/seu-usuario/o-porque-eu-te-amo.git
    ```
2.  **Abra no Android Studio**:
    - Selecione `File > Open` e navegue até a pasta do projeto.
3.  **Sincronize o Gradle**:
    - O Android Studio deve solicitar a sincronização automaticamente.
4.  **Execute o App**:
    - Conecte um dispositivo físico ou use um emulador e clique no botão `Run`.

## 🎨 Personalização

O projeto foi estruturado para ser facilmente personalizável:

- **Frases**: Edite o arquivo `app/src/main/res/values/strings.xml` para adicionar ou remover frases.
- **Assinatura**: Altere o nome na `MainActivity.kt` dentro da função `createPhraseBitmap`.
- **Cores**: Modifique `app/src/main/res/values/colors.xml` para mudar a paleta de cores do app.

---
Desenvolvido com ❤️ para celebrar o amor.

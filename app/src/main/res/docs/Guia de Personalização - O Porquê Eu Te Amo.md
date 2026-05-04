# Guia de Personalização - O Porquê Eu Te Amo

## Como Personalizar as Frases de Amor

Este guia mostra como você pode facilmente personalizar as frases do aplicativo para torná-lo ainda mais especial e único para sua pessoa amada.

### Passo 1: Localizando o Arquivo de Frases

1. Abra o projeto no Android Studio
2. Navegue até: `app/src/main/res/values/strings.xml`
3. Procure pela seção que começa com `<string-array name="love_phrases">`

### Passo 2: Editando as Frases Existentes

Cada frase está dentro de uma tag `<item>`. Por exemplo:
```xml
<item>Eu te amo porque seu sorriso ilumina meu dia.</item>
```

Para personalizar:
1. Substitua o texto entre as tags `<item>` e `</item>`
2. Mantenha sempre o formato "Eu te amo porque..."
3. Certifique-se de que a frase seja tocante e significativa

### Passo 3: Adicionando Novas Frases

Para adicionar uma nova frase, simplesmente adicione uma nova linha com:
```xml
<item>Eu te amo porque [sua frase personalizada aqui].</item>
```

### Passo 4: Removendo Frases

Para remover uma frase, simplesmente delete a linha completa com a tag `<item>`.

### Exemplos de Frases Personalizadas

Aqui estão algumas ideias para frases mais pessoais:

**Baseadas em memórias específicas:**
- "Eu te amo porque você lembrou do meu café favorito no nosso primeiro encontro."
- "Eu te amo porque você cantou para mim naquela noite chuvosa."

**Baseadas em características únicas:**
- "Eu te amo porque você faz aquela carinha engraçada quando está concentrado(a)."
- "Eu te amo porque você sempre sabe o que dizer quando estou triste."

**Baseadas em sonhos compartilhados:**
- "Eu te amo porque sonhamos juntos com nossa casa na praia."
- "Eu te amo porque você acredita nos meus sonhos mais loucos."

### Dicas Importantes

1. **Mantenha o formato**: Sempre comece com "Eu te amo porque"
2. **Seja específico**: Frases pessoais são mais tocantes
3. **Varie o comprimento**: Misture frases curtas e longas
4. **Teste no app**: Sempre teste como a frase aparece na tela
5. **Backup**: Faça uma cópia das frases originais antes de modificar

### Testando Suas Modificações

1. Salve o arquivo `strings.xml`
2. Execute o aplicativo no emulador ou dispositivo
3. Abra e feche o app várias vezes para ver diferentes frases
4. Verifique se todas as frases aparecem corretamente

### Exemplo Completo de Personalização

```xml
<string-array name="love_phrases">
    <!-- Frases originais mantidas -->
    <item>Eu te amo porque seu sorriso ilumina meu dia.</item>
    <item>Eu te amo porque você me faz sentir em casa, onde quer que eu esteja.</item>
    
    <!-- Suas frases personalizadas -->
    <item>Eu te amo porque você sempre deixa bilhetinhos carinhosos na minha bolsa.</item>
    <item>Eu te amo porque você dança comigo na cozinha enquanto cozinhamos.</item>
    <item>Eu te amo porque você me abraça mais forte quando sente que estou triste.</item>
    <item>Eu te amo porque você ri das minhas piadas ruins.</item>
    <item>Eu te amo porque você me ensinou que o amor pode ser simples e profundo ao mesmo tempo.</item>
</string-array>
```

Com essas personalizações, seu aplicativo se tornará uma declaração de amor única e especial, criada especialmente para a pessoa que você ama!


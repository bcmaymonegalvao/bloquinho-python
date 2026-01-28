# 📦 Guia de Download do BloquinhoPy APK

Este guia fornece instruções detalhadas para fazer o download e instalar o BloquinhoPy no seu dispositivo Android.

## 📥 Download Direto

### Método 1: GitHub Releases (Recomendado)

1. Acesse a página de releases: [BloquinhoPy Releases](https://github.com/bcmaymonegalvao/bloquinho-python/releases/latest)
2. Localize o arquivo **BloquinhoPy.apk** na última release
3. Clique no arquivo para fazer o download
4. Aguarde o download concluir (aproximadamente 50MB)

### Método 2: Link Direto para Última Versão

[![Download BloquinhoPy APK](https://img.shields.io/badge/Download-BloquinhoPy.apk-blue?style=for-the-badge&logo=android)](https://github.com/bcmaymonegalvao/bloquinho-python/releases/latest/download/BloquinhoPy.apk)

**Link direto:**
```
https://github.com/bcmaymonegalvao/bloquinho-python/releases/latest/download/BloquinhoPy.apk
```

---

## 📱 Instalação no Android

### Passo 1: Habilitar Instalação de Fontes Desconhecidas

**No Android 8.0 e superior:**
1. Abra **Configurações** do dispositivo
2. Vá em **Segurança** ou **Segurança e Privacidade**
3. Procure por **Instalar apps desconhecidos** ou **Fontes desconhecidas**
4. Selecione o navegador ou gerenciador de arquivos usado para baixar o APK
5. Ative a opção **Permitir desta fonte**

**No Android 7.1 e inferior:**
1. Abra **Configurações**
2. Vá em **Segurança**
3. Ative **Fontes desconhecidas**
4. Confirme a ação

### Passo 2: Instalar o APK

1. Localize o arquivo **BloquinhoPy.apk** baixado
   - Geralmente na pasta **Downloads** do dispositivo
2. Toque no arquivo APK
3. Se solicitado, confirme as permissões
4. Toque em **Instalar**
5. Aguarde a instalação concluir
6. Toque em **Abrir** ou encontre o ícone do BloquinhoPy no menu de aplicativos

### Passo 3: Configurar Permissões

Ao abrir o BloquinhoPy pela primeira vez, o app pode solicitar as seguintes permissões:

- ✅ **Armazenamento** - Para salvar e carregar notebooks
- ✅ **Internet** (opcional) - Para futuras funcionalidades de sincronização

Conceda as permissões necessárias para o funcionamento completo do aplicativo.

---

## ✅ Requisitos do Sistema

| Requisito | Especificação |
|-----------|----------------|
| **Sistema Operacional** | Android 8.0 (Oreo) ou superior |
| **Versão API** | API Level 26+ |
| **Armazenamento** | Mínimo 50MB livres (100MB recomendado) |
| **Memória RAM** | Mínimo 2GB |
| **Arquitetura** | ARM64-v8a |

---

## 🔒 Segurança e Privacidade

### Verificação de Segurança

Todos os APKs lançados passam por:
- ✅ Build automatizado no GitHub Actions
- ✅ Análise de código estático
- ✅ Assinatura digital com certificado de desenvolvedor
- ✅ Hospedagem em repositório GitHub oficial

### Política de Privacidade

O BloquinhoPy:
- ❌ **Não coleta dados pessoais**
- ❌ **Não transmite dados pela internet**
- ❌ **Não requer conta de usuário**
- ✅ **Funciona completamente offline**
- ✅ **Armazena dados apenas localmente no dispositivo**

Leia a [Política de Privacidade completa](https://raw.githubusercontent.com/bcmaymonegalvao/bloquinho-python/main/docs/PRIVACY_POLICY.md).

---

## ❓ Problemas Comuns e Soluções

### "App não instalado"

**Possíveis causas:**
1. Versão do Android incompatível (precisa ser 8.0+)
2. Espaço insuficiente no dispositivo
3. APK corrompido durante o download

**Soluções:**
- Verifique a versão do Android: **Configurações** > **Sobre o telefone**
- Libere espaço no dispositivo (mínimo 100MB)
- Faça o download novamente do APK

### "Fonte desconhecida bloqueada"

**Solução:**
- Siga o **Passo 1** das instruções de instalação acima
- Habilite a instalação de fontes desconhecidas para o aplicativo usado no download

### "App trava ao abrir"

**Soluções:**
1. Forçar encerramento:
   - **Configurações** > **Apps** > **BloquinhoPy** > **Forçar parada**
2. Limpar cache:
   - **Configurações** > **Apps** > **BloquinhoPy** > **Limpar cache**
3. Reinstalar o aplicativo

---

## 🔄 Atualizações

### Como Verificar Atualizações

1. Visite a página de [Releases](https://github.com/bcmaymonegalvao/bloquinho-python/releases)
2. Compare a versão instalada com a última disponível
3. Faça o download da nova versão se disponível
4. Instale sobre a versão existente (seus dados serão preservados)

### Changelog

Consulte o arquivo [CHANGELOG.md](https://github.com/bcmaymonegalvao/bloquinho-python/blob/main/CHANGELOG.md) para ver todas as mudanças entre versões.

---

## 📞 Suporte

### Relatar Problemas

Encontrou um bug? Abra uma issue no GitHub:
- [Reportar Bug](https://github.com/bcmaymonegalvao/bloquinho-python/issues/new?template=bug_report.md)
- [Solicitar Funcionalidade](https://github.com/bcmaymonegalvao/bloquinho-python/issues/new?template=feature_request.md)

### Comunidade

- 📚 [Documentação](https://github.com/bcmaymonegalvao/bloquinho-python/tree/main/docs)
- 🐛 [Issues](https://github.com/bcmaymonegalvao/bloquinho-python/issues)
- 🤝 [Contribuir](https://github.com/bcmaymonegalvao/bloquinho-python/blob/main/CONTRIBUTING.md)

---

## 🎯 Começando

Após a instalação:

1. **Abra o BloquinhoPy**
2. **Crie seu primeiro projeto**
3. **Adicione um notebook**
4. **Escreva código Python e execute!**

Exemplo de código inicial:
```python
print("Bem-vindo ao BloquinhoPy!")
import sys
print(f"Versão do Python: {sys.version}")
```

---

**Versão do Guia:** 1.0  
**Última Atualização:** 27 de Janeiro de 2026  
**Status:** ✅ Ativo

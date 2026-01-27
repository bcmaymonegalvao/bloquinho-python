# Segurança dos Dados - Google Play Store

Este documento fornece as respostas técnicas para os requisitos de segurança de dados do Google Play Store para o aplicativo BloquinhoPy.

## 1. Funcionalidades de Criação de Usuários

**Pergunta:** O app já conseguiu implementar funcionalidades para criação de usuários?

**Resposta:** ❌ **NÃO - O BloquinhoPy não implementa funcionalidades de criação de conta de usuários.**

### Justificativa:
O BloquinhoPy é um IDE Python local para execução de notebooks no Android. A arquitetura foi projetada para:

- ✅ Funcionar **completamente offline** sem necessidade de autenticação
- ✅ Armazenar dados **localmente** usando Room Database
- ✅ Não requer comunicação com servidor externo para funcionalidades principais
- ✅ Proteger privacidade do usuário não coletando dados pessoais

### Arquitetura de Dados:
```
Data Layer (Local)
├── Room Database (SQLite)
│   ├── ProjectEntity
│   ├── NotebookEntity
│   └── Execution History
└── No cloud/remote sync no MVP
```

**Conclusão:** Como não há criação de contas de usuários, não há dados de perfil ou credenciais para gerenciar.

---

## 2. URL para Exclusão de Conta de Usuários

**Pergunta:** Previsão de uma URL para exclusão de conta de usuários?

**Resposta:** ❌ **NÃO APLICÁVEL - Sem sistema de autenticação/contas.**

### Justificativa:
Como explicado acima, o BloquinhoPy **não implementa contas de usuários** nem armazena dados pessoais em servidor remoto. Portanto:

- ❌ Não há dados de usuário remotos para excluir
- ❌ Não há contas no servidor
- ❌ Não é necessária URL de exclusão de conta

### Política de Privacidade:
Para dados **locais** armazenados no dispositivo, o usuário pode:
1. Desinstalar o app - limpa todos os dados locais do BloquinhoPy
2. Usar "Limpar dados do app" (Settings > Apps > BloquinhoPy > Storage) - apaga Room Database

---

## 3. Criptografia de Dados em Trânsito

**Pergunta:** Os dados dos usuários são criptografados em transito no app?

**Resposta:** ✅ **SIM - NÃO HÁ TRANSMISSÃO DE DADOS (Arquitetura Local-Only)**

### Declaração Técnica:

O BloquinhoPy **não transmite dados de usuários pela internet**. A arquitetura foi projetada para privacidade:

#### Comunicação de Rede (Limitada):
- ❌ **Sem transmissão de dados de projetos/notebooks**
- ❌ **Sem sincronização com servidor**
- ❌ **Sem APIs remotas de dados do usuário**
- ✅ **Apenas INTERNET permission para futuras integrações opcionais** (vide AndroidManifest.xml)

#### Armazenamento Local (Não-Criptografado):
```xml
<!-- AndroidManifest.xml - Permissões Declaradas -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
```

#### Dados Locais (Room Database):
- 📁 Armazenados em **SQLite local** (`/data/data/io.github.bcmaymonegalvao.bloquinhopy/databases/`)
- 🔒 Protegidos pelo **sandbox do Android** e permissões do dispositivo
- 📝 Arquivos de projeto (`.ipynb`) salvos em **armazenamento externo/interno** do dispositivo
- **Criptografia de armazenamento:** Responsabilidade do SO Android (LUKS, FBE - File-Based Encryption)

### Por que Sem Criptografia de Trânsito:
1. **Nenhum dado é transmitido** - tudo processado localmente
2. **Chaquopy integrado** - Python execution no dispositivo, sem envio de código para servidores
3. **Room Database local** - apenas SQLite no armazenamento privado do app

### Roadmap de Segurança:
- 🚧 Fase 4+ (Planejada): Sincronização opcional com Firebase (com HTTPS/TLS 1.3+)
- 🚧 Fase 5+ (Planejada): Criptografia End-to-End para sync

---

## 4. Resumo Executivo para Google Play

| Aspecto | Status | Detalhes |
|--------|--------|----------|
| **Contas de Usuários** | ❌ Não Implementado | App offline, sem autenticação |
| **Dados de Usuário** | Mínimos | Apenas projetos/notebooks locais |
| **Transmissão de Dados** | ❌ Nenhuma | Arquitetura local-only |
| **Criptografia de Trânsito** | N/A | Não aplicável (sem transmissão) |
| **Armazenamento Local** | Seguro | Sandbox Android + permissões |
| **URL de Exclusão** | N/A | Sem contas remotas |
| **Política de Privacidade** | ✅ Implementada | https://raw.githubusercontent.com/bcmaymonegalvao/bloquinho-python/main/docs/PRIVACY_POLICY.md |

---

## 5. Conclusão

O **BloquinhoPy é um aplicativo de privacidade-primeira** que:
- ✅ Não coleta dados pessoais
- ✅ Funciona completamente offline
- ✅ Protege dados do usuário no armazenamento local do dispositivo
- ✅ Não transmite dados pela internet
- ✅ Não requer contas de usuários

O app atende aos requisitos de privacidade mais rigorosos do Google Play Store.

---

**Data:** 27 de Janeiro de 2026  
**Versão:** 1.0.0  
**Status:** Pronto para publicação

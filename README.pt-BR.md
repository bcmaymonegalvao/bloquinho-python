# BloquinhoPy - IDE Python Notebook para Android

**Idioma:** [English](./README.md) | Português 🇧🇷

[![CI](https://github.com/bcmaymonegalvao/bloquinho-python/workflows/CI/badge.svg)](https://github.com/bcmaymonegalvao/bloquinho-python/actions)

BloquinhoPy é um IDE Python offline-first para Android focado em uma experiência simples de notebook (.ipynb). Ele vem com um runtime Python embutido e uma pilha científica curada para dispositivos móveis.

## 🎯 Objetivos MVP

- ✅ **Fase 1**: Motor Básico (Hilt DI, Banco de Dados Room, PythonEngine)
- ✅ **Fase 2**: Integração UI (Jetpack Compose, Navegação, ViewModels)
- ✅ **Fase 3**: Recursos Avançados (Execução Python, Tratamento de Erros, Temas, Logging)
- 🔄 **Fase 4**: Expansão e Recursos Avançados (Em Andamento)

## 📱 Roadmap de Lançamento na Play Store

### Quando posso baixar da Play Store?

**Linha do Tempo para Play Store:**

🟢 **Fase 5: Testes Alpha** (Atual + 2-3 semanas)
- Completar integração Chaquopy com execução Python real
- Implementar import/export de arquivos .ipynb
- Tratamento básico de erros e relatório de crashes
- Testes internos (builds do Android Studio)
- **Status:** Você pode compilar e testar no seu dispositivo AGORA usando `./gradlew installDebug`

🟡 **Fase 6: Lançamento Beta** (1-2 meses)
- Testes beta fechados com Firebase App Distribution
- Otimização de performance
- Polimento de UI/UX
- Auditoria de segurança
- **Status:** Beta testers podem instalar via link do Firebase

🟠 **Fase 7: Lançamento de Produção** (3-4 meses)
- Criação de listagem na Play Store
- Materiais de marketing (screenshots, descrição)
- Política de privacidade e termos de serviço
- Testes finais em múltiplos dispositivos
- Aprovação do Google Play Console
- **Status:** Disponibilidade PÚBLICA na Play Store!

### 🚀 Início Rápido (Teste Agora!)

Você pode testar o BloquinhoPy no seu smartphone AGORA MESMO:

```bash
# 1. Clonar repositório
git clone https://github.com/bcmaymonegalvao/bloquinho-python.git
cd bloquinho-python

# 2. Compilar e instalar no dispositivo conectado
./gradlew installDebug

# 3. Ativar depuração USB no seu telefone Android
# Configurações > Opções do Desenvolvedor > Depuração USB

# 4. Conectar telefone via USB e instalar!
```

**Requisitos Mínimos:**
- Android 8.0 (API 26) ou superior
- 100MB de armazenamento livre
- Depuração USB ativada


---

[](url)## 🎯 Estado de Conclusão da Fase 4

**Infraestrutura Central** ✅
- [x] Workflow CI/CD corrigido e operacional
- [x] Runtime Python Chaquopy configurado
- [x] Camada de banco de dados completa com entidades e DAOs
- [x] Suporte multi-idioma (Inglês/Português)

**Pronto para Fase 5 - Lançamento de Produção**

---

## 📚 Recursos

- Execução Python offline com Chaquopy
- Editor de notebooks com células executáveis
- Persistência de dados com Room Database
- Interface moderna com Jetpack Compose
- Tema Material Design 3 (claro/escuro)
- Gerenciamento de projetos e notebooks

## 🛠️ Tecnologias

- **Linguagem**: Kotlin
- **Framework UI**: Jetpack Compose
- **Banco de Dados**: Room
- **Injeção de Dependência**: Hilt
- **Navegação**: Compose Navigation
- **Async**: Coroutines & Flow
- **Runtime Python**: Chaquopy
- **Ferramenta de Build**: Gradle 8.7
- **CI/CD**: GitHub Actions

## 📝 Licença

MIT - Veja o arquivo LICENSE para detalhes

## 👨‍💻 Autor

**Bruno César Maymone Galvão** - Desenvolvedor Sênior & Engenheiro de ML

- GitHub: [@bcmaymonegalvao](https://github.com/bcmaymonegalvao)
- Foco: Python, Machine Learning, Desenvolvimento Full-Stack

**BloquinhoPy** - Tornando o desenvolvimento Python acessível no Android 📱✨

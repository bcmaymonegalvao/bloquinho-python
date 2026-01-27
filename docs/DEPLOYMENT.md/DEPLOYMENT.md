# BloquinhoPy Deployment Guide

## Índice

1. **[Release Setup Guide](./RELEASE_SETUP.md)** - Configuração completa para compilação de release
   - Geração de keystore
   - Configuração de variáveis de ambiente
   - Build de APK/Bundle
   - Verificação de assinatura
   - Troubleshooting

## Checklist de Deploy - Passo 1 ✅

### Phase 1: Build Configuration

- ✅ **Versioning (build.gradle.kts)**
  - `compileSdk = 35`
  - `targetSdk = 35`
  - `minSdk = 26`
  - `versionCode = 1`
  - `versionName = "1.0.0"`

- ✅ **Signing Configuration**
  - Keystore path via environment variable: `KEYSTORE_PATH`
  - Passwords from environment variables: `KEYSTORE_PASSWORD`, `KEY_PASSWORD`
  - Key alias: `bloquinhopy-key`

- ✅ **R8 Optimization (proguard-rules.pro)**
  - Code minification enabled
  - Resource shrinking enabled
  - 5 optimization passes
  - Debug logging removed in release
  - ~5-10% APK size reduction

### Phase 2: Google Play Console Setup (Seu turno!)

- ⏳ Criar conta no Google Play Developer Console
- ⏳ Criar listagem do app
- ⏳ Preencher informações básicas
- ⏳ Aceitar políticas de conteúdo

### Phase 3: App Assets

- ⏳ Ícone do app (512x512px)
- ⏳ Screenshots (4-5 por orientação)
- ⏳ Feature graphic (1024x500px)
- ⏳ Descrição curta (80 caracteres)
- ⏳ Descrição completa (4000 caracteres)

### Phase 4: Testing

- ⏳ Beta testing no Google Play
- ⏳ Testes em múltiplos dispositivos
- ⏳ Testes de permissões de arquivo
- ⏳ Performance profiling

### Phase 5: Launch

- ⏳ Enviar .aab para production
- ⏳ Configurar rollout gradual
- ⏳ Monitorar crash reports
- ⏳ Responder a reviews

## Build Commands

### Setup

```bash
# Clone o repositório
git clone https://github.com/bcmaymonegalvao/bloquinho-python.git
cd bloquinho-python

# Gerar o keystore (primeira vez)
keytool -genkey -v -keystore bloquinhopy-release.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias bloquinhopy-key
```

### Compilação

```bash
# Definir variáveis de ambiente
export KEYSTORE_PATH="$(pwd)/bloquinhopy-release.jks"
export KEYSTORE_PASSWORD="sua-senha"
export KEY_ALIAS="bloquinhopy-key"
export KEY_PASSWORD="sua-senha"

# Compilar release bundle (preferido para Play Store)
./gradlew bundleRelease

# Resultado: app/build/outputs/bundle/release/app-release.aab
```

## Documentação Relacionada

- [Android Developer - App Signing](https://developer.android.com/studio/publish/app-signing)
- [Google Play Console](https://play.google.com/console)
- [R8 Code Shrinking](https://developer.android.com/studio/build/shrink-code)

## Próximas Etapas

**Passo 1 (Seu turno agora!):**
1. Crie conta no Google Play Console
2. Comece o onboarding de novo app
3. Preencha as informações básicas do app
4. Confira os requisitos de conformidade

Depois que completar o Passo 1, continuaremos com:
- Passo 2: Preparar assets visuais
- Passo 3: Descrições e screenshots
- Passo 4: Testing e beta
- Passo 5: Launch

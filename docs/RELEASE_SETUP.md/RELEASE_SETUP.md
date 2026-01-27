# BloquinhoPy Release Setup Guide

Este documento descreve como configurar o app para lançamento no Google Play Store.

## 1. Geração do Keystore de Assinatura

Antes de compilar uma versão de release, você precisa criar um keystore (arquivo de chave de assinatura).

### Opção A: Gerar um novo keystore (RECOMENDADO para primeira vez)

```bash
keytool -genkey -v -keystore bloquinhopy-release.jks \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -alias bloquinhopy-key
```

**Responda as perguntas:**
- Nome e sobrenome: Bruno César Maymone Galvão (ou seu nome)
- Unidade organizacional: BloquinhoPy
- Organização: Seu nome ou organização
- Cidade: Fátima
- Estado: Ceará (CE)
- País: BR
- Senha do keystore: **ESCOLHA UMA SENHA FORTE**
- Senha da chave: (pode ser igual à do keystore)

**IMPORTANTE:** Guarde este arquivo `bloquinhopy-release.jks` em um local seguro. Você precisará dele para todas as atualizações futuras.

### Opção B: Usar um keystore existente

Se você já tem um arquivo `.jks`, pule para a próxima seção.

## 2. Configurar Variáveis de Ambiente

Para que o Gradle possa acessar o keystore durante a compilação, configure as variáveis de ambiente:

### Windows (PowerShell)

```powershell
$env:KEYSTORE_PATH = "C:\caminho\para\bloquinhopy-release.jks"
$env:KEYSTORE_PASSWORD = "sua-senha-do-keystore"
$env:KEY_ALIAS = "bloquinhopy-key"
$env:KEY_PASSWORD = "sua-senha-da-chave"
```

### macOS/Linux (Bash)

```bash
export KEYSTORE_PATH="/caminho/para/bloquinhopy-release.jks"
export KEYSTORE_PASSWORD="sua-senha-do-keystore"
export KEY_ALIAS="bloquinhopy-key"
export KEY_PASSWORD="sua-senha-da-chave"
```

### Persistir as variáveis (Opcional)

Para não precisar definir a cada vez, adicione ao seu arquivo de configuração:

**macOS/Linux:**  
Edite `~/.bash_profile` ou `~/.zshrc`:

```bash
export KEYSTORE_PATH="$HOME/bloquinhopy-release.jks"
export KEYSTORE_PASSWORD="sua-senha"
export KEY_ALIAS="bloquinhopy-key"
export KEY_PASSWORD="sua-senha"
```

Depois:
```bash
source ~/.bash_profile  # ou source ~/.zshrc
```

**Windows:**  
Variáveis de ambiente do Sistema (vai durar entre sessões)

## 3. Compilar a Versão de Release

Com o keystore configurado e as variáveis de ambiente definidas:

```bash
# Limpar builds anteriores
./gradlew clean

# Compilar APK de release
./gradlew assembleRelease

# OU compilar Bundle de release (recomendado para Google Play)
./gradlew bundleRelease
```

**Resultado:**
- APK: `app/build/outputs/apk/release/app-release.apk`
- Bundle: `app/build/outputs/bundle/release/app-release.aab`

## 4. Verificar a Assinatura do APK

```bash
jarsigner -verify -verbose -certs app/build/outputs/apk/release/app-release.apk
```

Você deve ver:
```
jar verified. This jar contains entries whose signer certificate is self-signed.
```

## 5. Obter Informações do App

Antes de enviar para o Google Play Console, você pode precisar:

### SHA-1 e SHA-256 do certificado

```bash
keytool -list -v -keystore bloquinhopy-release.jks -alias bloquinhopy-key
```

Anote o **SHA-1** e **SHA-256** para qualquer integração futura (Firebase, APIs, etc).

## 6. Configuração para CI/CD (GitHub Actions)

Para automatizar builds no CI/CD, configure secrets no GitHub:

1. **Settings > Secrets and variables > Actions**
2. Adicione:
   - `KEYSTORE_PATH`: `/home/runner/bloquinhopy-release.jks`
   - `KEYSTORE_PASSWORD`: Sua senha
   - `KEY_ALIAS`: `bloquinhopy-key`
   - `KEY_PASSWORD`: Sua senha

3. Upload do keystore em base64:
   ```bash
   base64 -i bloquinhopy-release.jks | pbcopy  # macOS
   # ou
   certutil -encode bloquinhopy-release.jks temp && findstr /v CERTIFICATE temp > keystore.b64 && del temp  # Windows
   ```

4. Adicione o secret `KEYSTORE_B64` com o conteúdo em base64

## 7. Troubleshooting

### Erro: "Keystore file not found"

Verifique:
- O caminho completo está correto
- As variáveis de ambiente estão definidas
- O arquivo `.jks` existe

### Erro: "Invalid keystore format"

- Verifique se o arquivo não foi corrompido
- Tente recriar o keystore

### Erro: "Certificate chain not found for: bloquinhopy-key"

- Verifique se o alias está correto
- Use `keytool -list -v -keystore seu-arquivo.jks` para listar aliases

## Próximos Passos

1. ✅ Keystore configurado
2. ✅ Build de release funcionando
3. → Prepare os assets do Google Play (ícone, screenshots)
4. → Crie conta no Google Play Console
5. → Configure a listagem do app
6. → Envie o `.aab` para revisão

## Referências

- [Android Developer - App Signing](https://developer.android.com/studio/publish/app-signing)
- [Google Play Console - Sign your app](https://support.google.com/googleplay/android-developer/answer/7384423)
- [Gradle Build Documentation](https://docs.gradle.org/current/userguide/signing_plugin.html)

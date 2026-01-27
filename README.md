# BloquinhoPy - Python Notebook IDE for Android

**Language:** English | [Português 🇧🇷](./README.pt-BR.md)

[![CI](https://github.com/bcmaymonegalvao/bloquinho-python/workflows/CI/badge.svg)](https://github.com/bcmaymonegalvao/bloquinho-python/actions)

BloquinhoPy is an offline-first Python IDE for Android focused on a simple notebook experience (.ipynb). It ships with an embedded Python runtime and a curated scientific stack for mobile.

## 🎯 MVP Goals

- ✅ **Phase 1**: Core Engine (Hilt DI, Room Database, PythonEngine)
- ✅ **Phase 2**: UI Integration (Jetpack Compose, Navigation, ViewModels)
- ✅ **Phase 3**: Advanced Features (Python Execution, Error Handling, Theming, Logging)

## 🏗️ Architecture

### Project Structure
- ✅ **Phase 4**: Cell Output UI (Display execution results, formatted outputs)
- 🚧 **Phase 5**: Alpha Testing (Python execution, .ipynb serialization, notebook persistence)

```
├── 💉 di/                    # Dependency Injection (Hilt Modules)
│   ├── DatabaseModule.kt    # Room Database configuration
│   └── EngineModule.kt      # PythonEngine singleton
├── 📊 data/                 # Data Layer
│   ├── 💾 local/
│   │   ├── database/        # Room Database
│   │   ├── dao/             # Data Access Objects
│   │   └── entities/        # Entity definitions
│   ├── 📦 repository/       # Repository pattern
│   └── 📄 model/            # Data models
├── 🐍 engine/               # Python Execution Engine
│   └── PythonEngine.kt      # Chaquopy integration
├── 🎨 ui/                   # UI Layer (Jetpack Compose)
│   ├── 🧭 navigation/       # Navigation graph
│   ├── 📁 project/          # Project list screen
│   ├── 📓 notebook/         # Notebook editor screen
│   ├── 🎭 theme/            # Material3 theming
│   └── 🧩 components/       # Reusable components
├── 🛠️ util/                 # Utilities
│   └── Logger.kt            # Logging & Custom Exceptions
├── 🚀 BloquinhoApplication.kt # App initialization with Hilt
└── 🏠 MainActivity.kt        # Entry point
├── di/                      # Dependency Injection (Hilt Modules)
│   ├── DatabaseModule.kt    # Room Database configuration
│   └── EngineModule.kt      # PythonEngine singleton
├── data/                    # Data Layer
│ [[[[  ├[[[[[[[[[[[[[[[[[[[[[[[[[[[── local/
│   │   ├── database/        # Room Database
│   │   ├── dao/             # Data Access Objects
│   │   └── entities/        # Entity definitions
│   ├── repository/          # Repository pattern
│   └── model/               # Data models
├── engine/                  # Python Execution Engine
│   └── PythonEngine.kt      # Chaquopy integration (mock for now)
├── ui/                      # UI Layer (Jetpack Compose)
│   ├── navigation/          # Navigation graph
│   ├── project/             # Project list screen
│   ├── notebook/            # Notebook editor screen
│   ├── theme/               # Material3 theming
│   └── components/          # Reusable components
├── util/                    # Utilities
│   └── Logger.kt            # Logging & Custom Exceptions
├── BloquinhoApplication.kt  # App initialization with Hilt
└── MainActivity.kt          # Entry point
```

## 🔧 Technologies

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Database**: Room
- **Dependency Injection**: Hilt
- **Navigation**: Compose Navigation
- **Async**: Coroutines & Flow
- **Python Runtime**: Chaquopy (for actual Python execution)
- **Build Tool**: Gradle 8.7
- **CI/CD**: GitHub Actions

## 📦 Key Components

### 1. **PythonEngine** (`engine/PythonEngine.kt`)
- Handles Python code execution
- Currently has mock evaluation; ready for Chaquopy integration
- Returns `ExecutionResult` with output, errors, and execution time

### 2. **Logger & Error Handling** (`util/Logger.kt`)
- Unified logging system with different log levels
- Custom exception types for better error handling
- Exception categories: Database, Execution, Validation, NotFound

### 3. **Material3 Theme** (`ui/theme/`)
- Comprehensive color scheme (light & dark modes)
- Custom typography for all text styles
- Brand colors using teal palette

### 4. **Data Layer** (`data/`)
- **Entities**: `ProjectEntity`, `NotebookEntity` with relationships
- **DAOs**: `ProjectDao`, `NotebookDao` for CRUD operations
- **Repository**: `BloquinhoRepository` for unified data access
- **Database**: `AppDatabase` Room implementation

### 5. **UI Screens**
- **ProjectListScreen**: Display and create projects
- **NotebookScreen**: Edit notebooks with Python code execution
- **Navigation**: Jetpack Compose navigation with arguments

## 🚀 Getting Started

### Prerequisites
- Android SDK 34+
- JDK 17+
- Gradle 8.7+

### Build & Run

```bash
# Clone repository
git clone https://github.com/bcmaymonegalvao/bloquinho-python.git
cd bloquinho-python

# Build debug APK
./gradlew assembleDebug

# Install on device/emulator
./gradlew installDebug

# Run tests
./gradlew test
```

[## 📋 Next Steps (Phase 4)

[[- [ ] Integrate Chaquopy for actual Python execution
- [ ] 
## 🔄 Phase 4: Advanced Features & Expansion (In Progress)

### Implementation Status

**Chaquopy Integration** ✅ **Complete**- Runtime module setup with Python 3 and numpy
- ✅ Python runtime with Chaquopy integrated
- ✅ Real Python code execution in NotebookEngine
- ✅ Error handling and output captureNotebookEngine interface with mock execution ready for Chaquopy
- [TODO: Integrate Chaquopy Python interpreter for actual execution

**Notebook Persistence** ✅ **Complete**- Room database entities (ProjectEntity, NotebookEntity) implemented
- ✅ Room database entities (ProjectEntity, NotebookEntity) implemented
- ✅ .ipynb serialization/deserialization with NotebookSerializer
- ✅ Save/Load functions in NotebookViewModel
- 🚧 Import/export UI pending CRUD operations framework in place
- TODO: Add .ipynb serialization/deserialization
- TODO: Implement import/export functionality

**Planned Features**
- ✅ Chaquopy for actual Python execution] Integrate Chaquopy for actual Python execution
- ✅ .ipynb serialization/deserialization] Implement notebook persistence (`.ipynb` format)
- 🚧 Add UI for file import/export
- 🚧 File picker integration[ ] Add project collaboration features (basic)
- [ ] Create marketplace for packages
- [ ] Implement cloud sync (Firebase)
- [ ] Build documentation website

### 🎯 Phase 5 Status

**✅ Complete:**
- [x] Chaquopy Python execution fully integrated
- [x] .ipynb serialization/deserialization
- [x] Notebook save/load functions
- [x] Error handling for Python code

**🚧 Next Steps (Phase 6):**
- [ ] File import/export UI
- [ ] Performance optimization
- [ ] Extended Python library support

**Core Infrastructure** ✅
- [x] CI/CD workflow fixed and operational
- [x] Chaquopy Python runtime configured
- [x] Database layer complete with entities and DAOs
- [x] Multi-language support (English/Portuguese)

**Ready for Phase 5 - Production Release**

## 📱 Play Store Release Roadmap

### When can you download from Play Store?

**Timeline to Play Store:**

🟢 **Phase 5: Alpha Testing** (Current + 2-3 weeks)
- Complete Chaquopy integration with actual Python execution
- Implement .ipynb file import/export
- Basic error handling and crash reporting
- Internal testing (Android Studio builds)
- **Status:** You can build and test on your device NOW using `./gradlew installDebug`

🟡 **Phase 6: Beta Release** (1-2 months)
- Closed beta testing with Firebase App Distribution
- Performance optimization
- UI/UX polish
- Security audit
- **Status:** Beta testers can install via Firebase link

🟠 **Phase 7: Production Release** (3-4 months)
- Play Store listing creation
- Marketing materials (screenshots, description)
- Privacy policy and terms of service
- Final testing on multiple devices
- Google Play Console approval
- **Status:** PUBLIC availability on Play Store!

### 🚀 Quick Start (Test Now!)

You can test BloquinhoPy on your smartphone RIGHT NOW:

```bash
# 1. Clone repository
git clone https://github.com/bcmaymonegalvao/bloquinho-python.git
cd bloquinho-python

# 2. Build and install on connected device
./gradlew installDebug

# 3. Enable USB debugging on your Android phone
# Settings > Developer Options > USB Debugging

# 4. Connect phone via USB and install!
```

**Minimum Requirements:**
- Android 8.0 (API 26) or higher
- 100MB free storage
- USB debugging enabled


## 🧪 Alpha Testing

### Building from Source

1. Clone the repository:
```bash
git clone https://github.com/bcmaymonegalvao/bloquinho-python.git
cd bloquinho-python
```

2. Open in Android Studio (Hedgehog or later)

3. Sync Gradle and build the project

4. Run on your device or emulator

### Testing Python Execution

- Create new cells and execute Python code
- Test basic Python expressions: `print("Hello")`, `2 + 2`, etc.
- Check output display in cell results
- Verify error handling for invalid code

### Known Limitations

- 🚧 .ipynb file save/load UI not yet implemented (functions available in ViewModel)
- 🚧 Limited Python standard library (Chaquopy constraints)
- 🚧 No external package installation yet

---


MIT - See LICENSE file for details

## 👨‍💻 Author

**Bruno César Maymone Galvão** - Senior Developer & ML Engineer
- GitHub: [@bcmaymonegalvao](https://github.com/bcmaymonegalvao)
- Focus: Python, Machine Learning, Full-Stack Development

---

**BloquinhoPy** - Making Python development accessible on Android 📱✨


## 🚀 Play Store Deployment (Passo 1 - IN PROGRESS)

Veja a documentação completa em [docs/DEPLOYMENT.md](./docs/DEPLOYMENT.md)

### Passo 1: Build Configuration ✅ COMPLETO

- ✅ **build.gradle.kts** - Versionamento e configuração de assinatura
  - compileSdk/targetSdk: API 35
  - versionCode: 1 | versionName: "1.0.0"
  - Signing configs com variáveis de ambiente
  - R8 minification e resource shrinking ativados
  
- ✅ **proguard-rules.pro** - Otimizações de código
  - Mantém classes essenciais (Compose, Hilt, Python runtime)
  - Remove logging em builds de release
  - ~5-10% redução de tamanho APK

- ✅ **RELEASE_SETUP.md** - Guia completo de setup
  - Geração de keystore (keytool)
  - Configuração de variáveis de ambiente (Windows/macOS/Linux)
  - Compilação de APK/Bundle
  - Troubleshooting

### Próximos Passos

**Seu turno (Passo 2):**
1. Crie conta no [Google Play Console](https://play.google.com/console)
2. Comece novo app listing
3. Preencha informações básicas (nome, descrição, categoria)
4. Confira requisitos de conformidade

Depois disso, continuaremos com:
- **Passo 3**: Preparar assets (ícone 512x512, screenshots)
- **Passo 4**: Beta testing
- **Passo 5**: Launch na Play Store

**Build Commands:**
```bash
# Setup keystore (primeira vez)
keytool -genkey -v -keystore bloquinhopy-release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias bloquinhopy-key

# Compilar release bundle
export KEYSTORE_PATH="./bloquinhopy-release.jks"
export KEYSTORE_PASSWORD="sua-senha"
export KEY_ALIAS="bloquinhopy-key"
export KEY_PASSWORD="sua-senha"
./gradlew bundleRelease
```

Ver: [docs/RELEASE_SETUP.md](./docs/RELEASE_SETUP.md) para instruções detalhadas.

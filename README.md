# BloquinhoPy - Python Notebook IDE for Android

[![CI](https://github.com/bcmaymonegalvao/bloquinho-python/workflows/CI/badge.svg)](https://github.com/bcmaymonegalvao/bloquinho-python/actions)

BloquinhoPy is an offline-first Python IDE for Android focused on a simple notebook experience (.ipynb). It ships with an embedded Python runtime and a curated scientific stack for mobile.

## 🎯 MVP Goals

- ✅ **Phase 1**: Core Engine (Hilt DI, Room Database, PythonEngine)
- ✅ **Phase 2**: UI Integration (Jetpack Compose, Navigation, ViewModels)
- ✅ **Phase 3**: Advanced Features (Python Execution, Error Handling, Theming, Logging)

## 🏗️ Architecture

### Project Structure
```
app/src/main/java/io/github/bcmaymonegalvao/bloquinhopy/
├── di/                      # Dependency Injection (Hilt Modules)
│   ├── DatabaseModule.kt    # Room Database configuration
│   └── EngineModule.kt      # PythonEngine singleton
├── data/                    # Data Layer
│   ├── local/
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

## 📋 Next Steps (Phase 4)

- [ ] Integrate Chaquopy for actual Python execution
- [ ] 
## 🔄 Phase 4: Advanced Features & Expansion (In Progress)

### Implementation Status

**Chaquopy Integration** ✅ Configured
- Runtime module setup with Python 3 and numpy
- NotebookEngine interface with mock execution ready for Chaquopy
- TODO: Integrate Chaquopy Python interpreter for actual execution

**Notebook Persistence** 🔄 In Progress
- Room database entities (ProjectEntity, NotebookEntity) implemented
- CRUD operations framework in place
- TODO: Add .ipynb serialization/deserialization
- TODO: Implement import/export functionality

**Planned Features**
- [ ] Integrate Chaquopy for actual Python execution
- [ ] Implement notebook persistence (`.ipynb` format)
- [ ] Add project collaboration features (basic)
- [ ] Create marketplace for packages
- [ ] Implement cloud sync (Firebase)
- [ ] Build documentation website
- [ ] Implement notebook persistence (`.ipynb` format)
- [ ] Add project collaboration features
- [ ] Create marketplace for packages
- [ ] Implement cloud sync
- [ ] Build documentation website

## 📄 License

MIT - See LICENSE file for details

## 👨‍💻 Author

**Bruno César Maymone Galvão** - Senior Developer & ML Engineer
- GitHub: [@bcmaymonegalvao](https://github.com/bcmaymonegalvao)
- Focus: Python, Machine Learning, Full-Stack Development

---

**BloquinhoPy** - Making Python development accessible on Android 📱✨

# Hytale Kotlin Plugin Template

[![Build](https://github.com/revolutionxk/hytale-kotlin-template/workflows/Build/badge.svg)](https://github.com/revolutionxk/hytale-kotlin-template/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/kotlin-2.2.21-blue.svg?logo=kotlin)](http://kotlinlang.org)

This repository provides a clean, modern starting point for developing Hytale plugins in Kotlin. It includes a well-organized project structure, sample command and event implementations, and automated build and release workflows using GitHub Actions.

## Features


## Requirements


## Getting Started

### Using as a Template

1. Click the **"Use this template"** button on GitHub
2. Clone your new repository:
```bash
git clone https://github.com/yourusername/your-plugin.git
cd your-plugin
```

3. **Customize the project:**
   - Rename the package from `com.example.plugin` to your own package
   - Edit `manifest.json` with your plugin information
   - Update `group` and `version` in `build.gradle.kts`
   - Modify this README with your plugin details

4. **Add the Hytale Server JAR:**
```bash
mkdir -p libs
# Copy your HytaleServer.jar to libs/
```

5. **Build the project:**
```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`

## Project Structure

```
hytale-kotlin-template/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/example/plugin/
│   │   │       ├── Plugin.kt
│   │   │       ├── commands/
│   │   │       │   └── ExampleCommand.kt
│   │   │       ├── config/
│   │   │       │   └── ExampleConfig.kt
│   │   │       ├── events/
│   │   │       │   └── PlayerChatEventListener.kt
│   │   │       └── style/
│   │   │           └── ExampleStyle.kt
│   │   └── resources/
│   │       └── manifest.json
│   └── test/
├── libs/
├── build.gradle.kts
└── settings.gradle.kts
```

## Building and Deployment

### Local Build
```bash
./gradlew build
```

### Clean Build
```bash
./gradlew clean
```

### Build with Tests
```bash
./gradlew test
```

### Install on Server
Copy the JAR from `build/libs/` to your Hytale server's `plugins/` folder.

## CI/CD

This template includes GitHub Actions that automatically:

To create a release:
```bash
git tag v1.0.0
git push origin v1.0.0
```

## Customization

### Changing Plugin Name

1. **manifest.json**: Update `Name`, `Group`, `Description`, `Authors`
2. **build.gradle.kts**: Update `group` and `version`
3. **Packages**: Rename `com.example.plugin` to your package
4. **README.md**: Update this file

### Adding Dependencies

Edit `build.gradle.kts`:
```kotlin
dependencies {
    implementation("group:artifact:version")
}
```

## Contributing

Contributions are welcome! Feel free to:

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Useful Links


## Support

If you encounter issues or have questions:

Made by [Your Name](https://github.com/yourusername)

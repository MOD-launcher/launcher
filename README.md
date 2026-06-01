# MOD Launcher

A lightweight Java-based Minecraft launcher that aggregates mods and modpacks from multiple sources without relying on sketchy sites like 9minecraft.net.

## Features

✅ Search mods from **Modrinth**, **Technic**, and **FTB**  
✅ Beautiful JavaFX UI  
✅ Lightweight and fast  
✅ Supports modded Minecraft with VulkanMod  
✅ Multi-version support (1.8+)  

## Supported Platforms

- **Modrinth** - Modern mod platform
- **Technic** - Classic mod platform
- **FTB** - Feed The Beast modpacks

## Requirements

- Java 17+
- Gradle 7.0+

## Building

```bash
gradle build
```

## Running

```bash
gradle run
```

## Project Structure

```
src/main/java/com/modlauncher/
├── ModLauncher.java          # Main application entry
├── api/
│   ├── ModrinthAPI.java      # Modrinth API client
│   ├── TechnicAPI.java       # Technic API client
│   ├── FTBAPI.java           # FTB API client
│   └── ModInfo.java          # Mod data class
└── ui/
    ├── MainWindow.java       # Main window setup
    └── tabs/
        ├── ModrinthTab.java  # Modrinth UI
        ├── TechnicTab.java   # Technic UI
        └── FTBTab.java       # FTB UI
```

## Contributing

Contributions welcome! This is a community-driven project.

## License

MIT

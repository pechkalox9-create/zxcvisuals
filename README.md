# zxcvisuals

A client-side visual/HUD starter mod for **Minecraft 1.21.11 + Fabric**.

## Features

- Custom violet-themed ClickGUI (Right Shift)
- Toggleable HUD overlay (FPS and block coordinates)
- Client-only: no server installation required
- Gradle build and GitHub Actions workflow

## Build

Requirements: Java 21 and Git.

```bash
./gradlew build
```

The mod jar is created in `build/libs/`.

## Controls

- `Right Shift` — open/close the ClickGUI
- Click the `HUD overlay` row — toggle the HUD
- `Esc` — close the GUI

## Compatibility

Minecraft 1.21.11, Fabric Loader 0.18+, Fabric API 0.141.1+1.21.11, Java 21.

## License and attribution

This repository's original source code is licensed under MIT (see `LICENSE`). It does not bundle copied client code or assets. If third-party open-source components are added later, their licenses and notices must be preserved individually.

Minecraft is a trademark of Mojang Studios. This project is an independent community mod and is not affiliated with Mojang or Microsoft.

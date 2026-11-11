# ElectricianGlasses

[简体中文](#简体中文) | [English (US)](#english-us)

> **AI 生成声明**：本项目包含 AI 生成内容，代码仅供正常使用、参考和学习，**请不要将其再次用于 AI 训练**！
> **AI Generated Disclaimer**: This project contains AI-generated content. The code is provided for normal use, reference, and learning only. **Please do not use it for AI training again!**

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Version](https://img.shields.io/badge/version-1.1--SNAPSHOT-informational.svg)](https://github.com/EndlessMCOrg/ElectricianGlasses/releases)

---

## 简体中文

### 简介

ElectricianGlasses（电工眼镜）是一个面向 NeoForge 1.21.1 的小型模组。佩戴电工眼镜后，玩家可以看到 PowerGrid 模组中的电线；未佩戴时，这些电线会在服务端被隐藏，不会同步到客户端。

### 功能特性

- **电工眼镜物品**：注册于“工具与实用物品”创造模式标签页，可装备在头盔栏。
- **PowerGrid 电线显示**：佩戴后可见 `block_wire`、`hanging_wire`、`cord` 三类电线实体。
- **服务端隐藏**：未佩戴眼镜的玩家不会收到上述电线实体的追踪数据，避免客户端看到多余实体。
- **Curios 兼容**：安装 Curios 后，也可将眼镜放入头部饰品栏；未安装时仅支持原版头盔栏。
- **即时刷新**：戴上或摘下眼镜时，周围已追踪电线的可见状态会立即更新。
- **轻量独立**：未安装 PowerGrid 时模组仍可正常加载，只是不会影响任何实体。

### 环境要求

- 加载器：NeoForge 1.21.1（21.1.195 或更高版本）。
- Minecraft 版本：1.21.1。
- Java：21。
- 必需依赖：PowerGrid 0.6.0.1 或更高版本。
- 可选依赖：Curios 9.5.1 或更高版本。
- 构建工具：Gradle 8.8（使用项目自带 Wrapper，仅构建时需要）。

### 安装

1. 安装 NeoForge 1.21.1 服务端/客户端。
2. 将 PowerGrid 与（可选的）Curios 放入 `mods/` 目录。
3. 将构建生成的 `electricianglasses-<版本>.jar` 放入客户端与服务端的 `mods/` 目录。
4. 启动游戏。

> 本模组同时包含物品注册与服务端实体追踪逻辑，建议客户端与服务端都安装。

### 使用

- 在创造模式“工具与实用物品”标签页中获取电工眼镜。
- 将眼镜放入头盔栏，或放入 Curios 头部饰品栏。
- 佩戴后即可看到 PowerGrid 的电线；摘下后电线会重新隐藏。
- 当前版本暂无合成配方，仅可通过创造模式获取。

### 构建

- JDK：21。
- 使用项目自带的 Gradle Wrapper：
  - Linux / macOS：`./gradlew build`
  - Windows：`gradlew.bat build`
- 构建产物位于 `build/libs/electricianglasses-<版本>.jar`。
- 默认版本号由 `gradle.properties` 中的 `mod_version` 控制；构建时可覆盖，例如：

```bash
./gradlew build -Pmod_version=1.1.0
```

- 开发运行：`./gradlew runClient` 或 `./gradlew runServer`（Windows 使用 `gradlew.bat`）。

### 兼容性

- 目标平台：Minecraft 1.21.1 / NeoForge。
- PowerGrid：0.6.0.1+（电线实体显示）。
- Curios：9.5.1+（可选，头部饰品栏支持）。
- 服务端与客户端均需安装本模组。

### 实现说明

- 通过 Mixin 拦截 `ChunkMap$TrackedEntity#updatePlayer`，在玩家未佩戴眼镜时取消电线实体的追踪同步。
- 玩家佩戴状态变化时遍历已追踪实体，补发或撤回电线实体数据。
- Curios 兼容通过反射调用实现，未安装 Curios 时不会产生硬依赖；反射句柄会缓存以降低每 tick 开销。

### 变更日志

- `1.1-SNAPSHOT`：作者变更为 OutHimic 与 EndlessMCOrg，项目地址迁移至 EndlessMCOrg；补充 MIT 许可证；完善 `.gitignore`；清理无用代码与构建配置；缓存 Curios 反射调用；版本号步进。
- `1.0-SNAPSHOT`：初始版本，包含电工眼镜物品、PowerGrid 电线可见性控制与 Curios 头部饰品栏支持。

### 许可证

本项目使用 MIT 许可证，详见 [LICENSE](LICENSE)。

Copyright (c) 2024-2026 EndlessMC Organization

---

## English (US)

### Overview

ElectricianGlasses is a small NeoForge 1.21.1 mod. While wearing the Electrician Glasses, players can see wires added by the PowerGrid mod; without the glasses, those wires are hidden on the server and are not synchronized to the client.

### Features

- **Electrician Glasses item**: registered in the Tools & Utilities creative tab and equippable in the helmet slot.
- **PowerGrid wire visibility**: shows `block_wire`, `hanging_wire`, and `cord` entities while worn.
- **Server-side hiding**: players without the glasses do not receive tracking data for those wire entities.
- **Curios support**: when Curios is installed, the glasses can also be placed in the head curio slot; otherwise only the vanilla helmet slot is used.
- **Instant refresh**: wire visibility updates immediately when the glasses are equipped or removed.
- **Lightweight and standalone**: the mod loads normally without PowerGrid and simply has no effect on entities.

### Requirements

- Loader: NeoForge 1.21.1 (21.1.195 or newer).
- Minecraft version: 1.21.1.
- Java: 21.
- Required dependency: PowerGrid 0.6.0.1 or newer.
- Optional dependency: Curios 9.5.1 or newer.
- Build tool: Gradle 8.8 (via the bundled wrapper, build only).

### Installation

1. Install a NeoForge 1.21.1 client and server.
2. Place PowerGrid and (optionally) Curios into the `mods/` directory.
3. Place the built `electricianglasses-<version>.jar` into the `mods/` directory on both client and server.
4. Launch the game.

> The mod contains both item registration and server-side entity tracking logic, so installing it on both sides is recommended.

### Usage

- Obtain the Electrician Glasses from the Tools & Utilities creative tab.
- Place them in the helmet slot or the Curios head slot.
- PowerGrid wires become visible while worn and are hidden again when removed.
- There is currently no crafting recipe; the item can only be obtained in creative mode.

### Building

- JDK: 21.
- Use the bundled Gradle wrapper:
  - Linux / macOS: `./gradlew build`
  - Windows: `gradlew.bat build`
- The output is written to `build/libs/electricianglasses-<version>.jar`.
- The default version is controlled by `mod_version` in `gradle.properties`; it can be overridden at build time, for example:

```bash
./gradlew build -Pmod_version=1.1.0
```

- Development runs: `./gradlew runClient` or `./gradlew runServer` (use `gradlew.bat` on Windows).

### Compatibility

- Target platform: Minecraft 1.21.1 / NeoForge.
- PowerGrid: 0.6.0.1+ (wire entity visibility).
- Curios: 9.5.1+ (optional, head curio slot support).
- The mod must be installed on both client and server.

### Implementation Notes

- A Mixin cancels entity tracking in `ChunkMap$TrackedEntity#updatePlayer` for wire entities when the player is not wearing the glasses.
- When the player's wearing state changes, tracked entities are iterated to resend or withdraw wire entity data.
- Curios support is implemented through reflection, so Curios is not a hard dependency; reflection handles are cached to reduce per-tick overhead.

### Changelog

- `1.1-SNAPSHOT`: changed authors to OutHimic and EndlessMCOrg and moved the project to EndlessMCOrg; added the MIT license; improved `.gitignore`; removed dead code and unused build configuration; cached Curios reflection calls; bumped the version.
- `1.0-SNAPSHOT`: initial release with the Electrician Glasses item, PowerGrid wire visibility control, and Curios head slot support.

### License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

Copyright (c) 2024-2026 EndlessMC Organization

---

## 致谢 / Thanks

- PowerGrid 项目 / project
- Curios 项目 / project
- NeoForge 项目 / project
- Trae 提供的几乎*完美*的 IDEA / for the almost *perfect* IDE

# SimpleTask - Minecraft NPC 任务系统插件

`SimpleTask` 是一个基于 Bukkit/Spigot 的 Minecraft 插件，旨在为服务器提供灵活的 NPC 任务系统。通过该插件，服务器管理员可以创建自定义
NPC，并为玩家分配任务、对话和奖励。

---

## 功能特性

- **自定义 NPC**：创建具有唯一名称、职业和位置的 NPC。
- **任务系统**：为 NPC 分配任务，支持任务链和多任务。
- **对话系统**：支持多轮对话和玩家选项。
- **物品提交**：玩家可以通过提交指定物品完成任务或对话。
- **无敌 NPC**：NPC 无敌且不可移动，确保稳定性。
- **事件监听**：监听 NPC 死亡事件，自动重新生成 NPC。
- **数据持久化**：NPC 数据支持序列化和反序列化，可存储到数据库或文件。

---

## 安装步骤

### 1. 前置条件

- **Minecraft 服务器**：确保服务器运行 Bukkit 或 Spigot（推荐 1.20.1 版本）。
- **Java 17**：插件需要 Java 17 或更高版本。

### 2. 下载插件

1. 从 [Releases](https://github.com/your-repo/simple-task/releases) 页面下载最新的 `SimpleTask.jar`。
2. 将 `SimpleTask.jar` 放入服务器的 `plugins` 目录。

### 3. 启动服务器

启动服务器，插件会自动生成配置文件和数据文件。

---

## 使用方法

### 1. 创建 NPC

通过命令或配置文件创建 NPC。以下是一个示例配置文件：

```yaml
npcs:
  villager_1:
    name: "村民"
    profession: "FARMER"
    location: [ 100, 64, 200 ]
    tasks:
      - taskName: "收集木材"
        taskUuid: "123e4567-e89b-12d3-a456-426614174000"
        taskDescription: "收集10个橡木"
        taskText: [ "帮助村民收集木材。" ]
    dialogues:
      - npcMessage: "你好，你能给我5个橡木吗？"
        playerOptions: [ "好的", "拒绝" ]
        requiredItem:
          type: "OAK_LOG"
          amount: 5
        rewardItem:
          type: "GOLD_NUGGET"
          amount: 3
```

### 2. 与 NPC 交互

玩家右键点击 NPC 即可触发交互：

- 如果 NPC 有任务，玩家可以接受任务并提交所需物品。
- 如果 NPC 有对话，玩家可以选择对话选项并提交物品。

### 3. 管理 NPC

- **重新生成 NPC**：如果 NPC 死亡，插件会自动重新生成。
- **编辑 NPC**：通过配置文件或命令编辑 NPC 的任务和对话。

---

## 开发指南

### 1. 项目结构

```
src/main/kotlin/cn/qfys521/simpleTask
├── core        // 核心接口与抽象类
├── model       // 数据模型类
├── impl        // 接口实现
├── listener    // 事件监听
├── database    // 数据库支持
├── util        // 工具类
└── plugin      // 主插件入口
```

### 2. 构建插件

1. 克隆项目：
   ```bash
   git clone https://github.com/your-repo/simple-task.git
   ```
2. 构建插件：
   ```bash
   ./gradlew.bat shadowJar
   ```
3. 构建后的插件位于 `build/libs/SimpleTask.jar`。

### 3. 生成文档

生成 Kotlin 文档：

```bash
./gradlew.bat dokkaHtml
```

文档会输出到 `build/dokka` 目录。

---

## 配置示例

### `config.yml`

```yaml
npcs:
  villager_1:
    name: "村民"
    profession: "FARMER"
    location: [ 100, 64, 200 ]
    tasks:
      - taskName: "收集木材"
        taskUuid: "123e4567-e89b-12d3-a456-426614174000"
        taskDescription: "收集10个橡木"
        taskText: [ "帮助村民收集木材。" ]
    dialogues:
      - npcMessage: "你好，你能给我5个橡木吗？"
        playerOptions: [ "好的", "拒绝" ]
        requiredItem:
          type: "OAK_LOG"
          amount: 5
        rewardItem:
          type: "GOLD_NUGGET"
          amount: 3
```

---

## 贡献指南

欢迎贡献代码！请遵循以下步骤：

1. Fork 项目仓库。
2. 创建新分支：
   ```bash
   git checkout -b feature/your-feature
   ```
3. 提交更改：
   ```bash
   git commit -m "Add your feature"
   ```
4. 推送分支：
   ```bash
   git push origin feature/your-feature
   ```
5. 提交 Pull Request。

---

## 许可证

本项目采用 [AGPL-3.0 许可证](LICENSE)。

---

## 作者信息

- **作者**：qfys521
- **GitHub**：[qfys521](https://github.com/qfys521)

---

感谢使用 `SimpleTask`! 🎉

```
# Инструкция по запуску проекта

## 1. Java

В проекте используется версия **Java openjdk 21.0.6**. Убедитесь, что у вас установлена необходимая версия Java. Вы можете проверить установленную версию, выполнив следующую команду в терминале:

```bash
java -version
```

## 2. Maven

Проект использует систему сборки **Maven версии 3.8.7**. Чтобы проверить установленную версию Maven, выполните следующую команду:

```bash
mvn -v
```

## 3. Сторонние библиотеки

### 1. JCommander версии 2.0
Библиотека для сбора аргументов командной строки.
* Зависимость для Maven:
  ```xml
          <dependency>
            <groupId>org.jcommander</groupId>
            <artifactId>jcommander</artifactId>
            <version>2.0</version>
        </dependency>
  ```
* Ссылка на библиотеку: [JCommander](https://jcommander.org/)
  ### 2. Lombok версии 1.18.34
Библиотека для сокращения шаблонного кода с помощью аннотаций
* Зависимость для Maven:
  ```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>
  ```
* Ссылка на библиотеку: [Lombok](https://projectlombok.org/)

## 4. Запуск
Для запуска нужно выполнить следующую комнаду с соответствующими флагами:
```bash
java -jar out/artifacts/shift_test_task_2025_jar/shift-test-task-2025.jar [FLAG]... FILE...
```

# 📱 Mobile Tests (Wikipedia)

Репозиторий содержит автоматизированные тесты мобильного приложения **Wikipedia**.
Проект демонстрирует работу с Appium и Selenide, запуск Android-тестов на реальных
устройствах BrowserStack и создание отчётов Allure.

## ✅ Стек

- Java 17+
- JUnit 5
- Selenide + Appium
- BrowserStack
- OWNER
- Jenkins
- Allure Report

## ✅ Тесты

В пакете `tests` реализованы четыре проверки:

1. **Поиск статьи:** открытие поиска, ввод запроса `Sberbank`, проверка наличия результатов.
2. **Открытие статьи:** выбор статьи `Sberbank` и проверка отображения заголовка и содержимого.
3. **Очистка поиска:** удаление введённого запроса кнопкой крестика и проверка исчезновения результатов.
4. **Главный экран:** прохождение приветственных экранов и проверка открытия главного экрана Wikipedia.

Тесты запускаются на **Android**. Текущая конфигурация: **Google Pixel 7 / Android 13.0**.

## 📊 Jenkins и Allure

[![Jenkins](https://img.shields.io/badge/Jenkins-Открыть_проект-D24939?style=for-the-badge&logo=jenkins&logoColor=white)](https://jenkins.qa.guru/job/42_Chukanova_mobile/)
[![Allure Report](https://img.shields.io/badge/Allure-Посмотреть_отчёт-65A637?style=for-the-badge)](https://jenkins.qa.guru/job/42_Chukanova_mobile/allure/)

В Jenkins можно запустить сборку и просмотреть результат
в **Allure Report**. Отчёт содержит шаги тестов, скриншоты, структуру экрана и
ссылки на видеозаписи выполнения в BrowserStack.

## 📸 Визуализация тестов

Скриншоты из успешного запуска на Google Pixel 7:

<p align="center">
  <img src="images/screens/home.png" width="280" alt="Главный экран Wikipedia после прохождения приветствия" />
  &nbsp;&nbsp;
  <img src="images/screens/article.png" width="280" alt="Открытая статья Sberbank в Wikipedia" />
</p>

Видеозаписи доступны во вложениях **Video** к тестам в Allure.

## ⚙️ Настройка

Настройки устройства и приложения находятся в `src/test/resources/config.properties`.
Перед запуском загрузите APK Wikipedia в **BrowserStack App Automate** и укажите
полученный идентификатор `bs://...` в параметре `app`.

Логин и ключ BrowserStack для локального запуска задаются в файле
`.gradle/browserstack.properties`, который исключён из Git:

```properties
userName=YOUR_USERNAME
accessKey=YOUR_ACCESS_KEY
```

Тесты адаптированы под APK `50617-alpha-2026-11-13`. При использовании другой версии
приложения может потребоваться обновление локаторов.

## 🚀 Запуск

Все тесты:

```bash
./gradlew clean test
```

Только тесты поиска:

```bash
./gradlew test --tests tests.SearchTests
```

Генерация и просмотр отчёта Allure:

```bash
./gradlew allureReport
./gradlew allureServe
```

Результаты Allure сохраняются в `build/allure-results`.

В Jenkins логин и ключ передаются через параметры сборки:

```text
clean test
-DbrowserstackUrl=${browserstack_url}
-DuserName=${user_name}
-DaccessKey=${access_key}
```

## 🗂️ Структура проекта

- `config` — конфигурация BrowserStack через OWNER.
- `drivers` — создание AndroidDriver.
- `screens` — локаторы и действия экранов приложения.
- `tests` — тестовые сценарии и общий класс TestBase.
- `helpers` — вложения Allure и получение видео из BrowserStack.

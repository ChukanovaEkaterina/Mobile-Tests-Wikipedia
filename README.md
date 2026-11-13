# Мобильные тесты Wikipedia

Проект на Java, JUnit 5, Selenide и Appium для запуска Android-тестов в BrowserStack с отчётами Allure.


## Тесты

- Поиск статьи.
- Открытие найденной статьи.
- Очистка запроса и списка результатов кнопкой крестика.
- Открытие главного экрана при запуске приложения.

## Структура

- `config` — настройки BrowserStack через OWNER.
- `drivers` — создание AndroidDriver.
- `screens` — элементы и действия экранов Wikipedia.
- `helpers` — скриншоты, исходный код экрана и видео BrowserStack для Allure.
- `tests` — тесты и общий жизненный цикл драйвера.

## Настройка

Настройки устройства и приложения находятся в `src/test/resources/config.properties`.
Логин и ключ вашего аккаунта хранятся локально в `.gradle/browserstack.properties`:

```properties
userName=YOUR_USERNAME
accessKey=YOUR_ACCESS_KEY
```

Папка `.gradle` уже исключена из Git. На другом компьютере создайте этот файл самостоятельно
или передайте значения через `-DuserName` и `-DaccessKey`.

Перед запуском загрузите APK Wikipedia в BrowserStack App Automate и замените `app=bs://...`
на выданный `app_url`. Текущие тесты адаптированы под Alpha APK Wikipedia
`50617-alpha-2026-11-13` с английским интерфейсом. При запуске проходят четыре
приветственных экрана. Sample APK BrowserStack 2017 года не подходит:
в нём не загружается содержимое статей.

## Запуск

```bash
./gradlew testClasses
./gradlew test
./gradlew test --tests tests.SearchTests
./gradlew test -Dapp=bs://YOUR_APP_ID
./gradlew allureReport
./gradlew allureServe
```

Результаты Allure сохраняются в `build/allure-results`.
Для запуска Gradle 9.3 требуется JDK 17 или новее.

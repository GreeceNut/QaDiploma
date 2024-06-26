## Шаги по воспроизведению автотестов

1. Командой <code>git clone</code> склонировать репозиторий к себе на локальную папку
2. Запустить Docker Dekstop
3. Запустить Intellij IDEA
4. Запустить на Intellij IDEA склонированный проект
5. Запустить контейнеры в терминале командой <code>docker-compose up</code>
6. Запустить SUT командой: <code>java -jar ./artifacts/aqa-shop.jar</code>
7. Запустить тесты командой на выбор в терминале. **Для MSQL** <code>./gradlew clean test "-Ddb.url=jdbc:mysql:
   //localhost:3306/app",</code> **Для postgresql** <code>./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:
   5432/app"</code>
8. Сгенирировать отчеты Allure в терминале: <code>gradlew allureReport</code> (или <code>gradlew allureServe</code>)
9. Открыть отчет Allure. Отчет находится на пути: <code>build/reports/allure-report/allureReport/index.html</code>


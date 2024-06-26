# План автоматизации тестирования возможности записаться оплаты «Путешествие дня»

**Необходимо протестировать возможность оплаты «Путешествие дня» дебетовой и кредитной картами.**

### Предоставленные карты для тестирования

- 4444 4444 4444 4441, status: 'APPROVED' (далее одобренная карта)
- 4444 4444 4444 4442, status: 'DECLINED' (далее отклоненная карта)

### Валидными данными при заполнении полей следует считать:

- Номер карты: 16-значный номер карты формата — **** **** **** ****
- Месяц: 01-12, но не ранее текущего месяца, если указан текущий год
- Год: последние две цифры, соответствующие текущему году и далее, но не позже пяти лет
- Владелец: имя состоящее из двух слов на латинице
- CVC/CVV: 3-значное число формата — ***

## 1. Перечень автоматизируемых сценариев

### *Позитивные сценарии*

#### Ввод валидных данных в поле "Номер карты" при оплате по дебетовой карте

*Предусловие: остальные поля заполнены валидными данными*

1. Ввести в поле "номер карты" 4444 4444 4444 4441.
   _Ожидаемый результат: Поле приняло значение. Появляется сообщение "Ошибка! Банк отказал в проведении операции."
   В БД таблице payment_entity должна появиться информация о транзакции со статусом APPROVED_
2. Ввести в поле "номер карты" 4444 4444 4444 4442.
   _Ожидаемый результат: Поле приняло значение. Появляется сообщение "Ошибка! Банк отказал в проведении операции."
   В БД таблице payment_entity должна появиться информация о транзакции со статусом DECLINED_

#### Ввод валидных данных в поле "Номер карты" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными*

1. Ввести в поле "номер карты" 4444 4444 4444 4441.
   _Ожидаемый результат: Поле приняло значение. Появляется сообщение "Операция одобрена Банком.". В БД таблице
   credit_request_entity должна появиться информация о транзакции со статусом APPROVED_
2. Ввести в поле "номер карты" 4444 4444 4444 4442.
   _Ожидаемый результат: Поле приняло значение. Появляется сообщение "Ошибка! Банк отказал в проведении операции."
   В БД таблице credit_request_entity должна появиться информация о транзакции со статусом DECLINED_

### *Негативные сценарии*

#### Ввод невалидных данных в поле "Номер карты" при оплате по дебетовой карте

*Предусловие: остальные поля заполнены валидными данными*

1. Ввести в поле 4444 4444 4444 4444.
   _Ожидаемый результат: Поле приняло значение. Появляется сообщение "Ошибка! Банк отказал в проведении операции."
   В БД таблице payment_entity не должна появиться информация о попытки транзакции_
2. Ввести в поле 4444 4444 4444 444.
   _Поле не приняло значение. Появляется сообщение под полем "Неверный формат!"

#### Ввод невалидных данных в поле "Номер карты" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными*

1. Ввести в поле 4444 4444 4444 4444.
   _Ожидаемый результат: Поле приняло значение. Появляется сообщение "Ошибка! Банк отказал в проведении операции."
   В БД таблице payment_entity не должна появиться информация о попытки транзакции_
2. Ввести в поле 4444 4444 4444 444.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Неверный формат!"_

#### Ввод невалидных данных в поле "Месяц" при оплате по дебетовой карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" предыдущий месяц.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Истёк срок действия карты"_
2. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" — 13.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Неверно указан срок действия
   карты""_
3. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" — 00.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Неверно указан срок действия
   карты"_
4. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" — 5.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Неверный формат"_

#### Ввод невалидных данных в поле "Месяц" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" предыдущий месяц.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Истёк срок действия карты"_
2. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" — 13.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Неверно указан срок действия
   карты""_
3. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" — 00.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Неверно указан срок действия
   карты"_
4. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Месяц" — 5.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Месяц" "Неверный формат"_

#### Ввод невалидных данных в поле "Год" при оплате по дебетовой карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Год" предыдущий год.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Год" "Истёк срок действия карты"_
2. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Год" — +6 от текущего года.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Год" "Неверно указан срок действия
   карты"_

#### Ввод невалидных данных в поле "Год" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Год" предыдущий год.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Год"
   "Истёк срок действия карты"_
2. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Год" — +10 от текущего года.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Год"
   "Неверно указан срок действия карты"_

#### Ввод невалидных данных в поле "Владелец" при оплате по дебетовой карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" имя, состоящее из одного слова на латинице.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_
2. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" имя, состоящее из двух слов на кириллице.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_
3. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" номер 1234567890.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_
4. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" спецсимволы !"№;%:?*().
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_

#### Ввод невалидных данных в поле "Владелец" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" имя, состоящее из одного слова на латинице.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_
2. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" имя, состоящее из двух слов на кириллице.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_
3. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" номер 1234567890.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_
4. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "Владелец" спецсимволы !"№;%:?*().
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "Владелец" "Неверный формат"_

#### Ввод невалидных данных в поле "CVC/CVV" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "CVC/CVV" число 00.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "CVC/CVV" "Неверный формат"_

#### Ввод невалидных данных в поле "Владелец" при оплате по кредитной карте

*Предусловие: остальные поля заполнены валидными данными, кроме поля "Номер карты"*

1. Ввести в поле "Номер карты" 4444 4444 4444 4441, в поле "CVC/CVV" число 00.
   _Ожидаемый результат: Поле не приняло значение. Появляется сообщение под полем "CVC/CVV" "Неверный формат"_

## 2. Перечень используемых инструментов:

- **IntelliJ IDEA** — интегрированная среда разработки программного обеспечения. Данная программа является одной из
  самых удобных и многофункциональной в данной сфере. Подходит для работы на Java, JavaScript, Python. Я выбрала ее
  поскольку она содержит Java Virtual Machine, а автотесты как раз написаны на Java. Поддерживает подходящую мне
  операционную систему macOC.
- **Java 13** — один из самых популярных языков кодирования,
  автотесты написаны на этом языке. Данный язык очень удобный так как поддерживает большое количество библиотек для
  написания автотестов.
- **Gradle** — система управления зависимостями.
- **JUnit** — платформа для написания автотестов и их запуска. Легко подключается в файле build.gradle в зависимостях.
- **Selenide** — это фреймворк для автоматизированного тестирования веб-приложений на основе Selenium WebDriver.
  Удобен тем, что легко добавляется в зависимости в файл build.gradle. Не требует отдельной загрузки браузера. Элементы
  страницы удобно находятся через CSS=селекторы.
- **Docker** - система контейнерезации. Позволит подключить базы данных MySQL и PostgresSQL.
- **Allure** - фреймворк, позволяет создать визуально понятный и наглядный отчёт выполнения автотестов.
- **Appveyor** - это размещённая распределённая служба непрерывной интеграции, используемая для создания и тестирования
  проектов,
  размещённых на GitHub. Обладает простой интеграцией, удобен в работе.
- **CI** - необходим для разработки программного обеспечения. Концепция позволяет запускать различные типы тестов на
  каждом этапе.
- **Git** - распределённая система управлениями версиями.
  Позволяет удобно создавать специализированные системы контроля версий на базе Git или пользовательские интерфейсы.
- **GitHub** — удобен для использования хранения кода, в том числе автотестов.

## 3. Перечень и описание возможных рисков при автоматизации

* Отсутсвие описания работы веб-сервиса. Нет точных указаний как должен сервис реагировать на те или иные данные.
* Не уникальные локаторы, затрудняют работу на странице веб-сервиса.
* Отсутствие требований к API базы данных
* Обновление веб-сервиса, из-за которых могут "съехать" значения селекторов

## 4. Перечень необходимых специалистов для автоматизации

- Автотестировщик 1шт.

## 5. Интервальная оценка с учётом рисков (в часах)

- Написание автотестов: 48
- Подготовка отчётов о проведении тестирования: 8
- формирование и анализ отчетов: 8
  Общее время на тестирование составит: 64, с учетом рисков — 83

## 6. План сдачи работ

- завершение автотестов: 21.06.2024
- Предоставление отчетности: 24.06.2024 
## План автоматизированного тестирования

### Перечень автоматизируемых сценариев:
#### 1. Проверка полей ввода(одинаковые сценарии для оплаты и кредита)
* Ввод данных с одобренной картой
* Ввод данных с отклоненной картой
* Отправка пустых полей
* Ввод валидных данных с незаполненным полем "Номер карты"
* Ввод валидных данных с незаполненным полем "Месяц"
* Ввод валидных данных с незаполненным полем "Год"
* Ввод валидных данных с незаполненным полем "Владелец"
* Ввод валидных данных с незаполненным полем "CVC/CVV"
* Проверка граничных значений в поле "Номер карты" (1 знак и 15 знаков)
* Ввод невалидных данных в поле "Номер карты"(неверный номер каты)
* Ввод невалидных данных в поле "Месяц"(истекший месяц)
* Ввод невалидных данных в поле "Год"(истекший год)
* Ввод невалидных данных в поле "Владелец"(цифры)
* Ввод невалидных данных в поле "CVC/CVV"
### 2. Проверка информации в базе данных
* Проверка статуса операции в базе данных

### Перечень используемых инструментов
* junit jupiter - базовый фреймворк для тестирования кода.
* selenide - фреймворк для UI тестирования веб страниц,
  более продвинутая версия selenium
* docker - позволяет развернуть контейнер базы данных в отсутствии сервера
* java faker - генерирует данные случайных пользователей,
  позволяет не записывать данные в код, каждый новый тест будет проходить с новыми данными.
* lombok - облегчает работу с типовыми конструкциями кода

### Риски
* Сложность запуска и работы с SUT по причине требования дополнительных инструментов (docker, gate_simulator)
* Т.к. в приложении отсутствуют test_id, возникает дополнительная сложность при написании тестов
* Работа с 2мя базами данных, усложняет структуру тестов

### Интервальная оценка с учётом рисков
* Подготовка плана работы - 8 часов
* установка и настройка SUT - 16 часов
* Написание авто тестов - 40 часов
* Подготовка отчетной документации 24 часа

### План сдачи работ
* Подготовка и настойка - 1 день
* Разработка - 10 дней
* Отчетная документация 3 дня
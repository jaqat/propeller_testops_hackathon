### Skip java-тестов, падающих по известной причине (заведённая issue).

### Зачем:
 1) **Скорость разбора падений** - акцентируемся только на новых / ранее неизвестных падениях
 2) **Зелёный пайплайн** - красота + более строгое следование процессам (мерж PR только при всех "зелёных проверках")

### Структура проекта:

* **skipper-core** - core-интрефейсы и сущности
* **skipper-junit5** - пример реализации скипа в виде JUnit5-extensions
  * HazelcastJiraSkipperExtension (Hazelcast + Jira)
  * AllureTestOpsSkipperExtension (Allure Test Ops)  
* **environment** - пример docker-окружений для демо

### Готовые JUnit5 Extensions

#### HazelcastJiraSkipperExtension
При подключении данного extension в Allure-отчёте в качестве аттачмента добавляется html-форма для линковки багов. Информация о линковке багов хранится в Hazelcast (распределенная база данных в памяти). В результате - при наличии такой линковки на открытый Issue в Jirа - упавший тест будет скипаться.

Для использования необходимо определить следующие env-переменные:
* HAZELCAST_URL
* JIRA_USER
* JIRA_PASSWORD
* JIRA_URL

_Пример развёртывания Hazelcast можно посомтреть в environment/hazelcast/docker-compose.yml_

#### AllureTestOpsSkipperExtension
Приподключении данного extension - упавший тест будет скипнут, если его сообщение об ошибке будет заматчено на один из открытых дефектов в AllureTestOps.

Для использования необходимо определить следующие env-переменные:
* ALLURE_URL
* ALLURE_API_TOKEN
* ALLURE_PROJECT_ID

# Test container

В прошлых домашних заданиях при тестировании мы использовали встроенную базу данных H2 в памяти. Это значительно упрощало тестирование приложения. Но в некоторых случаях мы можем захотеть проверить работу приложения с реальной базой данных, с той, которая будет использоваться при работе. В этом домашнем задании вам предстоит написать тесты для проверки работы приложения с базой данных PostgreSQL. Для этого мы будем использовать библиотеку Testcontainer. Она позволит нам в тестах запустить Docker контейнер с базой данных PostgreSQL. Для выполнения этого задания вам понадобится установленный Docker.

## Ссылки

* [Интеграция JUnit и Testcontainer](https://www.testcontainers.org/test_framework_integration/junit_5/)
* [Аннотация `@Testcontainers` – Активизирует автоматический старт и остановку контейнеров в отмеченном тестовом классе](https://javadoc.io/doc/org.testcontainers/junit-jupiter/latest/org/testcontainers/junit/jupiter/Testcontainers.html)
* [Аннотация `@Container` – Отмечает контейнер, который будет запущен в тестах](https://javadoc.io/doc/org.testcontainers/junit-jupiter/latest/org/testcontainers/junit/jupiter/Container.html)
* [Аннотация `@DynamicPropertySource` – позволяет динамически установить свойства приложения при интеграционных тестах](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/DynamicPropertySource.html)
* [Класс PostgreSQLContainer – служит для создания контейнера с базой данных PostgreSQL](https://javadoc.io/static/org.testcontainers/postgresql/1.9.1/org/testcontainers/containers/PostgreSQLContainer.html)

## build.gradle

## Задачи

* Изучите код в файле *build.gradle*. Посмотрите, какие зависимости необходимы для работы библиотеки Testcontainer

## src/main/java/exercise

В этой директории находится простое Spring Boot приложение, реализующее полный CRUD сущности.

## Задачи

* Изучите код приложения. Обратите внимание, что оно использует базу данных PostgreSQL.

## src/test/java/exercise/AppTest.java

Так как наше приложение использует базу данных PostgreSQL, будем тестировать его работу именно с этой базой. Один из тестов на создание пользователей уже написан в классе, чтобы вы могли на него ориентироваться.

## Задачи

* Отметьте тестовый класс необходимой аннотацией, чтобы обеспечить автоматический старт и остановку контейнеров.

* Сделайте так, чтобы каждый тест запускался в транзакции. Использовать транзакции при тестировании базы данных является хорошей практикой. Это делает тесты независимыми друг от друга.

* Создайте контейнер для базы данных PostgreSQL. Укажите имя базы данных, имя пользователя, пароль. Для наполнения базы данных тестовыми данными используйте скрипт *src/test/resources/init.sql*

* Дальше нам потребуется указать приложению, какой источник данных использовать. Так как база данных поднимается в контейнере, мы не можем заранее знать URL базы и использовать файл *properties.yml* для указания источника. В этом случае нам потребуется установить свойства приложения динамически. Создайте публичный статический метод, который будет устанавливать свойства приложения. Метод должен принимать на вход экземпляр класса `DynamicPropertyRegistry`. Отметьте его аннотацией `@DynamicPropertySource`. Метод должен устанавливать такие свойства, как url, имя пользователя и пароль для подключения к базе данных. Пример можно посмотреть в документации по этой аннотации.

  На этом создание контейнера окончено. При запуске тестов будет автоматически запущен контейнер, создастся база данных и наполнится тестовыми данными.

* Допишите тесты, которые проверяют вывод списка всех пользователей, просмотр конкретного пользователя, редактирование и удаление пользователя. Проверьте только позитивные случаи.

* Запустите тесты и убедитесь, что они отработали корректно

## Подсказки

* Изучите пример в директории *examples*

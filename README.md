1. Установить Docker
2. Запустить Docker
3. В терминале перейти в директорию проекта. Выполнить: docker compose up -d     
4. Дождаться скачивания всех зависимотей
5. В браузере перейти по адресу: http://localhost:5050/browser/
6. "Please set a master password for pgAdmin" - установить пароль "workshop12345".
7. Нажать на "Add New Server"
- В General установить для поля "Name" значение "workshop" 
- В Connection установить для полей: "Host name/address" -> "postgres"; "Port" -> "5432"; "Maintenance database" -> "postgres"; "Username" -> "workshop"; "Password" -> "workshop12345";
- Нажать на "Save password"
8. Создать Database "customer". Правой кнопкой на "Databases" -> Create -> Database. В General установить для поля "Database" значение "customer". Нажать на "Save".
9. Создать Database "fraud". Правой кнопкой на "Databases" -> Create -> Database. В General установить для поля "Database" значение "fraud". Нажать на "Save".
10. Перейти в проект микросервисов (IntelliJ Idea). Модуль "customer" -> CustomerApplication и запустить приложение
11. Перейти в модуль "fraud" -> FraudApplication и запустить приложение
12. В браузере перейти по адресу: http://localhost:8080/swagger-ui.html
13. В контроллере "customer-controller" открыть POST /customers. Выполнить запрос: { "firstName": "Test", "lastName": "Test", "email": "test@gmail.com", "serNo": 1234567 }
14. В браузере перейти по адресу: http://localhost:5050/browser/ 
15. Правой кнопкой по таблице "customer" -> Query Tool. Выполнить запрос "select * from customer;"
16. Правой кнопкой по таблице "fraud" -> Query Tool. Выполнить запрос "select * from fraud;"



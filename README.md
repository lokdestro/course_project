# course_project
# 🗃️ База данных кредитной системы

## 📌 Структура базы данных

### 👤 Таблица `users`
Хранит основную информацию о пользователях системы.

| Поле | Тип | Описание |
|------|-----|----------|
| `id` | SERIAL | Первичный ключ |
| `email` | VARCHAR(100) | Уникальный email пользователя |
| `password` | VARCHAR(100) | Хэш пароля пользователя |
| `registration_status` | VARCHAR(5) | Статус регистрации |

### 📝 Таблица `personal`
Содержит персональные данные пользователей.

| Поле | Тип | Описание |
|------|-----|----------|
| `id` | SERIAL | Первичный ключ |
| `user_id` | INT | ID пользователя (внешний ключ) |
| `name` | VARCHAR(20) | Название поля (например, "имя", "фамилия") |
| `value` | VARCHAR(100) | Значение поля |
| `create_at` | TIMESTAMP | Дата создания записи (по умолчанию CURRENT_TIMESTAMP) |

### 📸 Таблица `photoes`
Хранит фотографии пользователей.

| Поле | Тип | Описание |
|------|-----|----------|
| `user_id` | INT | ID пользователя |
| `name` | VARCHAR(10) | Название фотографии |
| `photo` | VARCHAR(1024) | Уникальный путь/ссылка на фото |

### 💳 Таблица `cards`
Содержит информацию о банковских картах пользователей.

| Поле | Тип | Описание |
|------|-----|----------|
| `user_id` | INT | ID пользователя |
| `name` | VARCHAR(20) | Название карты |
| `number` | VARCHAR(20) | Номер карты |
| `card_date` | TIMESTAMP | Срок действия карты |

### 📄 Таблица `loan_applications`
Хранит заявки на кредиты.

| Поле | Тип | Описание |
|------|-----|----------|
| `id` | SERIAL | Первичный ключ |
| `user_id` | INT | ID пользователя |
| `amount` | INT | Сумма кредита |
| `term_months` | INT | Срок кредита в месяцах |
| `interest_rate` | INT | Процентная ставка |
| `status` | VARCHAR(20) | Статус заявки ('approved', 'rejected', 'pending') |
| `create_at` | TIMESTAMP | Дата создания заявки |
| `decision_date` | TIMESTAMP | Дата принятия решения |
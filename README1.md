# AsyncArrayProcessing

Програма на Java демонструє використання асинхронних обчислень для генерації масиву випадкових символів, їхнього відображення та фільтрації за категоріями. За допомогою `CompletableFuture` програма генерує масив символів, асинхронно виводить його на екран, а також фільтрує символи на чотири категорії:

- Алфавітні символи (малі і великі літери)
- Пробіли
- Табуляції
- Інші символи (цифри та інші символи)

Використання `CompletableFuture` дозволяє виконувати ці операції паралельно, не блокуючи основний потік.

## Основна ідея

Програма складається з трьох основних етапів:

1. **Генерація масиву**: Створюється масив випадкових символів, які можуть бути пробілами, табуляціями, літерами або цифрами. Генерація виконується асинхронно, щоб не блокувати основний потік виконання програми.

2. **Виведення масиву на екран**: Після генерації масиву, цей масив асинхронно виводиться на екран.

3. **Фільтрація масиву**: Масив фільтрується на чотири категорії: алфавітні символи, пробіли, табуляції та інші символи. Виведення кожної категорії виконується асинхронно.

Програма також вимірює час, витрачений на кожен етап (генерація масиву, виведення та фільтрація), щоб продемонструвати ефективність асинхронних обчислень.

## Технічні деталі

- Програма використовує `CompletableFuture` для асинхронного виконання трьох основних етапів.
- Для генерації випадкових символів використовується метод `generateRandomChar()`, який випадковим чином обирає символи між пробілами, табуляціями, цифрами та буквами.
- Після створення масиву виконується фільтрація, де кожен символ потрапляє в одну з чотирьох категорій.
- Програма також вимірює час виконання кожного етапу, щоб продемонструвати ефективність асинхронного виконання.


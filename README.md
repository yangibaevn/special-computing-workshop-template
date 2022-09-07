# Специальные вычислительный практикум (шаблон проекта)

## Автоформатирование
1. Переходим в ```Intelij IDEA => Preferences => Code style```
2. Импортируем из корня проекта ```google_codestyle.xml```
![img_1.png](images/img_1.png)
3. Также важно не забывать запускать автоформатирование. Его можно настроить на определенные клавиши (например, option + command + L):
![img_2.png](images/img_2.png)

## Настройка Checkstyle
Установите ```Preferences => Plugins => "CheckStyle-IDEA"```

Выставите настройки как на скрине:
![img_4.png](images/img_3.png)

## Настройка SonarLint
Установите ```Preferences => Plugins => "SonarLint"```

## Запуск JoCoCo
При запуске проверьте, что в конфигурации запуска указаны следующие настройки в ```code coverage```:
![img.png](images/img_4.png)

Ожидаемый результат:
![img.png](images/img_5.png)
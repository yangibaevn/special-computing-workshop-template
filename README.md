[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bas-kirill_special-computing-workshop-template&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=bas-kirill_special-computing-workshop-template)

# Специальный вычислительный практикум (шаблон проекта)

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

## Настройка SonarQube
Переходим в ```Preferences => Tools => SonarLint => Project Settings```
<img width="1012" alt="image" src="https://user-images.githubusercontent.com/39113667/190925153-de58462b-1adb-4da5-9c21-577366d2c234.png">

Переходим в ```Configure the connection...``` и добавляем ```SonarQube / SonarCloud connections```:

<img width="1071" alt="image" src="https://user-images.githubusercontent.com/39113667/190925394-a5fba60c-c8bf-4ce8-b61e-b053e415a325.png">

Нажимаем на ```Connect to the online service``` и регистрируемся под GitHub профилем.

В результате должна открыться страница с проверкой кода:

<img width="1445" alt="image" src="https://user-images.githubusercontent.com/39113667/190925502-2863a888-cd47-4de5-9ce0-ef1b1be09f43.png">


## Запуск JoCoCo
При запуске проверьте, что в конфигурации запуска указаны следующие настройки в ```code coverage```:
![img.png](images/img_4.png)

Ожидаемый результат:
![img.png](images/img_5.png)

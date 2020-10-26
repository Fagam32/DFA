# DFA
DZ

Строка символов `a`,`~`,`b`, начинающаяся с префикса `~` и заканчивающаяся суффиксом `~`,
между которыми располагается последовательность символов `a`,`b`, состоящая из повторяющихся
групп символов: четное число (нуль не считаем четным) подряд идущих символов `a`, за которыми следует один символ `b`, или нечетное число подряд
идущих символов `a`, за которыми следует два символа `b` (пример: `~~`
или `~abbaaaabaaabb~`).

# Таблица переходов

| state\symbol   |  a | b  | ~  |
|----|----|----|----|
| q0 | q0 | q0 | q1 |
| q1 | q2 | q7 | q6 |
| q2 | q3 | q4 | q7 |
| q3 | q2 | q5 | q7 |
| q4 | q7 | q1 | q7 |
| q5 | q2 | q7 | q6 |
| q6 | q2 | q7 | q6 |
| q7 | q7 | q7 | q1 |
| q8 |    |    |    |

# How it works

Чтобы запустить код нужно устанвить `Maven`, склонировать репозиторий, зайти в созданую папку и прописать команды:

`mvn clean install`
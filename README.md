1) export JAVA_HOME=`/usr/libexec/java_home -v 21`
2) mvn clean compile jib:dockerBuild
3) docker-compose up


```bash
export JAVA_HOME=`/usr/libexec/java_home -v 21`
```
```bash
mvn clean compile jib:dockerBuild
```
```bash
docker-compose up
```


Поля ответа метода /suggestions/api/suggest/party

| Описание                                  | Наименование поля ответа метода suggestions                                       | Наименование в поле данных xml  |
|-------------------------------------------|-----------------------------------------------------------------------------------|---------------------------------|
| Наименование компании                     | value                                                                             | //СвНаимЮЛСокр/@НаимСокр        | 
| =  value                                  | unrestricted_value                                                                | //СвНаимЮЛСокр/@НаимСокр        |
| ИНН                                       | data.inn                                                                          | //СвЮЛ/@ИНН                     |   
| КПП                                       | data.kpp                                                                          | //СвЮЛ/@КПП                     | 
| ОГРН                                      | data.ogrn                                                                         | //СвЮЛ/@ОГРН                    |
| Дата выдачи ОГРН                          | data.ogrn_date                                                                    | //СвЮЛ/@ДатаОГРН                |
| Тип организации                           | data.type (LEGAL — юридическое лицо; INDIVIDUAL — индивидуальный предприниматель) | СвЮЛ для ЕГРЮЛ (СвИП для ЕГРИП) |
| Полное наименование                       | data.name.full_with_opf                                                           | /СвНаимЮЛ/@НаимЮЛПолн           |
| Краткое наименование                      | data.name.short_with_opf                                                          | //СвНаимЮЛСокр/@НаимСокр        |
| Код ОКВЭД                                 |                                                                                   |                                 |
| Версия справочника ОКВЭД (2001 или 2014)  |                                                                                   |                                 |
|                                           |                                                                                   |                                 |
|                                           |                                                                                   |                                 |


Поля ответа метода /suggestions/api/findById/party
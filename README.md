
# Эндпоинты

**Получение списка всех товаров**

`GET` /api/products

Пример запроса:
```
/api/products
```
___

**Получение товара по названию**

`GET` /api/products/{title}

Пример запроса:
```
/api/products/Клавиатура
```
___

**Добавление нового товара**

`POST` /api/products

Пример запроса:
```json
{
        "title": "Клавиатура",
        "description": "Описание клавиатуры",
        "price": 1000,
        "inStock": true
}
```
___

**Изменение товара**

`PUT` "/api/products"

Пример запроса:

```json
{
        "id": 2,
        "title": "Клавиатура",
        "description": "Описание клавиатуры",
        "price": 1000,
        "inStock": true
    }
```
___


**Удаление товара**

`DELETE` "/api/products/{title}"

Пример запроса:
```
/api/products/Клавиатура
```
___

**Сортировка и фильтрация товара**

`GET` /api/products/search

Параметры запроса:

1) Сортировка

`?sortBy=`
- `title`- по названию;
- `price`- по цене.

2) Фильтрация:

- `?minPrice=` - минимальная цена;
- `?maxPrice=`- максимальная цена;
- `?title` - по названию или части названия товара.

Пример запроса:

```
/api/products/search?title=клавиатура&maxPrice=899&sortBy=title
```

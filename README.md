# Jugador y Equipo

## GET - Listados/Detalles

- /equipos
- /equipos/{id}
- /jugadores
- /jugador/{id}

## DELETE - Borrado

- /equipos/{id}
- /jugador/{id}

## PUT - Edición

- /equipo/{id}

  - ```JSON
      {
        "nombre_equipo": "Los maquinas",
        "puntos": 0,
        "logo": ""
      }
    ```

- /jugador/{id}

  - ```JSON
        {
        "nombre": "Nombre",
        "avatar": "",
        "id_equipo": 1,
        "puntos": 0
        }
    ```

## POST - Creación

- /equipos

  - ```JSON
      {
        "nombre_equipo": "Los maquinas",
        "puntos": 0,
        "logo": ""
      }
    ```

- /jugadores

  - ```JSON
        {
        "nombre": "Nombre",
        "avatar": "",
        "id_equipo": 1,
        "puntos": 0
        }
    ```

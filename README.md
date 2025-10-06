# Calculador Inmobiliario (Java)

Aplicación de consola para calcular comisiones e impuestos en operaciones inmobiliarias. Es una herramienta ligera para usar desde la terminal.

## Rápido — cómo ejecutar (opción recomendada)

1. Abre una terminal y navega a la carpeta del proyecto (donde está `Principal.java`).
2. Haz ejecutable el script una sola vez (macOS / Linux):

```bash
chmod +x run.sh
```

3. Ejecuta el script:

```bash
./run.sh
```

El script compila y luego ejecuta el programa.

## Alternativa — compilar y ejecutar manualmente

Si preferís compilar y ejecutar sin el script:

```bash
javac Principal.java
java Principal
```

Para probar rápidamente y que el programa salga inmediatamente (selecciona la opción 7):

```bash
javac Principal.java
printf "7\n" | java Principal
```

## Requisitos

- Java JDK 8 o superior instalado.
- Terminal (zsh, bash, etc.).

## Nota

El programa es interactivo: muestra un menú con opciones para calcular comisiones, IRPF, ITP, etc. Si querés que agregue un modo no interactivo (con argumentos) o que exporte resultados a un archivo, lo programo.

---

Si querés, también agrego un `Makefile` o un `run.bat` para Windows.

## Runbot (ejecución local y bot de Telegram opcional)

He incluido `runbot.py`, un pequeño script en Python que facilita compilar y ejecutar el proyecto desde tu PC y que además contiene un scaffold para ejecutar comandos vía Telegram si querés probar un bot local.

Archivos añadidos:
- `runbot.py` — script Python para compilar, ejecutar (interactivo) y realizar un "quick run" que envía la opción 7 para salir automáticamente.
- `requirements.txt` — dependencia opcional para correr el bot de Telegram (`python-telegram-bot==13.15`).

Uso básico (CLI):

```bash
# compilar
python3 runbot.py compile

# ejecutar interactivamente (hereda la terminal)
python3 runbot.py run

# quick run (compila y ejecuta enviando 7 para salir automáticamente)
python3 runbot.py quick
```

Usar el bot de Telegram (opcional):

1. Instalá la dependencia (si querés usar el bot):

```bash
pip install -r requirements.txt
```

2. Definí la variable de entorno `TELEGRAM_TOKEN` con el token de tu bot de Telegram:

```bash
export TELEGRAM_TOKEN="<tu-token-aqui>"
```

3. Iniciá el bot:

```bash
python3 runbot.py telegram
```

Comandos disponibles en el bot:
- `/compile` — compila el proyecto
- `/quick` — compila y hace un quick run (envía la salida del programa)
- `/run` — intenta ejecutar (no interactivo, útil para ver si arranca)

El bot es solo un scaffold para empezar: por seguridad y estabilidad conviene restringir qué chats pueden usarlo o desplegarlo en un servidor si planeás que esté siempre activo.

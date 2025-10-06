#!/usr/bin/env python3
"""
Runbot para el proyecto "Calculador Inmobiliario".

Funcionalidades:
- compile: compila Principal.java
- run: compila (si hace falta) y ejecuta el programa interactivamente (hereda la terminal)
- quick: compila y ejecuta pasando "7\n" para que el programa salga inmediatamente (útil para CI o tests)
- telegram: (opcional) inicia un bot de Telegram si la variable de entorno TELEGRAM_TOKEN está definida y la librería está instalada.

Uso:
  python3 runbot.py compile
  python3 runbot.py run
  python3 runbot.py quick
  python3 runbot.py telegram

Notas:
- El bot de Telegram es opcional: solo se iniciará si definís TELEGRAM_TOKEN y tenés instalada la dependencia (ver requirements.txt).
- El script intenta dar mensajes claros si falta javac o java.
"""

import os
import sys
import subprocess
import shutil
import argparse
import tempfile
import time

PROJECT_DIR = os.path.dirname(os.path.abspath(__file__))
PRINCIPAL = os.path.join(PROJECT_DIR, 'Principal.java')


def check_javac():
    if shutil.which('javac') is None:
        return False
    return True


def compile_java():
    if not os.path.exists(PRINCIPAL):
        return (False, "No se encontró Principal.java en el directorio del proyecto")
    if not check_javac():
        return (False, "'javac' no está disponible. Instalá el JDK (ver README).")
    proc = subprocess.run(['javac', PRINCIPAL], cwd=PROJECT_DIR, capture_output=True, text=True)
    if proc.returncode != 0:
        return (False, proc.stderr or proc.stdout)
    return (True, 'Compilación exitosa')


def run_interactive():
    # Ejecuta java Principal heredando stdio para que el usuario pueda interactuar
    if shutil.which('java') is None:
        print("'java' no está disponible. Instalá el JRE/JDK.")
        return 2
    # Ejecutar en el mismo directorio
    return subprocess.call(['java', 'Principal'], cwd=PROJECT_DIR)


def run_with_input(input_text, timeout=15):
    # Ejecuta y captura salida; envía input_text al stdin
    if shutil.which('java') is None:
        return (2, "'java' no está disponible. Instalá el JRE/JDK.")
    try:
        proc = subprocess.run(['java', 'Principal'], cwd=PROJECT_DIR, input=input_text, capture_output=True, text=True, timeout=timeout)
        out = proc.stdout + ("\n[STDERR]\n" + proc.stderr if proc.stderr else "")
        return (proc.returncode, out)
    except subprocess.TimeoutExpired:
        return (3, f'Ejecucion excedió timeout de {timeout} segundos')


# --- Telegram bot scaffold (opcional) ---

def start_telegram_bot(token):
    try:
        from telegram.ext import Updater, CommandHandler
    except Exception as e:
        print("No está instalada la librería 'python-telegram-bot'. Instala desde requirements.txt si querés usar el bot.")
        return

    updater = Updater(token=token, use_context=True)
    dp = updater.dispatcher

    def help_cmd(update, context):
        update.message.reply_text("Comandos:\n/compile - compila Principal.java\n/quick - compila y ejecuta con salida automática (envía la salida)\n/run - intenta ejecutar (no interactivo)\n/help - muestra este mensaje")

    def compile_cmd(update, context):
        update.message.reply_text("Compilando...")
        ok, msg = compile_java()
        update.message.reply_text(msg if ok else f'Error:\n{msg}')

    def quick_cmd(update, context):
        update.message.reply_text("Compilando y ejecutando (quick)...")
        ok, msg = compile_java()
        if not ok:
            update.message.reply_text(f'Error de compilación:\n{msg}')
            return
        code, out = run_with_input("7\n")
        update.message.reply_text(f'Return code: {code}\nSalida:\n{out}')

    def run_cmd(update, context):
        update.message.reply_text('Intentando ejecutar (no interactivo, timeout 10s)...')
        ok, msg = compile_java()
        if not ok:
            update.message.reply_text(f'Error de compilación:\n{msg}')
            return
        code, out = run_with_input("\n", timeout=10)
        update.message.reply_text(f'Return code: {code}\nSalida:\n{out}')

    dp.add_handler(CommandHandler('help', help_cmd))
    dp.add_handler(CommandHandler('compile', compile_cmd))
    dp.add_handler(CommandHandler('quick', quick_cmd))
    dp.add_handler(CommandHandler('run', run_cmd))

    print('Iniciando bot de Telegram... presioná Ctrl+C para detener')
    updater.start_polling()
    updater.idle()


# --- CLI ---

def main():
    parser = argparse.ArgumentParser(description='Runbot para Calculador Inmobiliario')
    parser.add_argument('action', nargs='?', default='help', choices=['compile', 'run', 'quick', 'telegram', 'help'], help='acción a ejecutar')
    args = parser.parse_args()

    if args.action == 'help':
        print(__doc__)
        return 0

    if args.action == 'compile':
        ok, msg = compile_java()
        if ok:
            print(msg)
            return 0
        else:
            print('Error en compilación:')
            print(msg)
            return 1

    if args.action == 'run':
        ok, msg = compile_java()
        if not ok:
            print('Error en compilación:')
            print(msg)
            return 1
        return run_interactive()

    if args.action == 'quick':
        ok, msg = compile_java()
        if not ok:
            print('Error en compilación:')
            print(msg)
            return 1
        code, out = run_with_input('7\n')
        print('Return code:', code)
        print('Salida:\n', out)
        return code

    if args.action == 'telegram':
        token = os.environ.get('TELEGRAM_TOKEN')
        if not token:
            print('Para iniciar el bot por favor exportá la variable de entorno TELEGRAM_TOKEN con tu token de bot.')
            print('Ejemplo (zsh): export TELEGRAM_TOKEN=123456:ABC-DEF...')
            return 2
        start_telegram_bot(token)
        return 0


if __name__ == '__main__':
    sys.exit(main())

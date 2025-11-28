#!/usr/bin/env bash
# Script para compilar y ejecutar Principal.java
# Uso:
#   chmod +x run.sh
#   ./run.sh

set -euo pipefail

# Verificar que javac está disponible
if ! command -v javac >/dev/null 2>&1; then
	cat <<'MSG'
Error: no se encontró 'javac' (Java JDK).
Para ejecutar esta aplicación necesitas instalar el Java Development Kit (JDK).

En macOS la forma más sencilla es usar Homebrew:

	brew update
	brew install openjdk

Luego sigue las instrucciones que muestre 'brew info openjdk' para agregarlo a tu PATH (por ejemplo añadir a ~/.zshrc).

Alternativas:
- Descargar e instalar desde https://adoptium.net o https://www.oracle.com/java/technologies/javase-downloads.html

Después de instalar, verifica con:

	java -version
	javac -version

MSG
	exit 1
fi

echo "Compilando Principal.java..."
javac Principal.java

echo "Ejecutando Principal (presiona 7 para salir si querés terminar rápido)..."
java Principal

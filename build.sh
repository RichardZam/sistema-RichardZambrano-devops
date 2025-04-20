#!/bin/bash

echo "🛠️  Compilando todos los microservicios..."

for dir in config-server usuarios-servicio asignaturas-servicio matriculas-servicio monitor-admin; do
  echo "📦 Compilando $dir"
  cd $dir
  ./mvnw clean package -DskipTests || { echo "❌ Falló la compilación en $dir"; exit 1; }
  cd ..
done

echo "🚀 Levantando contenedores con Docker Compose..."
docker-compose up -d --build

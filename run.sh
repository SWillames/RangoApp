#!/bin/bash

echo "Iniciando RangoApp com Docker Compose..."
echo ""

# Função de spinner
spinner() {
  local pid=$1
  local spin='-\|/'
  local i=0

  while kill -0 "$pid" 2>/dev/null; do
    i=$(( (i + 1) % 4 ))
    printf "\r[%c] Carregando..." "${spin:$i:1}"
    sleep 0.1
  done
}

# Verifica se Docker está rodando
if ! docker info > /dev/null 2>&1; then
  echo "❌ Docker não está em execução. Inicie o Docker e tente novamente."
  exit 1
fi

# Sobe os containers em background
docker-compose up -d --build > /dev/null 2>&1 &
PID=$!

# Mostra o spinner enquanto o docker-compose roda
spinner $PID

# Limpa a linha
printf "\r"

echo "[✔] Aplicação iniciada com sucesso!"
echo "Swagger disponível em: http://localhost:8080/swagger-ui.html"
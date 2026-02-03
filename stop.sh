#!/bin/bash

echo "Parando RangoApp..."
echo ""

# Função de spinner (mesma do start.sh)
spinner() {
  local pid=$1
  local spin='-\|/'
  local i=0

  while kill -0 "$pid" 2>/dev/null; do
    i=$(( (i + 1) % 4 ))
    printf "\r[%c] Finalizando..." "${spin:$i:1}"
    sleep 0.1
  done
}

# Executa docker-compose down em background
docker compose down > /dev/null 2>&1 &
PID=$!

# Mostra o spinner enquanto o comando executa
spinner $PID

# Limpa a linha
printf "\r"

echo "[✔] Aplicação parada com sucesso."
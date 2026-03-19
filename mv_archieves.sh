#!/bin/bash

# Configurações
DIRETORIO_ALVO="/home/matheus/Documentos/tictactoe"
PASTA_DESTINO="/home/matheus/Documentos/tictactoe/execs"

inotifywait -m -e close_write --exclude "execs/" --format '%f' "$DIRETORIO_ALVO" | while read NOVO_ARQUIVO
do
   if [[ "$NOVO_ARQUIVO" == *.class ]] || [[ "$NOVO_ARQUIVO" == *.jar ]]; then
        mv "$DIRETORIO_ALVO/$NOVO_ARQUIVO" "$PASTA_DESTINO/" 2>/dev/null
    fi
done

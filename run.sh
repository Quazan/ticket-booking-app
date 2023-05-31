#!/bin/sh

docker compose up -d --wait

curl -s -X 'GET' \
  'http://localhost:8080/screenings?from=2023-05-31T20%3A04%3A28.025Z&to=2023-06-30T20%3A04%3A28.025Z' \
  -H 'accept: application/json' | json_pp

curl -s -X 'GET' \
  'http://localhost:8080/screenings/1' \
  -H 'accept: application/json' | json_pp

docker compose down
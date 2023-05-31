#!/bin/sh

echo "Provide start of time interval when You want to watch movie (Format: 2023-05-31T16:00:00.00Z)"
read -r dateFrom

echo "Provide end of time interval when You want to watch movie (Format: 2023-05-31T20:00:00.00Z)"
read -r dateTo

curl -s -X 'GET' \
  "http://localhost:8080/screenings?from=$dateFrom&to=$dateTo"\
  -H 'accept: application/json' | json_pp

echo "Provide id of screenings You want to watch"
read -r screeningId

curl -s -X 'GET' \
  "http://localhost:8080/screenings/$screeningId" \
  -H 'accept: application/json' | json_pp

echo "Provide name"
read -r customerName

echo "Provide surname"
read -r customerSurname

echo "Provide seats Ids you want to reserve (Comma separated list of ids)"
read -r reservedSeatIds

echo "How many Adult tickets you want to reserve"
read -r adultTickets

echo "How many Student tickets you want to reserve"
read -r studentTickets

echo "How many Child tickets you want to reserve"
read -r childTickets

curl -s -X 'POST' \
  'http://localhost:8080/reservations' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d "{
  \"screeningId\":$screeningId,
  \"customerName\":\"$customerName\",
  \"customerSurname\":\"$customerSurname\",
  \"reservedSeatIds\": [
    $reservedSeatIds
  ],
  \"tickets\": [
    {
      \"count\": $adultTickets,
      \"ticketTypeId\": 1
    },
    {
      \"count\": $studentTickets,
      \"ticketTypeId\": 51
    },
    {
      \"count\": $childTickets,
      \"ticketTypeId\": 101
    }
  ]
}" | json_pp
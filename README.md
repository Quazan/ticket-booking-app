# Ticket booking app
## Seat reservation system for a multiplex

### How to build

Project uses docker compose to build containers with backend and database.

Simply run following command to build and run project

```bash
docker compose up --build
```

To remove all containers use
```bash
docker compose down
```

### Test suite

To run provided test suits use docker compose (make sure you are in root directory of the project)

```bash
docker compose -f docker-compose.tests.yaml run --rm tests
```

### Demo
Project on startup populate some data into the database.
All screenings are between 09.06.2023 and 10.06.2023. 
Current time in project is fixed to 15:00 06.06.2023.

Before running demo make sure project containers are running
(use `run.sh` script or `docker compose`)

To run demo use case use provided script.

```bash
./use_case.sh
```

Follow instructions in the demo.

After running demo remember to clean up containers (use `cleanup.sh` or `docker compose`)

Project provides swagger-ui under the link `http://localhost:8080/swagger-ui/index.html`

#### Notes
You may need to make scripts executable by using `chmod +x <script>`
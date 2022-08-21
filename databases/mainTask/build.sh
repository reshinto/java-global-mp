docker stop postgresql
docker rm postgresql
docker run --name postgresql -e POSTGRES_USER=root -e POSTGRES_PASSWORD=password -v $(pwd):/app -w='/app' -p 5432:5432 -d postgres
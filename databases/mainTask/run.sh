docker exec -i postgresql psql -d postgres -a -f init.sql
docker exec -i postgresql psql -d postgres -a -f queries.sql
# spring-reactive-elastic-search-client
How to start the elastic search server:

**docker-compose  -f elastic-search.yml up -d**

curl --location 'http://localhost:8338/reactive' \
--header 'Content-Type: application/json' \
--data '{
    "id":"123-njhbjjhb-1jhbhjb",
    "firstName":"testUser",
    "mobileNumber":"9876543210"
}'

# spring-reactive-elastic-search-client
## How to run

The first thing to do is to start Elasticsearch. For that, you can use the `docker-compose` file in this project  and run it like this:

```bash
$ docker-compose -f docker-compose up -d
``` 

It brings Elasticsearch up on a single node cluster with the cluster name `elasticsearch`.

Then you can run the application like below:

```bash
$ ./mvnw spring-boot:run
```

Then you can execute the curl command to create data in the elastic search
```bash
curl --location 'http://localhost:8338/reactive' \
--header 'Content-Type: application/json' \
--data '{
    "id":"123-njhbjjhb-1jhbhjb",
    "name":"Raja Gandharaw",
    "phoneNumber":"9876543210"
}'
```

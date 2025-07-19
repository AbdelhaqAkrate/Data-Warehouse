# ClusteredData Warehouse



## API Docs

#### Create a single deal

```http
  POST /api/deals/create
```

| Payload |  Response                       |
| :-------- |  :-------------------------------- |
| ` {id: String ,fromCurrency: String ,"toCurrency": String,amount: Double},`   |  `{message": "Deal inserted successfully","data":{"id": "DEAL254848","fromCurrency": "MAD","toCurrency":"MAD","amount": 123.4},"errors": null}` |

#### Insert deals

```http
  POST /api/deals/insert
```

| Payload |  Response                       |
| :-------- |  :-------------------------------- |
| ` {id: String ,fromCurrency: String ,"toCurrency": String,amount: Double},`   |  `{message": "Deal inserted successfully","data":[{"id": "DEAL254848","fromCurrency": "MAD","toCurrency":"MAD","amount": 123.4},"errors": null}]` |


## Using Swagger
You can access swagger api documentation by visiting /swagger-ui/index.html

You will be able to see a list of endPoints  `Deals` you can test any api by clicking `Try it out` then `Execute`


## Deploymenet

Just run `make run` and the project will start

If you are not using `docker` configure your local setup in `application.properties`

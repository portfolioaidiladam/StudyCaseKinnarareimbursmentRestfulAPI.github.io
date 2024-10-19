## Reimbursement Api Spec

## Submit Reimbursement

Endpoint : POST /api/reimbursements

Request Body :

```json
{
  "amount": "number",
  "description": "string"
}
```

Response Body (Success) :

```json
{
  "data" : "201 CREATED"
} 
```

## Approve  Reimbursement

Endpoint : PUT /api/reimbursements/{id}/approve

Request Body :

```json
{
  "data" : {
    "id": "number",
    "userId": "number",
    "username": "string",
    "amount": "number",
    "description": "string",
    "submissionDate": "string (ISO 8601)",
    "approvalDate": "string (ISO 8601)",
    "status": "string"
  }
}
```

Response Body (Success) :

```json
{
  "data" : " 200 OK"
} 
```



## Get Reimbursement

Endpoint : GET /api/reimbursements/{id}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id": "number",
    "userId": "number",
    "username": "string",
    "amount": "number",
    "description": "string",
    "submissionDate": "string (ISO 8601)",
    "approvalDate": "string (ISO 8601)",
    "status": "string"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Code: 400 BAD REQUEST",
  "errors" : "Code: 401 UNAUTHORIZED",
  "errors" : "Code: 403 FORBIDDEN",
  "errors" : "Code: 404 NOT FOUND",
  "errors" : "Code: 500 INTERNAL SERVER ERROR",
}
```

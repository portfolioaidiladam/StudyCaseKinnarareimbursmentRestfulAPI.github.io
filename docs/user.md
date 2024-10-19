# User API Spec

## Login

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "admin",
  "password" : "admin123",
}
```

Response Body (Success) :

```json
{
  "data" : "200 OK"
} 
```

Response Body (Failed) :

```json
{
  "errors" : "Authentication failed"
}
```



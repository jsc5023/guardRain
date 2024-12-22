# API 명세서

## 인증 API

### 회원가입
```
POST /api/auth/signup
Content-Type: application/json

Request:
{
    "username": "string",
    "password": "string",
    "email": "string",
    "name": "string"
}

Response: (200 OK)
{
    "id": "number",
    "username": "string",
    "email": "string",
    "name": "string",
    "createdAt": "datetime"
}
```

### 로그인
```
POST /api/auth/login
Content-Type: application/json

Request:
{
    "username": "string",
    "password": "string"
}

Response: (200 OK)
{
    "accessToken": "string",
    "refreshToken": "string",
    "tokenType": "Bearer",
    "expiresIn": "number"
}
```

### 토큰 갱신
```
POST /api/auth/refresh
Content-Type: application/json

Request:
{
    "refreshToken": "string"
}

Response: (200 OK)
{
    "accessToken": "string",
    "refreshToken": "string",
    "tokenType": "Bearer",
    "expiresIn": "number"
}
```

## 사용자 API

### 내 정보 조회
```
GET /api/users/me
Authorization: Bearer {token}

Response: (200 OK)
{
    "id": "number",
    "username": "string",
    "email": "string",
    "name": "string",
    "roles": ["string"],
    "lastLogin": "datetime"
}
```

### 내 정보 수정
```
PUT /api/users/me
Authorization: Bearer {token}
Content-Type: application/json

Request:
{
    "email": "string",
    "name": "string"
}

Response: (200 OK)
{
    "id": "number",
    "email": "string",
    "name": "string",
    "updatedAt": "datetime"
}
```

## 에러 응답 형식
```json
{
    "timestamp": "datetime",
    "status": "number",
    "error": "string",
    "message": "string",
    "path": "string"
}
```
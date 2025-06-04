# Car Control System - Backend

## 🔑 Endpoints

• Acessar ```client```, ex:
```
• Endpoint: http://localhost:8080/client

• Metodos: [GET, POST, PUT, DELETE]

• POST/PUT exemplo de corpo de requisição:
{
	"firstname": "your_firstname",
	"surname": "your_surname",
	"email": "your_email_address",
    	"address": "your_residence_address",
	"phone": ["phone_number_1", "phone_number_2", ...]
}
```

• Acessar ```vehicle```, ex:
```
• Endpoint: http://localhost:8080/vehicle

• Metodos: [GET, POST, PUT, DELETE]

• POST/PUT exemplo de corpo de requisição:
{
    "description": "vehicle_description",
    "plate": "vehicle_plate",
    "brand": "vehicle_brand",
    "model": "vehicle_model",
    "color": "vehicle_color",
    "client": "client_id"
}
```

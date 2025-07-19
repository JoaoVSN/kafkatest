# Kafka Test API - Pedidos

Esta API simula o envio e recuperação de pedidos utilizando uma estrutura baseada em Kafka. Abaixo estão exemplos de uso com `curl`, incluindo as requisições e as respostas esperadas.

---

## 📦 Criar um Pedido

**Endpoint:** `POST /kafka-test/pedidos`  
**Descrição:** Envia um novo pedido para processamento.

### Requisição

```bash
curl --location 'http://localhost:8080/kafka-test/pedidos' \
--header 'Content-Type: application/json' \
--data '{
    "clientId": "1",
    "items": [
        {
            "itemId": "1",
            "description": "banana"
        }
    ]
}'
```

### Resposta

```json
{
    "orderId": "202b6ba7-0a4d-40ee-a037-da68f37f3468",
    "clientOrder": {
        "clientId": "1",
        "items": [
            {
                "itemId": 1,
                "description": "banana"
            }
        ]
    }
}
```

---

## 📋 Listar Pedidos

**Endpoint:** `GET /kafka-test/pedidos`  
**Descrição:** Retorna a lista de todos os pedidos já registrados.

### Requisição

```bash
curl --location 'http://localhost:8080/kafka-test/pedidos'
```

### Resposta

```json
[
    {
        "orderId": "46abcea8-29fc-4ac4-9900-5b55afb53274",
        "clientOrder": {
            "clientId": "1",
            "items": [
                {
                    "itemId": 1,
                    "description": "banana"
                }
            ]
        }
    },
    {
        "orderId": "559fb5f8-3de5-487a-8e13-3a258f376c0c",
        "clientOrder": {
            "clientId": "1",
            "items": [
                {
                    "itemId": 1,
                    "description": "banana"
                }
            ]
        }
    },
    {
        "orderId": "202b6ba7-0a4d-40ee-a037-da68f37f3468",
        "clientOrder": {
            "clientId": "1",
            "items": [
                {
                    "itemId": 1,
                    "description": "banana"
                }
            ]
        }
    }
]
```

---

## 📌 Observações

- Cada pedido enviado recebe um `orderId` único.
- Os pedidos são armazenados e podem ser listados posteriormente via `GET`.
- A API utiliza estrutura de eventos, com integração Kafka para simular o fluxo de pedidos.

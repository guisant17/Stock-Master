# 📦 Stock Master

O **Stock Master** é um sistema Full-Stack de gerenciamento de estoque construído para aplicar conceitos práticos de Programação Orientada a Objetos (POO) e arquitetura de APIs RESTful. O projeto evoluiu de uma aplicação de terminal (console) para um sistema web completo e responsivo.

## 🚀 Funcionalidades

* **Cadastro e Consulta (CRUD):** Registro de novos produtos com ID, descrição, quantidade, peso (kg) e data de validade.
* **Movimentação de Estoque:** Controle de Entrada e Saída, atualizando automaticamente o saldo de unidades e o peso total em tempo real.
* **Monitoramento de Validade:** 
  * 🔴 Alerta visual em vermelho para produtos vencidos.
  * 🟡 Alerta visual em amarelo para produtos que vencem nos próximos 30 dias.
* **Persistência de Dados:** Salvamento automático das informações em um arquivo de texto local (`estoque.txt`), garantindo que os dados não sejam perdidos ao desligar o servidor.

## 💻 Tecnologias Utilizadas

**Back-end:**
* Java (JDK 21)
* Spring Boot (Web / RESTful API)
* Manipulação de Arquivos de Texto (`java.io`) para persistência

**Front-end:**
* HTML5 e CSS3
* JavaScript Vanilla (Integração assíncrona via Fetch API)

## ⚙️ Como executar o projeto

1. Certifique-se de ter o **Java 21** e o **Maven** instalados em sua máquina.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/stock-master.git](https://github.com/SEU_USUARIO/stock-master.git)

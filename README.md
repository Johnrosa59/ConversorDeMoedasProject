# 📱 Conversor de Moedas

Este é um aplicativo Android desenvolvido para realizar a conversão de moedas (Real para Dólar, Euro, Dólar Canadense e Peso Argentino) em tempo real.

---

## 🚀 Funcionalidades

* **Conversão em tempo real:** Consulta de taxas atualizadas através da API HG Brasil Finanças.
* **Interface Simples:** Uso de `Spinner` para seleção de moedas, `EditText` para entrada de valores e botões de ação (Converter e Limpar).

---

## 🏗 Arquitetura e Tecnologias

O projeto foi construído seguindo as melhores práticas de desenvolvimento Android, garantindo que o aplicativo seja fácil de testar e manter:

* **MVVM (Model-View-ViewModel):** Para separação de conceitos e gerenciamento de estado da interface.
* **Clean Architecture:** Divisão clara do projeto em camadas (`data`, `domain`, `presentation`).
* **Dependency Injection:** Utilização do **Hilt** para gerenciamento de dependências.
* **Retrofit:** Para consumo da API Rest.

---

## 📸 Demonstração e Estrutura

### Tela Principal do Aplicativo
<img width="390" height="810" alt="image" src="https://github.com/user-attachments/assets/ac489656-59e1-4843-b025-99787e3e9d97" />


### Estrutura das Pastas (Clean Architecture)
<img width="505" height="831" alt="image" src="https://github.com/user-attachments/assets/255f0aef-ceff-40b3-8aa5-b4b141e8b0b2" />

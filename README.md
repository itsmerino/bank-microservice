# Bank Microservice

Service that simulates a small bank.

It has the following functionalities that can be performed through REST endpoints:
- Create user
- Create wallet
- Get wallet information
- Deposit funds
- Transfer funds

The service has been implemented using a hexagonal architecture, and is tested using unit testing, integration testing and acceptance testing.

To store the information the following is used:
- An H2 database to store: users, wallets and movements
- Ganache to simulate an Ethereum network where the smart contract is deployed and used to manage the bank: deposit funds, transfer funds and get the balance of the wallets

The smart contract is automatically deployed when the microservice is started, so there is no need to do it manually.

## Tech Stack

Microservice:
- Maven
- Java 17
- Spring Boot
- H2
- Web3j
- Lombok
- Ganache
- JUnit 5
- Mockito
- Cucumber

Web3:
- Solidity
- Truffle
- Ganache

## API Reference

You can find the API specification in OpenAPI format here: [openapi.yaml](https://github.com/itsmerino/bank-microservice/blob/main/src/main/resources/openapi.yaml)

And the Postman collection for testing the API here: [bank.postman_collection](https://github.com/itsmerino/bank-microservice/blob/main/src/main/resources/bank.postman_collection)

## Configuration

For the whole system to work correctly, the following properties must be configured in the [application.yaml](https://github.com/itsmerino/bank-microservice/blob/main/src/main/resources/application.yaml) file:
- `web3.url`: URL where Ganache is running
- `web3.contract.privateKey`: private key of the account on which the smart contract will be deployed
- `web3.wallet1.address`: Ganache account address
- `web3.wallet1.privateKey`: Ganache account private key
- `web3.wallet2.address`: Ganache account address
- `web3.wallet2.privateKey`: Ganache account private key
- `web3.wallet3.address`: Ganache account address
- `web3.wallet3.privateKey`: Ganache account private key

The address and private key properties are required for integration test. Only `web3.url` and `web.contract.privateKey` properties are required to run the service.

In order to run the acceptance test, you need to replace address and private keys values contained in the [bank.feature](https://github.com/itsmerino/bank-microservice/blob/main/src/test/resources/features/bank.feature) file.

## Run Locally

Run Ganache and do properties configuration

```bash
  ganache-cli
```

Clone the project

```bash
  git clone https://github.com/itsmerino/bank-microservice
```

Go to the project directory

```bash
  cd product-prices-microservice
```

Run the application

```bash
  ./mvnw spring-boot:run
```

## Running Tests

Run Ganache and do properties configuration

```bash
  ganache-cli
```

Run the test

```bash
  ./mvnw test
```

## Author

- [Jorge Merino](https://www.github.com/itsmerino)
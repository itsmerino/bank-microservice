pragma solidity ^0.8.13;

contract Bank {

    mapping(address => uint256) public accounts;

    modifier isAmountValid(uint256 _amount) {
        require(_amount > 0);
        _;
    }

    modifier hasEnoughFunds(uint256 _amount) {
        require(accounts[msg.sender] >= _amount);
        _;
    }

    function deposit() public payable isAmountValid(msg.value) {
        accounts[msg.sender] += msg.value;
    }

    function transfer(address _to) public payable isAmountValid(msg.value) hasEnoughFunds(msg.value) {
        accounts[_to] += msg.value;
        accounts[msg.sender] -= msg.value;
    }

    function getBalance() public view returns (uint256) {
        return accounts[msg.sender];
    }
}

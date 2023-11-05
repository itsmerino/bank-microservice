package com.itsmerino.bank.domain.ports;

import com.itsmerino.bank.domain.Deposit;

public interface DepositPort {

    void depositFunds(Deposit deposit);
}

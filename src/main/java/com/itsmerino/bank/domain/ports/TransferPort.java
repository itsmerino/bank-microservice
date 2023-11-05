package com.itsmerino.bank.domain.ports;

import com.itsmerino.bank.domain.Transfer;

public interface TransferPort {

    void transferFunds(Transfer transfer);
}

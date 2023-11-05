Feature: Bank management

  Scenario: Make a deposit into his wallet
    Given a user "Bob" is registered
    And "Bob" has a wallet with address "0xD07aC32745373adFb9066e99f6d28Ece4bD47aDF" and privateKey "0xd45314015ff29662462100da9ca940c9718ac617341846d39c71f6754bd31a84"
    When the user "Bob" makes a deposit into his wallet
    Then the deposit is done successfully

  Scenario: Make a transfer to another wallet
    Given a user "Alice" is registered
    And "Alice" has a wallet with address "0xD07aC32745373adFb9066e99f6d28Ece4bD47aDF" and privateKey "0xd45314015ff29662462100da9ca940c9718ac617341846d39c71f6754bd31a84"
    Given a user "Mary" is registered
    And "Mary" has a wallet with address "0x4aF7Fab34ce81A789402ba23e3f8491f705AaBFB" and privateKey "0x1db505b3af6e21a74c7751a075c69de61ab26e59b3dd45e7d7d9a542aad7547e"
    And the user "Alice" makes a deposit into his wallet
    When the user "Alice" makes a transfer to "Mary"
    Then the transfer is done successfully

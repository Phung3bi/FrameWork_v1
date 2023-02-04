Feature: as a user i want to login page to the system sothat i can post some thing in to my wall

  Scenario: Show message with invalid password
    Given The login page is showed
    When The user provide his credential with invalid password then try to login
    Then The message"Pass khong dung" will be showed

  Scenario: Show message with invalid credential
    Given The login page is showed
    When The user provide his credential with invalid password then try to login
    Then The dasboad will be showed

  Scenario: Show message with existed email
    Given The login page is showed
    When The user provide his credential with existed email then try to login
    Then The message"Pass da ton tai" will be showed


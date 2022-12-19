Feature: Login into application 

  Scenario Outline: positive test validating login
  Given Initializing the browser with chrome
  And Navigating "http://qaclickacademy.com/" site
  And Click on login link to land on sign in page
  When User enters <username> and <password> and logs in
  Then Verify the user is successfully login
  And close the browsers
  
  Examples:
  |username            |password    |
  |test99@gmail.com    |123456      |
  |test12@gmail.com    |12345       |
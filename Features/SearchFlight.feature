Feature: Automate Kayak Website 

Scenario Outline: Search the flights 
	Given The "chrome" browser is open 
	And User enters website "http://www.kayak.com" 
	Then the webpage should load 
	When User enters "<Origin-airport>" and "<Destination-airport>" cities 
	And User selects departure and return dates 
		| departure | return |
		|2023-03-13 |2023-04-15|
	Then Click Search 
	When User selects a flight from the results 
	Then Validate Origin airport is  same as the one entered in the main screen 
	Then Validate  Destination airport is same as the one entered in the main screen 
	Then Validate departure date is same as the one entered in the main screen 
	Then Validate arrival date is same as the one entered in the main screen 
	Then log all the flight details to the reports
	
	Examples: 
		|Origin-airport| Destination-airport |
		|Vegas|San Francisco|
		|SFO  |London-CBG|
		|SFO-San Jose  |BOM|

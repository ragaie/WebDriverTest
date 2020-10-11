# Need to read before going dwon in code:-

1- Classes you find

(HomePage, LoginNewUser,CreateNewAccount,ShoppingItems,ReviewMyOrders)

2- Also there is a class for data driven  (DataDrivenManger)
this class it should have to read test data form file, but for this demo 
i just added one test case.
also it keeps selected item deatil to check against it when verify item placed in account.

3- ExtentReport.html file will be generated after you finish runing test.


#Flow of code :-
1- need to  change email value in Class newUser, for every time need to enter reqister flow.
otherwise it will login with this data provided.

* code start enter invalid format email to check validation message that working fine.
* clear this data and enter correct email, 
* and check if email already registered, by checking error message value.
* then it will try to login with same email and password thet alredy provided in data manger.
* after login and valid page loading
* start search for “Blouses” Subcategory in “Women” Category
* select first item
* click on it and showing details.
* keep item detail in data manger to check against it in list items.
* proceed in checkout and review address and accept terms and select payment way.
* last place order.

* then open user account
* select orders option.
* select first item 
* show details.
* verify item details against data saved before in data manger.


* if email was vaild and not registered before.
* it will open register new user screen 
* and adding data for data manger 
* create new user account with this data
* it will review user account 
* then it will logout.
* and login --> and take option for search and place order and review it.


 
 




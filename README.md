Data Filtering and Sorting Application
Develop a simple application for sorting and filtering data based on predefined rules. The application should be capable of working with lists of JSON objects of arbitrary structure, 
filtering objects containing keys with specific values, and sorting objects based on values using natural sorting order.

For example, given the following data:

{
  "data": [
    {"name": "John", "email": "john2@mail.com"},
    {"name": "John", "email": "john1@mail.com"},
    {"name": "Jane", "email": "jane@mail.com"}
  ]
}

and the condition:

{
  "condition": {
    "include": [{"name": "John"}],
    "sort_by": ["email"]
  }
}

which contains two rules - include and sort_by (where the include rule takes a set of key-value pairs to check records for compliance, 
and the sort_by rule takes a set of keys for sorting), the result will be an object containing only records with the name John, sorted by the email key:

{
  "result": [
    {"name": "John", "email": "john1@mail.com"},
    {"name": "John", "email": "john2@mail.com"}
  ]
}

When designing the code architecture of the application, consider the ability to extend functionality by adding new "modules" with rules. 
It's important that all modules have an identical structure, are isolated from each other and the rest of the application code, and interact with the main code using the same approach. 
For example, you can add a module with an additional exclude rule, which will discard records containing keys with a specific value.

Input Parameters:
A JSON object with a data list (data) and processing condition (condition):

{
  "data": [
    {"user": "mike@mail.com", "rating": 20, "disabled": false},
    {"user": "greg@mail.com", "rating": 14, "disabled": false},
    {"user": "john@mail.com", "rating": 25, "disabled": true}
  ],
  "condition": {
    "exclude": [{"disabled": true}],
    "sort_by": ["rating"]
  }
}

Output Data:
A JSON object with the data obtained after applying the processing condition (result):

{
  "result": [
    {"user": "greg@mail.com", "rating": 14, "disabled": false},
    {"user": "mike@mail.com", "rating": 20, "disabled": false}
  ]
}

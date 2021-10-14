# What I learned about code coverage?

### What is a code coverage?
Code coverage is a measure which describes the degree of which the source code of the program has been tested. It is one form of white box testing which finds the areas of the program not exercised by a set of test cases. It also creates some test cases to increase coverage and determining a quantitative measure of code coverage.

In most cases, code coverage system gathers information about the running program. It also combines that with source code information to generate a report about the test suite’s code coverage.

### Code Coverage Methods
Following are major code coverage methods:
* Statement Coverage
* Decision Coverage
* Branch Coverage
* Toggle Coverage
* FSM Coverage

Let's have a look at the branch coverage.

### Branch Coverage
Branch Coverage is a white box testing method in which every outcome from a code module(statement or loop) is tested. The purpose of branch coverage is to ensure that each decision condition from every branch is executed at least once. It helps to measure fractions of independent code segments and to find out sections having no branches.

For example, if the outcomes are binary, you need to test both True and False outcomes.

The formula to calculate Branch Coverage:

![Formula](https://cdn.guru99.com/images/1/102518_1122_CodeCoverag13.jpg)

### Example of Branch Coverage
To learn branch coverage, let’s consider the example given below.

Consider the following code:

```
Demo(int a) {                       
     If (a> 5)
    	a=a*3
     Print (a)
    }    
```

![Example](https://cdn.guru99.com/images/1/102518_1122_CodeCoverag10.png)

Branch Coverage will consider unconditional branch as well.

| Test Case | Value of A | Output | Branch Coverage |
| :---: | :---: | :---: | :---: | 
| 1 | 2 | 2 | 33% |
| 2 | 6 | 38 | 67% | 

### Advantages of Branch coverage:

Branch coverage Testing offers the following advantages:

* Allows you to validate-all the branches in the code
* Helps you to ensure that no branched lead to any abnormality of the program’s operation
* Branch coverage method removes issues which happen because of statement coverage testing
* Allows you to find those areas which are not tested by other testing methods
* It allows you to find a quantitative measure of code coverage
* Branch coverage ignores branches inside the Boolean expressions

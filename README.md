# Coffee Vending Machine

A coffee vending machine simulator program, implemented to showcase the [State Design Pattern](https://refactoring.guru/design-patterns/state) in action. 

This is a weekly lab assignment belonging to [CSE-3216 (Software Design Patterns Lab)](https://www.cse.du.ac.bd/curriculum/).

<br>

<p align="center">
    <img src="cover.png" alt="A Coffee Vending Machine Illustration" width="100%">
</p>

<br>

## Problem

You have to implement a coffee vending machine. Coffee machine works as follows:
- Machine accepts only coins of 10, 20 and 50 cents worth.
- Machine can prepare two types of drinks: Coffee and Cappuccino.
- Coffee is 1.20 and Cappuccino is 1.50.
- You can insert money and eject inserted money.
- When you select a beverage by pressing corresponding button, if there is not enough
money, it gets ejected automatically.
- In case if there was enough money, the change should be returned and beverage should
be prepared.
- When beverage is ready machine waits for the cup to be taken and only then you can
make next order.

Your task is to implement coffee vending machine simulator program. You may assume that there are at most 5 cups of each beverage and after it runs out, you won’t be able to buy it anymore. The remaining money after buying a drink should be returned in coins of 10, 20 and 50 cents worth.

<br>

## Solution

The class diagram that's designed to solve the above problem, is given below:

![Class Diagram](class-diagram.png)

Note that, this is just a rough solution. I hope to further work on it later and modify it for better. 
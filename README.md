The Package Optimizer README

To complete this assignment I chose to implement the interpreter and strategy design patterns. 

The interpreter allows to extend without modifying the expressions created. Other than possibly adding/removing
attributes to the `Package` or `Item` classes, there's no need to change the implementation of these expressions.

I chose to implement the strategy pattern because this could be on of many packaging algorithms. One could add a
`HighWeightLowCostStrategy`, `HighWeightHighCostStrategy` or `LowWeightLowCostStrategy`. The list can go on to as 
many strategies while maintaining low coupling and high cohesion.

Before implementation to algorithm, I had to look for a pattern in the example output. That's when I saw that before 
packaging the items, the items were sorted by descending price, then ascending weight. This made it easier to create 
the strategy. In the strategy I knew I had to implement a comparator of some sort. I had a few choices to choose
from. One was to implement the comparable interface on the `Item` class. The problem here is that I only compare one
attribute when I need to compare two. Even if I could compare multiple attributes, with this approach if/when I 
need to create another algorithm I would need to modify the `Item` class. Now we run into a violation of the Open/Closed
Principle. Which means the only approach would be to implement a comparator for this specific algorithm by adding
another class that implements the `Comparator` interface or using the `Comparator` static methods within a stream.

I chose to implement within the stream because adding another class for such a small piece of logic. It's also less
work for the compiler but still worth it.

Couple of things I changed around was the move from maven to gradle. I've found gradle to be faster and easier to 
work. It also provides a more concise configuration that maven does. I also moved from junit to spock for testing.
I really like spock's conciseness for data driven testing as well. Plus I've been with working in the groovy
language for the past few years as well.
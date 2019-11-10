# The Package Optimizer README

### Intro

To complete this assignment I chose to implement the interpreter and strategy design patterns. 

## Patterns

#### Interpreter

The interpreter allows to extend without modifying the expressions created. Other than possibly adding/removing
attributes to the `Package` or `Item` classes, there's no need to change the implementation of these expressions.

#### Strategy

I chose to implement the strategy pattern because this could be on of many packaging algorithms. One could add a
`HighWeightLowCostStrategy`, `HighWeightHighCostStrategy` or `LowWeightLowCostStrategy`. The list can go on to as 
many strategies while maintaining low coupling and high cohesion.

##### Strategy Algorithm

Before implementation to algorithm, I had to look for a pattern in the example output. That's when I saw that before 
packaging the items, the items were sorted by descending price, then ascending weight. This made it easier to create 
the strategy. In the strategy I knew I had to implement a comparator of some sort. I had a few choices to choose
from. One was to implement the comparable interface on the `Item` class. The problem here is that I only implement
one comparator strategy. If I have to create another strategy, then I would need to modify the `Item` class. 
Now we run into a violation of the Open/Closed Principle. Which means the only approach would be to implement a 
comparator for this specific algorithm by adding another class that implements the `Comparator` interface or using 
the `Comparator` static methods within a stream.

I chose to implement within the stream because adding another class for such a small piece of logic. It's also less
work for the compiler but still worth it.

## Other Notes

Couple of things I changed around was the move from maven to gradle. I've found gradle to be faster and easier to 
work. It also provides a more concise configuration that maven does. I also moved from junit to spock for testing.
I really like spock's conciseness for data driven testing as well. Plus I've been with working in the groovy
language for the past few years as well.

## Runnable CLI

To download a packaged version of the app without having to checkout the code, go to 
[CI/CD][https://gitlab.com/tdespenza/package-optimizer/pipelines] tab. Then go the far right and click on the
artifacts button.

Once you've downloaded the archive and extracted it, navigate to the `build/distributions/` folder and extract the
implementation.zip file. Once that's extracted, navigate to the bin sub directory and the following commands:

### Linux/Unix

`./implementation /absolute/path/to/utf/8/file.txt`

or

`./implementation` (you'll be prompted for the file path if not specified on start)

### Windows

`./implementation.bat C:/absolute/path/to/utf/8/file.txt`

or 
`./implementation.bat` (you'll be prompted for the file path if not specified on start)

[https://gitlab.com/tdespenza/package-optimizer/pipelines]: https://gitlab.com/tdespenza/package-optimizer/pipelines
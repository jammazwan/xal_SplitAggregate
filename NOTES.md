There are so many approaches to splitting a message body that I found it helpful to break it into different projects.

### Common to all x__Split___ Projects

There are often many different ways to split even just one kind of body. This is where Camel's "does everything to anything" approach can be a bit confusing to the newbie. You may eventually want to learn 20 great approaches, but right now you just want one specific approach that's going work for your use case.

So each Split___ example project has to show, and/or cross reference, working code that makes each of the relevant approaches work, quickly and without a bunch of cruft.

**All Split___ example projects** were inspired by a grep of **'.split('** on **'*Test.java'** in the actual **camel-core** code base - hundreds of examples. A good place to start, if you want a more in depth exploration of anything, not just '.split('.

You will probably want to read [**this doc on Camel's site**](http://camel.apache.org/splitter.html) before doing much with splits. It took me a week to find it, and ended up being one of the best sources.

### SplitAggregate NOTES:

This code is relatively self revealing.

Shown is the difference between 

 * a more typical split, then aggregate, in one sequential route
 * a split and aggregate in one simpler request/reply
 
A simple list of String elements was used in the body so that no tokenizer would be required.
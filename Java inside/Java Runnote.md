# Java SE 8 Programmer I (1Z0-808) learning and practice
 
**Includes all theory with examples** 


###Greatest algorithms:
```java
 public static void main(String args[]){
        int base=14235478;
        int[] holder= new int[Integer.toString(base).length()];
        int i=0;
        //int count=1;
        while(base>0){
            int rem = base%10;
            int newBase = base/10;
            base=newBase;
            holder[i++]=rem;
        }
        System.out.print(holder);
        int count=1;
        int k=0;
        int[] finalArr= new int[10];
        for(int j=holder.length-1;j>=0;j--){
            if (count%2==0)
                finalArr[k++]=holder[j];
            count++;
        }
        System.out.print(finalArr);

    }
```

```java
 Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(i -> getVal(Long.valueOf(i.getValue())));
```
comment from github

Classes
    fields/variable         :states
    methods/functions       :operations 

Packages and imports:


Naming conflicts
Using multiple classes with same name
Wildcard imports 
COde formatting on exam












> https://help.github.com/en/github/writing-on-github/basic-writing-and-formatting-syntax
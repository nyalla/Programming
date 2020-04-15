# Java SE 8 Programmer I (1Z0-808) learning and practice
 
**Includes all theory with examples** 


##Greatest algorithms:
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

##static imports : import static Maths.max;

##String literals:
We can mention L or l after number to tell that is long data type.
long max=1223; // error throws 
long max=1234L; //Compiles
long max=1_234L;    //Compiles

double myDouble = 2.54;
double myDouble2 = 2.54F;

double scientific = 5.000125E03;    //5000.125
double scientific = 5.0001250E3;    //5000.125
double scientific = 5000.125;       //5000.125

double hexPi= = 0x1.91eb851eb1fp1;  //P indicates hexadecimal floating point : OP:3.14

Char ch1='1';
char uniChar='\u03A9';  //uppercase greek omega character



##Octal (0-7) Only accespts digits from 0 to 7

int oct=07;
int firstOct=010;   //8 in decimal
int secondOct=022;  // 10 in decimal

int sumOct = firstOct + secondOct;  //26 in decimal, 32 octal
    Output of sumOct will be decimal, to convert to octa decimal, Integer.toOctalString(sumOctal)

##Hexa decimal (0-9 and A-F)

int firstHex=0xF;   //15 decimal
int secondHex = 0x1E;   //30 decimal

int sumHex= firstHex + secondHex;   //45 decima,2d Hex
    Output of sumHex will be decimal, to convert to Hexa decimal, Integer.toHexString(sumHex)

##Binary (1 or 0)
int firstBin=0b1001;    //9 decimal
int secondBin=0b0111;   //7 decimal

int sumBin= firstBin+secondBin; // 16 decimal, 10000 binary
    Output of sumBin will be decimal, to convert to Bin decimal, Integer.toBinaryString(sumOctal)



##Default intilization

For lcoal primitive variables are not initialized, there will be error saying variable is not initilizaed when using. 

```java
Class ClassName{
    static boolean a;
    static byte b;
    static short c;
    static int d;
    static long e;
    static float f;
    static double g;
    static char h;

     public static void main1(String[] args)
    {
      int localInt;
      System.out.println(lcoalInt); //throws error becuase not initialized

      System.out.println(a);    //flase
      System.out.println(b);    //0
      System.out.println(c);    //0
      System.out.println(d);    //0
      System.out.println(e);    //0
      System.out.println(f);    //0.0
      System.out.println(g);    //0.0
      System.out.println(h);    // 
    } 
} 
```


##Variable scopes
    Global variables
    local variables


## Elements class

    Package
    import
    Class decelration
    Field decelrations          //Anywhere inside class
    Method decelrations         //Anywhere inside class

**Only one public class inside one java file**

##Understanding NULL

```java
Class ClassName{
    static Object ob1=new Object();
    static Object ob2;

     public static void main1(String[] args)
    {
        Object localOb1=new Object();
        Object localOb2;   

        String name="java" ;
        String anotherName=null;        

      System.out.println(ob1);      // objct@12323
      System.out.println(ob2);      //null
      System.out.println(localOb1); // objct@232434
      System.out.println(localOb2);     //compilation error, not intilised

      System.out.println(name);         //java
      System.out.println(anotherName);  //null

      anotherName.toUpperCase();    //gets null pointer exception as value is null
    } 
} 

```

##Primitive Wrapper Types

Wrapper are objects of primitive types
int value =10;
Integer value1  = new Integer(10);


Un-Boxing   :    conveting Integer to int  //  wrapper to primitive // int value1=value2
Note: Primitive cant contain null

Boxing      :    convert int to Integer // primitive to Wrapper//   Integer value = new Integer(10);



##Java Benefits
    Object Oriented
    Encapsulation
    Platform Independent
    Robust
    Simple
    Secure


##QuestionAndAnswers:



























> https://help.github.com/en/github/writing-on-github/basic-writing-and-formatting-syntax
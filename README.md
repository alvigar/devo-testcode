# devo-testcode

To run the application and use the differents functions that implements this app, first its need to
 build it with `mvn clean install`
 
 The jar is `devo-testcode-1.0-SNAPSHOT.jar` in `target` folder, and to execute:
 
 `java -jar devo-testcode-1.0-SNAPSHOT.jar [OPTIONS]`
 
### Palindrome `-P`

To check if a word is palindrome, execute the app with the option `-P` and after separated with spaces
the different words that would be checked.

Example:

`java -jar devo-testcode-1.0-SNAPSHOT.jar -P oso bear test dvd`

Output:

```
oso is palindrome: true
bear is palindrome: false
test is palindrome: false
dvd is palindrome: true
```

### Complementary `-C`

To check in an array of integers which elements are K-complementary, execute the app with the option `-C`
and after separated with spaces first the K number and after the integer array wihout spaces and separating
the elements with comma.

Example:

`java -jar devo-testcode-1.0-SNAPSHOT.jar -C 10 1,2,3,4,5,6,7,8,9,10 10 2,5,8,10,600,20,0,43,89,150,23`

Output:

```
Complementary of A = 1,2,3,4,5,6,7,8,9,10 and K = 10
         - {x1=1, x2=9}
         - {x1=2, x2=8}
         - {x1=3, x2=7}
         - {x1=4, x2=6}
Complementary of A = 2,5,8,10,600,20,0,43,89,150,23 and K = 10
         - {x1=2, x2=8}
         - {x1=10, x2=0}
```

### Term Frequency `-T`

To obtain the top N of files which set of term have the highest frequency, execute the app with the option `-T`
and with the following options:
- `-d` directory to find the files
- `-n` top results to show
- `-p` period to check the files again
- `-t` set of terms to be analyzed

All of them are mandatory, if anyone is missing the app returns a error message.

Example:

`java -jar devo-testcode-1.0-SNAPSHOT.jar -T -d test -n 5 -p 500 -t "test devo code"`

Output:

```
test\p4.txt - 1.0
test\p2.txt - 0.6363636363636364
----------
test\p4.txt - 1.0
test\p8.txt - 0.6666666666666666
test\p2.txt - 0.6363636363636364
----------
test\p4.txt - 1.0
test\p9.txt - 1.0
test\p8.txt - 0.6666666666666666
test\p2.txt - 0.6363636363636364
----------
test\p4.txt - 1.0
test\p9.txt - 1.0
test\p1.txt - 0.6666666666666666
test\p8.txt - 0.6666666666666666
test\p2.txt - 0.6363636363636364
----------
test\p4.txt - 1.0
...
```

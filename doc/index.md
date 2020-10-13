## Java桥接方法
- 一个简单的示例
```java
public class Test {

	public class Node<T> {

		public T data;

		public Node(T data) {
			this.data = data;
		}

		public void setData(T data) {
			System.out.println("Node.setData");
			this.data = data;
		}
	}

	public class MyNode extends Node<Integer> {
		public MyNode(Integer data) {
			super(data);
		}

		public void setData(Integer data) {
			System.out.println("MyNode.setData");
			super.setData(data);
		}
	}
}
```
- javap -v Test\$MyNode
```text
Classfile /Users/huqingfeng/Downloads/Test$MyNode.class
  Last modified 2020-10-13; size 760 bytes
  MD5 checksum 70732b17dc1e2f44bea5872ce3384abf
  Compiled from "Test.java"
public class Test$MyNode extends Test$Node<java.lang.Integer>
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Fieldref           #9.#26         // Test$MyNode.this$0:LTest;
   #2 = Methodref          #10.#27        // Test$Node."<init>":(LTest;Ljava/lang/Object;)V
   #3 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
   #4 = String             #30            // MyNode.setData
   #5 = Methodref          #31.#32        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #6 = Methodref          #10.#33        // Test$Node.setData:(Ljava/lang/Object;)V
   #7 = Class              #34            // java/lang/Integer
   #8 = Methodref          #9.#35         // Test$MyNode.setData:(Ljava/lang/Integer;)V
   #9 = Class              #37            // Test$MyNode
  #10 = Class              #39            // Test$Node
  #11 = Utf8               this$0
  #12 = Utf8               LTest;
  #13 = Utf8               <init>
  #14 = Utf8               (LTest;Ljava/lang/Integer;)V
  #15 = Utf8               Code
  #16 = Utf8               LineNumberTable
  #17 = Utf8               setData
  #18 = Utf8               (Ljava/lang/Integer;)V
  #19 = Utf8               (Ljava/lang/Object;)V
  #20 = Utf8               Signature
  #21 = Utf8               Node
  #22 = Utf8               InnerClasses
  #23 = Utf8               LTest$Node<Ljava/lang/Integer;>;
  #24 = Utf8               SourceFile
  #25 = Utf8               Test.java
  #26 = NameAndType        #11:#12        // this$0:LTest;
  #27 = NameAndType        #13:#40        // "<init>":(LTest;Ljava/lang/Object;)V
  #28 = Class              #41            // java/lang/System
  #29 = NameAndType        #42:#43        // out:Ljava/io/PrintStream;
  #30 = Utf8               MyNode.setData
  #31 = Class              #44            // java/io/PrintStream
  #32 = NameAndType        #45:#46        // println:(Ljava/lang/String;)V
  #33 = NameAndType        #17:#19        // setData:(Ljava/lang/Object;)V
  #34 = Utf8               java/lang/Integer
  #35 = NameAndType        #17:#18        // setData:(Ljava/lang/Integer;)V
  #36 = Class              #47            // Test
  #37 = Utf8               Test$MyNode
  #38 = Utf8               MyNode
  #39 = Utf8               Test$Node
  #40 = Utf8               (LTest;Ljava/lang/Object;)V
  #41 = Utf8               java/lang/System
  #42 = Utf8               out
  #43 = Utf8               Ljava/io/PrintStream;
  #44 = Utf8               java/io/PrintStream
  #45 = Utf8               println
  #46 = Utf8               (Ljava/lang/String;)V
  #47 = Utf8               Test
{
  final Test this$0;
    descriptor: LTest;
    flags: ACC_FINAL, ACC_SYNTHETIC

  public Test$MyNode(Test, java.lang.Integer);
    descriptor: (LTest;Ljava/lang/Integer;)V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=3
         0: aload_0
         1: aload_1
         2: putfield      #1                  // Field this$0:LTest;
         5: aload_0
         6: aload_1
         7: aload_2
         8: invokespecial #2                  // Method Test$Node."<init>":(LTest;Ljava/lang/Object;)V
        11: return
      LineNumberTable:
        line 16: 0

  public void setData(java.lang.Integer);
    descriptor: (Ljava/lang/Integer;)V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
         0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #4                  // String MyNode.setData
         5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: aload_0
         9: aload_1
        10: invokespecial #6                  // Method Test$Node.setData:(Ljava/lang/Object;)V
        13: return
      LineNumberTable:
        line 19: 0
        line 20: 8
        line 21: 13

  public void setData(java.lang.Object);
    descriptor: (Ljava/lang/Object;)V
    flags: ACC_PUBLIC, ACC_BRIDGE, ACC_SYNTHETIC
    Code:
      stack=2, locals=2, args_size=2
         0: aload_0
         1: aload_1
         2: checkcast     #7                  // class java/lang/Integer
         5: invokevirtual #8                  // Method setData:(Ljava/lang/Integer;)V
         8: return
      LineNumberTable:
        line 15: 0
}
Signature: #23                          // LTest$Node<Ljava/lang/Integer;>;
SourceFile: "Test.java"
InnerClasses:
     public #21= #10 of #36; //Node=class Test$Node of class Test
     public #38= #9 of #36; //MyNode=class Test$MyNode of class Test
```
**会发现有两个setData方法，其中有一个setData(Object)的就是桥接方法**
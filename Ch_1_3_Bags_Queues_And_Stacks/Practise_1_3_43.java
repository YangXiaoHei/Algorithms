package Ch_1_3_Bags_Queues_And_Stacks;

import java.io.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

/*
 * 思路 :
 * 
 * 递归的方式 :
 * 递归基是 判断当前文件是否不是文件夹
 * 递归方法有两个参数，一个是文件，一个是递归调用的深度，用于打印时设置偏移量 
 * 用自然语言叙述如下，对于文件夹，首先打印出来，然后遍历所有子文件，递归深度加1，然后把子文件和递归深度作为参数
 * 传入下一层
 * 如果碰到非文件夹类型，打印完成后直接 return, 此时会返回到 for 循环中的某一次迭代，我们将递归深度减一
 * 然后继续下一次迭代
 * 
 * 队列的方式 :
 * 首先把根目录文件放入队列中进行初始化操作
 * 然后开始执行 条件为队列不为空 的循环
 * 
 * 在循环中，首先对出队元素进行打印，之后判断出队元素是否是文件夹，如果是的话，就把所有子文件夹入队
 * 
 */
public class Practise_1_3_43 {
	@SuppressWarnings("unchecked")
	static class Queue<T> {
		private T[] items = (T[])new Object[1];
		private int head;
		private int tail;
		private int size;
		void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur++];
				if (cur == items.length) 
					cur = 0;
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		int size() { return size; };
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * size);
			size++;
			items[tail++] = item;
			if (tail == items.length) 
				tail = 0;
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			size--;
			T del = items[head];
			items[head++] = null;
			if (head == items.length)
				head = 0;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
		T peek() {
			return items[head];
		}
		boolean isEmpty() { return size == 0; }
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for (T item : items)
				sb.append(String.format(" %2s |", item == null ? " " : item));
			return sb.toString();
		}
	}
	
	
	/*
	 * recursively print
	 */
	public static void printByRecursivion(File file) {
		StdOut.println("====== Recursion ======");
		int depth = 0;
		print(file, depth);
	}
	public static void print(File file, int traceDepth) {
		if (!file.isDirectory()) {
			StdOut.println(fileString(file, traceDepth));
			return;
		}
		StdOut.println(fileString(file, traceDepth));
		for(File f : file.listFiles()) {
			traceDepth++;
			print(f, traceDepth);
			traceDepth--;
		}
	}
	/*
	 * implemented by queue
	 */
	public static void printByQueue(File file) {
		StdOut.println("\n\n\n====== Queue ======");
		Queue<File> files = new Queue<File>();
		Queue<Integer> depths = new Queue<Integer>();
		files.enqueue(file);
		depths.enqueue(0);
		while(!files.isEmpty()) {
			File f = files.dequeue();
			int depth = depths.dequeue();
			StdOut.println(fileString(f, depth));
			if (f.isDirectory()) {
				for(File ff : f.listFiles()) {
					files.enqueue(ff);
					depths.enqueue(depth + 1);
				}
			}
		}
	}
	public static String fileString(File file, int depth) {
		String str = "";
		for(int i = 0; i < depth; i++)
			str += "     ";
		return  str + "- " + file.getName();
	}

	public static void main(String[] args) {
		 File file = new File("/Users/bot/Documents/C 语言程序设计(现代方法)");
		 printByRecursivion(file);
		 printByQueue(file);
	}
	// output
	/*
	 * ====== Recursion ======
- C 语言程序设计(现代方法)
     - .DS_Store
     - 10 程序结构
          - .DS_Store
          - 10 程序结构
               - main.c
          - 10 程序结构.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 10 程序结构.xcscheme
                              - xcschememanagement.plist
     - 11 指针
          - .DS_Store
          - 11 指针
               - main.c
          - 11 指针.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 11 指针.xcscheme
                              - xcschememanagement.plist
     - 12 指针和数组
          - .DS_Store
          - 12 指针和数组
               - main.c
          - 12 指针和数组.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 12 指针和数组.xcscheme
                              - xcschememanagement.plist
     - 13 字符串
          - .DS_Store
          - 13 字符串
               - main.c
          - 13 字符串.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 13 字符串.xcscheme
                              - xcschememanagement.plist
     - 13 字符串(编程题)
          - .DS_Store
          - 13 字符串(编程题)
               - main.c
          - 13 字符串(编程题).xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 13 字符串(编程题).xcscheme
                              - xcschememanagement.plist
     - 14 宏定义
          - 14 宏定义
               - main.c
          - 14 宏定义.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcschemes
                              - 14 宏定义.xcscheme
                              - xcschememanagement.plist
     - 15 编写大型程序
          - 15 编写大型程序
               - main.c
          - 15 编写大型程序.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcschemes
                              - 15 编写大型程序.xcscheme
                              - xcschememanagement.plist
     - 16 结构、联合和枚举
          - .DS_Store
          - 16 结构、联合和枚举
               - Inventory.c
               - Inventory.h
               - main.c
          - 16 结构、联合和枚举.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 16 结构、联合和枚举.xcscheme
                              - xcschememanagement.plist
     - 16 结构、联合和枚举(编程题)
          - .DS_Store
          - 16 结构、联合和枚举(编程题)
               - main.c
          - 16 结构、联合和枚举(编程题).xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 16 结构、联合和枚举(编程题).xcscheme
                              - xcschememanagement.plist
     - 17 指针的高级应用
          - .DS_Store
          - 17 指针的高级应用
               - main.c
          - 17 指针的高级应用.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 17 指针的高级应用.xcscheme
                              - xcschememanagement.plist
     - 17 指针的高级应用(编程题)
          - .DS_Store
          - 17 指针的高级应用(编程题)
               - inventory.c
               - inventory.h
               - line.c
               - line.h
               - main.c
               - word.c
               - word.h
          - 17 指针的高级应用(编程题).xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 17 指针的高级应用(编程题).xcscheme
                              - xcschememanagement.plist
     - 18 声明
          - .DS_Store
          - 18 声明
               - main.c
               - Test.c
               - Test.h
          - 18 声明.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcschemes
                              - 18 声明.xcscheme
                              - xcschememanagement.plist
     - 19 程序设计
          - .DS_Store
          - 19 程序设计
               - main.c
               - QueueADT.c
               - QueueADT.h
               - QueueADT_Array.c
               - QueueADT_Array.h
               - StackADT.c
               - StackADT.h
               - Test.c
               - Test.h
          - 19 程序设计.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 19 程序设计.xcscheme
                              - xcschememanagement.plist
     - 20 底层程序设计
          - .DS_Store
          - 20 底层程序设计
               - main.c
          - 20 底层程序设计.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 20 底层程序设计.xcscheme
                              - xcschememanagement.plist
     - 20 底层程序设计(练习题)
          - .DS_Store
          - 20 底层程序设计(练习题)
               - main.c
          - 20 底层程序设计(练习题).xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 20 底层程序设计(练习题).xcscheme
                              - xcschememanagement.plist
     - 22 输入\输出
          - .DS_Store
          - 22 输入\输出
               - main.c
          - 22 输入\输出.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 22 输入\输出.xcscheme
                              - xcschememanagement.plist
     - 3 格式化输出
          - .DS_Store
          - 格式化输出
               - main.c
          - 格式化输出.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcschemes
                              - xcschememanagement.plist
                              - 格式化输出.xcscheme
     - 5 表达式
          - 4 表达式
               - main.c
          - 4 表达式.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcschemes
                              - 4 表达式.xcscheme
                              - xcschememanagement.plist
     - 6 循环
          - .DS_Store
          - 5 循环
               - main.c
          - 5 循环.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcschemes
                              - 5 循环.xcscheme
                              - xcschememanagement.plist
     - 7 整数类型
          - .DS_Store
          - 7 整数类型
               - main.c
          - 7 整数类型.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 7 整数类型.xcscheme
                              - xcschememanagement.plist
     - 8 数组
          - .DS_Store
          - 8 数组
               - main.c
          - 8 数组.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 8 数组.xcscheme
                              - xcschememanagement.plist
     - 9 函数
          - .DS_Store
          - 9 函数
               - main.c
          - 9 函数.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - 9 函数.xcscheme
                              - xcschememanagement.plist
     - C基础 9 - 结构嵌套以及结构与指针
          - .DS_Store
          - C基础 9 - 结构嵌套以及结构与指针
               - main.c
          - C基础 9 - 结构嵌套以及结构与指针.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - C基础 9 - 结构嵌套以及结构与指针.xcscheme
                              - xcschememanagement.plist
     - C基础 9 文件操作
          - .DS_Store
          - C基础 9 文件操作
               - main.c
          - C基础 9 文件操作.xcodeproj
               - project.pbxproj
               - project.xcworkspace
                    - contents.xcworkspacedata
                    - xcuserdata
                         - YangHan.xcuserdatad
                              - UserInterfaceState.xcuserstate
               - xcuserdata
                    - YangHan.xcuserdatad
                         - xcdebugger
                              - Breakpoints_v2.xcbkptlist
                         - xcschemes
                              - C基础 9 文件操作.xcscheme
                              - xcschememanagement.plist



====== Queue ======
- C 语言程序设计(现代方法)
     - .DS_Store
     - 10 程序结构
     - 11 指针
     - 12 指针和数组
     - 13 字符串
     - 13 字符串(编程题)
     - 14 宏定义
     - 15 编写大型程序
     - 16 结构、联合和枚举
     - 16 结构、联合和枚举(编程题)
     - 17 指针的高级应用
     - 17 指针的高级应用(编程题)
     - 18 声明
     - 19 程序设计
     - 20 底层程序设计
     - 20 底层程序设计(练习题)
     - 22 输入\输出
     - 3 格式化输出
     - 5 表达式
     - 6 循环
     - 7 整数类型
     - 8 数组
     - 9 函数
     - C基础 9 - 结构嵌套以及结构与指针
     - C基础 9 文件操作
          - .DS_Store
          - 10 程序结构
          - 10 程序结构.xcodeproj
          - .DS_Store
          - 11 指针
          - 11 指针.xcodeproj
          - .DS_Store
          - 12 指针和数组
          - 12 指针和数组.xcodeproj
          - .DS_Store
          - 13 字符串
          - 13 字符串.xcodeproj
          - .DS_Store
          - 13 字符串(编程题)
          - 13 字符串(编程题).xcodeproj
          - 14 宏定义
          - 14 宏定义.xcodeproj
          - 15 编写大型程序
          - 15 编写大型程序.xcodeproj
          - .DS_Store
          - 16 结构、联合和枚举
          - 16 结构、联合和枚举.xcodeproj
          - .DS_Store
          - 16 结构、联合和枚举(编程题)
          - 16 结构、联合和枚举(编程题).xcodeproj
          - .DS_Store
          - 17 指针的高级应用
          - 17 指针的高级应用.xcodeproj
          - .DS_Store
          - 17 指针的高级应用(编程题)
          - 17 指针的高级应用(编程题).xcodeproj
          - .DS_Store
          - 18 声明
          - 18 声明.xcodeproj
          - .DS_Store
          - 19 程序设计
          - 19 程序设计.xcodeproj
          - .DS_Store
          - 20 底层程序设计
          - 20 底层程序设计.xcodeproj
          - .DS_Store
          - 20 底层程序设计(练习题)
          - 20 底层程序设计(练习题).xcodeproj
          - .DS_Store
          - 22 输入\输出
          - 22 输入\输出.xcodeproj
          - .DS_Store
          - 格式化输出
          - 格式化输出.xcodeproj
          - 4 表达式
          - 4 表达式.xcodeproj
          - .DS_Store
          - 5 循环
          - 5 循环.xcodeproj
          - .DS_Store
          - 7 整数类型
          - 7 整数类型.xcodeproj
          - .DS_Store
          - 8 数组
          - 8 数组.xcodeproj
          - .DS_Store
          - 9 函数
          - 9 函数.xcodeproj
          - .DS_Store
          - C基础 9 - 结构嵌套以及结构与指针
          - C基础 9 - 结构嵌套以及结构与指针.xcodeproj
          - .DS_Store
          - C基础 9 文件操作
          - C基础 9 文件操作.xcodeproj
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - Inventory.c
               - Inventory.h
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - inventory.c
               - inventory.h
               - line.c
               - line.h
               - main.c
               - word.c
               - word.h
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - Test.c
               - Test.h
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - QueueADT.c
               - QueueADT.h
               - QueueADT_Array.c
               - QueueADT_Array.h
               - StackADT.c
               - StackADT.h
               - Test.c
               - Test.h
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
               - main.c
               - project.pbxproj
               - project.xcworkspace
               - xcuserdata
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                    - contents.xcworkspacedata
                    - xcuserdata
                    - YangHan.xcuserdatad
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                         - YangHan.xcuserdatad
                         - xcdebugger
                         - xcschemes
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 10 程序结构.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 11 指针.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 12 指针和数组.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 13 字符串.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 13 字符串(编程题).xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - 14 宏定义.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - 15 编写大型程序.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 16 结构、联合和枚举.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 16 结构、联合和枚举(编程题).xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 17 指针的高级应用.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 17 指针的高级应用(编程题).xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - 18 声明.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 19 程序设计.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 20 底层程序设计.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 20 底层程序设计(练习题).xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 22 输入\输出.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - xcschememanagement.plist
                              - 格式化输出.xcscheme
                              - UserInterfaceState.xcuserstate
                              - 4 表达式.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - 5 循环.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 7 整数类型.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 8 数组.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - 9 函数.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - C基础 9 - 结构嵌套以及结构与指针.xcscheme
                              - xcschememanagement.plist
                              - UserInterfaceState.xcuserstate
                              - Breakpoints_v2.xcbkptlist
                              - C基础 9 文件操作.xcscheme
                              - xcschememanagement.plist


	 */
	
}

package 第一章_算法分析;

public class Practise_1_4_32 {
	static class Stack<T> {
		@SuppressWarnings("unchecked")
		T[] items = (T[])new Object[1];
		int size;
		/*
		 * 数组扩容后的大小序列为 2 4 8 16 32 64 ... n
		 * 数组扩容为 2 时，把旧数组元素装进新数组需要访问一次,再把入栈元素装进新数组，共记访问两次
		 * 数组扩容为 4 时，把旧数组元素进行迁移，需要访问数组 2次，装新元素 1次，共记访问三次
		 * 数组扩容为 8 时，需要访问数组 4 + 1 = 5 次
		 * 扩容为 16，访问 8 + 1 = 9 次
		 * 扩容为 32, 访问 16 + 1 = 17 次
		 * 以上访问次数，抛除掉原本入栈元素需要访问的一次外，涉及到扩容新旧元素迁移的额外访问次数为扩容后尺寸的一半
		 * 因为随着数组扩容，数组额外访问的次数为 1 2 4 8 16 32 ... n/2
		 * 假如经过 M 次栈操作后数组尺寸扩容为 N
		 * 那么额外数组访问的次数为 1 + 2 + 4 + ... + N/8 + N/4 + N/2 < N
		 * 
		 * push 2 次, 数组扩容为 2
		 * push 5 次, 数组扩容为 8
		 * push 9 次, 数组扩容为 16
		 * ...
		 * 所以，要让数组扩容为 N, 栈操作的次数 M > N/2
		 * 所以 2M > N > 额外访问数组总次数
		 * 综上所述，M 次栈操作后数组总访问次数 S < M + 2M = 3M 
		 * 
		 */
		void resize(int newSize) {
			@SuppressWarnings("unchecked")
			T[] newItems = (T[])new Object[newSize];
			for (int i = 0; i < size; i++) 
				newItems[i] = items[i];
			items = newItems;
		}
		void push(T item) {
			if (size == items.length)
				resize(size * 2);
			items[size++] = item;
		}
		T pop() {
			T del = items[--size];
			items[size] = null;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
	}
}

public class TestMyArrayList {
	public static void main(String[] args) {
		// Create a list
		MyList<String> list = new MyArrayList<String>();

		// Add elements to the list
		list.add("Mladen"); // Add it to the list
		System.out.println("(1) " + list);

		list.add(0, "Zeljko"); // Add it to the beginning of the list
		System.out.println("(2) " + list);

		list.add("Boris"); // Add it to the end of the list
		System.out.println("(3) " + list);

		list.add("Dejan"); // Add it to the end of the list
		System.out.println("(4) " + list);

		list.add(3, "Estela"); // Add it to the list at index 2
		System.out.println("(5) " + list);

		list.add(5, "Maja"); // Add it to the list at index 5
		System.out.println("(6) " + list);

		// Remove elements from the list
		list.remove("Mladen"); // Same as list.remove(0) in this case
		System.out.println("(7) " + list);

		list.remove(2); // Remove the element at index 2
		System.out.println("(8) " + list);

		list.remove(list.size() - 1); // Remove the last element
		System.out.println("(9) " + list );
		
		System.out.print("(10) " + list.subList(1,2) + "\n(11) ");

		for (String s : list)
			System.out.print(s.toUpperCase() + " ");
	}
}

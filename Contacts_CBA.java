package contacts;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TrieNode {

	private boolean isWord; // to check the at this character any word is ending or not
	private char val;       // to store the character
	TrieNode[] childrens;
	private int tw = 26;  

	public TrieNode() {         
		childrens = new TrieNode[tw];
	}

	public TrieNode(char val) {   
		this.val = val;
		childrens = new TrieNode[tw];
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord() {
		this.isWord = true;
	}

	public char getWord() {
		return this.val;
	}

	public void emptyTheChildNodes() {
		this.childrens = new TrieNode[tw];
	}
	
	
}

class Trie {

	TrieNode root = null;
	String a = "";

	public Trie() {
		root = new TrieNode();
	}
	
	/****************** insertion  *******************/

	public void insert(String word) {
		TrieNode temp = root;

		for (int i = 0; i < word.length(); i++) {

			if (temp.childrens[word.charAt(i) - 'a'] == null) {
				temp.childrens[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
			}
			temp = temp.childrens[word.charAt(i) - 'a'];
		} // for
		temp.setWord();
	}// insert

	/****************** search  *******************/
	
	public void search(String word) {

		TrieNode temp = root, temp1 = root;

		for (int i = 0; i < word.length(); i++) {

			if (temp.childrens[word.charAt(i) - 'a'] != null) {

				a += temp.childrens[word.charAt(i) - 'a'].getWord();
				temp1 = temp.childrens[word.charAt(i) - 'a'];

				temp = temp.childrens[word.charAt(i) - 'a'];

				search2(temp1);
			} else
				System.out.println("contact not found");
			temp = temp1;
		}

	}// search

	int count = 0;

	public void search2(TrieNode t) {

		TrieNode temp = root;
		temp = t;

		for (int j = 0; j < 26; j++) {

			if (t.childrens[j] != null) {

				a += t.childrens[j].getWord();

				if (t.childrens[j].isWord()) {

					
					t = t.childrens[j];
					

					for (int k = 0; k < 26; k++) {

						if (t.childrens[k] != null) {
							
							count++;
							System.out.println(a + "    " + Contacts_CBA.map.get(a));
							a = a + t.childrens[k].getWord();
							search2(t.childrens[k]);
						}
					}
				
					if (Contacts_CBA.map.containsKey(a)) {
						System.out.println(a + "    " + Contacts_CBA.map.get(a));
						a = "" + a.charAt(0);
					} 

				}
				if (t.childrens[j] != null)
					search2(t.childrens[j]);
			}

		} 

	}

	public void searchString(String word) {

		TrieNode temp = root, temp1 = root;

		for (int i = 0; i < word.length(); i++) {

			if (temp.childrens[word.charAt(i) - 'a'] != null) {

				if (i > 0) {

					a += temp.getWord();
				}
				temp = temp.childrens[word.charAt(i) - 'a'];
				if (i == word.length() - 1) {

					a += temp.getWord();

					search2(temp);

				}

			} // if

		} // for

	}// ss

	
	/****************** Search phone *******************/
	public void searchphone(String search) {
		for (Map.Entry<String, String> ite : Contacts_CBA.map.entrySet())
			if (ite.getValue().equals(search))
				System.out.print(ite.getKey() + ", ");
	}

}// class trie

public class Contacts_CBA {
	static Map<String, String> map = new HashMap<>();

	public static void main(String[] args) {
		// construct a new Trie node
		Scanner sc = new Scanner(System.in);
		Trie t = new Trie();
		boolean b = true;
		while (b) {
			System.out.println("Enter 1 to Add contacts");
			System.out.println("Enter 2 to search");
			System.out.println("Enter 3 to search by phone number");
			System.out.println("Enter 4 to exit");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Enter the name and phone number");
				String name = sc.next();
				String phone = sc.next();
				map.put(name, phone);
				t.insert(name.toLowerCase());
			}
			if (choice == 2) {
				System.out.println("Enter the substring of a  name of starting character of name to search");
				String search = sc.next();
				t.searchString(search.toLowerCase());
				t.a = "";
			}
			if (choice == 3) {
				System.out.println("Enter the Phone number to find the contacts name");
				String search = sc.next();
				t.searchphone(search);
				t.a = "";
			}

			if (choice == 4)
				b = false;
		} 

	}
}

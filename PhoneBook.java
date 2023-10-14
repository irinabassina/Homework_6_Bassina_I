// 6. Хранение и обработка данных ч3: множество коллекций Set
// Формат сдачи: ссылка на подписанный git-проект.

// Задание
// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре будут повторяющиеся 
// имена с разными телефонами, их необходимо считать, как одного человека с разными 
// телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.
// (Task note: для повторяющихся имен использовать HashMap, для повторяющихся телефонов - HashSet, 
// сортировку через stream, выводить итератором Set  .forEach)

import java.util.*;

public class PhoneBook {

    private HashMap<String, HashSet<Integer>> phoneBook = new HashMap<>();

    public void add(String name, Integer phoneNum) {
        HashSet<Integer> phones = phoneBook.getOrDefault(name, new HashSet<>());
        phones.add(phoneNum);
        phoneBook.put(name, phones);
    }

    @Override        
    public String toString() {
        StringBuilder sb = new StringBuilder();
        phoneBook.entrySet()      
                .stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.comparing(Set::size, Comparator.reverseOrder())
                ))
                .forEach(k -> sb.append(k.getKey()).append("=").append(k.getValue()).append("\n"));
        return sb.toString();
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", 112233);
        phoneBook.add("Ivanov", 223311);
        phoneBook.add("Ivanov", 778899);
        phoneBook.add("Sidorov", 909090);
        phoneBook.add("Sidorov", 333333);
        phoneBook.add("Sidorov", 333333);
        phoneBook.add("Sidorov", 666677);
        phoneBook.add("Fedorov", 456434);
        phoneBook.add("Fedorov", 998880);
        phoneBook.add("Medvedev", 111111);

        System.out.println(phoneBook);
    }
}
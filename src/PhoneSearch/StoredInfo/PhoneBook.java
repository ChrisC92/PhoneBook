package PhoneSearch.StoredInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

    private Map<String, Set<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<String, Set<String>>();
    }

    public void addNumber(String name, String number) {
        Set<String> numbers = phoneBook.get(name);
        if (numbers == null) {
            numbers = new HashSet<String>();
            phoneBook.put(name, numbers);
        }
        numbers.add(number);
    }

    public Set<String> searchByName(String name) {
        if (phoneBook.get(name) == null) {
            throw new IllegalArgumentException("");
        }
        return phoneBook.get(name);
    }

    public String searchByNumber(String number) {

        for (String name : phoneBook.keySet()) {
            if (phoneBook.get(name).contains(number)) {
                return " " + name;
            }
        }
        return "not found";
    }
    
    public void removeNumbers(String name){
        phoneBook.remove(name);
    }
    
    public Set<String> getNames(){
        Set<String> names = new HashSet<String>();
        
        for(String name: phoneBook.keySet()){
            names.add(name);
        }
        return names;
    }
    
    public Set<String> searchPhoneBook(String search){
        Set<String> names = new HashSet<String>();
        
        for(String name : phoneBook.keySet()){
            if(name.contains(search)){
                names.add(name);
            }
        }
        return names;
    }
    
    public boolean isNull(String name){
        return phoneBook.get(name) == null;
    }
}

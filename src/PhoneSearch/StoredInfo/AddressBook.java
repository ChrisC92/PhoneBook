package PhoneSearch.StoredInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddressBook {

    private Map<String, String> addressBook;

    public AddressBook() {
        this.addressBook = new HashMap<String, String>();
    }
    
    public void addAddress(String name, String address) {
        addressBook.put(name, address);
    }
    
    public String getAddress(String name){
        if(addressBook.get(name) == null){
            throw new IllegalArgumentException();
        }
        return addressBook.get(name);
    }
    
    public void removeAddress(String name){
        addressBook.remove(name);
    }
    
    public Set<String> getNames(){
        Set<String> names = new HashSet<String>();
        
        for(String name : addressBook.keySet()){
            names.add(name);
        }
        return names;
    }
    
    public Set<String> searchAddressBook(String search){
        Set<String> names = new HashSet<String>();
        
        for(String name : addressBook.keySet()){
            if(name.contains(search)){
                names.add(name);
            }
            if(addressBook.get(name).contains(search)){
                names.add(name);
            }
        }
        
        return names;
    }
    public boolean isNull(String name){
        return addressBook.get(name) == null;
    }
}

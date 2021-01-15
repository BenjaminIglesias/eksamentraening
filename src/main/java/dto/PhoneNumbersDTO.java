/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import entities.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class PhoneNumbersDTO {
           List<PhoneNumberDTO> allNumbers = new ArrayList();
        
        public PhoneNumbersDTO(List<PhoneNumber> phoneNumberEntities) {
        phoneNumberEntities.forEach((p) -> {
            allNumbers.add(new PhoneNumberDTO(p));
        });

}

    public List<PhoneNumberDTO> getAll() {
        return allNumbers;
    }
}

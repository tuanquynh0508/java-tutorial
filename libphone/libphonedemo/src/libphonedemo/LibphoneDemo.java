/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libphonedemo;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 *
 * @author nntuan
 * https://github.com/googlei18n/libphonenumber
 * http://repo1.maven.org/maven2/com/googlecode/libphonenumber/libphonenumber/
 * https://github.com/googlei18n/libphonenumber/tree/master/java/libphonenumber/test/com/google/i18n/phonenumbers
 */
public class LibphoneDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String swissNumberStr = "+840903258221";
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "VN");
            boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
            if (isValid == true) {
                // Produces "+41 44 668 18 00"
                System.out.println("INTERNATIONAL: " + phoneUtil.format(swissNumberProto, PhoneNumberFormat.INTERNATIONAL));
                // Produces "044 668 18 00"
                System.out.println("NATIONAL: " + phoneUtil.format(swissNumberProto, PhoneNumberFormat.NATIONAL));
                // Produces "+41446681800"
                System.out.println("E164: " + phoneUtil.format(swissNumberProto, PhoneNumberFormat.E164));
            } else {
                System.out.println("Phone invalid");
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
    }

}

package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {



        /*
            POJO ------> Plain Old Java Object
                    ---> Pojo class is perfect template for creating Objects

                1)  Create all private variables
                2)  Create Constructors with all parameters and without parameters
                3)  Create getters and setters for all variables
                4)  Create toString() method
     */

    //  1)  Create all private variables
    private String checkin;
    private String checkout;

//   2)  Create Constructors with all parameters and without parameters


    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

//     3)  Create getters and setters for all variables

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


//    4)  Create toString() method

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}

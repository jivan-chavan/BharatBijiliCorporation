package com.bbc.ubp.utility;
import java.util.Random;

import com.bbc.ubp.constants.ConstantValues;

public class OTPGenerator {
		

    public static String generateOTP() {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < ConstantValues.OTP_LENGTH; i++) {
            otp.append(random.nextInt(10)); 
        }

        return otp.toString();
    }
}



